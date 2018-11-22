package com.xfs.fsyuncai.bridge.retrofit.service

import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *@author : HaoBoy
 *@date : 2018/8/17
 *@description :公共信息
 *
 * */

interface CommonService {

    /**
     * 地址区域选择
     */
    @POST(ApiConstants.ADDRESS_AREA)
    @FormUrlEncoded
    fun getAreaByParentCode(
            @Field("parentCode") level: String
    ): Observable<String>


    /**
     * 获取所有的城市
     */
    @POST(ApiConstants.LOCATION_CITY)
    fun getAllCity(): Observable<String>


    /**
     * 获取指定城市的仓库信息
     */
    @POST(ApiConstants.ADDRESS_BY_NAME)
    @FormUrlEncoded
    fun getInfoByCity(
            @Field("name") name: String
    ): Observable<String>

    /**
     * 获取banner信息
     */
    @POST(ApiConstants.HOME_BANNER)
    @FormUrlEncoded
    fun getBannerInfo(
            @Field("category_id") category_id: Int,
            @Field("status") status: Int,
            @Field("platform") platform: Int,
            @Field("password") password: Int,
            @Field("warehouse_code") warehouse_code: Int
    ): Observable<String>


}