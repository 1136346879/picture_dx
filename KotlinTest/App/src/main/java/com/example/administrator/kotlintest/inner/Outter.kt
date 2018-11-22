package com.example.administrator.kotlintest.inner

import android.view.View

class Outter{

     var a:Int = 0

    //默认静态内部类（只是逻辑上）
     class Inner1{

        var b = Outter().a

    }

    //非静态内部类(内部类必须依赖于外部类存在)
     inner class Inner2{
        var a : Int = 4

        var b =this@Outter.a
        var c =a
    }
}
public class View {
    var onClickListener :onClickListener? = null
}

interface  onClickListener{
    fun onClicks()
}