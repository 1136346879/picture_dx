package com.example.administrator.kotlintest.update;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;

import com.example.administrator.kotlintest.util.SPUtils;
import com.example.baselibrary.common.ToastUtil;
import com.example.baselibrary.widgets.TLog;
import com.xfs.fsyuncai.main.appupdate.Constant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 有关APP的信息包括版本号，版本名，签名，安装路径等
 *
 * @author Hao
 * @date 2017-6-7 14:57
 */
public class AppUpdateUtil {

    private AppUpdateUtil() {
    }

    /**
     * 启动service
     *
     * @param context
     * @param apkUrl
     * @param conn
     * @return
     */
    public static boolean bindsService(Context context, String apkUrl, ServiceConnection conn) {
        if (context == null) {
            return false;
        }
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
        return context.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 获取应用包名
     *
     * @param context 上下文信息
     * @return 包名
     */
    public static String getPackageName(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Should not be null");
        }
        return context.getPackageName();
    }

    /**
     * @param context 上下文信息
     * @return 获取包信息
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        /** getPackageName()是当前类的包名，0代表获取版本信息 */
        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 判断应用是否已经启动
     *
     * @param context 一个context
     * @return boolean
     */
    public static boolean isAppAlive(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for (int i = 0; i < processInfos.size(); i++) {
            if (processInfos.get(i).processName.equals(getPackageName(context))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取应用版本号
     *
     * @param context
     * @return 成功返回版本号，失败返回20
     */
    public static int getVersionCode(Context context) {
        if (getPackageInfo(context) != null) {
            int code = getPackageInfo(context).versionCode;
            return code;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * 获取应用版本名
     *
     * @param context
     * @return 成功返回版本名， 失败返回null
     */
    public static String getVersionName(Context context) {
        if (getPackageInfo(context) != null) {
            return getPackageInfo(context).versionName;
        }

        return null;
    }

    /**
     * 获取APP名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 打开指定包名的APP
     *
     * @param context
     * @param packageName
     */
    public void openOtherApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent launchIntentForPackage = manager.getLaunchIntentForPackage(packageName);
        if (launchIntentForPackage != null) {
            context.startActivity(launchIntentForPackage);
        }
    }

    /**
     * 判断当前ＡＰＰ处于前台还是后台；
     * 需添加<uses-permission android:name="android.permission.GET_TASKS"/>
     * 并且必须是系统应用，该方法才有效
     *
     * @param context
     * @return
     */
    public static boolean isApplicationBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (tasks != null) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 安装APK
     *
     * @param context context
     * @param apkPath 安装包的路径
     */
    public static void installApk(Context context, Uri apkPath) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean isPer = context.getPackageManager().canRequestPackageInstalls();
            if (!isPer) {
                ToastUtil.INSTANCE.showCustomToast("请授予应用内安装的权限");
                Uri packageUrl = Uri.parse("package:" + context.getPackageName());
                Intent intent1 = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageUrl);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                return;
            }
        }
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = null;
            try {
                contentUri = FileProvider.getUriForFile(context, "com.example.baselibrary.provider", new File(new URI(apkPath.toString())));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");

        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(apkPath, "application/vnd.android.package-archive");
        }
        intent.setAction(Intent.ACTION_VIEW);
        context.startActivity(intent);
    }

    /**
     * 卸载APP
     *
     * @param context
     * @param packageName 包名
     */
    public static void uninstallAPK(Context context, String packageName) {
        Uri packageURI = Uri.parse("package:" + packageName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        context.startActivity(uninstallIntent);
    }

    /**
     * 获取已安装应用的签名
     *
     * @param context
     * @return
     */
    public static String getInstalledApkSign(Context context) {
        PackageInfo packageInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);

            return packageInfo.signatures[0].toCharsString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前应用的 源Apk路径
     *
     * @param context
     * @return
     */
    public static String getOldApkSrcPath(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);

            return applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取未安装APK的签名
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String> getUninstallApkSignatures(File file) {
        List<String> signatures = new ArrayList<String>();
        try {
            JarFile jarFile = new JarFile(file);
            JarEntry je = jarFile.getJarEntry("AndroidManifest.xml");
            byte[] readBuffer = new byte[8192];
            Certificate[] certs = loadCertificates(jarFile, je, readBuffer);
            if (certs != null) {
                for (Certificate c : certs) {
                    String sig = toCharsString(c.getEncoded());
                    signatures.add(sig);
                }
            }
        } catch (Exception ex) {
        }
        return signatures;
    }

    /**
     * 加载签名
     *
     * @param jarFile
     * @param je
     * @param readBuffer
     * @return
     */
    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is = jarFile.getInputStream(je);
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {
            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 将签名转成转成可见字符串
     *
     * @param sigBytes
     * @return
     */
    private static String toCharsString(byte[] sigBytes) {
        byte[] sig = sigBytes;
        final int N = sig.length;
        final int N2 = N * 2;
        char[] text = new char[N2];
        for (int j = 0; j < N; j++) {
            byte v = sig[j];
            int d = (v >> 4) & 0xf;
            text[j * 2] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            d = v & 0xf;
            text[j * 2 + 1] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
        }
        return new String(text);
    }

    /**
     * 获取未安装APK的签名, 由于市面上的Android系统版本各一，不推荐使用这种方法获取应用签名
     *
     * @param apkPath
     * @return
     */
    public static String getUninstallAPKSignatures(String apkPath) {
        //参数列表类型
        Class[] typeArgs = new Class[1];
        //参数列表值
        Object[] valueArgs = new Object[1];
        try {
            //2.这是一个Package 解释器, 是隐藏的，获取PackageParser的类
            Class pkgParserCls = Class.forName("android.content.pm.PackageParser");

            //2.创建PackageParser的实例
            typeArgs[0] = String.class;
            Constructor pkgParserCt = pkgParserCls.getConstructor(typeArgs);
            valueArgs[0] = apkPath;
            Object pkgParser = pkgParserCt.newInstance(valueArgs);

            //3.获取PackageParser的类的  parsePackage方法；PackageParser.Package mPkgInfo = packageParser.parsePackage(new File(apkPath), apkPath, metrics, 0);   
            typeArgs = new Class[4];
            typeArgs[0] = File.class;
            typeArgs[1] = String.class;
            typeArgs[2] = DisplayMetrics.class;
            typeArgs[3] = int.class;
            Method pkgParser_parsePackageMtd = pkgParserCls.getDeclaredMethod("parsePackage", typeArgs);

            //4.执行parsePackage方法
            valueArgs = new Object[4];
            valueArgs[0] = new File(apkPath);
            valueArgs[1] = apkPath;
            DisplayMetrics metrics = new DisplayMetrics();
            metrics.setToDefaults();
            valueArgs[2] = metrics;
            valueArgs[3] = PackageManager.GET_SIGNATURES;
            Object pkgParserPkg = pkgParser_parsePackageMtd.invoke(pkgParser, valueArgs);

            //5.获取PackageParser类的collectCertificates方法
            typeArgs = new Class[2];
            typeArgs[0] = pkgParserPkg.getClass();
            typeArgs[1] = int.class;
            Method pkgParser_collectCertificatesMtd = pkgParserCls.getDeclaredMethod("collectCertificates", typeArgs);

            //6.执行collectCertificates方法
            valueArgs = new Object[2];
            valueArgs[0] = pkgParserPkg;
            valueArgs[1] = PackageManager.GET_SIGNATURES;
            pkgParser_collectCertificatesMtd.invoke(pkgParser, valueArgs);

            //7.获取PackageParser.Package的类的mSignatures属性
            Field packageInfoFld = pkgParserPkg.getClass().getDeclaredField("mSignatures");

            //8.获取PackageParser.Package的类的mSignatures属性的值
            Signature[] info = (Signature[]) packageInfoFld.get(pkgParserPkg);
            return info[0].toCharsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取sd卡的路径
     *
     * @return
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }

    /**
     * 删除上次更新存储在本地的apk
     */
    public static void removeOldApk(Context context) {
        //获取老ＡＰＫ的存储路径
        File fileName = new File((String) Objects.requireNonNull(SPUtils.INSTANCE.getObjectForKey(Constant.SP_DOWNLOAD_PATH, "")));
        TLog.INSTANCE.i("老APK的存储路径 =" + SPUtils.INSTANCE.getObjectForKey(Constant.SP_DOWNLOAD_PATH, ""));

        if (fileName.exists() && fileName.isFile()) {
            //noinspection ResultOfMethodCallIgnored
            fileName.delete();
            SPUtils.INSTANCE.setObject(Constant.SP_DOWNLOAD_PATH, "");
//            ToastUtil.INSTANCE.showToast("老安装包已经删除");
        }
    }
}
