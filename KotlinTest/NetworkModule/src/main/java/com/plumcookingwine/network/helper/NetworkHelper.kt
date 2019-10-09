package com.plumcookingwine.network.helper

import android.annotation.SuppressLint
import android.content.Context
import com.plumcookingwine.network.cookie.AbsCookieResult
import com.plumcookingwine.network.utils.NetworkUtils
import okhttp3.Interceptor

/**
 * 网络请求辅助工具类, 需在application中调用init方法
 */
 class NetworkHelper {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        private var mBaseUrl: String? = null

        private var mCookieResultImpl: AbsCookieResult? = null

        private var mIsDebug: Boolean = false

        private val mInterceptors = mutableListOf<Interceptor>()

        /**
         * 初始化网络配置
         */
            fun init(
            context: Context,
            baseUrl: String,
            isDebug: Boolean = false
        ) {
            mContext = context
            mBaseUrl = baseUrl
            mIsDebug = isDebug
        }

        /**
         * 初始化网络配置
         */
            fun init(
            context: Context,
            baseUrl: String,
            isDebug: Boolean = false,
            cookieResult: AbsCookieResult? = null
        ) {
            mContext = context
            mBaseUrl = baseUrl
            mIsDebug = isDebug
            mCookieResultImpl = cookieResult
        }

        /**
         * 初始化网络配置
         */
          fun init(
            context: Context,
            baseUrl: String,
            isDebug: Boolean = false,
            interceptors: List<Interceptor>? = null
        ) {
            mContext = context
            mBaseUrl = baseUrl
            mIsDebug = isDebug
            if (interceptors != null)
                mInterceptors.addAll(interceptors)
        }

        /**
         * 初始化网络配置
         */
            fun init(
            context: Context,
            baseUrl: String,
            isDebug: Boolean = false,
            interceptors: List<Interceptor>? = null,
            cookieResult: AbsCookieResult? = null
        ) {
            mContext = context
            mBaseUrl = baseUrl
            mIsDebug = isDebug
            if (interceptors != null)
                mInterceptors.addAll(interceptors)
            mCookieResultImpl = cookieResult
        }
        val instance by lazy { NetworkHelper() }
    }

    /**
     * 获取上下文对象
     */
    fun getContext(): Context {
        if (mContext == null) {
            throw  Throwable("please init NetworkHelper in Application")
        }
        return mContext!!
    }

    /**
     * 获取基础路径
     */
    fun getBaseUrl(): String {
        if (mBaseUrl.isNullOrEmpty()) {
            throw  Throwable("please init NetworkHelper in Application")
        }
        return mBaseUrl!!
    }

    /**
     * 是否打开调试模式
     */
    fun getIsDebug(): Boolean {
        return mIsDebug
    }

    /**
     * 自定义缓存方式
     */
    fun getCookResultImpl(): AbsCookieResult? {
        return mCookieResultImpl
    }


    /**
     * 自定义拦截器
     */
    fun getInterceptors(): List<Interceptor> {
        return mInterceptors
    }

    /**
     * 是否有网络
     */
    fun hasNetWork(): Boolean {
        return NetworkUtils.isNetworkConnected(mContext)
    }
}