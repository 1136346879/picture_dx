package com.xfs.fsyuncai.entity.repository

import com.xfs.fsyuncai.bridge.retrofit.RetrofitClient
import com.xfs.fsyuncai.bridge.retrofit.service.DetailService
import com.xfs.fsyuncai.entity.accont.AccessManager
import io.reactivex.Observable

/**
 * Created by kangf on 2018/6/14.
 */
object DetailRepository {

    private val mDetailService = RetrofitClient.instance().createService(DetailService::class.java)

    /**
     * 获取商品详情
     */
//    fun getGoodsDetail(key: String?, spuId: Int): Observable<String> {
//        val platform = if(AccessManager.instance().accountType() == 10) {
//            1
//        } else {
//            2
//        }
//
//        return mDetailService!!.detailGoods(key,
//                FsYuncaiApp.cityId(),
//                spuId,
//                AccessManager.instance().customerId(),
//                platform,
//                FsYuncaiApp.warehouseId())
//    }


    /**
     * 规格参数
     */
    fun getProductDetail(spuId: Int): Observable<String> {
        return mDetailService!!.detailProduct(spuId)
    }


}