package com.example.administrator.kotlintest.ui.activity.extactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.ktx.ext.longToast
import com.example.ktx.ext.startKtxActivity
import com.example.ktx.ext.startKtxActivityForResult
import com.example.ktx.ext.toast
import kotlinx.android.synthetic.main.activity_activity_ext.*
import kotlinx.android.synthetic.main.title_layout.*

/**
 * Created by luyao
 * on 2019/7/9 14:29
 */
class ActivityExtActivity : BaseActivity() {
    override fun resLayout(): Int {
        return R.layout.activity_activity_ext
    }

    override fun init() {
        mToolbar.title = "ActivityExt"

    }

    override fun logic() {
        toastBt.setOnClickListener { toast("I am groot.") }
        longToastBt.setOnClickListener { longToast("I am long groot.") }
        startActivity.setOnClickListener {
            startKtxActivity<AnotherActivity>()
        }

        startActivityWithFlag.setOnClickListener {
            startKtxActivity<AnotherActivity>(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        startActivityForResult.setOnClickListener {
            startKtxActivityForResult<AnotherActivity>(requestCode = 1024)
        }

        startActivityWithValue.setOnClickListener {
            startKtxActivity<AnotherActivity>(value = "string" to "single value")
        }

        startActivityWithMultiValue.setOnClickListener {
            startKtxActivity<AnotherActivity>(
                    values = arrayListOf(
                            "int" to 1,
                            "boolean" to true,
                            "string" to "multi value"
                    )
            )
        }

        startActivityWithBundle.setOnClickListener {
            startKtxActivity<AnotherActivity>(
                    extra = Bundle().apply {
                        putInt("int", 2)
                        putBoolean("boolean", true)
                        putString("string", "from bundle")
                    }
            )
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((resultCode == Activity.RESULT_OK) and (requestCode == 1024)) {
            toast("onActivityResult")
        }

    }
}