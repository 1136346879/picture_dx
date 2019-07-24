package com.example.administrator.kotlintest.common

/**
 * Created by kangf on 2018/7/25.
 */
object ActiveResultDef {

    const val ACTIVITY_RESULT_LOCATION = 0x00

    //点击同意用户协议按钮
    const val ACTIVITY_RESULT_PROTOCOL = 0x01

    //收货地址，点击条目返回的地址信息
    const val ADDRESS_RESULT_ITEM_KEY = 0x02

    //增加/修改收货地址后回调
    const val ADDRESS_ADD_UPDATE_CODE = 0x03

    //优惠券传递参数
    const val COUPON_RESULT_CODE = 0x04
    //支付配送
    const val PAY_SEND_RESULT_CODE = 0x05

    //发票信息
    const val INVOICE__RESULT_CODE = 0x06
    //回到首页
    const val BACK_MAIN_HOME = 0x07

    //收货地址，点击返回，返回的地址信息
    const val ADDRESS_RESULT_BACK_KEY = 0x08

    //收货地址onlyONe
    const val ADDRESS_RESULT_BACK_KEY_ONE = 0x09

    //维修和退货详情页返回
    const val REPAIR_RETURN_RESULT_BACK_KEY_ONE = 0x02
    //支付
    const val PAY_RESULT_CODE = 0x10

    //file
    const val FILE_RESULT_CODE = 0x11
    //图片
    const val SELECT_PHOTO = 0x12
    //添加附件页面
    const val FILE_ADD_PAGE = 0x13
}
