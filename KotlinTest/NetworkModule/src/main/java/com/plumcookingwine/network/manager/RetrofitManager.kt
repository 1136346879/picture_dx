package com.plumcookingwine.network.manager

import android.util.Log
import com.plumcookingwine.network.config.AbsRequestOptions
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager private constructor() {

    companion object {

        private val TAG = RetrofitManager::class.java.simpleName

        val instance by lazy { RetrofitManager() }
    }

    fun <T> getRetrofit(options: AbsRequestOptions<T>): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(options.baseUrl)
            .client(getOkHttpClient(options))
            .build()
    }

    private fun <T> getOkHttpClient(options: AbsRequestOptions<T>): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(options.timeOut, TimeUnit.SECONDS)
            readTimeout(options.timeOut, TimeUnit.SECONDS)
            writeTimeout(options.timeOut, TimeUnit.SECONDS)
            // 添加自定义拦截器
            options.getInterceptor().map {
                addInterceptor(it)
            }
            // debug模式打印日志
            if (options.isDebug()) {
                addInterceptor(getHttpLoggingInterceptor())
            }
        }.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor {
            Log.i(TAG, it)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}