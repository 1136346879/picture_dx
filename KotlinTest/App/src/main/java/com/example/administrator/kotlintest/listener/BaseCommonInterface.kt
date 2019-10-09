package com.example.administrator.kotlintest.listener

import com.plumcookingwine.network.callback.ICommonInterface
import com.plumcookingwine.network.exception.ApiErrorModel
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * @author kangf
 * @data 2019/9/30
 * @description class BaseCommonInterface
 */
open class BaseCommonInterface(private val lifecycleProvider: LifecycleProvider<*>)  :ICommonInterface {

    override fun lifecycle(): LifecycleProvider<*> {
        return lifecycleProvider
    }

    override fun onSubscribe(loadingMsg: String) {
    }

    override fun onComplete() {
    }

    override fun isDefaultError(): Boolean {
        return false
    }

    override fun error(msg: ApiErrorModel) {

    }

}