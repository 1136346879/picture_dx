package com.example.administrator.kotlintest.update;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.example.administrator.kotlintest.util.SPUtils;
import com.example.baselibrary.widgets.TLog;
import com.xfs.fsyuncai.main.appupdate.Constant;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 用DownloadManager来实现版本更新
 *
 * @author Hao
 */
public class DownloadService extends Service {

    public static final int HANDLE_DOWNLOAD = 0x001;
    public static final String BUNDLE_KEY_DOWNLOAD_URL = "download_url";
    public static final float UNBIND_SERVICE = 2.0F;

    private DownloadBinder binder;
    private DownloadManager downloadManager;
    private DownloadChangeObserver downloadObserver;
    private BroadcastReceiver downLoadBroadcast;
    private ScheduledExecutorService scheduledExecutorService;

    //下载任务ID
    private long downloadId;
    private OnProgressListener onProgressListener;

    @SuppressLint("HandlerLeak")
    public Handler downLoadHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (onProgressListener != null && HANDLE_DOWNLOAD == msg.what) {
                //被除数可以为0，除数必须大于0
                if (msg.arg1 >= 0 && msg.arg2 > 0) {
                    onProgressListener.onProgress(msg.arg1 / (float) msg.arg2);
                }
            }
        }
    };

    private Runnable progressRunnable = this::updateProgress;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new DownloadBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {

        String downloadUrl = intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_URL);
        TLog.INSTANCE.i("Apk下载路径传递成功：" + downloadUrl);
        downloadApk(downloadUrl);
        return binder;
    }

    /**
     * 下载最新APK
     */
    private void downloadApk(String url) {
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadObserver = new DownloadChangeObserver();

        registerContentObserver();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        request.setDescription("方盛云彩新版本下载");

        request.setMimeType("application/vnd.android.package-archive");
        /*设置用于下载时的网络状态*/
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        /*设置通知栏是否可见*/
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


        /*设置漫游状态下是否可以下载*/
        request.setAllowedOverRoaming(false);
        /*如果我们希望下载的文件可以被系统的Downloads应用扫描到并管理，
         我们需要调用Request对象的setVisibleInDownloadsUi方法，传递参数true.*/
        request.setVisibleInDownloadsUi(true);
        /*设置文件保存路径*/
        request.setDestinationInExternalFilesDir(getApplicationContext(), "fsyc", "fsyc.apk");
//        request.setDestinationInExternalPublicDir(getSDPath(), "fsyc.apk");
        /*将下载请求放入队列， return下载任务的ID*/
        downloadId = downloadManager.enqueue(request);

        registerBroadcast();
    }

//    public String getSDPath() {
//        File sdDir = null;
//        boolean sdCardExist = Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
//        if (sdCardExist) {
//            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
//        }
//        if (sdDir == null) return "";
//        else return sdDir.toString();
//    }

    /**
     * 注册广播
     */
    private void registerBroadcast() {
        /*注册service 广播 1.任务完成时 2.进行中的任务被点击*/
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        registerReceiver(downLoadBroadcast = new DownLoadBroadcast(), intentFilter);
    }

    /**
     * 注销广播
     */
    private void unregisterBroadcast() {
        if (downLoadBroadcast != null) {
            unregisterReceiver(downLoadBroadcast);
            downLoadBroadcast = null;
        }
    }

    /**
     * 注册ContentObserver
     */
    private void registerContentObserver() {
        /* observer download change **/
        if (downloadObserver != null) {
            getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), false, downloadObserver);
        }
    }

    /**
     * 注销ContentObserver
     */
    private void unregisterContentObserver() {
        if (downloadObserver != null) {
            getContentResolver().unregisterContentObserver(downloadObserver);
        }
    }

    /**
     * 关闭定时器，线程等操作
     */
    private void close() {
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdown();
        }

        if (downLoadHandler != null) {
            downLoadHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 发送Handler消息更新进度和状态
     */
    private void updateProgress() {
        int[] bytesAndStatus = getBytesAndStatus(downloadId);
        downLoadHandler.sendMessage(downLoadHandler.obtainMessage(HANDLE_DOWNLOAD, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]));
    }

    /**
     * 通过query已下载数据大小，总大小，下载状态
     *
     * @param downloadId
     * @return
     */
    private int[] getBytesAndStatus(long downloadId) {
        int[] bs = new int[]{-1, -1, 0};
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        try (Cursor cursor = downloadManager.query(query)) {
            if (cursor != null && cursor.moveToFirst()) {
                //已下载文件大小
                bs[0] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //文件的总大小
                bs[1] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //下载状态
                bs[2] = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        }
        return bs;
    }

    /*
     * 绑定此DownloadService的Activity实例
     *
     * @param activity
     */
//    public void setTargetActivity(Activity activity) {
//        Activity activity1 = activity;
//    }

    /**
     * 接受下载完成广播
     */
    private class DownLoadBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            String action = intent.getAction();
            if (action == null) return;
            switch (action) {
                case DownloadManager.ACTION_DOWNLOAD_COMPLETE:
                    if (downloadId == downId && downId != -1 && downloadManager != null) {

                        close();

                        Cursor c = downloadManager.query(new DownloadManager.Query().setFilterById(downId));
                        if (c != null) {
                            c.moveToFirst();

                            int fileUriIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                            String fileUri = c.getString(fileUriIdx);
                            String fileName = null;
                            //Android 7.0以上的方式：请求获取写入权限，这一步报错
                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                                if (fileUri != null) {
                                    fileName = Uri.parse(fileUri).getPath();
                                }
                            } else {
                                //过时的方式：DownloadManager.COLUMN_LOCAL_FILENAME
                                int fileNameIdx = c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
                                fileName = c.getString(fileNameIdx);
                            }

                            if (fileName == null) {
                                c.close();
                                return;
                            }
                            SPUtils.INSTANCE.setObject(Constant.SP_DOWNLOAD_PATH, fileName);
                            TLog.INSTANCE.i("广播监听下载完成，APK存储路径为 ：" + "file://" + fileName);

                            AppUpdateUtil.installApk(context, Uri.parse("file://" + fileName));
                            c.close();
                        }

                        if (onProgressListener != null) {
                            onProgressListener.onProgress(UNBIND_SERVICE);
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 监听下载进度
     */
    private class DownloadChangeObserver extends ContentObserver {

        DownloadChangeObserver() {
            super(downLoadHandler);
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }

        /**
         * 当所监听的Uri发生改变时，就会回调此方法
         *
         * @param selfChange 此值意义不大, 一般情况下该回调值false
         */
        @Override
        public void onChange(boolean selfChange) {
            scheduledExecutorService.scheduleAtFixedRate(progressRunnable, 0, 1, TimeUnit.SECONDS);
        }
    }

    public class DownloadBinder extends Binder {
        /**
         * 返回当前服务的实例
         *
         * @return
         */
        public DownloadService getService() {
            return DownloadService.this;
        }

    }

    public interface OnProgressListener {
        /**
         * 下载进度
         *
         * @param fraction 已下载/总大小
         */
        void onProgress(float fraction);
    }

    /**
     * 对外开放的方法
     *
     * @param onProgressListener
     */
    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
        unregisterContentObserver();
        TLog.INSTANCE.i("下载任务服务销毁");
    }
}
