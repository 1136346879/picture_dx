package com.xfs.fsyuncai.bridge.retrofit.service.body

import java.io.Serializable

/**
 *@author : HaoBoy
 *@date : 2018/10/22
 *@description :退货/维修记录 、退货/维修申请  入参
 **/
class SaledJsonBody :Serializable{

    var login_account: String? = null
    var account_type: Int = 0
    var page_num: Int = 0
    var page_size: Int = 0
    /*退货、维修记录使用,搜索关键字(order_id、sku_id、product_name（三种其中一种),*/
    var combined_param: String? = null
    /*退货、维修申请使用,搜索关键字(order_id、sku_id、product_name（三种其中一种),*/
    var search_param: String? = null
    /*退货订单状态*/
    var refund_status: Int = 0
    /*维修订单状态*/
    var maintain_status: Int = 0

    var created_at_begin: String? = null
    var created_at_end: String? = null

}