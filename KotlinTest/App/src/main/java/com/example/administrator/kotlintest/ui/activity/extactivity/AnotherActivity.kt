package com.example.administrator.kotlintest.ui.activity.extactivity

import android.app.Activity
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.ktx.ext.toast
import kotlinx.android.synthetic.main.activity_another.*
import kotlinx.android.synthetic.main.title_layout.*

/**
 * Created by luyao
 * on 2019/7/9 14:32
 */
class AnotherActivity : BaseActivity() {
    override fun resLayout(): Int {
        return R.layout.activity_another
    }

    override fun init() {
        mToolbar.title = "AnotherActivity"

        toast("$int\n$boolean\n$string")
    }

    override fun logic() {
        finish.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    private val int by lazy { intent.getIntExtra("int", 0) }
    private val boolean by lazy { intent.getBooleanExtra("boolean", false) }
    private val string by lazy { intent.getStringExtra("string") }

}