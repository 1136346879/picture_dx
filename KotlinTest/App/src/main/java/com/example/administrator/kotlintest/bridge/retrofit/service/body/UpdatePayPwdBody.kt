package com.xfs.fsyuncai.bridge.retrofit.service.body

/**
 * Created by kangf on 2018/8/8.
 *
 * 修改支付密码请求体
 */
class UpdatePayPwdBody {

    var loginAccount: String? = null
    var paidPassword: String? = null
    var paidPasswordConfirm: String? = null
    var mobile: String? = null
    var token: String? = null
    var uuid: String? = null
    var code: String? = null
}