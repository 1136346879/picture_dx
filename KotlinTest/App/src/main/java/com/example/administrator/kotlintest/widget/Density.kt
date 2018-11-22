package com.example.administrator.kotlintest.widget

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.util.DisplayMetrics

/**
 * Created by kangf on 2018/8/29.
 */
object Density {
    private var appDensity: Float = 0.toFloat()
    private var appScaledDensity: Float = 0.toFloat()
    private var appDisplayMetrics: DisplayMetrics? = null
    private var barHeight: Int = 0

    val WIDTH = "width"

    val HEIGHT = "height"
@JvmOverloads
    fun setDensity(application: Application) {
        //获取application的DisplayMetrics
        appDisplayMetrics = application.resources.displayMetrics
        //获取状态栏高度
        barHeight = UIUtils.getStatusBarHeight(application)
        if (appDensity == 0f) {
            //初始化的时候赋值
            appDensity = appDisplayMetrics!!.density
            appScaledDensity = appDisplayMetrics!!.scaledDensity
            //添加字体变化的监听
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration?) {
                    //字体改变后,将appScaledDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaledDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {}
            })
        }
    }

    //此方法在BaseActivity中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好了)
    fun setDefault(activity: Activity) {
        setAppOrientation(activity, WIDTH)
    }

    //此方法用于在某一个Activity里面更改适配的方向
    fun setOrientation(activity: Activity, orientation: String) {
        setAppOrientation(activity, orientation)
    }

    /**
     * targetDensity
     * targetScaledDensity
     * targetDensityDpi
     * 这三个参数是统一修改过后的值
     *
     *
     * orientation:方向值,传入width或height
     */
    private fun setAppOrientation(activity: Activity?, orientation: String) {
        val targetDensity: Float
        if (orientation == "height") {
            targetDensity = (appDisplayMetrics!!.heightPixels - barHeight) / 667f
        } else {
            targetDensity = appDisplayMetrics!!.widthPixels / 360f
        }
        val targetScaledDensity = targetDensity * (appScaledDensity / appDensity)
        val targetDensityDpi = (160 * targetDensity).toInt()
        /** * * 最后在这里将修改过后的值赋给系统参数 * * 只修改Activity的density值  */
        val activityDisplayMetrics = activity!!.resources.displayMetrics
        activityDisplayMetrics.density = targetDensity
        activityDisplayMetrics.scaledDensity = targetScaledDensity
        activityDisplayMetrics.densityDpi = targetDensityDpi
    }
}
