package com.example.administrator.kotlintest.ui.activity

import android.os.Handler
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.ktx.core.lifecycle.KtxHandler
import com.example.ktx.core.lifecycle.KtxManager
import com.example.ktx.ext.loge
import com.example.ktx.ext.toast
import kotlinx.android.synthetic.main.activity_lifecycle.*

/**
 * Created by luyao
 * on 2019/8/6 9:43
 */
class LifeCycleActivity : BaseActivity() {
    override fun resLayout(): Int {
        return R.layout.activity_lifecycle
    }

    override fun init() {
        myHandler.sendEmptyMessageDelayed(10, 5 * 1000)

    }

    override fun logic() {
        currentActivityBt.setOnClickListener { KtxManager.currentActivity?.let { toast(it.localClassName) } }
        finishMainBt.setOnClickListener { KtxManager.finishActivity(MainActivity::class.java) }
        finishAllBt.setOnClickListener { KtxManager.finishAllActivity() }
    }


    private val myHandler = KtxHandler(this, Handler.Callback {
        "receive from handler : ${it.what}".loge()
        true
    })

}