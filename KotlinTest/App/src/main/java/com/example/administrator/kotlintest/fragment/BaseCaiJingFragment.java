package com.example.administrator.kotlintest.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.common.CancellableTask;
import com.example.administrator.kotlintest.common.RxTask;
import com.example.administrator.kotlintest.common.TaskQueue;
import com.xfs.qrcode_module.util.Task;

import java.util.concurrent.Future;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author hexun
 */
public abstract class BaseCaiJingFragment extends Base2Fragment {

    protected CompositeDisposable disposables;

    private boolean isResume;
    private boolean isShowing;
    private boolean isFirstLoad = true;
    private long startTime;

    private final String className = getClass().getSimpleName();
    protected Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        isResume = false;
        isShowing = false;
        startTime = System.currentTimeMillis();
    }

    private static final int START_TIME_NUM = 16;

    @Override
    public void onResume() {
        super.onResume();
        isResume = true;
        if (startTime > 0) {
            startTime = System.currentTimeMillis() - startTime;
            if (startTime > START_TIME_NUM) {
                Log.e("UI WARN", className + getResources().getString(R.string.ui_elapsed_time_warm) + startTime);
            } else {
                Log.d("UI WARN", className + getResources().getString(R.string.ui_elapsed_time_like) + startTime);
            }
            startTime = 0;
        }
        checkShow();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        checkShow();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        checkShow();
    }

    @Override
    public void onPause() {
        super.onPause();
        isResume = false;
        checkShow();
    }

    private void checkShow() {
        if ((isAdded() && !isHidden() && isResume && getUserVisibleHint()) != isShowing) {
            isShowing = !isShowing;
            switchShow(isShowing);
        }
    }

    void switchShow(boolean show) {
        if (show) {
            onShowing();
        } else {
        }
        onShowingChanged(show);
    }

    protected String pageName() {
        return getClass().getSimpleName();
    }

    public boolean isShowing() {
        return isShowing;
    }

    protected void onShowingChanged(boolean isShowing) {
    }

    protected void onShowing() {
        if (isFirstLoad) {
            isFirstLoad = !firstLoad();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isFirstLoad = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /***
     * 当前fragment可见并且fragment的onCreateView()调用过之后调用
     * 在该方法调用之后isPrepared置为false，以确保该方法只在fragment
     * 初始化成功之后并且显示时调用一次
     */
    public boolean firstLoad() {
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposables != null) {
            disposables.dispose();
        }
    }

    /**
     * 添加rxJava disposable以便onDestroy时统一取消订阅.
     *
     * @param disposable 传入disposable
     */
    public void addDisposable(Disposable disposable) {
        if (null == disposables) {
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);
    }


    /**
     * 执行优先级异步任务(与UI不相关)
     *
     * @param task
     */
    protected void executeTask(Task task) {
        TaskQueue.getInstance().execute(task);
    }

    /**
     * 执行优先级异步任务(与UI不相关)
     *
     * @param cancellableTask
     */
    protected <T> Future<T> submitCancelTask(CancellableTask<T> cancellableTask) {
        return TaskQueue.getInstance().submit(cancellableTask);
    }

    /**
     * 执行串行异步任务(与UI不相关)
     *
     * @param runnable
     */
    protected void enqueueTask(Runnable runnable) {
        TaskQueue.getInstance().enqueue(runnable);
    }


    /**
     * 和页面绑定的异步任务, 方便compositeDisposable管理
     */
    protected <T> void runRxTask(final boolean isAutoCancel, final RxTask<T> rxTask) {
        Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                T t = rxTask.run();
                if (t != null) {
                    e.onNext(t);
                }
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (isAutoCancel) {
                            addDisposable(d);
                        }
                    }

                    @Override
                    public void onNext(T t) {
                        rxTask.onNext(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        rxTask.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        rxTask.onComplete();
                    }
                });
    }

}
