package com.example.administrator.kotlintest.util

import android.content.Context
import android.content.SharedPreferences
import com.example.baselibrary.widgets.UIUtils

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Created by Kangf on 2016/9/14.
 */
object SPUtils {
    const val CITY_ID: String = "CITY_ID"//城市id标识
    const val WAREHOUSE_ID: String = "WAREHOUSE_ID"//仓库地址标识
    const val CITY_NAME: String = "CITY_NAME"//城市名称标识
    const val CITY_CODE: String = "CITY_CODE"//城市code标识

    const val CURRENT_VERSION_CODE: String = "CURRENT_VERSION_CODE"//线上版本
    /**
     * 保存在手机里面的名字
     */
    private const val FILE_NAME = "shared_data"

    /**
     * 返回所有的键值对
     *
     * @return
     */
    val all: Map<String, *>
        get() { //只生成一个get方法
            val sharedPreferences = UIUtils.context().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
            return sharedPreferences.all
        }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param obj
     */
    fun setObject(key: String, obj: Any): Boolean {
        val sharedPreferences = UIUtils.context().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        when (obj) {
            is String -> editor.putString(key, obj)
            is Int -> editor.putInt(key, obj)
            is Boolean -> editor.putBoolean(key, obj)
            is Float -> editor.putFloat(key, obj)
            is Long -> editor.putLong(key, obj)
            else -> editor.putString(key, obj.toString())
        }
        return SPUtils.SharedPreferencesCompat.apply(editor)
    }

    /**
     * 保存数据的方法，拿到数据保存数据的基本类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param obj
     */
    fun setObject(filename: String, key: String, obj: Any) {
        val sharedPreferences = UIUtils.context().getSharedPreferences(filename, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        when (obj) {
            is String -> editor.putString(key, obj)
            is Int -> editor.putInt(key, obj)
            is Boolean -> editor.putBoolean(key, obj)
            is Float -> editor.putFloat(key, obj)
            is Long -> editor.putLong(key, obj)
            else -> editor.putString(key, obj.toString())
        }
        SPUtils.SharedPreferencesCompat.apply(editor)
    }

    /**
     * 获取保存数据的方法，我们根据默认值的到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key           键的值
     * @param defaultObject 默认值
     * @return
     */

    fun getObjectForKey(key: String, defaultObject: Any): Any? {
        val sharedPreferences = UIUtils.context().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return when (defaultObject) {
            is String -> sharedPreferences.getString(key, defaultObject)
            is Int -> sharedPreferences.getInt(key, defaultObject)
            is Boolean -> sharedPreferences.getBoolean(key, defaultObject)
            is Float -> sharedPreferences.getFloat(key, defaultObject)
            is Long -> sharedPreferences.getLong(key, defaultObject)
            else -> sharedPreferences.getString(key, null)
        }

    }

    fun getObjectForKey(filename: String, key: String, defaultObject: Any): Any? {
        val sharedPreferences = UIUtils.context().getSharedPreferences(filename, Context.MODE_PRIVATE)
        return when (defaultObject) {
            is String -> sharedPreferences.getString(key, defaultObject)
            is Int -> sharedPreferences.getInt(key, defaultObject)
            is Boolean -> sharedPreferences.getBoolean(key, defaultObject)
            is Float -> sharedPreferences.getFloat(key, defaultObject)
            is Long -> sharedPreferences.getLong(key, defaultObject)
            else -> sharedPreferences.getString(key, null)
        }

    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    fun remove(key: String) {
        val sharedPreferences = UIUtils.context().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        SPUtils.SharedPreferencesCompat.apply(editor)
    }

    /**
     * 移除某个文件下key值已经对应的值
     *
     * @param key
     */
    fun remove(fileName: String, key: String) {
        val sharedPreferences = UIUtils.context().getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(key)
        SPUtils.SharedPreferencesCompat.apply(editor)
    }

    /**
     * 清除指定文件所有的数据
     */
    fun clear(fileName: String) {
        val sharedPreferences = UIUtils.context().getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        SPUtils.SharedPreferencesCompat.apply(editor)
    }

    /**
     * 清除默认文件所有的数据
     */
    fun clear() {
        val sharedPreferences = UIUtils.context().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        SPUtils.SharedPreferencesCompat.apply(editor)
    }

    /**
     * 查询某个key是否存在
     *
     * @param key
     * @return
     */
    operator fun contains(key: String): Boolean {
        val sharedPreferences = UIUtils.context().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.contains(key)
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private object SharedPreferencesCompat {
        private val sApplyMethod = findApplyMethod()

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        private fun findApplyMethod(): Method? {
            try {
                val clz = SharedPreferences.Editor::class.java
                return clz.getMethod("apply")
            } catch (e: NoSuchMethodException) {
            }

            return null
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        fun apply(editor: SharedPreferences.Editor): Boolean {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor)
                    return true
                }
            } catch (e: IllegalArgumentException) {
                return false
            } catch (e: IllegalAccessException) {
                return false
            } catch (e: InvocationTargetException) {
                return false
            }

            return editor.commit()
        }
    }

}