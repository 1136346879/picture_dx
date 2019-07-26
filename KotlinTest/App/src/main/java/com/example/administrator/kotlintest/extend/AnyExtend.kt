package com.xfs.fsyuncai.extend

import android.content.Context
import android.content.Intent
import com.xfs.fsyuncai.entity.accont.AccessManager
import org.jetbrains.anko.intentFor

/**
 * Created by kangf on 2018/7/3.
 */

/**
 * 获取对象中所有的key
 */
fun Any.getFiledName(): List<String> {
    val fields = this.javaClass.declaredFields
    val fieldNames = arrayListOf<String>()
    return fieldNames.apply {
        fields.map {
            this.add(it.name)
        }
    }
}

/**
 * 对象中根据key获取值
 */
fun Any.getFieldValueByName(fieldName: String): Any? {
    try {
        val firstLetter = fieldName.substring(0, 1).toUpperCase()
        val getter = "get" + firstLetter + fieldName.substring(1)
        val method = this.javaClass.getMethod(getter, *arrayOf())
        return method.invoke(this, arrayOf<Any>())
    } catch (e: Exception) {

        return null
    }

}

fun isLogin(login: () -> Unit, unLogin:()->Unit?) {

    if (AccessManager.instance().isLogin()) {
        login()
    } else {
        unLogin()
//        this.startActivity(intentFor<LoginActivity>().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}
//
//fun Context.isLogin(login: () -> Unit) {
//
//    if (AccessManager.instance().isLogin()) {
//        login()
//    } else {
//        this.startActivity(intentFor<LoginActivity>().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
//    }
//}
//
//fun Context.reLogin() {
//    AccessManager.instance().deleteUser()
//    startActivity(Intent(this, LoginActivity::class.java))
//    AppManager.instance().finishAllActivity()
//}
//
///*用戶是否登陸*/
//fun Context.islogin(): Boolean {
//    return AccessManager.instance().isLogin()
//}
