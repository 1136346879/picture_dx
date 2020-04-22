package com.example.baselibrary;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.didichuxing.doraemonkit.DoraemonKit;
import com.example.baselibrary.widgets.Density;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.telegram.messenger.ApplicationLoader;

public  class MyApplication extends MultiDexApplication {
 public  static   Context cxt;
 public  static Resources resources;
  public static  boolean isDebug = Boolean.parseBoolean(null);
    private RefWatcher refWatcher;
    private ApplicationLoader applicationLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        cxt = getApplicationContext();
        resources = cxt.getResources();
        DoraemonKit.install(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
                        return;
                     }
        refWatcher = LeakCanary.install(this);
        Density.INSTANCE.setDensity(this);
        applicationLoader = new  ApplicationLoader();
        applicationLoader.onCreate(getApplicationContext());
        if (getIsDebug()) {
            // 崩溃日志的收集,便于测试发现崩溃后处理
            CrashHandler.Companion.getInstance().init(this);
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();  // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        ARouter.init(this); // 尽可能早，推荐在Application中初始化

//        try {
            //初始化X5内核
//            QbSdk.initX5Environment(applicationContext, object : QbSdk.PreInitCallback {
//                override fun onCoreInitFinished() {
//                    //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
//                    Log.e("x5--", "加载内核回调--onCoreInitFinished")
//                    val map = mutableMapOf<String, Any>()
//                    map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
//                    QbSdk.initTbsSettings(map)
//                }
//
//                override fun onViewInitFinished(b: Boolean) {
//                    //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                    Log.e("x5--", "加载内核是否成功:$b")
//                }
//            })


//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        applicationLoader.onConfigurationChanged(newConfig);

    }

    private boolean getIsDebug(){
        return  isDebug =  0 != (cxt.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE);

    }
    public static RefWatcher getRefWatcher (Context context){
        MyApplication application =(MyApplication) context . getApplicationContext ();
        return application.refWatcher;
    }


}
