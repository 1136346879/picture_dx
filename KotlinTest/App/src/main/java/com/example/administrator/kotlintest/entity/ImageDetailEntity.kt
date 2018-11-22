package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/9/19.
 */

data class ImageDetailEntity(
    val spuDetail: List<SpuDetail>,
    val errorCode: Int,
    val skuDetail: List<Any>
)

data class SpuDetail(
    val pack_list: String,
    val detail: String,
    val spu_id: Int
)
