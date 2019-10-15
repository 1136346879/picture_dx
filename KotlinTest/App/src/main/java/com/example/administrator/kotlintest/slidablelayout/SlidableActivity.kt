package com.example.administrator.kotlintest.slidablelayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.administrator.kotlintest.R

class SlidableActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slidable)
    }

    fun toDemoForFragment(v: View) {
        startActivity(Intent(this, DemoForFragment::class.java))
    }

    fun toDemoForView(v: View) {
        startActivity(Intent(this, DemoForView::class.java))
    }

    fun toDemoForLoop(v: View) {
        startActivity(Intent(this, DemoForLoop::class.java))
    }

    fun toDemoForAutoSlide(v: View) {
        startActivity(Intent(this, DemoForAutoSlide::class.java))
    }
}
