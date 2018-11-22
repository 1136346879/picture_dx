package com.xfs.fsyuncai.bridge.retrofit.service

import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.bridge.retrofit.service.body.*
import com.xfs.fsyuncai.entity.accont.AccessManager
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by kangf on 2018/8/7.
 *
 * 订单相关
 */

interface OrderService {

    @Headers("www")
    @POST(ApiConstants.ORDER_NUMBER)
    fun orderNum(
            @Body body: OrderNumBody = OrderNumBody(
                    "${AccessManager.instance().memberId()}",
                    AccessManager.instance().loginAccount(),
                    AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list?.enable_audit,
                    AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list?.enable_order,
                    AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list?.enable_settle
            )
    ): Observable<String>


    /**
     * app 支付单
     */
    @Headers("www")
    @POST(ApiConstants.SAVE_PAY_INFO)
    fun savePayInfo(
            @Body body: OrderSavePayBody
    ): Observable<String>


    /**
     *  APP-支付流水单
     */
    @Headers("www")
    @POST(ApiConstants.COMBINE_PAY)
    fun combinePay(
            @Body body: OrderCombinePayBody
    ): Observable<String>

    /**
     * 支付结果查询（在线支付）
     */
    @Headers("www")
    @GET(ApiConstants.PAY_RESULT)
    fun payResult(@Query("serialnoId") order: String): Observable<String>


    /**
     * 支付结果查询（线下支付）
     */
    @Headers("www")
    @POST(ApiConstants.INSTANT_CONFIRM)
    fun payOfflineResult(@Body body: PayResultBody): Observable<String>

    /**
     *  APP-确认订单
     */
    @Headers("www")
    @POST(ApiConstants.CONFIRM_TRADE)
    fun confirmTradeBody(
            @Body body: OrderConfirmBody
    ): Observable<String>
    /**
     *  APP-采购单结算前  check
     */
    @Headers("www")
    @POST(ApiConstants.CHECK_PRODUCTS)
    fun checkProductsBody(
            @Body body: OrderCheckBody
    ): Observable<String>
    /**
     *  提交订单
     */
    @Headers("www")
    @POST(ApiConstants.COMMIT_TRADE)
    fun commitOrderTrade(
            @Body body: CommitOrderBody
    ): Observable<String>


    /**
     *  APP-查询发票信息
     */
    @Headers("www")
    @POST(ApiConstants.QUERY_INVOICEINFO)
    fun queryInvoiceBody(
            @Query("memberId") memberId: String,
            @Query("invoiceType") invoiceType: String
    ): Observable<String>

    /**
     *  APP-保存发票信息
     */
    @Headers("www")
    @POST(ApiConstants.SAVE_INVOICEINFO)
    fun saveInvoiceBody(
            @Body body:InvoiceBody
    ): Observable<String>


    /**
     *  APP-退货订单详情页
     */
    @Headers("www")
    @POST(ApiConstants.RETURN_ORDERD_ETAILS)
    fun returnOrderdEtailsBody(
            @Query("member_id") orderId: String,
            @Query("refund_id") refundId: String
    ): Observable<String>

    /**
     *  APP-维修订单详情页
     */
    @Headers("www")
    @POST(ApiConstants.REPAIR_ORDERD_ETAILS)
    fun repairOrderdEtailsBody(
            @Query("member_id") orderId: String,
            @Query("maintain_id") refundId: String
    ): Observable<String>


    /**
     *  APP-取消退货
     */
    @Headers("www")
    @POST(ApiConstants.CANCLE_RETURN_ORDERD)
    fun cancelReturn(
            @Body body:CancleReturnOrderBody
    ): Observable<String>
    /**
     *  APP-取消维修
     */
    @POST(ApiConstants.CANCLE_REPAIR_ORDERD)
    @Headers("www")
    fun cancleRepair(
            @Body body:CancleRepairOrderBody
    ): Observable<String>

    /**
     *  APP-我的订单
     */
    @Headers("www")
    @POST(ApiConstants.MY_ORDERD)
    fun maOrder(
            @Body body:MyOrderBody
    ): Observable<String>


    /**
     *  APP-图片搜索
     */
    @Multipart
    @POST(ApiConstants.PICTURE_SEARCH)
    fun pictureSearch(
            @Part body: MultipartBody.Part
    ): Observable<String>
}
