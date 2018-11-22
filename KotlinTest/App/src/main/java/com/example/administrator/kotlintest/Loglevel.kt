package com.example.administrator.kotlintest

enum class Loglevel(val id:Int){
    VERBOSE(1),DEBUG(2),INFO(3),WARN(4),ERROR(5),ASSERT(6);

    override fun toString(): String {
        return "$ordinal,$name"
    }

//    fun getString():String{
//        return "$id,$name"
//    }
}