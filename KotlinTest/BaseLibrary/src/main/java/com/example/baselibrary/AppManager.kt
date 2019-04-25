package com.example.baselibrary

import android.app.Activity
import java.util.*

/**
 * Created by kangf on 2018/6/14.
 */
class AppManager {

    private var activityStack: Stack<Activity>? = Stack()

    companion object {
        fun instance() = Holder.instance

    }

    private object Holder {
        val instance = AppManager()
    }

    fun getActivityNumber(): Int {
        return activityStack!!.size
    }


    /* 获取当前activity */
//    fun getCurrentActivity(): Activity {
//        return activityStack!!.lastElement()
//    }

    /* 结束某个activity */
    fun finishActivity(activity: Activity?) {
        if (activity != null) {
            activity.finish()
            removeActivity(activity)
        }
    }

    /* 结束某个activity */
    fun finishActivity(cls: Class<*>) {

        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /* 结束所有activity */
    fun finishAllActivity() {
        for (i in activityStack!!.indices) {
            activityStack!![i].finish()
        }
        activityStack!!.clear()
    }

    /* 结束除某个activity之外的所有activity */
//    fun finishAllActivity(activity: Activity) {
//        for (i in activityStack!!.indices) {
//            if (activityStack!![i].javaClass == activity.javaClass) {
//                return
//            }
//            activityStack!![i].finish()
//            activityStack!!.removeAt(i)
//        }
//    }

    /* 将activity加入栈中 */
    fun addActivity(activity: Activity) {
        activityStack!!.add(activity)
    }

    /* 将activity从栈中删除 */
    fun removeActivity(activity: Activity) {
        activityStack!!.remove(activity)
    }

    fun hasActivity(cls: Class<*>) :Boolean {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                return true
            }
        }
        return false
    }

    /* 0为正常退出，1为异常退出 */
    fun exitApp(status: Int) {
        try {
            finishAllActivity()
            System.exit(status)
        } catch (e: RuntimeException) {
        }

    }
}