package com.example.baselibrary.ui

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.example.baselibrary.R
import com.example.baselibrary.widgets.Density
import com.example.baselibrary.widgets.TLog


/**
 * Created by kangf on 2018/6/14.
 */
abstract class BaseRecycleviewActivity : RxAppCompatActivity() {
    private val className = this.javaClass.simpleName
    private var startTime: Long = 0
    private val START_TIME_NUM = 16

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
        if(startTime > 0 ){
            startTime = System.currentTimeMillis() - startTime
            if ( startTime > START_TIME_NUM) {
                TLog.i("UI WARN "+ className.toString() + resources.getString(R.string.ui_elapsed_time_warm) + startTime)
            } else {
                TLog.i("UI WARN "+ className.toString() + resources.getString(R.string.ui_elapsed_time_like) + startTime)
            }
            startTime = 0
        }
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
//        AppManager.instance().addActivity(this)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Density.setDefault(this)
    }

    /**
     * 点击软键盘之外的空白处，隐藏软件盘
     *
     * @param ev
     * @return
     */
//    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//        if (ev.action == MotionEvent.ACTION_DOWN) {
//            val v = currentFocus
//            if (SoftInputUtil.isShouldHideSoftKeyBoard(v, ev)) {
//
//                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                if (imm != null) {
//                    imm!!.hideSoftInputFromWindow(v!!.windowToken, 0)
//                }
//            }
//            return super.dispatchTouchEvent(ev)
//        }
//        // 必不可少，否则所有的组件都不会有TouchEvent了
//        return if (window.superDispatchTouchEvent(ev)) {
//            true
//        } else onTouchEvent(ev)
//    }


    override fun onDestroy() {
//        AppManager.instance().removeActivity(this)
        super.onDestroy()
    }
}