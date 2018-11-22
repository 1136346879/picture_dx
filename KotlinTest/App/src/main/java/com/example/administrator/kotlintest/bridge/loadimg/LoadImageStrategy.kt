package com.example.administrator.kotlintest.bridge.loadimg

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import java.io.File

/**
 * Created by kangf on 2018/6/14.
 */
interface LoadImageStrategy {

    fun loadAvatar(imageView: ImageView, url: String)

    fun loadImage(imageView: ImageView, url: String)

    fun loadImage(imageView: ImageView, file: File)

    fun loadImage(imageView: ImageView, resId: Int, options: RequestOptions? = null)
}