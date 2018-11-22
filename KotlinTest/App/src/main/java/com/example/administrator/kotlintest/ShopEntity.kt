package com.example.administrator.kotlintest

import com.example.administrator.kotlintest.ui.base.BaseClass
import com.example.administrator.kotlintest.widget.ToastUtilKt

class ShopEntity : BaseClass() {

lateinit var stud : String
    var h :String? = null
protected  var b= 0
   protected get() {
        return field
    }
    protected  set(value) {
        field = value
//        ToastUtilKt.showCustomToast(h!!.length.toString())


    }

    val dg : String =""


    get() {
        return field
    }
    infix fun on (help:String){

        ToastUtilKt.showCustomToast(help)
    }
}
