package com.xfs.fsyuncai.art.base.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.example.administrator.kotlintest.util.SoftInputUtil
import com.example.baselibrary.AppManager
import com.example.baselibrary.widgets.Density

/**
 * Created by kangf on 2018/6/14.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        base()
        super.onCreate(savedInstanceState)
        startTime = System.currentTimeMillis()
        setContentView(resLayout())
        getSavedInstance(savedInstanceState)
        init()
        logic()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    abstract fun resLayout(): Int

    abstract fun init()

    abstract fun logic()

    open fun getSavedInstance(savedInstanceState: Bundle?) {

    }

    open fun base() {
        AppManager.instance().addActivity(this)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        try {
            if (isTranslucentOrFloating && Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
                return
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } catch (e: Exception) {
            //
        }
        Density.setDefault(this)
    }

    /**
     * 点击软键盘之外的空白处，隐藏软件盘
     *
     * @param ev
     * @return
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (SoftInputUtil.isShouldHideSoftKeyBoard(v, ev)) {

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }


    override fun onDestroy() {
        AppManager.instance().removeActivity(this)
        super.onDestroy()
    }

    private val isTranslucentOrFloating: Boolean
        @SuppressLint("PrivateApi")
        get() {
            var isTranslucentOrFloating = false
            try {
                val styleableRes = Class.forName("com.android.internal.R\$styleable").getField("Window").get(null) as IntArray
                val ta = obtainStyledAttributes(styleableRes)
                val m = ActivityInfo::class.java.getMethod("isTranslucentOrFloating", TypedArray::class.java)
                m.isAccessible = true
                isTranslucentOrFloating = m.invoke(null, ta) as Boolean
                m.isAccessible = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return isTranslucentOrFloating
        }
}