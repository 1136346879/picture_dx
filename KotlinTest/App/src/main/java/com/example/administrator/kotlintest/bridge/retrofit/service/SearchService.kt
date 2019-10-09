package com.xfs.fsyuncai.bridge.retrofit.service

import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.bridge.retrofit.service.body.SearchResultRequestBody
import com.xfs.fsyuncai.entity.imagesearch.ImageSearchRequestBody
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by kangf on 2018/6/23.
 */
interface SearchService {


    /**
     * 获取搜索结果 -- 通过分类搜索的
     *
     * @param map 参数集合
     *
     *  categoryName        品类名称
     *  categoryLevel       品类
     *  brandCatLevel       分类级别
     *  sort                价格排序
     *  scoreSort           综合排序
     *  page                当前页
     *  rows                每页显示条数
     *  cityId              城市id
     *  customerId          客户id
     *  attriNameAndValues  属性值字符串
     *  queryString         搜索框输入词
     *  stockSort           有无库存
     *  priceRange          价格区间
     *  brandNameStr        品牌名称
     *  brandCatName        分类
     *  brandCatLevel       分类级别
     */
    @Streaming
    @POST(ApiConstants.SEARCH_RESULT)
    fun searchResult(@Body body: SearchResultRequestBody):
            Observable<String>

    //通过搜索页面搜索
    @Streaming
    @POST(ApiConstants.SEARCH_RESULT_BY_STRING)
    fun searchResultByInput(@Body body: SearchResultRequestBody):
            Observable<String>


    /**
     * 智能联想
     */
    @FormUrlEncoded
    @POST(ApiConstants.SEARCH_ASSOCIATION)
    fun searchAssociation(
            @Field("queryString") queryString: String?,
            @Field("cityId") cityId: Int?,
            @Field("platformType") platformType: Int?,
            @Field("wareHouseId") wareHouseId: Int?
    ): Observable<String>

    /**
     * 图片搜索获取商品列表
     */

    @POST(ApiConstants.IMAGE_SEARCH_GOODS_LIST)
    fun getImageSearchGoods(
            @Body body: ImageSearchRequestBody
    ): Observable<String>


}