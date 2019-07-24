package com.example.administrator.kotlintest.entity

/**
 *@author : HaoBoy
 *@date : 2019/3/20
 *@description :对应仓的实体类
 **/
class WareHouseEntity {

    /**
     * data : {"id":5,"status":0,"warehouse_name":"济南仓","warehouse_type":1,"parent_warehouse_id":0,"warehouse_code":"5","creation_date":"2019-03-12T13:20:12.000+0000","creation_by":123,"last_modified_date":"2019-03-12T13:20:15.000+0000","last_modified_by":0,"warehouse_cover_address":[]}
     * errorMessage : 成功
     * errorCode : 0
     */

    var data: DataBean? = null
    var errorMessage: String? = null
    var errorCode: Int = 0

    class DataBean {
        /**
         * id : 5
         * status : 0
         * warehouse_name : 济南仓
         * warehouse_type : 1
         * parent_warehouse_id : 0
         * warehouse_code : 5
         * creation_date : 2019-03-12T13:20:12.000+0000
         * creation_by : 123
         * last_modified_date : 2019-03-12T13:20:15.000+0000
         * last_modified_by : 0
         * warehouse_cover_address : []
         */

        var id: Int = 0
        var status: Int = 0
        var warehouse_name: String? = null
        var warehouse_type: String? = null
        var parent_warehouse_id: String? = null
        var warehouse_code: String? = null
        var creation_date: String? = null
        var creation_by: String? = null
        var last_modified_date: String? = null
        var last_modified_by: String? = null
        var warehouse_cover_address: List<Any>? = null
    }
}
