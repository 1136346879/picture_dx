package com.example.administrator.kotlintest.util

import android.Manifest
import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.example.baselibrary.MyApplication
import com.example.baselibrary.widgets.TLog
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by HaoBoy on 2018/8/26.
 * 定位
 */
class BDLocationUtils : BDAbstractLocationListener() {
    private var rxPermissions: RxPermissions? = null

    // 关于定位的参数
    private var mLocClient: LocationClient? = null
    private var mOption: LocationClientOption? = null

    private var callback: ResultCallback? = null

    //单例
    companion object {
        @JvmStatic
        val instance: BDLocationUtils by lazy { BDLocationUtils() }
    }

    /**
     * @return DefaultLocationClientOption  setScanSpan=0是只定位一次
     */
    private val defaultLocationClientOption: LocationClientOption
        get() {
            if (mOption == null) {
                mOption = LocationClientOption()
                mOption!!.locationMode = LocationClientOption.LocationMode.Hight_Accuracy
                mOption!!.setCoorType("bd09ll")
                mOption!!.setScanSpan(0)
                mOption!!.setIsNeedAddress(true)
                mOption!!.setIgnoreKillProcess(true)
                mOption!!.isOpenGps = true
                mOption!!.setIsNeedAltitude(false)
            }
            return mOption!!
        }

    fun startLocation(context: FragmentActivity, callback: ResultCallback) {
        this.callback = callback
        rxPermissions = RxPermissions(context)
        location(rxPermissions!!)
    }

    fun startLocation(context: Fragment, callback: ResultCallback) {
        this.callback = callback
        rxPermissions = RxPermissions(context)
        location(rxPermissions!!)
    }

    @SuppressLint("CheckResult")
    fun location(rxPermissions: RxPermissions) {
        rxPermissions
                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe({}, {}, {
                    if (rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION)
                            && rxPermissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        if (mLocClient == null) {
                            // 定位初始化
                            mLocClient = LocationClient(MyApplication.cxt)
                            mLocClient!!.registerLocationListener(this)
                            mLocClient!!.locOption = defaultLocationClientOption
                        }
                        mLocClient!!.start()
                    } else {
                        callback?.onPermissionError()
                    }
                })
    }

    /**
     * 再不需要定位的时候调用（必须）
     */
    fun stopLocation() {
        if (mLocClient != null) {
            mLocClient!!.unRegisterLocationListener(instance)
            mLocClient!!.stop()
            mLocClient = null
        }
    }

    fun restartLocation() {
        if(mLocClient != null)
            if(!mLocClient!!.isStarted) mLocClient!!.restart()

    }

    override fun onReceiveLocation(bdLocation: BDLocation?) {
        if (null != bdLocation) {
            TLog.i("定位完成类型：-------" + bdLocation.locType)
            if (callback != null) {
                 if (bdLocation.city != null) {
                    callback!!.success(bdLocation.city)
                } else {
                    callback!!.fail()
                }
            }
        } else {
            callback!!.fail()
        }
    }

    interface ResultCallback {
        fun success(city: String)//定位成功的返回
        fun onPermissionError()//未允许定位权限返回
        fun fail()//定位失败
    }

}
