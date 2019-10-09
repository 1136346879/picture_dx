package com.plumcookingwine.network.callback

import com.plumcookingwine.network.exception.ApiErrorModel
import com.trello.rxlifecycle2.LifecycleProvider

interface ICommonInterface {

    fun lifecycle(): LifecycleProvider<*>

    fun onSubscribe(loadingMsg: String)

    fun onComplete()

    /**
     * 是否使用默认的error事件
     */
    fun isDefaultError(): Boolean

    /**
     * 自定义统一的error事件
     */
    fun error(msg: ApiErrorModel)
}