package com.xfs.fsyuncai.bridge.retrofit.http

import android.content.Context
import com.example.baselibrary.widgets.TLog
import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.bridge.retrofit.callback.ApiResponse
import com.xfs.fsyuncai.bridge.retrofit.callback.HttpOnNextListener
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.CookieInterceptor
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.PublicParamsInterceptor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by kangf on 2018/6/15.
 */
class HttpManager private constructor() {


    companion object {
        @JvmStatic
        fun instance() = Holder.INSTANCE
    }


    private object Holder {
        val INSTANCE = HttpManager()
    }

    private var msg: String = ""

    private var option: RequestOption? = null

    fun showProgress(msg: String): HttpManager {
        this.msg = msg
        return this
    }

    fun setOption(option: RequestOption?): HttpManager {
        this.option = option
        return this
    }

    fun doHttpDeal(context: Context,
                   observable: Observable<String>,
                   listener: HttpOnNextListener,showLoading:Boolean) {
        observable
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    if (it.isEmpty())
                        error("数据格式错误！")
                    it
                }
                .apply {
                    val apiResponse = if (option == null) {
                        ApiResponse(context, msg, RequestOption(), listener,showLoading)
                    } else {
                        ApiResponse(context, msg, option!!, listener,showLoading)
                    }
                    option = null
                    this.subscribe(apiResponse)
                }
    }

    fun <T> createService(serviceClass: Class<T>,baseUrl: String? = ApiConstants.BASE_URL): T {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(60, TimeUnit.SECONDS)
//        httpClientBuilder.addInterceptor(PublicHeaderInterceptor())
        httpClientBuilder.addInterceptor(PublicParamsInterceptor())
        httpClientBuilder.addInterceptor(
                CookieInterceptor(if (option == null) false else option!!.isCache,
                        if (option == null) "" else option!!.getUrl()))
        if (ApiConstants.IS_DEBUG)
            httpClientBuilder.addInterceptor(getHttpLoggingInterceptor())
        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl ?:ApiConstants.BASE_URL)

        return retrofitBuilder.client(httpClientBuilder.build()).build().create(serviceClass)

    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor {
            TLog.i(it, 1000)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}