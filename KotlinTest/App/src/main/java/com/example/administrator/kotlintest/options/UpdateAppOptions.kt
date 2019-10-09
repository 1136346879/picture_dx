package com.example.administrator.kotlintest.options

import com.plumcookingwine.network.config.AbsRequestOptions
import com.xfs.fsyuncai.bridge.retrofit.service.CommonService
import io.reactivex.Observable
import retrofit2.Retrofit

/**
 * @author kangf
 * @data 2019/9/30
 * @description class UpdateAppOptions
 */
class UpdateAppOptions : AbsRequestOptions<String>() {


    init {
        isShowProgress = false
    }

    override fun createService(retrofit: Retrofit): Observable<String> {
        return retrofit.create(CommonService::class.java).getAppUpdateVersionData()
    }
}