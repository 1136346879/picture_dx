package com.example.administrator.kotlintest.common;

/**
 * Created by hexun on 2017/10/18.
 * @author ZHANG PEIYUAN
 */

public abstract class RxTask<T> {

    /**
     * 子线程执行任务的方法
     * @return
     */
    public abstract T run();


    public void onNext(T t) {}

    public void onError(Throwable e) {}

    public void onComplete() {}

}
