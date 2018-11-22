package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/8/28.
 */
class AddCartEntity {


    /**
     * errorMessage : 成功添加购物车
     * errorCode : 0
     * baseResponse : {"msg":"SUCCESS","errorCode":0,"sub_code":"0","sub_msg":"成功添加购物车","data":null}
     * token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MzU0NTczMTcsInN1YiI6IntcImxvZ2luQWNjb3VudFwiOlwia2FuZ2ZhbjEyMzRcIixcImxvZ2luRmxhZ1wiOnRydWUsXCJtZW1iZXJJZFwiOlwiMTA4XCJ9IiwiZXhwIjoxNTM1NDYwOTE3fQ.Lb8tmNhKfTjRKcuWI1szu_LM-ZYpaSLHfiuGpUL12O8
     * memberId : 108
     */

    var errorMessage: String? = null
    var errorCode: String? = null
    var baseResponse: BaseResponseBean? = null
    var token: String? = null
    var memberId: String? = null

    class BaseResponseBean {
        /**
         * msg : SUCCESS
         * errorCode : 0
         * sub_code : 0
         * sub_msg : 成功添加购物车
         * data : null
         */

        var msg: String? = null
        var errorCode: Int = 0
        var sub_code: String? = null
        var sub_msg: String? = null
        var data: Any? = null
    }
}
