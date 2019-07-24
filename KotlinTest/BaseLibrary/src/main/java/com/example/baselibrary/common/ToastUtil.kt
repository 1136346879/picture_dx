package com.example.baselibrary.common

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.example.baselibrary.widgets.UIUtils

object ToastUtil{

    private var toast: Toast? = null
        @SuppressLint("ShowToast")
        fun showCustomToast(msg: String): Toast {
            if (toast == null) {
                toast = Toast.makeText(UIUtils.context(), null, Toast.LENGTH_SHORT)
            }
            toast!!.setText(msg)
            toast!!.show()
            toast!!.setGravity(Gravity.CENTER, 0, 0)
            return toast!!
        }
}