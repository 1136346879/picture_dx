package com.example.administrator.kotlintest.fragment;

import android.view.View;

/**
 * Created by Kangfan on 2017/11/9.
 */

public abstract class BaseTabFragment extends BaseFragment {


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null) {
            if (menuVisible) {
                this.getView().setVisibility(View.VISIBLE);
            } else {
                this.getView().setVisibility(View.GONE);
            }
        }
    }


}
