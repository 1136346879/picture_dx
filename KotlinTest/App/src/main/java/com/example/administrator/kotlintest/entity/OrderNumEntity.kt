package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/9/18.
 */



data class OrderNumEntity(
    val After_picking: Int,
    val To_be_audited: Int,
    val Pending_delivery: Int,
    val Goods_to_be_received: Int,
    val Transaction_completion: Int,
    val Settlement_to_be_settled: Int,
    val Successful_trade: Int,
    val Transaction_closure: Int,
    val Order_cancellation: Int,
    val Pending_payment: Int
)