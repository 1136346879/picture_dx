package com.xfs.fsyuncai.bridge.retrofit.service.body

import com.example.administrator.kotlintest.entity.ConfirmOrderentity
import com.example.administrator.kotlintest.entity.address.AddressEntity
import com.xfs.fsyuncai.entity.CouponEntity

/**
 * Created by kangf on 2018/8/7.
 */
class OrderConfirmBody {


    var wannaDeliverAtBegin: String? = null
    var warehouseId: Int? = null
    var customerId: String? = null
    var optId: Int? = null
    var deliverWay: String? = null
    var token: String? = null
    var wannaDeliverAtEnd: String? = null
    var memberId: Int? = null
    var customerCode: String? = null
    var device_platform: String? = null
    var cityId: Int? = null
    var shippingAddressModel: AddressEntity.ListBean? = null
    var couponChoosen: CouponEntity? = null
    var payModelChoosen: ConfirmOrderentity.PayModelChoosenBean? = null


    var skuModelList:   Map<String,List<ParamsBody>>? = null

     class ParamsBody{
         var cartId: String? = null
         var salePrice: Int? = null
         var buyyerCount: Int? = null
         var skuCode: String? = null



         var skuId: Int? = null
         var customerId: String? = null
         var memberId: Int? = null
         var spuId: Int? = null
         var productPic: String? = null

     }


}