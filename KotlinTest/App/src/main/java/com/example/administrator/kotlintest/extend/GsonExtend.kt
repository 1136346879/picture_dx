package com.xfs.fsyuncai.extend

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by kangf on 2018/8/28.
 */
inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, object : TypeToken<T>() {}.type)
}