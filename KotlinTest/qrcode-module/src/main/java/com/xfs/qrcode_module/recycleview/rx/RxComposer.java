package com.xfs.qrcode_module.recycleview.rx;

import com.xfs.qrcode_module.R;
import com.xfs.qrcode_module.recycleview.exception.BusinessException;
import com.xfs.qrcode_module.recycleview.exception.ErrorHandler;
import com.lzy.okgo.model.Response;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhangpeiyuan
 * @date 2017/10/18
 */

public class RxComposer {


    /**
     * 切换线程操作符,需要加泛型,比较麻烦,可选择性使用
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> ioToMain() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 分离业务异常,保持onNext纯净
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<ApiResult<T>, T> handleError() {

        return new ObservableTransformer<ApiResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ApiResult<T>> upstream) {
                return upstream.flatMap(new Function<ApiResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull ApiResult<T> apiResult) throws Exception {
                        if (apiResult.isSuccess()) {
                            return Observable.just(apiResult.data);
                        } else if (apiResult.isSuccess() && apiResult.data == null) {
                            return Observable.error(new BusinessException(ErrorHandler.NULL_DATA, ResourceHelper.getInstance().getString(R.string.null_data_error)));
                        } else {
                            return Observable.error(new BusinessException(apiResult.getStatus(),
                                    apiResult.getErrorMessage()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 切换线程,并分离业务异常
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<ApiResult<T>, T> dealThreadError() {
        return new ObservableTransformer<ApiResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<ApiResult<T>> upstream) {
                return upstream.flatMap(new Function<ApiResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(@NonNull ApiResult<T> apiResult) throws Exception {
                        if (apiResult.isSuccess() && apiResult.data != null) {
                            return Observable.just(apiResult.data);
                        } else if (apiResult.isSuccess() && apiResult.data == null) {
                            return Observable.error(new BusinessException(ErrorHandler.NULL_DATA, ResourceHelper.getInstance().getString(R.string.null_data_error)));
                        } else {
                            return Observable.error(new BusinessException(apiResult.getStatus()
                                    , apiResult.getErrorMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * 处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<Response<ApiResult<T>>, ApiResult<T>> handleCacheError() {
        return new ObservableTransformer<Response<ApiResult<T>>, ApiResult<T>>() {
            @Override
            public ObservableSource<ApiResult<T>> apply(Observable<Response<ApiResult<T>>> upstream) {
                return upstream.flatMap(new Function<Response<ApiResult<T>>, ObservableSource<ApiResult<T>>>() {
                    @Override
                    public ObservableSource<ApiResult<T>> apply(Response<ApiResult<T>> response) throws Exception {

                        if (response.isSuccessful()){

                            ApiResult<T> result = response.body();
                            if (result == null){
                                return Observable.error(new BusinessException(ErrorHandler.NULL_DATA, ResourceHelper.getInstance().getString(R.string.null_data_error)));
                            }
                            if (response.isFromCache()){
                                result.setFromCache(true);
                            }
                            if (result.isSuccess() && result.data != null) {
                                return Observable.just(result);
                            } else if (result.isSuccess() && result.data == null) {
                                return Observable.error(new BusinessException(ErrorHandler.NULL_DATA, ResourceHelper.getInstance().getString(R.string.null_data_error)));
                            } else {
                                return Observable.error(new BusinessException(result.getStatus()
                                        , result.getErrorMessage()));
                            }

                        }else {
                            return Observable.error(response.getException());
                        }
                    }
                });
            }
        };
    }


}
