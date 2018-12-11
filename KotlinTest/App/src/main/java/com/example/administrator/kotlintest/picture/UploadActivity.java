package com.example.administrator.kotlintest.picture;


import com.example.administrator.kotlintest.R;
import com.example.baselibrary.ui.activity.BaseUIActivity;

public class UploadActivity extends BaseUIActivity {
    PictureUploadFragment fragment;

    @Override
    public int initLayout() {
        return R.layout.activity_balance;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if (fragment == null) {
            fragment = PictureUploadFragment.newInstance();
//            fragment.setData(jsonData);
        }
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, fragment, PictureUploadFragment.class.getSimpleName()
        ).commit();
    }
}
