package com.example.baselibrary;


import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;

import com.example.baselibrary.widgets.Density;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public  class MyApplication extends MultiDexApplication {
 public  static   Context cxt;
 public  static Resources resources;
    private RefWatcher refWatcher;
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
    }

    public static RefWatcher getRefWatcher (Context context){
        MyApplication application =(MyApplication) context . getApplicationContext ();
        return application.refWatcher;
    }
}
