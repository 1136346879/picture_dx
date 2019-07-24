package com.example.administrator.kotlintest.bridge.loadimg.glide

import com.example.administrator.kotlintest.picture.PictureType


/**
 * Created by Kevin on 2019/3/12.
 */
/**
 * 对图片做对应处理，如需要去除对应的缩略图
 *
 * @param url         加载的图片url
 * @param pictureType 需要加载的图片分辨率类型
 * @return url
 */

class GlideManager {

    companion object {
        fun replacePictureUrlToComfortableUrl(url: String?, pictureType: PictureType?): String {

            // val urlConnect = "?x-oss-process=style/"
            if (url == null) {
                return ""
            }
            if (pictureType == null) {
                return url
            }
            return when {
                PictureType.style50.pictureTypeName == pictureType.pictureTypeName -> url.replace("marking_text", PictureType.style50.pictureTypeName)
                PictureType.style60.pictureTypeName == pictureType.pictureTypeName -> url.replace("marking_text", PictureType.style60.pictureTypeName)
                PictureType.style150.pictureTypeName == pictureType.pictureTypeName -> url.replace("marking_text", PictureType.style150.pictureTypeName)
                PictureType.style200.pictureTypeName == pictureType.pictureTypeName -> url.replace("marking_text", PictureType.style200.pictureTypeName)
                PictureType.style350.pictureTypeName == pictureType.pictureTypeName -> url.replace("marking_text", PictureType.style350.pictureTypeName)
                PictureType.marking_text.pictureTypeName == pictureType.pictureTypeName -> url
                else -> url
            }
        }
    }


}