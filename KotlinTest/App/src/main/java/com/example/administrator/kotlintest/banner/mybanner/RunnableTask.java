package com.example.administrator.kotlintest.banner.mybanner;


import android.os.Handler;

/**
 * Created by hexun on 2017/12/1.
 * banner计时线程
 */

public class RunnableTask implements Runnable {
    private Handler handler;

    public RunnableTask(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(0);
    }
}
