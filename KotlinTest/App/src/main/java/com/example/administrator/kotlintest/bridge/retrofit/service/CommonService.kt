package com.xfs.fsyuncai.bridge.retrofit.service

import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

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
     * 获取地址对应的仓库
     * @return
     */
    @POST(ApiConstants.GET_WAREHORSE_BY_CITY)
    @FormUrlEncoded
    fun getWarehouseByCity(
            @Field("code") code: String
    ): Observable<String>
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
    /**
     * 下载附件然后预览
     */
    @GET
    fun loadPdfFile(@Url fileUrl: String)
            : Call<ResponseBody>
    /**
     * 获取App版本更新的信息
     */
    @POST(ApiConstants.APP_UPDATE_URL)
    fun getAppUpdateVersionData(): Observable<String>
}