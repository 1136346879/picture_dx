package com.example.administrator.kotlintest.ui.activity

import android.app.Activity
import android.os.Bundle
import java.util.*


class OverLoader{

//    fun getOverLoaderMethod(){
//
//    }
    @JvmOverloads
    fun getOverLoaderMethod(a:Int =0):Int{
        return a
    }
//    fun getOverLoaderMethod(aOfInt: String):Int{
//        return aOfInt.length
//    }

//    fun getOverLoaderMethod(string:String):String{
//        return string
//    }



    fun methd():Int{
      return  getOverLoaderMethod(5)
    }
}

