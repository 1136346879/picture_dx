package com.xfs.fsyuncai.entity

class BannerEntity {

    /**
     * data : [{"created_name":"a","warehouse_code":"1,2,4","image_url":"","created_at":"2018-09-22 15:52:41","color_value":"","title":"app-方盛云采 公测上线","created_by":4,"platform":"30,40","last_modified_name":"a","product_category_id":0,"sequence":1,"warehouse_name":"北京仓/天津仓/郑州仓","category_id":6,"config_location":"","image_path":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/common/20180922155235815352.jpg","resource_id":1088,"started_at":"2018-09-22 15:52","state":21,"keyword":"","floor":0,"end_in":"2018-10-31 15:52","status":"生效"}]
     * errorMessage : Request success!
     * errorCode : 0
     */

    var errorMessage: String? = null
    var errorCode: Int = 0
    var data: List<DataBean>? = null

    class DataBean {
        /**
         * created_name : a
         * warehouse_code : 1,2,4
         * image_url :
         * created_at : 2018-09-22 15:52:41
         * color_value :
         * title : app-方盛云采 公测上线
         * created_by : 4
         * platform : 30,40
         * last_modified_name : a
         * product_category_id : 0
         * sequence : 1
         * warehouse_name : 北京仓/天津仓/郑州仓
         * category_id : 6
         * config_location :
         * image_path : https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/common/20180922155235815352.jpg
         * resource_id : 1088
         * started_at : 2018-09-22 15:52
         * state : 21
         * keyword :
         * floor : 0
         * end_in : 2018-10-31 15:52
         * status : 生效
         */

        var created_name: String? = null
        var warehouse_code: String? = null
        var image_url: String? = null
        var created_at: String? = null
        var color_value: String? = null
        var title: String? = null
        var created_by: Int = 0
        var platform: String? = null
        var last_modified_name: String? = null
        var product_category_id: Int = 0
        var sequence: Int = 0
        var warehouse_name: String? = null
        var category_id: Int = 0
        var config_location: String? = null
        var image_path: String? = null
        var resource_id: Int = 0
        var started_at: String? = null
        var state: Int = 0
        var keyword: String? = null
        var floor: Int = 0
        var end_in: String? = null
        var status: String? = null
    }
}
