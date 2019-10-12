package com.xfs.qrcode_module.util;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public final class Util {

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private Util() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param app 应用
     */
    public static void init(@NonNull final Application app) {
        Util.sApplication = app;
        app.registerActivityLifecycleCallbacks(ActivityWatcher.getInstance());
    }

    /**
     * 获取Application
     *
     * @return Application
     */
    public static Application getApp() {
        if (sApplication != null) {
            return sApplication;
        }
        throw new NullPointerException("u should init first");
    }
    /**
     * 获取Application
     *
     * @return Application
     */
    public static Application getContext() {
        if (sApplication != null) {
            return sApplication;
        }
        throw new NullPointerException("u should init first");
    }
}
