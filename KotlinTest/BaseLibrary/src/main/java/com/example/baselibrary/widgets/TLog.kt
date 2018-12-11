package com.example.baselibrary.widgets

import android.util.Log
import org.jetbrains.anko.AnkoLogger

/**
 * Created by kangf on 2018/6/15.
 */
object TLog : AnkoLogger {

    //是否打印log
    private val isOpenDebug = false

    private val debugInfo = true
    private val debugError = true


    fun i(msg: String) {
        if (isOpenDebug && debugInfo) {
            Log.i("TLog", msg)
        }

    }

    fun i(msg: String, count: Int) {
        if (isOpenDebug && debugInfo) {
            if (msg.length > count) {
                val show = msg.substring(0, count)
                i(show)
                if ((msg.length - count) > count) {
                    val partLog = msg.substring(count, msg.length)
                    i(partLog, count)
                } else {
                    val surplusLog = msg.substring(count, msg.length)
                    i(surplusLog)
                }
            } else {
                i(msg)
            }
        }

    }

    fun e(msg: String) {
        if (isOpenDebug && debugError)
            error(msg)
    }


}