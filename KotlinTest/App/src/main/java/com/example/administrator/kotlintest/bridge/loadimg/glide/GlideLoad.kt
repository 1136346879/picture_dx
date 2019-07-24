package com.example.administrator.kotlintest.bridge.loadimg.glide

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.bridge.loadimg.LoadImageStrategy
import com.example.administrator.kotlintest.picture.PictureType
import java.io.File

/**
 * Created by kangf on 2018/6/14.
 */
class GlideLoad : LoadImageStrategy {

    override fun loadImage(imageView: ImageView, resId: Int, options: RequestOptions?) {

        imageView.context?.let {
            Glide.with(it)
                    .setDefaultRequestOptions(options
                            ?: RequestOptions()
                                    .placeholder(R.drawable.placeholder)
                                    .error(R.drawable.placeholder))
                    .load(resId)
                    .into(imageView)
        }


    }

    override fun loadImage(imageView: ImageView, url: String, pictureType: PictureType?) {

        imageView.context?.let {
            Glide.with(it)
                    .setDefaultRequestOptions(RequestOptions()
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder))
                    .load(GlideManager.replacePictureUrlToComfortableUrl(url, pictureType))
                    .into(imageView)
        }
    }

    override fun loadImage(imageView: ImageView, url: String) {

        imageView.context?.let {
            Glide.with(it)
                    .setDefaultRequestOptions(RequestOptions()
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder))
                    .load(url)
                    .into(imageView)
        }

    }

    override fun loadImage(imageView: ImageView, file: File) {

        imageView.context?.let {
            Glide.with(it)
                    .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.placeholder))
                    .load(file)
                    .into(imageView)
        }
    }

    override fun loadRoundImage(imageView: ImageView, url: String, radius: Int, pictureType: PictureType?) {
        imageView.context?.let {
            Glide.with(it)
                    .setDefaultRequestOptions(RequestOptions.bitmapTransform(RoundedCorners(radius))
                            //.override(300, 300)
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder)

                    )
                    .load(url)
                    .into(imageView)
        }
    }

    override fun loadImage(imageView: ImageView, bitmap: Bitmap) {
        if (imageView.context == null) return
        Glide.with(imageView.context)
                .setDefaultRequestOptions(RequestOptions()
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder))
                .load(bitmap)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        bitmap.recycle()
                        return true
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        if (resource != null) {
                            if (isFirstResource)
                                imageView.setImageDrawable(resource)
                        }

                        bitmap.recycle()
                        return true
                    }
                })
                .into(imageView)
    }

    override fun loadAvatar(imageView: ImageView, url: String) {
        if (imageView.context == null) return
        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(imageView)
    }

    /**
     * 圆角矩形
     * @roundingRadius 圆角度数
     */
    override fun loadAvatar(imageView: ImageView, file: File, roundingRadius: Int) {
        if (imageView.context == null) return
        //设置图片圆角角度
        val roundedCorners = RoundedCorners(roundingRadius)
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        val options = RequestOptions.bitmapTransform(roundedCorners).override(100, 100)
        Glide.with(imageView.context)
                .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.placeholder))
                .load(file)
                .apply(options)
                .into(imageView)
    }
}