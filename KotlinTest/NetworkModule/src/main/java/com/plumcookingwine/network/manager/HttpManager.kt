package com.plumcookingwine.network.manager

import com.plumcookingwine.network.callback.INetworkCallback
import com.plumcookingwine.network.ext.bind
import com.plumcookingwine.network.callback.BaseObserver
import com.plumcookingwine.network.config.AbsRequestOptions
import com.plumcookingwine.network.exception.ApiErrorModel
import com.plumcookingwine.network.func.RetryWhenFunc
import java.lang.ref.SoftReference

class HttpManager private constructor() {

    companion object {
        val instance by lazy { HttpManager() }
    }

    /**
     * 网络请求
     */
    fun <T> doHttpDeal(options: AbsRequestOptions<T>?, callback: INetworkCallback<T>) {

        if (options == null) {
            callback.onError(ApiErrorModel(0x00, "options is con not null"))
            return
        }
        val inter = callback.getCommonInter()
        if(inter == null) {
            callback.onError(ApiErrorModel(0x01, "Implement the ICommonInterface interface"))
            return
        }
        options.createService(RetrofitManager.instance.getRetrofit(options))
            .bind(inter.lifecycle())
            .retryWhen(
                RetryWhenFunc(
                    options.retryCount,
                    options.retryDelay,
                    options.retryIncreaseDelay
                )
            )
            .map {
                if (it == null) {
                    throw Throwable("server error: data is null!")
                }
                it
            }
            .subscribe(
                BaseObserver(SoftReference(callback), options)
            )
    }
}