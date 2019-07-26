package com.example.administrator.kotlintest

import com.xfs.fsyuncai.bridge.retrofit.ApiConstants

/**
 * Created by HaoBoy on 2018/8/27.
 *
 * H5链接
 */
object WebUrlDef {

    //升级认证会员
//    const val URL_UP_TO_VIP = "http://192.168.65.123:8000/upMember.html"
    const val URL_UP_TO_VIP = ApiConstants.WEB_BASE_URL + "h5/orderlist/upMember.html"
    //优惠券列表
//    const val URL_COUPON = "http://192.168.65.54:3000/coupon.html?customerId="
    const val URL_COUPON = ApiConstants.WEB_BASE_URL + "h5/orderlist/coupon.html?customerId="
    //首页绑定手机号
    const val URL_BIND_PHONE = ApiConstants.WEB_BASE_URL + "h5/orderlist/account/binding_phone.html"
    //支付结果页面-订单详情页
    const val URL_ORDER_DETAIL = ApiConstants.WEB_BASE_URL + "h5/orderlist/order-detail/orderdel-index.html"
    //增值税确认书
    const val ADD_TAX_PAGE_CONFIRM_H5_URL = ApiConstants.WEB_BASE_URL + "h5/orderlist/invoice/invoice.html"
    //忘记密码
    const val FORGET_PASS_WORD_URL = ApiConstants.WEB_BASE_URL + "h5/orderlist/account/retrieve_password.html"
    //修改密码
    const val CHANGE_PASS_WORD_URL = ApiConstants.WEB_BASE_URL + "h5/orderlist/account/update_login_password.html"
    //注册
    const val REGIST_URL = ApiConstants.WEB_BASE_URL + "h5/orderlist/account/register_fast.html"
    //修改绑定手机号
    const val BIND_PHONE = ApiConstants.WEB_BASE_URL + "h5/orderlist/account/update_bind_phone.html"
    //采购单
    const val SHOPPING_CAR_URL = ApiConstants.WEB_BASE_URL + "h5/orderlist/purchaseOrder/purchaseOrder.html"
    //端木  本地的环境
//    const val SHOPPING_CAR_URL =  "http://192.168.65.123:8000/purchaseOrder/purchaseOrder.html"
    //在线客服
    const val ONLINE_CONTACTS_URL = "https://kefu.huayunworld.com/H5/index.html#/chat?skillGroupId=43&enterpriseId=9"
    //我的各种订单
    const val ORDER_URL = ApiConstants.WEB_BASE_URL + "h5/orderlist/index.html"

    //无人仓 --  列表
    const val WRC_LIST_URL = ApiConstants.WEB_BASE_URL + "h5/unmannedStorehouse/h5App/unmannedList.html"
    //无人仓 -- 商品列表
    const val WRC_GOODS_URL = ApiConstants.WEB_BASE_URL + "h5/unmannedStorehouse/h5App/purchaseOrder.html"
    //注册协议
    const val REGIST_POLICY_URL ="file:///android_asset/private_policy.html"

}