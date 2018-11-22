package com.xfs.fsyuncai.entity.repository

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.xfs.fsyuncai.bridge.retrofit.RetrofitClient
import com.xfs.fsyuncai.bridge.retrofit.service.OrderService
import com.xfs.fsyuncai.bridge.retrofit.service.body.*
import com.xfs.fsyuncai.entity.accont.AccessManager
import com.xfs.fsyuncai.extend.getFieldValueByName
import io.reactivex.Observable
import okhttp3.RequestBody

/**
*@author : HaoBoy
*@date : 2018/8/17
*@description :公共信息
**/
object ConfirmOrder {

    private val mCommonService = RetrofitClient.instance().createService(OrderService::class.java)


    /**
     * 获取订单信息
     */
    fun getOrderVomfirmBody(level: OrderConfirmBody,activity: RxAppCompatActivity): Observable<String> {
        return mCommonService!!.confirmTradeBody(level).bindToLifecycle(activity)
    }

    /**
     * 提交订单信息
     */
    fun getOrderCommitBody(level: CommitOrderBody): Observable<String> {
        return mCommonService!!.commitOrderTrade(level)
    }


    /**
     * 退货订单详情页
     */
    fun getReturnOrderDetail(member_id: String,refund_id : String): Observable<String> {
        return mCommonService!!.returnOrderdEtailsBody(member_id,refund_id)
    }
    /**
     * 维修订单详情页
     */
    fun getRepairOrderDetail(member_id: String,maintain_id : String): Observable<String> {
        return mCommonService!!.repairOrderdEtailsBody(member_id,maintain_id)
    }
    /**
     * 取消退货服务
     */
    fun cancleReturn(level: CancleReturnOrderBody): Observable<String> {
        return mCommonService!!.cancelReturn(level)
    }
    /**
     * 取消维修服务
     */
    fun cancleRepair(level: CancleRepairOrderBody): Observable<String> {
        return mCommonService!!.cancleRepair(level)
    }
    /**
     * 获取发票信息
     */
    fun getInvoiceBody(memerId: String,invoiceType: String,activity: RxAppCompatActivity): Observable<String> {
        return mCommonService!!.queryInvoiceBody(memerId, invoiceType).bindToLifecycle(activity)
    }
    /**
     * 保存发票信息
     */
    fun saveInvoiceBody(level:InvoiceBody,activity: RxAppCompatActivity): Observable<String> {
        return mCommonService!!.saveInvoiceBody(level).bindToLifecycle(activity)
    }


    /**
     * check 商品列表
     */
    fun getOrderCheckBody(level: OrderCheckBody,activity: RxAppCompatActivity): Observable<String> {
        return mCommonService!!.checkProductsBody(level).bindToLifecycle(activity)
    }


    /**
     *
     */
//    fun searchPic(name :RequestBody,level: RequestBody,activity: RxAppCompatActivity): Observable<String> {
//        return mCommonService!!.pictureSearch(name,level).bindToLifecycle(activity)
//    }
}