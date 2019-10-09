package com.xfs.fsyuncai.bridge.retrofit.service

import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by kangf on 2018/6/14.
 */
interface CategoryService {

    /**
     * 获取某一级下的所有分类
     *
     * @param categoryLevel 分类级别
     */
    @FormUrlEncoded
    @POST(ApiConstants.CATEGORY_TOP)
    fun getTopCategory(
//            @Field("categoryLevel") level: Int
            @Field("category_level") level: Int
    ): Observable<String>


    /**
     * 根据一级商品品类级别和编码获取此品类所有子集品类
     *
     * @param categoryLevel 分类级别
     * @param categoryCode  分类编码
     */
    @FormUrlEncoded
    @POST(ApiConstants.CATEGORY_ALL)
    fun allCategory(
            @Field("categoryLevel") level: Int,
            @Field("categoryCode") code: String
    ): Observable<String>


}