package com.xfs.fsyuncai.bridge.retrofit.service.body

/**
 * Created by kangf on 2018/8/7.
 */
class OrderSavePayBody {

    var orderIds: String? = null
    var memberId: String? = null
    var customerId: String? = null
    var customerCode: String? = null
    var payType: Int? = 20
    var accountFlag: Int? = 1
    var totalAmount: Double? = null

}