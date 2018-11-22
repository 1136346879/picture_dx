package com.example.administrator.kotlintest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.kotlintest.Loglevel;
import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.inner.View;
import com.example.administrator.kotlintest.inner.onClickListener;
import com.example.administrator.kotlintest.widget.ToastUtilKt;

public class TestActivityJava extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_java);

        OverLoader overLoader = new OverLoader();
//       ToastUtilKt.INSTANCE.showCustomToast( overLoader.getOverLoaderMethod(3)+"");
//       ToastUtilKt.INSTANCE.showToast( overLoader.methd()+"");
//        ToastUtilKt.INSTANCE.showToast(StringExtendKt.times("很快乐》",3));

        final View view = new View();
        view.setOnClickListener(new onClickListener() {
            @Override
            public void onClicks() {

            }
        });

        for (Loglevel loglevel : Loglevel.values()) {
            ToastUtilKt.INSTANCE.showCustomToast(loglevel.toString());
        }


    }



}
