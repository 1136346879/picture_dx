package com.example.administrator.kotlintest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.kotlintest.Loglevel;
import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.banner.mybanner.ImageBanner;
import com.example.administrator.kotlintest.inner.View;
import com.example.administrator.kotlintest.widget.ToastUtilKt;

import java.util.ArrayList;
import java.util.List;

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
        view.setOnClickListener(() -> {

        });
        //设置图片加载集合
     List imageArray=new ArrayList<>();

        imageArray.add("http://img3.imgtn.bdimg.com/it/u=2758743658,581437775&fm=15&gp=0.jpg");
        imageArray.add("http://img3.imgtn.bdimg.com/it/u=2105877023,3759180926&fm=15&gp=0.jpg");
        imageArray.add("http://img2.imgtn.bdimg.com/it/u=1876814088,3589919070&fm=15&gp=0.jpg");


        //设置图片标题集合
        List  imageTitle=new ArrayList<>();
        imageTitle.add("aaaaaaaaa");
        imageTitle.add("bbbbbbbbb");
        imageTitle.add("ccccccccc");

        for (Loglevel loglevel : Loglevel.values()) {
            ToastUtilKt.INSTANCE.showCustomToast(loglevel.toString());
        }

       ImageBanner imageBanner =  findViewById(R.id.myBanner);
        imageBanner.setList(imageArray,imageTitle);



    }



}
