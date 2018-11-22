package com.example.baselibrary.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayout())

        initView()

        initData()
    }

    abstract fun initLayout(): Int

    abstract fun initView()

    abstract fun initData()


}
