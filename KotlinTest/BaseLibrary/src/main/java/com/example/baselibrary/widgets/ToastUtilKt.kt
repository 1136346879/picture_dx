package com.example.baselibrary.widgets

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.baselibrary.MyApplication
import com.example.baselibrary.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**
 * Created by kangf on 2018/6/16.
 */
object ToastUtilKt : AnkoLogger{

    private var toast: Toast? = null

    private var toastWithIcon: Toast? = null

    fun showToast(msg: String) {
        MyApplication.cxt.toast(msg)
    }

    fun showCustomToast(msg: String): Toast {
        if (toast == null) {
            toast = Toast.makeText(UIUtils.context(), msg, Toast.LENGTH_SHORT)
        } else {

            toast!!.setText(msg)
        }
        toast!!.show()
        toast!!.setGravity(Gravity.CENTER, 0, 0)
        return toast!!
    }

    /**
     * msg 提示信息
     * icon 指定图片资源，默认传0
     * isShowIcon  是否显示图片
     */
    fun showIconToast(msg: String,icon:Int,isShowIcon:Boolean) : Toast {
        if (toastWithIcon == null) {
            toastWithIcon = Toast.makeText(UIUtils.context(), msg, Toast.LENGTH_SHORT)
        } else {
            val view = LayoutInflater.from(UIUtils.context()).inflate(R.layout.toast_with_icon,
                    null,false)
            val tvMessage = view.findViewById<TextView>(R.id.tvMessage)
            tvMessage.text = msg
            val image = view.findViewById<ImageView>(R.id.tIcon)
            if (icon != 0){image.setImageResource(icon)}
            if(isShowIcon){image.visibility = View.VISIBLE}
            else{image.visibility = View.GONE}
            toastWithIcon!!.view = view
        }
        toastWithIcon!!.show()
        toastWithIcon!!.setGravity(Gravity.CENTER, 0, 0)
        toastWithIcon!!.duration = Toast.LENGTH_SHORT

        return toastWithIcon!!

    }

}