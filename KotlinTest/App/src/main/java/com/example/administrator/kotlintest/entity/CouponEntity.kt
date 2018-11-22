package com.xfs.fsyuncai.entity

import java.io.Serializable

/**
 * 优惠券实体类
 */
class CouponEntity : Serializable {
    /**
     * couponCode : 12811
     * couponDesc : 浅浅不限
     * couponRealValue : 10.00
     * couponScope : 20
     * couponType : 10
     * couponUseCondition : 0.00
     * couponUseStatus : 0
     * couponValue : 10.00
     * spuSkuBoList : [{"skuId":"2125","spuId":"2"}]
     * useBegtime : 2018-08-21 10:01:10
     * useEndtime : 2018-08-22 23:59:59
     */

    var couponCode: String? = null
    var couponDesc: String? = null
    var couponRealValue: String? = null
    var couponScope: String? = null
    var couponType: Int = 0
    var couponUseCondition: String? = null
    var couponUseStatus: Int? = null
    var couponValue: String? = null
    var useBegtime: String? = null
    var useEndtime: String? = null
    var spuSkuBoList: List<SpuSkuBoListBeanX>? = null

    class SpuSkuBoListBeanX : Serializable {
        /**
         * skuId : 2125
         * spuId : 2
         */

        var skuId: String? = null
        var spuId: String? = null
    }
}