package com.xfs.fsyuncai.bridge.retrofit


/**
 * Created by kangf on 2018/6/12.
 */
class ApiConstants private constructor() {

    companion object {

        /*控制是否输入日志*/
        const val IS_DEBUG = true
//测试环境
        const val BASE_URL = "https://t.fsyuncai.com/api/mobile/"
//        const val BASE_URL_TEST = "https://t.fsyuncai.com/"
//        const val BASE_URL_WWW = "https://www.fsyuncai.com/"
        const val UPLOAD_BASE_URL = "http://192.168.0.55:9060/"//图片上传
//        const val BASE_URL = "http://192.168.0.34:8888/"
//                const val BASE_URL = "https://t.fsyuncai.com/api/mobile/"
//        const val BASE_URL = "http://192.168.0.31/"
//        const val BASE_URL = "http://192.168.65.116:8888/"

        //正式环境
//        const val BASE_URL = "https://www.fsyuncai.com/api/mobile/"
        //图片识别上传
//        const val UPLOAD_BASE_URL = "http://111.200.196.1:9060/"



//        const val BASE_URL = "http://192.168.0.107:36060/"

//        const val BASE_URL = "http://192.168.0.55:9004/"
//        const val BASE_URL = "http://192.168.65.106:8888"
        /**
         * addCartSuccess
         * 分类接口
         */

        //根据级别获取分类
//        const val CATEGORY_TOP = "aproduct/category/categoryByParam"
        const val CATEGORY_TOP = "product/category/listCategory"
        //获取所有分类
        const val CATEGORY_ALL = "product/category/allCategory"

        //获取搜索结果
        const val SEARCH_RESULT = "search/searchPro.jhtml"
        //通过搜索框获取搜索结果
        const val SEARCH_RESULT_BY_STRING = "search/queryString.jhtml"

        //智能联想
        const val SEARCH_ASSOCIATION = "search/queryStringAuto.jhtml"

        //商品详情
        const val GOODS_DETAIL = "product/product/skuDetailList"
        //规格参数
        const val PRODUCT_DETAIL = "product/product/skuShopDetailInformation"


        // 登录
        const val USER_LOGIN = "membership/login.jhtml"
        // 登录验证码
        const val CODE_LOGIN = "membership/getImgVerifyCode.jhtml"
        // 手机验证码
        const val CODE_MOBILE = "membership/getMobileVerifyCode"
        // 校验手机验证码
        const val CODE_MOBILE_CHECK = "membership/checkSmsCode.jhtml"
        // 根据token获取用户信息
        const val GET_USER_INFO_BY_TOKEN = "membership/queryMemberInfoByToken.jhtml"
        // 根据memberId获取用户信息
        const val GET_USER_INFO_BY_MEMBER_ID = "membership/queryMemberInfo.jhtml"

        //首次设置支付密码
        const val SET_PAY_PWD = "membership/firstSetPayPwd.jhtml"
        //修改支付密码
        const val UPDATE_PAY_PWD = "membership/updatePayPwd.jhtml"
        //是否设置支付密码
        const val HAS_SET_PAY_PWD = "membership/queryPayPwdIsSet"
        //校验支付密码
        const val CHECK_PAY_PWD = "membership/checkPayPwd.jhtml"


        /**
         * 采购单
         */
        // 购物车添加
        const val ADD_SHOPPING_CART = "cart/addShoppingCartReturnSku"
        // 采购单数量
        const val NUM_SHOPPING_CART = "cart/findShoppingCartCount"
        // 购物车列表展示
        const val SHOPPING_CART_List = "cart/findShoppingCartList"


        /**
         * 订单
         */
        // 获取订单数量
        const val ORDER_NUMBER = "order/orderbase_number/select_orderbase"
        // 生成支付单
        const val SAVE_PAY_INFO = "payment/app/save_pay_info"
        // 生成支付流水单
        const val COMBINE_PAY = "payment/app/combine_pay"
        // 支付宝 -- 支付结果查询
        const val PAY_RESULT = "payment/ali_pay_succ"
        //线下付款，确认支付结果
        const val INSTANT_CONFIRM = "order/payment/instantConfirm"
        //确认订单
        const val CONFIRM_TRADE = "trade/confirm"
        //提交订单
        const val COMMIT_TRADE = "trade/submitTrade"
        //采购单结算前  check商品
        const val CHECK_PRODUCTS = "api/pc/trade/checkCartProduct"


        /**
         * common
         */

        // 获取收货地址列表
        const val ADDRESS_LIST = "membership/adminQueryAddressPerson.jhtml"

        //收货地址-编辑查看-参数shipAddId
        const val ADDRESS_EDIT_SHOW = "membership/adminViewMemberShipPerson.jhtml"

        // 删除收货地址
        const val ADDRESS_DELETE = "membership/deleteMemberShoppingAddress.jhtml"

        //新增收货地址
        const val ADDRESS_ADD = "membership/adminAddMemberShoppingAddress.jhtml"

        //修改收货地址
        const val ADDRESS_EDIT = "membership/adminUpdateMemberShoppingAddress.jhtml"

        //设置为默认收货地址
        const val ADDRESS_DEFAULT = "membership/modifyDefaultShopAddress.jhtml"

        //收货地址区域选择
        const val ADDRESS_AREA = "baseservice/area/getLowerAddress"

        // 区域选择 --  首页、购物车、详情页
        const val LOCATION_CITY = "baseservice/area/getWarehouseCoverAddressAllCity"

        // 根据市查询仓库id， cityid
        const val ADDRESS_BY_NAME = "baseservice/area/getAddressData"

        // 收货人列表-签约平台有-普通平台无
        const val ADDRESS_PERSON_LIST = "membership/consignee/getConsigneeByCustomerCode.jhtml"
        //查询会员发票信息
        const val QUERY_INVOICEINFO = "membership/invoice/queryInvoiceInfoByMemberId"
        //保存会员发票信息
        const val SAVE_INVOICEINFO = "membership/invoice/saveInvoiceInfo"
        //首页banner（生产环境）
        const val HOME_BANNER = "api/pc/store/operation/search_resource_conf"

        //-退货订单详情页
        const val RETURN_ORDERD_ETAILS = "api/pc/order/refundrecord/queryStoreRefundOrderDetails"
        //-维修订单详情页
        const val REPAIR_ORDERD_ETAILS = "api/pc/order/maintainrecord/queryMaintainOrderDetails"

        //-取消退货
        const val CANCLE_RETURN_ORDERD = "api/pc/order/refundrecord/cancelRefundOrder"
        //-取消维修订单
        const val CANCLE_REPAIR_ORDERD = "api/pc/order/maintainrecord/cancelMaintainOrder"

        //-退货、维修申请列表
        const val T_X_APPLY_LIST = "api/pc/order/maintainorderstore/queryStoreMaintainOrder"
        //退货记录
        const val T_HISTORY_LIST = "api/pc/order/refundrecord/queryStoreRefundOrder"
        //维修记录
        const val X_HISTORY_LIST = "api/pc/order/maintainrecord/queryStoreMaintainOrder"
        //我的订单
        const val MY_ORDERD = "api/pc/order/orderstore/queryStoreOrders"
        //图片上传（搜索）
        const val PICTURE_SEARCH = "sotu_submit"
        //图片搜索数据列表
        const val IMAGE_SEARCH_GOODS_LIST = "search/searchByImg"
        /**
         * 无人仓
         */
        const val SELECT_HOST_POWER = "api/pc/nobodyhouse/selectHousePower"
            //获取地址对应的仓
            const val GET_WAREHORSE_BY_CITY = "baseservice/area/getAddressCorrespondWarehouse"
    }

}