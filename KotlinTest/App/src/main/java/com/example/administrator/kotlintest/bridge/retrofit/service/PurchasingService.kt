package com.xfs.fsyuncai.bridge.retrofit.service

import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.bridge.retrofit.service.body.AddCartBody
import com.xfs.fsyuncai.bridge.retrofit.service.body.ShoppingNumRequestBody
import com.xfs.fsyuncai.bridge.retrofit.service.body.ShoppingListRequestBody
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by kangf on 2018/8/2.
 */
interface PurchasingService {

    @POST(ApiConstants.ADD_SHOPPING_CART)
    fun addCart(
            @Body body: AddCartBody
    ): Observable<String>

    /**
     * 获取购物车商品数量
     */
    @POST(ApiConstants.NUM_SHOPPING_CART)
    fun getCartNum(
            @Body body: ShoppingNumRequestBody
    ): Observable<String>

    /**
     * 购物车商品列表
     */
    @POST(ApiConstants.SHOPPING_CART_List)
    fun getCartList(
            @Body body: ShoppingListRequestBody
    ): Observable<String>
}