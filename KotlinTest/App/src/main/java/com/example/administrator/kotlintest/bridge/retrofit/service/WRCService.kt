package com.xfs.fsyuncai.bridge.retrofit.service

import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by kangf on 2018/11/8.
 */
interface WRCService {


    @FormUrlEncoded
    @POST(ApiConstants.SELECT_HOST_POWER)
    fun hasPower(@Field("memberId") memberId: Int): Observable<String>
}