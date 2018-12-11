package com.example.baselibrary.ui.activity

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseUIActivity : RxAppCompatActivity() {

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
