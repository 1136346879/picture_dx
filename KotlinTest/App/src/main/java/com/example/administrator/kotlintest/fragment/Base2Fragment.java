package com.example.administrator.kotlintest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Base2Fragment extends Fragment {
    public View view;
    public Context context;

    public Base2Fragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this.getActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = this.getContentView(inflater);
        return this.view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.initView(view, savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        this.initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    protected <T extends View> T getViewById(int resId) {
        return this.view.findViewById(resId);
    }

    protected abstract View getContentView(LayoutInflater var1);

    protected abstract void initView(View var1, Bundle var2);

    protected abstract void initData(Bundle var1);
}
