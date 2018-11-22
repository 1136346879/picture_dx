package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/9/20.
 */

data class IsSetPayPwdEntity(
    val isSet: String,
    val errorMessage: String,
    val errorCode: Int,
    val token: String,
    val memberId: String
)