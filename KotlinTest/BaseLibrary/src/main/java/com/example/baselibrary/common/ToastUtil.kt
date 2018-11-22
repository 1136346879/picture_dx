package com.example.baselibrary.common

import android.content.Context
import android.widget.Toast

class ToastUtil{


    companion object {
        fun showToast(context: Context, message : String, length : Int = Toast.LENGTH_SHORT){
            Toast.makeText(context,message,length).show()

        }
    }
}