package com.xfs.fsyuncai.bridge.retrofit.http.interceptor

import com.example.administrator.kotlintest.bridge.retrofit.callback.CookieResulte
import com.xfs.fsyuncai.bridge.database.CookieDbUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.nio.charset.Charset

/**
 * Created by kangf on 2018/6/16.
 *
 * 网络请求-数据持久化
 */
class CookieInterceptor(private val cache: Boolean,
                        private val url: String) : Interceptor {


    private var dbUtil: CookieDbUtil? = null

    init {
        dbUtil = CookieDbUtil.instance()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (cache) {
            val body = response.body()
            val source = body!!.source()
            source?.request(Long.MAX_VALUE)
            val buffer = source.buffer()
            var charset = Charset.defaultCharset()
            val contentType = body.contentType()
            charset = contentType?.charset(charset);
            val bodyString = buffer.clone().readString(charset)
            var result = dbUtil!!.queryCookieBy(url)
            val time = System.currentTimeMillis()
            if (result == null) {
                result = CookieResulte(url, bodyString, time)
                dbUtil!!.saveCookie(result)
            } else {
                result.resulte = bodyString
                result.time = time
                dbUtil!!.updateCookie(result)
            }
        }
        return response!!
    }
}