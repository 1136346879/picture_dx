package com.xfs.fsyuncai.entity.repository

import com.xfs.fsyuncai.bridge.retrofit.RetrofitClient
import com.xfs.fsyuncai.bridge.retrofit.service.CommonService
import io.reactivex.Observable

/**
*@author : HaoBoy
*@date : 2018/8/17
*@description :公共信息
**/
object CommonRepository {

    private val mCommonService = RetrofitClient.instance().createService(CommonService::class.java)

    /**
     * 获取区域信息
     */
    fun getAreaByParentCode(level: String): Observable<String> {
        return mCommonService!!.getAreaByParentCode(level)
    }

    /**
     * 获取全部城市
     */
    fun getAllCity():Observable<String>{
        return mCommonService!!.getAllCity()
    }

}