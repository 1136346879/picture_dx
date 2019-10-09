package com.example.administrator.kotlintest.bridge.retrofit.http

import android.content.Context
import android.text.TextUtils
import com.example.administrator.kotlintest.bridge.retrofit.callback.ApiResponse
import com.example.baselibrary.widgets.TLog
import com.example.administrator.kotlintest.bridge.retrofit.ext.bind
import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.bridge.retrofit.callback.HttpOnNextListener
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.CookieInterceptor
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.PublicParamsInterceptor
import com.xfs.fsyuncai.bridge.retrofit.service.CommonService
import io.reactivex.Observable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

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

    fun doHttpDeal(context: Context?,
                   observable: Observable<String>,
                   listener: HttpOnNextListener) {
        if(context == null) {
            return
        }
        observable
                .bind()
                .map {
                    if (it.isEmpty())
                        error("数据格式错误！")
                    it
                }
                .apply {
                    val apiResponse = if (option == null) {
                        ApiResponse(context, msg, RequestOption(), listener)
                    } else {
                        ApiResponse(context, msg, option!!, listener)
                    }
                    option = null
                    this.subscribe(apiResponse)
                }
    }

    private fun getSSLFactory(): SSLSocketFactory {

//         Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        return sslContext.socketFactory
    }


    fun getOkHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(option?.connectionTime ?: 6L, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(option?.connectionTime ?: 6L, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(option?.connectionTime ?: 6L, TimeUnit.SECONDS)
        httpClientBuilder.addInterceptor(PublicParamsInterceptor())
        httpClientBuilder.addInterceptor(
                CookieInterceptor(if (option == null) false else option!!.isCache,
                        if (option == null) "" else option!!.getUrl()))
        httpClientBuilder.addInterceptor(getHttpLoggingInterceptor())

        //证书忽略  添加下面代码  start
        httpClientBuilder.sslSocketFactory(getSSLFactory());
        httpClientBuilder.hostnameVerifier(object : HostnameVerifier {
            override fun verify(hostname: String, session: SSLSession): Boolean {
                return true
            }
        })
        //end
        return httpClientBuilder.build()
    }

    fun <T> createService(serviceClass: Class<T>, baseUrl: String? = ApiConstants.BASE_URL): T {

        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl ?: ApiConstants.BASE_URL)

        return retrofitBuilder
                .client(getOkHttpClient())
                .build()
                .create(serviceClass)
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor {
            TLog.i(it)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //将参数值转成RequestBoby
    fun toRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }

    private fun <T> createUploadService(serviceClass: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.fsyuncai.com/#/index")
                .addConverterFactory(GsonConverterFactory.create())

        return retrofit
                .build()
                .create(serviceClass)
    }

    fun loadPdfFile(url: String, callback: Callback<ResponseBody>) {
        val mLoadFileApi = createUploadService(CommonService::class.java)
        if (!TextUtils.isEmpty(url)) {
            val call = mLoadFileApi.loadPdfFile(url)
            call.enqueue(callback)
        }
    }
}