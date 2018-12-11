package com.example.administrator.kotlintest.intface

import com.example.baselibrary.widgets.ToastUtilKt

interface InputDevice{

    fun input(event:Any)

}



interface UsbInputDevice : InputDevice


interface BloothInputDevice :InputDevice

open class UsbMouse(private val name:String):UsbInputDevice,OpticalMouse{
    override fun input(event: Any) {
    }

    override fun toString(): String {
        return name
    }
}
class LuojIUsbMouse : UsbMouse("清华同方"){


}
interface OpticalMouse

class Computer{


    fun addUsbInputDevice(inputDevice: UsbInputDevice){

        ToastUtilKt.showCustomToast("插入USB设配是：${inputDevice}")


    }


    fun addBloothInoutDevice(inputDevice: BloothInputDevice){
        ToastUtilKt.showCustomToast("连接蓝牙设配是：$inputDevice")
    }

    fun addInputDevice(inputDevice: InputDevice){
        ToastUtilKt.showCustomToast("插入和连接设配是：$inputDevice")

        when(inputDevice){
            is BloothInputDevice-> addBloothInoutDevice(inputDevice)
            is UsbInputDevice -> addUsbInputDevice(inputDevice)
            else -> ToastUtilKt.showToast("连接其他类型的设备")
        }
    }

}