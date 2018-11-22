package com.example.administrator.kotlintest.bridge.loadimg

import com.xfs.fsyuncai.bridge.loadimg.glide.GlideLoad

/**
 * Created by kangf on 2018/6/14.
 */
class LoadImage {

    private var loadImageStrategy: LoadImageStrategy = GlideLoad()

    companion object {
        @JvmStatic
        fun instance() = Inner.instance.loadImageStrategy
    }

    object Inner {
        val instance = LoadImage()
    }
}