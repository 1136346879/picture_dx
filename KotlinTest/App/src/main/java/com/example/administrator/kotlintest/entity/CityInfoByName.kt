package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/8/28.
 */
class CityInfoByName {

    /**
     * data : {"code":"1","name":"北京","level":101,"available":100,"address_id":1,"area_id":100,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"1","child_address_library":[]}
     * errorMessage : 成功
     * errorCode : 0
     */
    var data: DataBean? = null
    var errorMessage: String? = null
    var errorCode: Int = 0

    class DataBean {
        /**
         * code : 1
         * name : 北京
         * level : 101
         * available : 100
         * address_id : 1
         * area_id : 100
         * zip_code :
         * parent_code :
         * seq_no : 0
         * created_at : 2018-07-27T12:36:05.000+0000
         * upate_time : 2018-07-27T12:36:05.000+0000
         * limit_line : 20
         * warehouse_code : 1
         * child_address_library : []
         */

        var code: String? = null
        var name: String? = null
        var level: Int = 0
        var available: Int = 0
        var address_id: Int = 0
        var area_id: Int = 0
        var zip_code: String? = null
        var parent_code: String? = null
        var seq_no: Int = 0
        var created_at: String? = null
        var upate_time: String? = null
        var limit_line: Int = 0
        var warehouse_code: String? = null
        var child_address_library: List<*>? = null
    }
}
