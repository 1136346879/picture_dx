package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/11/8.
 */
data class WRCEntity(
        var msg: String? = null,
        var errorCode: Int? = null,
        var sub_code: String? = null,
        var sub_msg: String? = null,
        var data: String? = null)