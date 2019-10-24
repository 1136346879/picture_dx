package com.example.administrator.kotlintest.ui.activity.extactivity

import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.ktx.ext.copyToClipboard
import com.example.ktx.ext.screenHeight
import com.example.ktx.ext.screenWidth
import com.example.ktx.ext.toast
import kotlinx.android.synthetic.main.activity_common_ext.*
import kotlinx.android.synthetic.main.title_layout.*

/**
 * Created by luyao
 * on 2019/7/17 15:26
 */
class CommonExtActivity : BaseActivity() {
    override fun resLayout(): Int {
        return R.layout.activity_common_ext
    }

    override fun init() {
        mToolbar.title = "CommonExt"
    }

    override fun logic() {
        screenWidthBt.text = "screen width : $screenWidth"
        screenHeightBt.text = "screen height : $screenHeight"
        copyBt.setOnClickListener {
            val content = copyEt.text.toString()
            if (content.isNotEmpty()) {
                copyToClipboard(content)
            } else {
                toast("please input some words")
            }
        }

    }


}