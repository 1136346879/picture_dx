package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/7/10.
 */
data class ProductDetailBean(

        var errorCode: Int,
        var spuDetail: List<SpuDetail>
) {

    data class SpuDetail(var detail: String, var pack_list: String, var spu_id: Int)

}