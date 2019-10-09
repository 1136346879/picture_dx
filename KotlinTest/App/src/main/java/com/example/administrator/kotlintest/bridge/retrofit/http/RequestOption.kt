package com.example.administrator.kotlintest.bridge.retrofit.http

import android.text.TextUtils
import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants

/**
 * Created by kangf on 2018/6/16.
 */
class RequestOption {

    var isCache = false

    var isShowProgress = true

    var isCanCancel = true

    var method = ""

    var connectionTime = 6L

    var cookieNetWorkTime = 60

    var cookieNoNetWorkTime = 60 * 60 * 24 * 30

    var retryCount = 1

    var retryDelay = 100

    var retryIncreaseDelay = 10

    var cacheUrl = ""


    fun getUrl() = if (TextUtils.isEmpty(cacheUrl)) {
        "${ApiConstants.BASE_URL}$method"
    } else {
        cacheUrl
    }
}