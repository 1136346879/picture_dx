package com.example.administrator.kotlintest.banner.mybanner.imageLoader;

/**
 * Created by hexun on 2017/8/14.
 */

public interface Callback {

    void onSuccess();

    void onError();

    public static class EmptyCallback implements Callback {

        @Override
        public void onSuccess() {
        }

        @Override
        public void onError() {
        }
    }
}
