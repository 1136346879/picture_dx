package com.plumcookingwine.network.config

import com.plumcookingwine.network.cookie.AbsCookieResult
import com.plumcookingwine.network.helper.NetworkHelper
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.Interceptor
import retrofit2.Retrofit

abstract class AbsRequestOptions<T> {

    /**
     * 以下为网络配置，可根据需要自行修改
     */

    // 是否显示加载框，默认显示
    var isShowProgress: Boolean = true
    // 设置加载框显示文字
    var loadingText: String? = null

    var baseUrl: String = NetworkHelper.instance.getBaseUrl()
    // 设置超时时间
    var timeOut: Long = 6
    // 是否缓存
    var isCache: Boolean = false
    // 缓存的URL （设置缓存时必填）
    var cacheUrl: String? = null
    // 有网缓存30s
    var cacheTimeForNetwork: Long = 1000 * 30
    // 无网缓存一天
    var cacheTimeForNoNetwork: Long = 1000 * 60 * 60 * 24
    // 超时重连次数
    var retryCount: Int = 3
    // 超时重连延时（ms）
    var retryDelay: Long = 100
    // 超时重连递增延时（ms）
    var retryIncreaseDelay: Long = 10

    abstract fun createService(retrofit: Retrofit): Observable<T>

    open fun createFlowable(retrofit: Retrofit): Flowable<T>? {
        return null
    }

    open fun getCookieResult(): AbsCookieResult? {
        return NetworkHelper.instance.getCookResultImpl()
    }

    open fun getInterceptor(): List<Interceptor> {
        return NetworkHelper.instance.getInterceptors()
    }

    open fun isDebug(): Boolean {
        return NetworkHelper.instance.getIsDebug()
    }

}