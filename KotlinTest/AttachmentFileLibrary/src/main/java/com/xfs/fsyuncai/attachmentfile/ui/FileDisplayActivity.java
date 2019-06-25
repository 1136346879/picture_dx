package com.xfs.fsyuncai.attachmentfile.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xfs.fsyuncai.art.base.network.loadimg.LoadImage;
import com.xfs.fsyuncai.art.base.network.retrofit.http.HttpManager;
import com.xfs.fsyuncai.art.base.utils.MD5Util;
import com.xfs.fsyuncai.art.base.utils.TLog;
import com.xfs.fsyuncai.art.base.view.activity.BaseActivity;
import com.xfs.fsyuncai.attachmentfile.R;
import com.xfs.fsyuncai.attachmentfile.widget.SuperFileView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FileDisplayActivity extends BaseActivity {


    SuperFileView mSuperFileView;
    private HttpManager httpManager = HttpManager.instance();
    String filePath;

    @Override
    public int resLayout() {
        return R.layout.activity_file_display;
    }

    @Override
    public void init() {
        mSuperFileView = findViewById(R.id.mSuperFileView);
        findViewById(R.id.tv_right).setVisibility(View.GONE);
        TextView title = findViewById(R.id.tvTitle);
        ImageView imageViewPreview = findViewById(R.id.image_preview);

        mSuperFileView.setOnGetFilePathListener(this::getFilePathAndShowFile);
        Intent intent = this.getIntent();
        String path = (String) intent.getSerializableExtra("path");
        if (path == null) {
            return;
        }
        if (path.toUpperCase().endsWith("BMP")
                || path.toUpperCase().endsWith("JPG")
                || path.toUpperCase().endsWith("JPEG")
                || path.toUpperCase().endsWith("PNG")
        ) {
            imageViewPreview.setVisibility(View.VISIBLE);
            mSuperFileView.setVisibility(View.GONE);
            LoadImage.instance().loadImage(imageViewPreview, path);
            title.setText("图片预览");
        } else {
            if (!TextUtils.isEmpty(path)) {
                TLog.INSTANCE.i("文件path:" + path);
                setFilePath(path);
            }
            title.setText("文档预览");
            mSuperFileView.setVisibility(View.VISIBLE);
            imageViewPreview.setVisibility(View.GONE);
            mSuperFileView.show();
        }
    }

    @Override
    public void logic() {
        findViewById(R.id.ivBack).setOnClickListener(v -> finish());
    }

    private void getFilePathAndShowFile(SuperFileView mSuperFileView2) {


        if (getFilePath().contains("http")) {//网络地址要先下载

            downLoadFromNet(getFilePath(), mSuperFileView2);

        } else {
            mSuperFileView2.displayFile(new File(getFilePath()));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        TLog.INSTANCE.i("FileDisplayActivity-->onDestroy");
        if (mSuperFileView != null) {
            mSuperFileView.onStopDisplay();
        }
    }


    public static void show(Context context, String url) {
        Intent intent = new Intent(context, FileDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("path", url);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    public void setFilePath(String fileUrl) {
        this.filePath = fileUrl;
    }

    private String getFilePath() {
        return filePath;
    }

    private void downLoadFromNet(final String url, final SuperFileView mSuperFileView2) {

        //1.网络下载、存储路径、
        File cacheFile = getCacheFile(url);
        if (cacheFile.exists()) {
            if (cacheFile.length() <= 0) {
                TLog.INSTANCE.i("删除空文件！！");
                cacheFile.delete();
                return;
            } else {
                mSuperFileView2.displayFile(cacheFile);
                return;
            }
        }
        loadfileOpen(url, mSuperFileView2);
    }

    private void loadfileOpen(String url, SuperFileView mSuperFileView2) {
        httpManager.loadPdfFile(url, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                TLog.INSTANCE.i("下载文件-->onResponse");
                boolean flag;
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    ResponseBody responseBody = response.body();
                    is = responseBody.byteStream();
                    long total = responseBody.contentLength();

                    File file1 = getCacheDir(url);
                    if (!file1.exists()) {
                        file1.mkdirs();
                        TLog.INSTANCE.i("创建缓存目录： " + file1.toString());
                    }
                    //fileN : /storage/emulated/0/pdf/kauibao20170821040512.pdf
                    File fileN = getCacheFile(url);//new File(getCacheDir(url), getFileName(url))

                    TLog.INSTANCE.i("创建缓存文件： " + fileN.toString());
                    if (!fileN.exists()) {
                        boolean mkdir = fileN.createNewFile();
                    }
                    fos = new FileOutputStream(fileN);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        TLog.INSTANCE.i("写入缓存文件" + fileN.getName() + "进度: " + progress);
                    }
                    fos.flush();
                    TLog.INSTANCE.i("文件下载成功,准备展示文件。");
                    //2.ACache记录文件的有效期
                    mSuperFileView2.displayFile(fileN);
                } catch (Exception e) {
                    TLog.INSTANCE.i("文件下载异常 = " + e.toString());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                        TLog.INSTANCE.e(e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                TLog.INSTANCE.i("文件下载失败");
                File file = getCacheFile(url);
                if (file.exists()) {
                    TLog.INSTANCE.i("删除下载失败文件");
                    file.delete();
                }
            }
        });
    }

    /***
     * 获取缓存目录
     *
     * @param url
     * @return
     */
    private File getCacheDir(String url) {

        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/007/");

    }

    /***
     * 绝对路径获取缓存文件
     *
     * @param url
     * @return
     */
    private File getCacheFile(String url) {
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/007/"
                + getFileName(url));
        TLog.INSTANCE.i("缓存文件 = " + cacheFile.toString());
        return cacheFile;
    }

    /***
     * 根据链接获取文件名（带类型的），具有唯一性
     *
     * @param url
     * @return
     */
    private String getFileName(String url) {
        String fileName = MD5Util.hashKey(url) + "." + getFileType(url);
        return fileName;
    }

    /***
     * 获取文件类型
     *
     * @param paramString
     * @return
     */
    private String getFileType(String paramString) {
        String str = "";

        if (TextUtils.isEmpty(paramString)) {
            TLog.INSTANCE.i("paramString---->null");
            return str;
        }
        TLog.INSTANCE.i("paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            TLog.INSTANCE.i("i <= -1");
            return str;
        }
        str = paramString.substring(i + 1);
        TLog.INSTANCE.i("paramString.substring(i + 1)------>" + str);
        return str;
    }


}
