package com.xfs.fsyuncai.bridge.retrofit.service.body

import com.example.administrator.kotlintest.entity.address.AddressEntity
import com.xfs.fsyuncai.entity.CouponEntity

/**
 * Created by kangf on 2018/8/7.
 */
class CommitOrderBody {


    var codeUuid: String? = null//
    var token: String? = null//
    var memberId: String? = null//
    var accountType: String? = null//
    var cityId: Int? = null//
    var customerId: String? = null//    用户DI
    var loginAccount: String? = null//  账户名
    var customerName: String? = null//  用户名
    var customerCode: String? = null//
    var qualityFileRequired: Int? = null//  是否需要资质文件  10 需要  20  不需要
    var fileCopies: Int? = null//  文件分数
    var originalFile: Int? = null// 是否需要原件   10  需要  20  不需要
    var usedCoupon: String? = null//优惠券  10  使用  20 不适用
    var finalTotalAmount: Float? = null//最后金额
    var goodTotalMoney: Float? = null//  总价格
    var goodTotalNum: String? = null//  商品总数
    var goodTotalWeight: String? = null// 总重量
    var payMoney: String? = null// 应付总额
    var payModel: PayModel? = null//  支付方式
    var source: String? = null//
    var remarks: String? = null//物流
    var limitLine: String? = null//s
    var deliverWay: String? = null//
    var receiveOnlyAt: Int? = null//
    var wannaArrivedAtBegin: String? = null//
    var wannaArrivedAtEnd: String? = null//
    var freight: Double? = null//
    var separateShippingFee: Int? = null//
    var invoiceFlag: Int? = null//
    var invoiceType: Int? = null //
    var invoiceTitle: String? = null   //单位发票抬头
    var taxpayerIdentifyNum: String? = null //纳税人识别码
    var registerAddress: String? = null //注册地址
    var registerPhone: String? = null //注册电话
    var openBank: String? = null //开户银行
    var bankAccount: String? = null //银行账户
    var shippingAddress: AddressEntity.ListBean? = null//
    var dispatchBillModel: DispatchBillModel? = null//
    var couponModel:CouponEntity? = null

    var device_platform: String? = null
    var warehouseId: Int? = null

}
class DispatchBillModel {
    var totalWeight: String? = null


    var skuModelList: List<SkumodelListBeanSmall>? = null

}
class SkumodelListBeanSmall {
    var buyyerCount: String? = null
    var cartId: String? = null
    var productPic: String? = null
    var salePrice: String? = null
    var memberId: String? = null
    var skuId: String? = null
    var spuId: String? = null
    var customerId: String? = null
    var skuCode: String? = null
    var skuName: String? = null
}

class PayModel {
    var payName: String? = null
    var payType: String? = null
}
