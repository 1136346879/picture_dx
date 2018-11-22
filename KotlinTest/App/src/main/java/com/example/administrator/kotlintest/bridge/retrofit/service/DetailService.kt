package com.xfs.fsyuncai.bridge.retrofit.service

import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by kangf on 2018/6/27.
 */
interface DetailService {

    /**
     * 获取商品详情
     */
    @FormUrlEncoded
    @POST(ApiConstants.GOODS_DETAIL)
    fun detailGoods(
            @Field("key") key: String?,
            @Field("cityId") cityId: Int,
            @Field("spuId") spuId: Int,
            @Field("customerId") customerId: String = "CN00000388",
            @Field("platformType") platformType: Int = 1,
            @Field("wareHouseId") wareHouseId: Int = 1

    ): Observable<String>


    /**
     * 规格参数
     */
    @FormUrlEncoded
    @POST(ApiConstants.PRODUCT_DETAIL)
    fun detailProduct(@Field("spuId") spuId: Int): Observable<String>
}