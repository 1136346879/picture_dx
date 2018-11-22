package com.xfs.fsyuncai.entity.address

/**
 *@author : HaoBoy
 *@date : 2018/9/12
 *@description : 签约用户关联收货人
 **/

data class ConsigneeEntity(
        val consignee: List<ConsigneeBean>,
        val errorMessage: String,
        val errorCode: Int
) {

    data class ConsigneeBean(
            val consignee_id: Int,
            val consignee_fixedphone: String,
            val authorized_consignee_id: Int,
            val consignee_telephone: String,
            val consignee_name: String,
            val consignee_status: Int,
            val create_date: String,
            val consignee_idcard: String,
            val customer_code: String,
            val update_date: String
    )
}