package com.xfs.qrcode_module.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by hexun on 2018/4/12.
 */

public class ActivityWatcher implements Application.ActivityLifecycleCallbacks {

    private static ActivityWatcher INSTANCE;
    private WeakReference<Activity> topActivity;
    private boolean isActive = false;

    private ActivityWatcher() {
    }

    public static ActivityWatcher getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ActivityWatcher();
        }
        return INSTANCE;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if (topActivity == null || activity != topActivity.get()) {
            topActivity = new WeakReference<>(activity);
        }
        if (!isActive) {
            isActive = true;
//            EventBus.getDefault().post(new CaiDaoBaseEvent.AppForegroundChangedEvent(true));
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {

            if (isActive) {
                isActive = false;
//                EventBus.getDefault().post(new CaiDaoBaseEvent.AppForegroundChangedEvent(false));

        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public Activity getTopActivity() {
        if (topActivity != null) {
            return topActivity.get();
        }
        return null;
    }


    public boolean onForeground() {
        return isActive;
    }

}

