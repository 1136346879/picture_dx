package com.xfs.fsyuncai.bridge.retrofit.service.body

/**
*@author : HaoBoy
*@date : 2018/8/17
*@description :
**/
class AddressSaveBody {

    var provinceId: Int? = null
    var cityId: Int? = null
    var districtId: Int? = null
    var streetId: Int? = null
    var detailAddress: String? = null
    var linkman: String? = null
    var mobile: String? = null
    var addAlias: String? = null
    var officePhone: String? = null
    var email:String? = null
    var memberId:Int? = null
    var isDefaultAddress:Int? = null

    var shipAddPersonJson: List<ParamsBody>? = null

     class ParamsBody{
            var addressId: Int? = null
            var id: Int? = null
            var mobile: String? = null
            var name: String? = null
            var receiverId: Int? = null
            var status: Int? = null
     }


}