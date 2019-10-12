package com.example.usercenter.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baselibrary.widgets.ToastUtilKt
import com.example.usercenter.R
import com.example.usercenter.ui.activity.simplecache.AcacheActivity
import kotlinx.android.synthetic.main.activity_main_usercenter.*
import org.jetbrains.anko.intentFor

class UserCenterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_usercenter)
        user_center.setOnClickListener {
//            val intent = Intent()
//            intent.setClass(this@UserCenterActivity,AcacheActivity::class.java)
//            startActivity(intent)
            startActivity(this.intentFor<AcacheActivity>())
        }
    }
}
