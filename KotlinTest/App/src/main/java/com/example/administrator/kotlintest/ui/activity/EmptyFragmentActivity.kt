package com.example.administrator.kotlintest.ui.activity

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.administrator.kotlintest.R
import com.example.baselibrary.ui.activity.BaseUIActivity

class EmptyFragmentActivity : BaseUIActivity(){
    lateinit var keyBoardFragment : androidx.fragment.app.Fragment
    override fun initLayout(): Int {

        return R.layout.activity_empty_fragment_activity
    }

    override fun initView() {

      keyBoardFragment = ARouter.getInstance().build(RouterApi.KeboardLibrary.ROUTER_KEYBOARD_FRAGMENT_URL).navigation() as androidx.fragment.app.Fragment
    }

    override fun initData() {

        supportFragmentManager.beginTransaction().add(R.id.container,keyBoardFragment,"KeyBoardFragment").commit()
    }

}