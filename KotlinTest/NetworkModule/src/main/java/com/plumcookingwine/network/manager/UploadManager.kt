package com.plumcookingwine.network.manager

import android.annotation.SuppressLint
import android.util.Log
import com.plumcookingwine.network.callback.BaseObserver
import com.plumcookingwine.network.callback.INetworkCallback
import com.plumcookingwine.network.config.AbsRequestOptions
import com.plumcookingwine.network.ext.bind
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.io.File
import java.lang.ref.SoftReference

/**
 * @author kangf
 * @data 2019/8/21
 * @description class UploadManager 文件上传管理类
 */

class UploadManager private constructor() {

    companion object {
        val instance by lazy { UploadManager() }
    }

    @SuppressLint("CheckResult")
    fun <T> upload(
        options: List<AbsRequestOptions<T>>,
        files: List<File>,
        callbacks: List<INetworkCallback<T>>
    ) {
        if (options.size != files.size ||
            options.size != callbacks.size ||
            callbacks.size != files.size
        ) {
            throw Throwable("因为是多线程并行，所以回调数量和网络请求配置项的数量和文件数量要一一对应")
        }

        val subscribes = arrayOfNulls<Subscriber<Observable<T>>>(files.size)
        val observables = mutableListOf<Observable<T>>()
        files.mapIndexed { i, _ ->
            //options[i].isShowProgress = false
            val commonInterface = callbacks[i].getCommonInter() ?: throw Throwable("error")
            observables.add(options[i].createService(RetrofitManager.instance.getRetrofit(options[i]))
                .bind(commonInterface.lifecycle()))
            subscribes[i] = object : Subscriber<Observable<T>> {
                override fun onComplete() {
                }

                override fun onSubscribe(s: Subscription?) {
                    Log.i("TAG", "start")
                    val t =
                        options[i].createService(RetrofitManager.instance.getRetrofit(options[i]))
                            .bind(commonInterface.lifecycle())
                    onNext(t)
                }

                override fun onNext(t: Observable<T>) {
                    Log.i("TAG", "next")
                    t.subscribe(BaseObserver(SoftReference(callbacks[i]), options[i]))
                }

                override fun onError(t: Throwable?) {
                    Log.i("TAG", "error$t")
                }

            }
        }

        @Suppress("UnstableApiUsage")
        Flowable.fromIterable(observables)
            .parallel(observables.size)
            .runOn(Schedulers.io())
            .subscribe(subscribes)


    }


}