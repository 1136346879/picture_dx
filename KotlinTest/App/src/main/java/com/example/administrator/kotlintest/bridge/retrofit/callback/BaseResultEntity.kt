package com.xfs.fsyuncai.bridge.retrofit.callback

/**
 * Created by kangf on 2018/6/13.
 */
data class BaseResultEntity<T>(var errorCode: Int, var msg: String = "错误", var data: T)