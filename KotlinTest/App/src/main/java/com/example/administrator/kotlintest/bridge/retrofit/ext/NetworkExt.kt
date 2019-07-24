package com.example.administrator.kotlintest.bridge.retrofit.ext

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.bind(): Observable<T> {

    return this
            /*http请求线程*/
            .subscribeOn(Schedulers.io())
            /*http解绑线程*/
            .unsubscribeOn(Schedulers.io())
            /*回调线程*/
            .observeOn(AndroidSchedulers.mainThread())

}

fun <T> Observable<T>.bind(lifecycle: LifecycleProvider<*>): Observable<T> {
    return this
            .bind()
            .bindToLifecycle(lifecycle)

}