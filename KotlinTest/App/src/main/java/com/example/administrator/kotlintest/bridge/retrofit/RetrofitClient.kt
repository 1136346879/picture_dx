package com.xfs.fsyuncai.bridge.retrofit

import com.example.administrator.kotlintest.widget.TLog
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.CookieInterceptor
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.PublicParamsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by kangf on 2018/6/12.
 */
class RetrofitClient private constructor() {

    private lateinit var mRetrofit: Retrofit

    companion object {
        fun instance() = Holder.INSTANCE
    }


    private object Holder {
        val INSTANCE = RetrofitClient()
    }

    fun <T> createService(serviceClass: Class<T>): T? {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
//        httpClientBuilder.addInterceptor(PublicHeaderInterceptor())
        httpClientBuilder.addInterceptor(PublicParamsInterceptor())
        httpClientBuilder.addInterceptor(CookieInterceptor(true, ""))
        if (ApiConstants.IS_DEBUG)
            httpClientBuilder.addInterceptor(getHttpLoggingInterceptor())

        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiConstants.BASE_URL)
        mRetrofit = retrofitBuilder.client(httpClientBuilder.build()).build()
        return mRetrofit.create(serviceClass)

    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor {
            TLog.i(it, 1000)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }


}