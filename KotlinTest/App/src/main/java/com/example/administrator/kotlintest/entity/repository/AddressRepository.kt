package com.xfs.fsyuncai.entity.repository

import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import com.xfs.fsyuncai.bridge.retrofit.RetrofitClient
import com.xfs.fsyuncai.bridge.retrofit.service.AddressService
import com.xfs.fsyuncai.bridge.retrofit.service.CommonService
import com.xfs.fsyuncai.bridge.retrofit.service.body.AddressSaveBody
import io.reactivex.Observable

/**
 *@author : HaoBoy
 *@date : 2018/8/15
 *@description :
 **/
object AddressRepository {

    private val mAddressService = RetrofitClient.instance().createService(AddressService::class.java)

    /**
     * 获取收货地址列表
     */
    fun getAddressList(memberId: Int,pageNum: Int,pageSize: Int,type: Int): Observable<String> {
        return mAddressService!!.getAddressList(memberId,pageNum,pageSize,type)
    }

    /**
     * 添加一个收货地址
     */
    fun saveAddress(param:Map<String,String>):Observable<String>{
        return mAddressService!!.saveAddress(param)
    }

    /**
     * 修改一个收货地址
     */
    fun updateAddress(param:Map<String,String>):Observable<String>{
        return mAddressService!!.updateAddress(param)
    }



    /**
     * 删除收货地址
     */
    fun deleteAddress(memberId: Int,shipAddId: Int):Observable<String>{
        return mAddressService!!.deleteAddress(memberId,shipAddId)
    }

    /**
     * 设置默认收货地址
     */
    fun setDefault(id:Int,token: String):Observable<String>{
        return mAddressService!!.setDefaultAddress(id,token)
    }

    /**
     * 编辑查看收货地址
     */
    fun getAddressEditable( memberId: Int,shipAddId :Int) :Observable<String>{
        return mAddressService!!.getAddressEditable(memberId,shipAddId)
    }

    /**
     * 获取签约用户关联收货人信息
     */
    fun getAddressPersons(customerCode: String,customerName :String) :Observable<String>{
        return mAddressService!!.getAddressPersons(customerCode,customerName)
    }



}