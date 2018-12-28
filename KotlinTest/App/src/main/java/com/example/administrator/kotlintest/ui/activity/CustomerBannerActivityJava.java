package com.example.administrator.kotlintest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.administrator.kotlintest.Loglevel;
import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.banner.mybanner.ImageBanner;
import com.example.administrator.kotlintest.inner.View;
import com.example.administrator.kotlintest.loadingview.LoadingView;
import com.example.administrator.kotlintest.widget.DensityUtil;
import com.example.baselibrary.widgets.ToastUtilKt;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义显示轮播图
 */
public class CustomerBannerActivityJava extends Activity implements LoadingView.OnLoadingViewClickListener {

    private LoadingView loadingView;
    private RelativeLayout newsDetailContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_java);
        loadingView = (LoadingView) LayoutInflater.from(this).inflate(R.layout.view_loading, null);
        loadingView.setOnLoadingViewClickListener(this);
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
        newsDetailContent = findViewById(R.id.news_detail_content);
        imageBanner.setList(imageArray,imageTitle);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//        layoutParams.addRule(RelativeLayout.BELOW, R.id.topbar_news_detail);
//        layoutParams.addRule(RelativeLayout.ABOVE, R.id.bottombar_news_detail);
        layoutParams.topMargin = DensityUtil.dip2px(this, 0.5f);
        layoutParams.bottomMargin = DensityUtil.dip2px(this, 0.5f);
        loadingView.setLayoutParams(layoutParams);
        newsDetailContent.addView(loadingView);


        loadingView.showProgress();

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
//               loadingView.showErrorView("网络错误，点击重新加载");
               loadingView.dismiss();
               imageBanner.setStartRoll();
           }
       }, 2000);

    }

    /**
     * loadview  点击重新加载
     * @param type
     */
    @Override
    public void onLoadingViewClick(int type) {
        ToastUtilKt.INSTANCE.showCustomToast("点击重新加载");
    }
}
