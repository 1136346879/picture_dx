package com.xfs.fsyuncai.bridge.retrofit.service.body

/**
 * Created by kangf on 2018/8/28.
 */
class AddCartBody {

    var cityId: Int = 0
    var warehouseId: Int = 0
    var memberId: Int = 0
    var skuIdNumlist: List<SkuNumList>? = null
    var token: String = ""


    data class SkuNumList(var skuId: Int = 0,
                          var skuNum: Int = 0)

}