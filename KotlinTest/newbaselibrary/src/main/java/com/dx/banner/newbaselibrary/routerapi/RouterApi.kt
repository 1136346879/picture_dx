package com.dx.banner.newbaselibrary.routerapi

/**
 * Created by Kevin on 2019/3/12.
 *
 * 拆分各个模块的路由
 */
class RouterApi {

    /**
     * 主模块（启动页，首页）
     */
    class MainLibrary {

        companion object {

            const val ROUTER_MAIN_URL = "/mainLibrary/main"

            const val ROUTER_MAIN_SELECT_CITY = "/mainLibrary/selectCity"

            const val ROUTER_MAIN_GUIDE_URL = "/mainLibrary/guide"

            const val ROUTER_MAIN_ONLINE_SERVICE = "/mainLibrary/service"
        }
    }

    class  KeboardLibrary{
        companion object {

            const val ROUTER_KEYBOARD_ACTIVITY_URL = "/KeboardLibrary/KeyBroadActivity"
            const val ROUTER_KEYBOARD_FRAGMENT_URL = "/KeboardLibrary/KeyBroadFragment"
        }


    }
    /**
     * 未拆分的模块
     */
    class AppLibrary {

        companion object {

            const val ROUTER_APP_BALANCE = "/app/balance"

            const val ROUTER_APP_SALE_LIST = "/app/saleList"

            const val ROUTER_APP_ADDRESS = "/app/address"

            const val ROUTER_APP_SETTINGS = "/app/settings"
        }
    }

    class PaySdk {
        companion object {

            const val ROUTER_PAY_CHECKSTAND = "/pay/checkStand"

        }
    }

    /**
     * 用户中心
     */
    class UserCenter {
        companion object {
            // 登录
            const val ROUTER_USER_LOGIN_URL = "/user/login"
            // 手机验证码
            const val ROUTER_USER_VERIFY_MOB = "/user/verifyCode"
        }
    }

    /**
     * 购物车
     */
    class ShoppingCart {
        companion object {
            const val ROUTER_SHOPPING_CART_RUL = "/shopping/cart"

        }
    }

    /**
     * 商品模块（商品搜索，商品列表，商品详情）
     */
    class GoodsCenter {

        companion object {

            const val ROUTER_GOODS_SEARCH = "/goods/search"

            const val ROUTER_GOODS_LIST = "/goods/list"

            const val ROUTER_GOODS_PROMOTION = "/goods/promotion"

            const val ROUTER_GOODS_DETAIL = "/goods/detail"
        }
    }

    /**
     * 相机模块（扫一扫，拍照）
     */
    class CameraLibrary {

        companion object {
            const val ROUTER_CAMERA_URL = "/camera/open"
        }
    }

    /**
     * 通用web模块
     */
    class WebLibrary {

        companion object {

            const val ROUTER_WEB_BASE_URL = "/web/weburl"
        }
    }

}