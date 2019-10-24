package com.example.baselibrary;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
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
