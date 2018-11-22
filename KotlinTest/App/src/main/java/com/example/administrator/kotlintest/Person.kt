package com.example.administrator.kotlintest

abstract class Person {
     open fun work(){

//         ToastUtilKt.showCustomToast("父类的work")

     }


}


class MaNong : Person() {

    override fun work() {
    }
}


class Doctor : Person() {
    override fun work() {
        super.work()
//        ToastUtilKt.showToast("doctor.work")
    }

}

