package com.xfs.fsyuncai.extend

import java.io.UnsupportedEncodingException


/**
 * Created by kangf on 2018/6/25.
 */
fun String.stringToUnicode(str: String): String? {
    var result: String? = null
    try {
        result = String(str.toByteArray())
    } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
    }
    return result
}

operator fun String.times(int: Int): String {
    val stringBuilder = StringBuilder()
    for (i in 0 until int) {
        stringBuilder.append(this)
    }
    return stringBuilder.toString()

}

///**
// * Base64编码
// */
//fun String.Base64Encode(): String {
//    return "${Base64.encode(this.toByteArray())}"
//}
//
///**
// * Base64解码
// */
//fun String.Base64Decode(): String {
//    return "${String(Base64.decode(this))}"
//}