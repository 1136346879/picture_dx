package com.example.administrator.kotlintest.ui.entity

import com.example.administrator.kotlintest.annotations.PoKo
import com.example.administrator.kotlintest.widget.ToastUtilKt


@PoKo
 data class 学生 (var 姓:String,var age : Int=0){
//    override fun toString(): String {
//        return "${姓}学生"
//    }

    fun play(jump:String){

        ToastUtilKt.showCustomToast(jump)
    }
     //中缀表达式
    infix fun on (help:String){

        ToastUtilKt.showCustomToast(help)
    }
}