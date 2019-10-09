package com.xfs.fsyuncai.bridge.retrofit.service

import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
*@author : HaoBoy
*@date : 2018/8/23
*@description :收货地址相关
**/
interface AddressService {

    /**
     * 添加收货地址
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST(ApiConstants.ADDRESS_ADD)
    fun saveAddress(
            @FieldMap body: Map<String,String>
    ): Observable<String>

   /**
    * 修改收货地址
    * @param map
    * @return
    */
    @FormUrlEncoded
    @POST(ApiConstants.ADDRESS_EDIT)
    fun updateAddress(
            @FieldMap body: Map<String,String>
    ): Observable<String>

    /**
     * 获取收货地址列表
     * @param map
     * @return
     */
    @POST(ApiConstants.ADDRESS_LIST)
    @FormUrlEncoded
    fun getAddressList(
            @Field("memberId") memberId: Int,
            @Field("pageNum") pageNum: Int,
            @Field("pageSize") pageSize: Int,
            @Field("type") type: Int
    ): Observable<String>

    /**
     * 删除收货地址
     * @param map
     * @return
     */
    @POST(ApiConstants.ADDRESS_DELETE)
    @FormUrlEncoded
    fun deleteAddress(
            @Field("memberId") memberId: Int,
            @Field("shipAddId") shipAddId: Int
    ):Observable<String>

    /**
     * 设置默认收货地址
     */
    @POST(ApiConstants.ADDRESS_DEFAULT)
    @FormUrlEncoded
    fun setDefaultAddress(
            @Field("shipAddId") param: Int,
            @Field("token") token: String

    ):Observable<String>

    /**
     * 编辑查看收货地址
     * @return
     */
    @POST(ApiConstants.ADDRESS_EDIT_SHOW)
    @FormUrlEncoded
    fun getAddressEditable(
            @Field("memberId") memberId: Int,
            @Field("shipAddId") shipAddId: Int
    ): Observable<String>


    /**
     * 获取签约用户关联收货人信息
     * @return
     */
    @POST(ApiConstants.ADDRESS_PERSON_LIST)
    @FormUrlEncoded
    fun getAddressPersons(
            @Field("customerCode") memberId: String,
            @Field("consigneeName") shipAddId: String
    ): Observable<String>

}