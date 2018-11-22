package com.xfs.fsyuncai.bridge.retrofit.service.body

/**
 * Created by kangf on 2018/9/18.
 */
data class OrderNumBody(
        var member_id: String,
        var login_account: String = "",
        var enable_audit: Int?,
        var enable_order: Int?,
        var enable_settle: Int?
)