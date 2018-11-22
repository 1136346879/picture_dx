package com.xfs.fsyuncai.bridge.retrofit.exception

import android.content.Context
import com.example.administrator.kotlintest.R

/**
 * Created by kangf on 2018/6/15.
 */
enum class ApiErrorType(val code: Int, private val messageId: Int) {

    //根据实际情况进行增删
    INTERNAL_SERVER_ERROR(500, R.string.INTERNAL_SERVER_ERROR),
    BAD_GATEWAY(502, R.string.BAD_GATEWAY),
    NOT_FOUND(404, R.string.NOT_FOUND),
    CONNECTION_TIMEOUT(408, R.string.CONNECTION_TIMEOUT),
    NETWORK_NOT_CONNECT(499, R.string.NETWORK_NOT_CONNECT),
    UNEXPECTED_ERROR(700, R.string.UNEXPECTED_ERROR),
    PARSING_FAILURE(1001, R.string.PARSING_FAILURE);

    private val DEFAULT_CODE = 1

    fun getApiErrorModel(context: Context): ApiErrorModel {
        return ApiErrorModel(DEFAULT_CODE, context.getString(messageId))
    }
}