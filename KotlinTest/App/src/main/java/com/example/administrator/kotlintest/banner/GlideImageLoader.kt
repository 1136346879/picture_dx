package com.xfs.fsyuncai.ui.main.home.banner

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xfs.fsyuncai.entity.BannerEntity

import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {

        path?.apply {
//            val entity = path as BannerEntity.DataBean
            Glide.with(context)
//                    .setDefaultRequestOptions(RequestOptions().skipMemoryCache(true))//设置不缓存
                    .load(path)
                    .into(imageView)
//            val uri: Uri = Uri.parse(path)
//            imageView.setImageURI(uri)
        }
    }
}