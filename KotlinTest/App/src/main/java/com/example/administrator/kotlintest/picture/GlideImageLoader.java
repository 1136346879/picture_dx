package com.example.administrator.kotlintest.picture;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.kotlintest.R;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;

/**
 * Created by yechao on 2017/4/28.
 * Describe :
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        RequestOptions options = new RequestOptions();
        options.error(R.mipmap.default_image);
        options.placeholder(R.mipmap.default_image);
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(activity)                             //配置上下文
//                .load(Uri.fromFile(new File(path)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
//                .error(R.mipmap.default_image)           //设置错误图片
//                .placeholder(R.mipmap.default_image)     //设置占位图片
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
//                .into(imageView);

        Glide.with(activity)
                .load(Uri.fromFile(new File(path)))
                .apply(options)
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
