package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/9/20.
 */
/**
 * {"orderId":"18100329915","limitTime":1536026549000,"curentTime":1535955205840,"totalAmount":109.1}
 */
class GoPayEntity {
    var orderId: String = ""
    var limitTime: Long = 0
    var curentTime: Long = 0
    var totalAmount: Double = 0.toDouble()
    var payType: Int = 0
}