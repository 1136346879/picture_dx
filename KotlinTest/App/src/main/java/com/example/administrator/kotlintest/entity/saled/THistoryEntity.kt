package com.xfs.fsyuncai.entity.saled

/**
 *@author : HaoBoy
 *@date : 2018/10/22
 *@description :退货记录返回实体类
 **/
class THistoryEntity {
    /**
     * totalPage : 1
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 2
     * startIndex : 0
     * result : [{"refund_id":510021777,"order_id":"18101628537","refund_status":50,"created_at":"2018-10-20 17:17:29","total_refund_count":2,"listOrderRefundItems":[{"id":2834,"refund_id":510021777,"order_id":"18101628537","member_id":93,"shop_id":1,"warehouse_id":2,"spu_id":1979,"sku_id":641,"sku_info":"4kg","product_code":"06.12.01.036812","product_name":"绿美家LvMeiJia 快粘粉","product_pic":"http://192.168.0.107/images/036812a.jpg","color":"蓝色","unit_name":"袋","refund_count":1,"final_refund_count":1,"coupon_value":0,"shipping_fee":27.5,"return_service_fee":1,"return_shipping_fee":0,"goods_total_price":5.8,"created_at":"2018-10-20 17:17:29"}],"authorityList":[]}]
     */

    var totalPage: Int = 0
    var pageSize: Int = 0
    var pageNumber: Int = 0
    var totalRecord: Int = 0
    var startIndex: Int = 0
    var result: List<ResultBean>? = null

    class ResultBean {
        /**
         * refund_id : 510021777
         * order_id : 18101628537
         * refund_status : 50
         * created_at : 2018-10-20 17:17:29
         * total_refund_count : 2
         * listOrderRefundItems : [{"id":2834,"refund_id":510021777,"order_id":"18101628537","member_id":93,"shop_id":1,"warehouse_id":2,"spu_id":1979,"sku_id":641,"sku_info":"4kg","product_code":"06.12.01.036812","product_name":"绿美家LvMeiJia 快粘粉","product_pic":"http://192.168.0.107/images/036812a.jpg","color":"蓝色","unit_name":"袋","refund_count":1,"final_refund_count":1,"coupon_value":0,"shipping_fee":27.5,"return_service_fee":1,"return_shipping_fee":0,"goods_total_price":5.8,"created_at":"2018-10-20 17:17:29"}]
         * authorityList : []
         */

        var refund_id: Int = 0
        var order_id: String? = null
        var refund_status: Int = 0
        var created_at: String? = null
        var total_refund_count: Int = 0
        var listOrderRefundItems: List<ListOrderRefundItemsBean>? = null
        var authorityList: List<*>? = null

        class ListOrderRefundItemsBean {
            /**
             * id : 2834
             * refund_id : 510021777
             * order_id : 18101628537
             * member_id : 93
             * shop_id : 1
             * warehouse_id : 2
             * spu_id : 1979
             * sku_id : 641
             * sku_info : 4kg
             * product_code : 06.12.01.036812
             * product_name : 绿美家LvMeiJia 快粘粉
             * product_pic : http://192.168.0.107/images/036812a.jpg
             * color : 蓝色
             * unit_name : 袋
             * refund_count : 1
             * final_refund_count : 1
             * coupon_value : 0
             * shipping_fee : 27.5
             * return_service_fee : 1
             * return_shipping_fee : 0
             * goods_total_price : 5.8
             * created_at : 2018-10-20 17:17:29
             */

            var id: Int = 0
            var refund_id: Int = 0
            var order_id: String? = null
            var member_id: Int = 0
            var shop_id: Int = 0
            var warehouse_id: Int = 0
            var spu_id: Int = 0
            var sku_id: Int = 0
            var sku_info: String? = null
            var product_code: String? = null
            var product_name: String? = null
            var product_pic: String? = null
            var color: String? = null
            var unit_name: String? = null
            var refund_count: Int = 0
            var final_refund_count: Int = 0
            var coupon_value: Double = 0.0
            var shipping_fee: Double = 0.toDouble()
            var return_service_fee: Int = 0
            var return_shipping_fee: Double = 0.0
            var goods_total_price: Double = 0.toDouble()
            var created_at: String? = null
        }
    }
}