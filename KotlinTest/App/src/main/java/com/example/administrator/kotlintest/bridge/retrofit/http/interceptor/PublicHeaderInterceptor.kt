package com.xfs.fsyuncai.bridge.retrofit.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response


/**
 * Created by kangf on 2018/6/25.
 */
class PublicHeaderInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return response.newBuilder()
                .header("application/json", "Content-Type")
                .build()
    }

}