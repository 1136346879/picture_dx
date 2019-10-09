package com.plumcookingwine.network.callback

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.plumcookingwine.network.config.AbsRequestOptions
import com.plumcookingwine.network.cookie.CookieResultDefault
import com.plumcookingwine.network.cookie.CookieResultListener
import com.plumcookingwine.network.cookie.dao.CookieDao
import com.plumcookingwine.network.exception.ApiErrorModel
import com.plumcookingwine.network.exception.ApiErrorType
import com.plumcookingwine.network.exception.CustomException
import com.plumcookingwine.network.helper.NetworkHelper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.lang.ref.SoftReference
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class BaseObserver<T>(
    softCallback: SoftReference<INetworkCallback<T>>,
    private val requestOptions: AbsRequestOptions<T>
) : Observer<T> {

    /**
     * 网络请求回调
     */
    private val mCallback: INetworkCallback<T>? = softCallback.get()

    private val isCache = requestOptions.isCache

    private val cacheUrl = requestOptions.cacheUrl ?: ""

    private val cookieResult = requestOptions.getCookieResult()
        ?: CookieResultDefault(cacheUrl)

    /**
     * 完成一次任务后
     */
    override fun onComplete() {
        mCallback?.getCommonInter()?.onComplete()
    }

    /**
     * 任务开始
     */
    @SuppressLint("CheckResult")
    override fun onSubscribe(d: Disposable) {
        if (isCache) {
            // 如果缓存路径为空，那么就不缓存数据
            if (cacheUrl.isEmpty()) {
                if (requestOptions.isShowProgress) {
                    mCallback?.getCommonInter()?.onSubscribe(requestOptions.loadingText ?: "")
                }

                return
            }
            val cookieDao = cookieResult.getData()
            // 获取缓存内容，如果没有缓存的话，不做任何操作，继续往下走
            if (cookieDao == null) {
                if (requestOptions.isShowProgress) {
                    mCallback?.getCommonInter()?.onSubscribe(requestOptions.loadingText ?: "")
                }
            } else {
                val currentTime = System.currentTimeMillis()
                val cookieDate = cookieDao.date ?: 0L

                val cacheTime = if (NetworkHelper.instance.hasNetWork()) requestOptions.cacheTimeForNetwork
                else requestOptions.cacheTimeForNoNetwork

                if ((currentTime - cookieDate) > cacheTime) {
                    //超过了缓存时间，清除缓存,并继续请求
                    if (requestOptions.isShowProgress) {
                        mCallback?.getCommonInter()?.onSubscribe(requestOptions.loadingText ?: "")
                    }
                    cookieResult.deleteData(cookieDao)
                } else {
                    // 否则进入缓存回调, 并更新缓存时间
                    // (此处并不进行解析json，可以以json字符串作为T的类型)
                    val result = cookieDao.result

                    if (!result.isNullOrEmpty()) {
                        val isCache = mCallback?.onCache(result)
                        if (isCache == true) {
                            if (requestOptions.isDebug()) {
                                // 打印缓存日志
                                Log.i("RetrofitManager", "<-- cache $cacheUrl")
                                Log.i("RetrofitManager", "$result")
                            }
                            cookieDao.date = currentTime
                            cookieResult.upData(cookieDao)
                            d.dispose()
                            onComplete()
                        }

                    }
                }
            }
            return
        }
        // 如果没缓存不做任何操作
        if (requestOptions.isShowProgress) {
            mCallback?.getCommonInter()?.onSubscribe(requestOptions.loadingText ?: "")
        }
    }

    /**
     * 任务发送成功
     */
    override fun onNext(t: T) {
        if (mCallback == null) {
            throw Throwable("callback is con't null")
        }

        mCallback.onSuccess(t, object : CookieResultListener {
            override fun saveCookie() {
                // 如果缓存，保存数据
                if (isCache) {
                    val json: String = if (t is String) t else Gson().toJson(t)
                    cookieResult.saveData(CookieDao(0, json, System.currentTimeMillis()))
                }
            }

        })

    }

    /**
     * 处理异常情况
     */
    override fun onError(e: Throwable) {
        val apiErrorModel: ApiErrorModel?
        when (e) {
            is HttpException -> // retrofit请求异常
                apiErrorModel = when (e.code()) {
                    ApiErrorType.INTERNAL_SERVER_ERROR.code -> {
                        ApiErrorType.INTERNAL_SERVER_ERROR.getApiErrorModel()
                    }
                    ApiErrorType.BAD_GATEWAY.code -> {
                        ApiErrorType.BAD_GATEWAY.getApiErrorModel()
                    }
                    ApiErrorType.NOT_FOUND.code -> {
                        ApiErrorType.NOT_FOUND.getApiErrorModel()
                    }
                    ApiErrorType.PARSING_FAILURE.code -> {
                        ApiErrorType.PARSING_FAILURE.getApiErrorModel()
                    }
                    else -> {
                        otherError(e)
                    }
                }
            is CustomException -> // 自定义异常
                apiErrorModel = ApiErrorModel(e.code, e.message ?: "未知错误")
            else -> {    // 其他错误
                val apiErrorType: ApiErrorType = when (e) {
                    //发送网络问题或其它未知问题，请根据实际情况进行修改
                    is UnknownHostException,
                    is ConnectException,
                    is SocketTimeoutException ->
                        ApiErrorType.NETWORK_NOT_CONNECT
                    is TimeoutException ->
                        ApiErrorType.CONNECTION_TIMEOUT
                    else -> ApiErrorType.UNEXPECTED_ERROR
                }
                apiErrorModel = apiErrorType.getApiErrorModel()
            }
        }
        if (mCallback == null) {
            throw Throwable("callback is con't null")
        }
        mCallback.onError(apiErrorModel)
        // 网络出现错误后通知view
        mCallback.getCommonInter()?.onComplete()
    }

    private fun otherError(e: HttpException): ApiErrorModel? {
        var model: ApiErrorModel? = null
        try {
            model = Gson().fromJson(e.response().errorBody()?.charStream(), ApiErrorModel::class.java)

        } catch (e: Exception) {
            Log.i("Http", e.toString())
        }
        return model
    }

}