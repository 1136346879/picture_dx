package com.plumcookingwine.network.exception

import com.plumcookingwine.network.R
import com.plumcookingwine.network.helper.NetworkHelper

enum class ApiErrorType(val code: Int, private val messageId: Int) {

    /* 网络异常 */
    CONNECTION_TIMEOUT(0x11111111, R.string.NETWORK_NOT_CONNECT),
    NETWORK_NOT_CONNECT(0x22222222, R.string.NETWORK_NOT_CONNECT),
    /* 服务器错误 */
    BAD_GATEWAY(502, R.string.BAD_GATEWAY),
    NOT_FOUND(404, R.string.NOT_FOUND),
    INTERNAL_SERVER_ERROR(500, R.string.INTERNAL_SERVER_ERROR),
    /* 解析异常 */
    PARSING_FAILURE(0x33333333, R.string.PARSING_FAILURE),
    /* 未知错误 */
    UNEXPECTED_ERROR(0x44444444, R.string.UNEXPECTED_ERROR);


    fun getApiErrorModel(): ApiErrorModel {
        return ApiErrorModel(code, NetworkHelper.instance.getContext().getString(messageId))
    }

}