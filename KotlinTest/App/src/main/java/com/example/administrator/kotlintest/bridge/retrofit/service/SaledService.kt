package com.xfs.fsyuncai.bridge.retrofit.service

import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.bridge.retrofit.service.body.SaledJsonBody
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
*@author : HaoBoy
*@date : 2018/10/22
*@description : 逆向所有service
**/
interface SaledService {

    /**
     * 退货维修申请列表
     */
    @POST(ApiConstants.T_X_APPLY_LIST)
    fun getTxApplyList(
            @Body body: SaledJsonBody
    ): Observable<String>

    /**
     * 退货记录列表
     */
    @POST(ApiConstants.T_HISTORY_LIST)
    fun getTHistoryList(
            @Body body: SaledJsonBody
    ): Observable<String>

    /**
     * 维修记录列表
     */
    @POST(ApiConstants.X_HISTORY_LIST)
    fun getXHistoryList(
            @Body body: SaledJsonBody
    ): Observable<String>



}