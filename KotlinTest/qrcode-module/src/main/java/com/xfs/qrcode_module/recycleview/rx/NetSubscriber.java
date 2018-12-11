package com.xfs.qrcode_module.recycleview.rx;

import com.xfs.qrcode_module.recycleview.exception.BusinessException;
import com.xfs.qrcode_module.recycleview.exception.ErrorHandler;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;

/**
 * @author zhangpeiyuan
 * @date 2017/10/18
 */

public abstract class NetSubscriber<T> implements Observer<ApiResult<T>> {


    @Override
    public final void onError(@NonNull Throwable e) {
        if (e instanceof BusinessException) {
            BusinessException exception = (BusinessException) e;
            if (exception.getErrorCode() == ErrorHandler.NULL_DATA) {
                onData(null,false);
            } else {
                onError(exception.getErrorCode(), exception);
            }
        } else if (e instanceof ParseException || e instanceof JSONException) {
            onError(ErrorHandler.PARSE_DATA_EXCEPTION, e);
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            onError(ErrorHandler.NET_EXCEPTION, e);
        } else {
            onError(ErrorHandler.UNKNOWN_EXCEPTION, e);
        }
        onDone();
    }


    @Override
    public final void onComplete() {
        onDone();
    }

    @Override
    public final void onNext(@NonNull ApiResult<T> item) {
        onData(item.data,item.isFromCache());
    }

    @Override
    public final void onSubscribe(@NonNull Disposable d) {
        onStart(d);
    }

    /**
     * 任务开始执行,在当前线程回调
     * @param d
     */
    public abstract void onStart(@NonNull Disposable d);

    /**
     * 获取到数据
     * @param t 数据
     * @param isFromCache 是否来自缓存
     */
    public abstract void onData(@Nullable T t,boolean isFromCache);

    /**
     * 业务异常和网络回调
     *
     * @param code 错误码
     * @param e    错误
     */
    public abstract void onError(int code, Throwable e);

    /**
     * onError和onComplete都会执行,可以执行统一的uI操作或者逻辑
     */
    public abstract void onDone();



}
