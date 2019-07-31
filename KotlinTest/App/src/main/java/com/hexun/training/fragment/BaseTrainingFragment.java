package com.hexun.training.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.fragment.Base2Fragment;


public abstract class BaseTrainingFragment extends Base2Fragment {

    private boolean isResume;
    private boolean isShowing;

    private long startTime;
    private final String className = getClass().getSimpleName();
    /**该fragment是否可见，调用onHiddenChanged()和setUserVisibleHint()时改变*/
    protected boolean visibleToUser=false;
    /**fragment的onCreateView()是否调用过。调用过置为true，调用过firstLoad()后置为false*/
    protected boolean isPrepared=false;
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

    /**
     * ui_elapsed_time_like
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        isPrepared = true;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        isResume = true;
        if (startTime > 0) {
            startTime = System.currentTimeMillis() - startTime;
            if (startTime > START_TIME_NUM) {
                Log.e("UI WARN", className + getResources().getString(R.string.ui_elapsed_time_warm) + startTime);
            } else {
                Log.d("UI WARN", className +
                        getResources().getString(R.string.ui_elapsed_time_like) +
                        startTime);
            }
            startTime = 0;
        }
        checkShow();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        checkShow();
        visibleToUser=!hidden;
        if (visibleToUser&&isPrepared){
            firstLoad();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibleToUser=isVisibleToUser;
        if (visibleToUser&&isPrepared){
            firstLoad();
        }
        checkShow();
    }

    /***
     * 当前fragment可见并且fragment的onCreateView()调用过之后调用
     * 在该方法调用之后isPrepared置为false，以确保该方法只在fragment
     * 初始化成功之后并且显示时调用一次
     */
    public void firstLoad(){
    }

    @Override
    public void onPause() {
        super.onPause();
        isResume = false;
//        MobclickAgent.onPageEnd(getClass().getSimpleName());
        checkShow();
    }

    /**
     * 被遮盖
     *
     * @param isCovering
     */
    public final void setCover(boolean isCovering) {
        setUserVisibleHint(!isCovering);
//        checkShow();
    }

    private void checkShow() {
        if ((isAdded() && !isHidden() && isResume && getUserVisibleHint()) != isShowing) {
            isShowing = !isShowing;
            switchShow(isShowing);
        }
    }

    public boolean isShowing() {
        return isShowing;
    }

    private void switchShow(boolean show) {
        if (show) {
        } else {
        }
        onShowingChanged(show);
    }

    protected void onShowingChanged(boolean isShowing) {
    }

    protected String pageName() {
        return getClass().getSimpleName();
    }

}
