package com.xfs.qrcode_module.recycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xfs.qrcode_module.R;

import static com.xfs.qrcode_module.recycleview.RecycleViewFragment.newInstance;

public class RecycleviewActivity extends com.example.baselibrary.ui.BaseRecycleviewActivity {
    RecycleViewFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_activity);
    }

    @Override
    public int resLayout() {
        return R.layout.recycleview_activity;
    }

    @Override
    public void init() {

    }

    @Override
    public void logic() {
        if (fragment == null) {
            fragment = newInstance();
//            fragment.setData(jsonData);
        }
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, fragment, RecycleViewFragment.class.getSimpleName()
        ).commit();
    }
}
