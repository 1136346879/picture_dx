package com.example.administrator.kotlintest.bridge.loadimg

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.example.administrator.kotlintest.picture.PictureType
import java.io.File

/**
 * Created by kangf on 2018/6/14.
 */
interface LoadImageStrategy {

    fun loadAvatar(imageView: ImageView, url: String)

    fun loadAvatar(imageView: ImageView, file: File, roundingRadius: Int)
    /**
     * @params  pictureType  根据需求传入对应的pictureType，不传就是默认
     */
    fun loadImage(imageView: ImageView, url: String, pictureType: PictureType? = null)

    fun loadRoundImage(imageView: ImageView, url: String, radius: Int = 6, pictureType: PictureType? = null)

    fun loadImage(imageView: ImageView, url: String)

    fun loadImage(imageView: ImageView, file: File)

    fun loadImage(imageView: ImageView, bitmap: Bitmap)

    fun loadImage(imageView: ImageView, resId: Int, options: RequestOptions? = null)
}