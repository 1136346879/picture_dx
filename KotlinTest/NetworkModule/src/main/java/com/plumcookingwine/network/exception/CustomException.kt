package com.plumcookingwine.network.exception

import java.lang.RuntimeException

/***
 * 自定义异常类型
 */
class CustomException(val code: Int = 0, message: String = "") : RuntimeException(message)