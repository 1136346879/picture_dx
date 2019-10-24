package com.example.administrator.kotlintest.ui.activity.extactivity

import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.ktx.ext.bean.Person
import com.example.ktx.ext.sharedpreferences.getSpValue
import com.example.ktx.ext.sharedpreferences.putSpValue
import com.example.ktx.ext.toast
import kotlinx.android.synthetic.main.activity_sharedperferences.*
import kotlinx.android.synthetic.main.title_layout.*

/**
 * Created by luyao
 * on 2019/7/8 15:04
 */
class SharedPreferencesActivity : BaseActivity() {
    override fun resLayout(): Int {
        return R.layout.activity_sharedperferences
    }

    override fun init() {
        mToolbar.title = "SharedPreferencesExt"
    }

    override fun logic() {


        putInt.setOnClickListener {
            putSpValue("int", 1)
            toast(getSpValue("int", 0).toString())
        }
        putFloat.setOnClickListener {
            putSpValue("float", 1f)
            toast(getSpValue("float", 0f).toString())
        }
        putBoolean.setOnClickListener {
            putSpValue("boolean", true)
            toast(getSpValue(key = "boolean", default = false).toString())
        }
        putString.setOnClickListener {
            putSpValue("string", "ktx")
            toast(getSpValue("string", "null"))
        }
        putSerializable.setOnClickListener {
            putSpValue("serialize", Person("Man", 3))
            toast(getSpValue("serialize", Person("default", 0)).toString())
        }
        putMulti.setOnClickListener {
            putSpValue("tag1", 1)
            putSpValue("tag2", false)
            putSpValue("tag3", Person("bingxin", 1))
            toast(
                    "${getSpValue("tag1", 0)}\n" +
                            "${getSpValue("tag2", true)}\n" +
                            "${getSpValue("tag3", Person("default", 0))}"
            )
        }

        putAnotherFile.setOnClickListener {
            putSpValue("another","from another sp file",name = "another")
            toast(getSpValue("another","null",name = "another"))
        }
    }

}