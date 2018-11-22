package com.xfs.fsyuncai.entity.saled

/**
 *@author : HaoBoy
 *@date : 2018/10/22
 *@description :退货维修申请实体类
 **/
class TXApplyEntity {

    /**
     * totalPage : 1
     * pageSize : 10
     * pageNumber : 1
     * totalRecord : 1
     * startIndex : 0
     * result : [{"page_num":0,"page_size":0,"start_index":0,"role_name":null,"warehouse_id":0,"settled_amount":null,"created_at":"2018-10-19 20:59:01","created_at_begin":null,"created_at_end":null,"order_status":0,"paid_type":0,"final_paid_type":null,"member_id":0,"customer_name":null,"sales_manager_name":null,"final_total_amount":null,"paid_amount":null,"receiver_province":null,"receiver_city":null,"source":0,"login_account":"jiruru002","account_type":0,"month_query":0,"total_account":null,"sub_account":null,"audit_account":null,"listOrderItems":[{"id":25274,"order_id":"18101628537","member_id":93,"customer_name":"(Z48)0821688北京世纪盛祥五金交电有限公司","merchant_id":0,"shop_id":1,"warehouse_id":2,"spu_id":1979,"sku_id":641,"cost_price":4.75,"original_price":5.8,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":5.8,"sale_price":5.8,"sku_code":"91001979001","sku_weight":0.025,"color":"蓝色","product_name":"绿美家LvMeiJia 快粘粉","sku_info":"4kg","product_pic":"http://192.168.0.107/images/036812a.jpg","buyyer_count":100,"delivery_count":100,"receive_count":100,"coupon_value":0,"fare":2750,"unit_name":"袋","product_code":"06.12.01.036812","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":100,"arrival_cycle":3,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}],"listOrderVerifyRecord":null,"order_push":0,"operated_at":null,"operated_at_begin":null,"operated_at_end":null,"received_name":null,"received_phone":null,"created_address_code":null,"paid_at":null,"paid_at_begin":null,"paid_at_end":null,"search_param":null,"order_id":"18101628537","product_name":null,"sku_code":null,"anomal_type":null,"customer_verify":0,"customer_verify_status":0,"flag":0,"order_total_count":100,"orderIds":null,"operate_account_type":0,"operate_login_account":null,"current_time":0,"limit_time":0,"is_show":true,"authorityList":[],"order_delivername":null,"order_deliverphone":null,"order_drivername":null,"order_driverphone":null}]
     */

    var totalPage: Int = 0
    var pageSize: Int = 0
    var pageNumber: Int = 0
    var totalRecord: Int = 0
    var startIndex: Int = 0
    var result: List<ResultBean>? = null

    class ResultBean {
        /**
         * page_num : 0
         * page_size : 0
         * start_index : 0
         * role_name : null
         * warehouse_id : 0
         * settled_amount : null
         * created_at : 2018-10-19 20:59:01
         * created_at_begin : null
         * created_at_end : null
         * order_status : 0
         * paid_type : 0
         * final_paid_type : null
         * member_id : 0
         * customer_name : null
         * sales_manager_name : null
         * final_total_amount : null
         * paid_amount : null
         * receiver_province : null
         * receiver_city : null
         * source : 0
         * login_account : jiruru002
         * account_type : 0
         * month_query : 0
         * total_account : null
         * sub_account : null
         * audit_account : null
         * listOrderItems : [{"id":25274,"order_id":"18101628537","member_id":93,"customer_name":"(Z48)0821688北京世纪盛祥五金交电有限公司","merchant_id":0,"shop_id":1,"warehouse_id":2,"spu_id":1979,"sku_id":641,"cost_price":4.75,"original_price":5.8,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":5.8,"sale_price":5.8,"sku_code":"91001979001","sku_weight":0.025,"color":"蓝色","product_name":"绿美家LvMeiJia 快粘粉","sku_info":"4kg","product_pic":"http://192.168.0.107/images/036812a.jpg","buyyer_count":100,"delivery_count":100,"receive_count":100,"coupon_value":0,"fare":2750,"unit_name":"袋","product_code":"06.12.01.036812","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":100,"arrival_cycle":3,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}]
         * listOrderVerifyRecord : null
         * order_push : 0
         * operated_at : null
         * operated_at_begin : null
         * operated_at_end : null
         * received_name : null
         * received_phone : null
         * created_address_code : null
         * paid_at : null
         * paid_at_begin : null
         * paid_at_end : null
         * search_param : null
         * order_id : 18101628537
         * product_name : null
         * sku_code : null
         * anomal_type : null
         * customer_verify : 0
         * customer_verify_status : 0
         * flag : 0
         * order_total_count : 100
         * orderIds : null
         * operate_account_type : 0
         * operate_login_account : null
         * current_time : 0
         * limit_time : 0
         * is_show : true
         * authorityList : []
         * order_delivername : null
         * order_deliverphone : null
         * order_drivername : null
         * order_driverphone : null
         */

        var page_num: Int = 0
        var page_size: Int = 0
        var start_index: Int = 0
        var role_name: Any? = null
        var warehouse_id: Int = 0
        var settled_amount: Any? = null
        var created_at: String? = null
        var created_at_begin: Any? = null
        var created_at_end: Any? = null
        var order_status: Int = 0
        var paid_type: Int = 0
        var final_paid_type: Any? = null
        var member_id: Int = 0
        var customer_name: Any? = null
        var sales_manager_name: Any? = null
        var final_total_amount: Any? = null
        var paid_amount: Any? = null
        var receiver_province: Any? = null
        var receiver_city: Any? = null
        var source: Int = 0
        var login_account: String? = null
        var account_type: Int = 0
        var month_query: Int = 0
        var total_account: Any? = null
        var sub_account: Any? = null
        var audit_account: Any? = null
        var listOrderVerifyRecord: Any? = null
        var order_push: Int = 0
        var operated_at: Any? = null
        var operated_at_begin: Any? = null
        var operated_at_end: Any? = null
        var received_name: Any? = null
        var received_phone: Any? = null
        var created_address_code: Any? = null
        var paid_at: Any? = null
        var paid_at_begin: Any? = null
        var paid_at_end: Any? = null
        var search_param: Any? = null
        var order_id: String? = null
        var product_name: Any? = null
        var sku_code: Any? = null
        var anomal_type: Any? = null
        var customer_verify: Int = 0
        var customer_verify_status: Int = 0
        var flag: Int = 0
        var order_total_count: Int = 0
        var orderIds: Any? = null
        var operate_account_type: Int = 0
        var operate_login_account: Any? = null
        var current_time: Int = 0
        var limit_time: Int = 0
        var isIs_show: Boolean = false
        var order_delivername: Any? = null
        var order_deliverphone: Any? = null
        var order_drivername: Any? = null
        var order_driverphone: Any? = null
        var listOrderItems: List<ListOrderItemsBean>? = null
        var authorityList: List<*>? = null

        class ListOrderItemsBean {
            /**
             * id : 25274
             * order_id : 18101628537
             * member_id : 93
             * customer_name : (Z48)0821688北京世纪盛祥五金交电有限公司
             * merchant_id : 0
             * shop_id : 1
             * warehouse_id : 2
             * spu_id : 1979
             * sku_id : 641
             * cost_price : 4.75
             * original_price : 5.8
             * plat_discount_rate : 1
             * category_discount_rate : 1
             * init_sale_price : 5.8
             * sale_price : 5.8
             * sku_code : 91001979001
             * sku_weight : 0.025
             * color : 蓝色
             * product_name : 绿美家LvMeiJia 快粘粉
             * sku_info : 4kg
             * product_pic : http://192.168.0.107/images/036812a.jpg
             * buyyer_count : 100
             * delivery_count : 100
             * receive_count : 100
             * coupon_value : 0
             * fare : 1.354
             * unit_name : 袋
             * product_code : 06.12.01.036812
             * have_returned_count : 0
             * can_returned_count : 0
             * maintaining_count : 0
             * can_maintain_count : 0
             * occupy_store : 0
             * occupy_virtual_store : 100
             * arrival_cycle : 3
             * sku_long : 10
             * sku_width : 0.025
             * sku_height : 2
             * sku_volume : 15
             */

            var id: Int = 0
            var order_id: String? = null
            var member_id: Int = 0
            var customer_name: String? = null
            var merchant_id: Int = 0
            var shop_id: Int = 0
            var warehouse_id: Int = 0
            var spu_id: Int = 0
            var sku_id: Int = 0
            var cost_price: Double = 0.toDouble()
            var original_price: Double = 0.toDouble()
            var plat_discount_rate: Int = 0
            var category_discount_rate: Int = 0
            var init_sale_price: Double = 0.toDouble()
            var sale_price: Double = 0.toDouble()
            var sku_code: String? = null
            var sku_weight: Double = 0.toDouble()
            var color: String? = null
            var product_name: String? = null
            var sku_info: String? = null
            var product_pic: String? = null
            var buyyer_count: Int = 0
            var delivery_count: Int = 0
            var receive_count: Int = 0
            var coupon_value: Double = 0.0
            var fare: Double = 0.0
            var unit_name: String? = null
            var product_code: String? = null
            var have_returned_count: Int = 0
            var can_returned_count: Int = 0
            var maintaining_count: Int = 0
            var can_maintain_count: Int = 0
            var occupy_store: Int = 0
            var occupy_virtual_store: Int = 0
            var arrival_cycle: Int = 0
            var sku_long: Int = 0
            var sku_width: Double = 0.0
            var sku_height: Double = 0.0
            var sku_volume: Double = 0.0
        }
    }
}