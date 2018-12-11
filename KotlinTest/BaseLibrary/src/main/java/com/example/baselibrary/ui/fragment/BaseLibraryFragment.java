package com.example.baselibrary.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baselibrary.R;
import com.example.baselibrary.widgets.Density;
import com.example.baselibrary.widgets.TLog;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by Kangfan on 2017/11/9.
 */

public abstract class BaseLibraryFragment extends RxFragment {

    private View view;
    private final String className = getClass().getSimpleName();
    private long startTime;
    private static final int START_TIME_NUM = 16;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startTime = System.currentTimeMillis();
        base();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(layoutResId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        logic();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void base() {
        Density.INSTANCE.setDefault(this.getActivity());
    }
    protected <T extends View> T getViewById(int resId) {
        return this.view.findViewById(resId);
    }
    public abstract int layoutResId();

    public abstract void init();

    public abstract void logic();

    @Override
    public void onResume() {
        super.onResume();
        if(startTime > 0 ){
            startTime = System.currentTimeMillis() - startTime;
            if (startTime > START_TIME_NUM) {
                TLog.INSTANCE.i("UI WARN "+ className + getResources().getString(R.string.ui_elapsed_time_warm) + startTime);
            } else {
                TLog.INSTANCE.i("UI WARN "+ className + getResources().getString(R.string.ui_elapsed_time_like) + startTime);
            }
            startTime = 0;
        }
    }
}
