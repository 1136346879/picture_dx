package com.xfs.fsyuncai.entity.repository

import com.xfs.fsyuncai.bridge.retrofit.RetrofitClient
import com.xfs.fsyuncai.bridge.retrofit.service.AddressService
import com.xfs.fsyuncai.bridge.retrofit.service.SaledService
import com.xfs.fsyuncai.bridge.retrofit.service.body.SaledJsonBody
import io.reactivex.Observable

/**
 *@author : HaoBoy
 *@date : 2018/10/22
 *@description :
 **/
object SaledRepository {

    private val mSaledService = RetrofitClient.instance().createService(SaledService::class.java)

    /**
     * 退货维修申请
     */
    fun getTxApplyList(mSaledJsonBody: SaledJsonBody): Observable<String> {
        return mSaledService!!.getTxApplyList(mSaledJsonBody)
    }


    /**
     * 退货记录列表
     */
    fun getTHistoryList(mSaledJsonBody: SaledJsonBody): Observable<String> {
        return mSaledService!!.getTHistoryList(mSaledJsonBody)
    }

    /**
     * 维修记录列表
     */
    fun getXHistoryList(mSaledJsonBody: SaledJsonBody): Observable<String> {
        return mSaledService!!.getXHistoryList(mSaledJsonBody)
    }
}