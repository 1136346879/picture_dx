package com.xfs.fsyuncai.bridge.retrofit.service.body

/**
 * Created by kangf on 2018/8/7.
 */
class OrderCombinePayBody {

    var params: List<ParamsBody>? = null

     class ParamsBody{
            var payId: Long? = null
            var platform: Int? = null
            var payWayName: String? = null
            var payableAmount: Double? = null
            var accountPwd: String? = null
            var ipAddress: String? = null
     }


}