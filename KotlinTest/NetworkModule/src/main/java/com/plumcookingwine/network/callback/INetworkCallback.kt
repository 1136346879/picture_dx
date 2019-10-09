package com.plumcookingwine.network.callback

import android.widget.Toast
import com.plumcookingwine.network.R
import com.plumcookingwine.network.cookie.CookieResultListener
import com.plumcookingwine.network.exception.ApiErrorModel
import com.plumcookingwine.network.helper.NetworkHelper

abstract class INetworkCallback<T>(private val commonInterface: ICommonInterface) {

    /**
     * 通用的view层的接口，用于与view交互
     */
    fun getCommonInter(): ICommonInterface? {
        return commonInterface
    }

    /**
     * 请求成功回调
     */
    abstract fun onSuccess(obj: T, cookieListener: CookieResultListener)

    /**
     * 缓存回调
     *
     * @return boolean 是否需要走到缓存逻辑
     * 你可能同一个请求中，在某些条件下需要缓存，某些条件下不需要缓存；
     * 你可以在option中设置缓存，并在这里进行控制
     */
    open fun onCache(json: String): Boolean {

        return false
    }


    /**
     * 可重写此方法，自定义错误事件
     */
    open fun onError(err: ApiErrorModel?) {
        getCommonInter()?.onComplete()

        if (getCommonInter()?.isDefaultError() == true) {
            Toast
                .makeText(
                    NetworkHelper.instance.getContext(),
                    err?.message ?: NetworkHelper.instance.getContext().getString(R.string.UNEXPECTED_ERROR),
                    Toast.LENGTH_SHORT
                )
                .show()
        } else {
            if (err == null) return
            getCommonInter()?.error(err)
        }

    }


}