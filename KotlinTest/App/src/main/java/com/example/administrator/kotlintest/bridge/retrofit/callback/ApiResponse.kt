package com.xfs.fsyuncai.bridge.retrofit.callback

import android.content.Context
import com.example.baselibrary.widgets.TLog
import com.google.gson.Gson
import com.xfs.fsyuncai.bridge.database.CookieDbUtil
import com.xfs.fsyuncai.bridge.retrofit.LoadingDialog
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorModel
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorType
import com.xfs.fsyuncai.bridge.retrofit.http.RequestOption
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by kangf on 2018/6/15.
 */
class ApiResponse(private val context: Context,
                  private val msg: String,
                  private val option: RequestOption,
                  private val listener: HttpOnNextListener?,
                  private val showLoading: Boolean) : Observer<String> {

    override fun onComplete() {
        LoadingDialog.dissmiss()
    }

    override fun onSubscribe(d: Disposable) {
        if(showLoading){
            if (option.isShowProgress) LoadingDialog.show(context, msg,true)
        } else
            if (option.isShowProgress) LoadingDialog.show(context, msg,false)
        if (option.isCache) {
            val cookieResult = CookieDbUtil.instance().queryCookieBy(option.getUrl())
            if (cookieResult != null) {
                TLog.i("读取数据库${option.getUrl()}")
                val time = (System.currentTimeMillis() - cookieResult.time) / 1000
                if (time < option.cookieNetWorkTime) {
                    listener?.onCache(cookieResult.resulte)
                }

                onComplete()
            }

        }
    }

    override fun onNext(str: String) {
        listener?.onNext(str)
    }

    override fun onError(error: Throwable) {
        LoadingDialog.dissmiss()
        if (!option.isCache) {
            errorDo(error)
            return
        }
        Observable.just(option.getUrl())
                .subscribe(object : Observer<String> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: String) {
                            try{
                                val cookieResult = CookieDbUtil.instance().queryCookieBy(t)
                                if (cookieResult == null) {
                                    errorDo(error)
                                    return
                                }
                                val time = (System.currentTimeMillis() - cookieResult.time) / 1000
                                if (time < option.cookieNoNetWorkTime) {
                                    listener?.onCache(cookieResult.resulte)
                                } else {
                                    CookieDbUtil.instance().deleteCookie(cookieResult)
                                    errorDo(error)
                                }
                            }catch (e:Exception){
                            }
                    }

                    override fun onError(e: Throwable) {
                        errorDo(e)
                        CookieDbUtil.instance().queryCookieBy(option.getUrl())?.let {
                            CookieDbUtil.instance().deleteCookie(it)
                        }

                    }

                })

    }

    private fun errorDo(e: Throwable) {
        TLog.i("http_error>>>>>>${e.message}")
        if (e is HttpException) {

            val apiErrorModel: ApiErrorModel? = when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR.getApiErrorModel(context)
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY.getApiErrorModel(context)
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND.getApiErrorModel(context)
                ApiErrorType.PARSING_FAILURE.code ->
                    ApiErrorType.PARSING_FAILURE.getApiErrorModel(context)
                else -> otherError(e)
            }
            listener?.onError(e.code(), apiErrorModel)
            return
        }

        val apiErrorType: ApiErrorType = when (e) {  //发送网络问题或其它未知问题，请根据实际情况进行修改
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECTION_TIMEOUT
            else -> ApiErrorType.UNEXPECTED_ERROR
        }
        listener?.onError(apiErrorType.code, apiErrorType.getApiErrorModel(context))
    }

    private fun otherError(e: HttpException): ApiErrorModel? {
        var model: ApiErrorModel? = null
        try {
            model = Gson().fromJson(e.response().errorBody()?.charStream(), ApiErrorModel::class.java)

        } catch (e: Exception) {
            TLog.i(e.toString())
        }


        return model
    }


}