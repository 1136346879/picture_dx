package com.example.administrator.kotlintest.activity

import android.util.DisplayMetrics
import android.view.Gravity
import com.example.administrator.kotlintest.R

/**
 * pop式Activity
 */
abstract class BasePopActivity : BaseActivity() {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()    // 设置本Activity在父窗口的位置
        val windowManager = windowManager
        val display = windowManager.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)// 获取屏幕的宽和高
        val p = window.attributes // 获取对话框当前的参数值
        p.width = metrics.widthPixels // 宽度设置为屏幕的1
        window.attributes = p // 设置生效
        window.setGravity(Gravity.BOTTOM)// 设置靠底部对齐
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.bottom_dialog_enter, R.anim.bottom_dialog_exit)
    }
}