package com.example.administrator.kotlintest.common

/**
 * Created by kangf on 2018/6/25.
 */
object IntentDataDef {

    /**
     * 用户中心 - - 登录、注册
     */
    // 快速注册传递手机号
    const val REGISTER_MOBILE_KEY = "REGISTER_MOBILE_KEY"

    const val LOCATION_KEY = "LOCATION_KEY"
    const val ID_LOCATION_KEY = "ID_LOCATION_KEY"

    // 商品列表接收的 搜索文字  和    品类文字
    const val SEARCH_NAME_KEY = "SEARCH_NAME_KEY"
    const val SEARCH_CATEGOR_ID_KEY = "SEARCH_CATEGOR_ID_KEY"
    const val SEARCH_CATEGORY_KEY = "SEARCH_CATEGORY_KEY"
    const val SEARCH_CATEGORY_LEVEL_KEY = "SEARCH_CATEGORY_LEVEL_KEY"
    const val SEARCH_ACTIVE_ID = "SEARCH_ACTIVE_ID" // 活动id
    const val SEARCH_ACTIVE_NAME = "SEARCH_ACTIVE_NAME" // 活动名称
    const val SEARCH_ACTIVE_PROMOTION = "SEARCH_ACTIVE_PROMOTION" // 活动名称

    // 商品详情页接收的 spuId
    const val DETAIL_SPU_ID_KEY = "DETAIL_SPU_ID_KEY"
    const val DETAIL_SkU_ID_KEY = "DETAIL_SkU_ID_KEY"
    const val HOME_SHOPPING_CART_ENTER = "HOME_SHOPPING_CART_ENTER"
    // 价格区间
    const val DETAIL_SPU_PRICE_RANGE = "DETAIL_SPU_PRICE_RANGE"
    // 商品详情 点击规格选择， 弹出对话框， 传递所有信息
    const val DETAIL_SKU_KEY = "DETAIL_SKU_KEY"
    const val DETAIL_IS_ADD_CART = "DETAIL_IS_ADD_CART"
    const val SHOPPING_SELECT_FRAGMENT="SHOPPING_SELECT_FRAGMENT"
    const val SHOPPING_SELECT_NUM="SHOPPING_SELECT_NUM"

    //点击查看大图，传递图片路径集合
    const val SEE_DETAIL_IMAGE = "SEE_DETAIL_IMAGE"
    //点击查看大图， 传递当前索引
    const val SEE_DETAIL_IMAGE_INDEX = "SEE_DETAIL_IMAGE_INDEX"
    //从详情页（false）进去还是从附件（true）进入
    const val FROM_WHERE = "FROM_WHERE"
    //商品详情，点击添加购物车，传递所有规格集合
    const val ALL_SKU_GOOD = "ALL_SKU_GOOD"

    //地址列表点击回传
    const val ADDRESS_SELECT_KEY = "ADDRESS_SELECT_KEY"
    //地址列表只有一个数据
    const val ADDRESS_SELECT_KEY_ONE = "ADDRESS_SELECT_KEY_ONE"
    //地址列表点击回传的标记--value传1
    const val ADDRESS_SELECT_TAG = "ADDRESS_SELECT_TAG"
    //地址列表点击回传的ID
    const val ADDRESS_SELECT_ID = "ADDRESS_SELECT_ID"

    //优惠卷列表
    const val COUPON_PARAM = "COUPON_PARAM"
    //默认优惠券
    const val COUPON_PARAM_DEARULT = "COUPON_PARAM_DEARULT"

    //WebActivity-公共-url
    const val TAG_URL_KEY = "COMMON_URL_KEY"
    //WebActivity-公共-title
    const val TAG_URL_TITLE_KEY = "COMMON_URL_TITLE_KEY"
    //加载html标签
    const val RICH_TEXT_KEY = "RICH_TEXT_KEY"
    //支付配送
    const val SEND_PAY_PARAM = "SEND_PAY_PARAM"
    //添加附件
    const val ADD_FILE_PARAM = "ADD_FILE_PARAM"
    //进入附件列表页
    const val FROM_ADD_FILE_PARAM = "FROM_ADD_FILE_PARAM"
    const val ORDER_ID = "ORDER_ID"

    //结算
    const val BALANCE_DATA = "BALANCE_DATA"

    // 跳转至收银台传递参数(订单id)
    const val CHECK_STAND_ORDER_ID = "CHECK_STAND_ORDER_ID"
    // 跳转至收银台传递参数(总价格)
    const val CHECK_STAND_TOTAL_PRICE = "CHECK_STAND_TOTAL_PRICE"
    // 订单截至时间
    const val CHECK_STAND_ORDER_END_TIME = "CHECK_STAND_ORDER_END_TIME"
    // 当前时间
    const val CHECK_STAND_ORDER_CURRENT_TIME = "CHECK_STAND_ORDER_CURRENT_TIME"
    // 线下支付时是转账还是汇款
    const val CHECK_STAND_OFFLINE_TYPE = "CHECK_STAND_OFFLINE_TYPE"
    // 货到付款显示预计送达时间
    const val CHECK_STAND_DELIVERY_TIME = "CHECK_STAND_DELIVERY_TIME"


    // 发票参数
    const val INVOICE_PARAM = "INVOICE_PARAM"
    //注册成功后进入登录页参数
    const val USER_NAME_PARAM = "USER_NAME_PARAM"
    const val PHONE_NUM_PARAM = "PHONE_NUM_PARAM"

    // 提交订单结果 -- 订单号
    const val ORDER_RESULT_CODE = "ORDER_RESULT_CODE"
    // 提交订单结果 -- 已经支付金额
    const val ORDER_RESULT_PAY_COUNT = "ORDER_RESULT_PAY_COUNT"
    // 提交订单结果 -- 待支付金额
    const val ORDER_RESULT_NEED_PAY_COUNT = "ORDER_RESULT_NEED_PAY_COUNT"

    // 手机验证码判断类型
    const val VERIFY_CODE_TYPE_KEY = "VERIFY_CODE_TYPE_KEY"
    // 手机号
    const val VERIFY_CODE_MOBILE = "VERIFY_CODE_MOBILE"
    // 设置支付密码-发送手机验证码
    const val SET_PAY_PWD_VALUE = 3
    // 修改支付密码-发送手机验证码
    const val UPDATE_PAY_PWD_VALUE = 23
    // 短信验证码登录
    const val SMS_LOGIN_VALUE = 24
    //支付时间选择页面参数
    const val INFO_USER_CHANGE_ENTITY = "INFO_USER_CHANGE_ENTITY"
    /**
     *     确认订单商品清单参数
     */
    //是否分摊类型  type
    const val SEND_MORE_LESS_TYPE_ = "SEND_MORE_LESS_TYPE"
    //总运费
    const val TOTAL_FREIGHT = "TOTAL_FREIGHT"
    //商品总价格
    const val GOODS_TOTAL_MONEY = "GOODS_TOTAL_MONEY"
    //优惠金额
    const val REDUCEMONEY = "REDUCEMONEY"
    //商品清单列表数据
    const val SKU_MODEL_LISTBEAN_LIST = "SKU_MODEL_LISTBEAN_LIST"
    //设置是否让WEBVIEW reload
    const val NO_RELOAD_WEBVIEW = "NO_RELOAD_WEBVIEW"
    //设置是否有左箭头
    const val IS_SHOW_VISIBLE="IS_SHOW_VISIBLE"

    // 逆向-进入搜索界面传入的参数
    const val N_SEARCH_KEY = "N_SEARCH_KEY"
    const val N_SEARCH_VALUE = "N_SEARCH_VALUE"
    //拍摄或剪切后请求到的json
    const val SEARCH_IMAGE_IMAGE_JSON = "SEARCH_IMAGE_IMAGE_JSON"

    const val INTENT_CODE_SELECT_FILE = 0x000001
    //裁剪图片地址
    const val INTENT_FILE_KEY = "INTENT_FILE_KEY"
    //原始图片地址
    const val INTENT_FILE_ORIGIN_KEY = "INTENT_FILE_ORIGIN_KEY"
    const val INTENT_FILE_TYPE_KEY = "INTENT_FILE_TYPE_KEY"
    const val INTENT_FILE_VALUE_UPLOAD = 100
    const val INTENT_FILE_VALUE_CROP = 101

    //浮层也同意回调
    const val HOME_SELECT_CHECKBOX = "HOME_SELECT_CHECKBOX"

    //自提点联系人手机号
    const val SELF_ADDRESS_PHONE = "SELF_ADDRESS_PHONE"

    //自提点联系人名称
    const val SELF_ADDRESS_NAME = "SELF_ADDRESS_NAME"

    //自提点
    const val SELF_ADDRESS = "SELF_ADDRESS"

    //banner相关
    const val BANNER_ACTIVITY_ID = "BANNER_ACTIVITY_ID"
    const val BANNER_STATUS = "BANNER_STATUS"
    //banner链接
    const val BANNER_ROUTE_URL = "BANNER_ROUTE_URL"
    const val BANNER_TITLE = "BANNER_TITLE"
    //pc专属
    const val BANNER_PC_TY = "BANNER_PC_TY"

    /**
     * web assembly params
     */

    const val WEB_H5_URL = "h5_url"
    const val WEB_H5_TITLE = "h5_title"
    const val WEB_H5_TYPE = "h5_type"
    const val FILE_UPLOAD_TYPE = "file_upload_type"

}

