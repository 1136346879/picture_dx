package com.example.administrator.kotlintest.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.example.administrator.kotlintest.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.xiecheng_layout.*
import kotlinx.coroutines.*

class XieCheng : RxAppCompatActivity(){
    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xiecheng_layout)
        job = Job()
        launchFromGlobalScope()
    }
    private fun launchFromGlobalScope() {
        GlobalScope.launch(Dispatchers.Main+job) {
            val deferred = async(Dispatchers.IO) {
                // network request
                delay(3000)
                "Get it"
            }
            tv1.text = deferred.await()
            Toast.makeText(applicationContext, "GlobalScope", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}