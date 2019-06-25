package com.xfs.fsyuncai.attachmentfile.ui


import com.google.gson.Gson
import com.xfs.fsyuncai.art.base.common.service.CommonService
import com.xfs.fsyuncai.art.base.common.service.body.BindFileToOrderBody
import com.xfs.fsyuncai.art.base.common.service.body.DeleteFileToOrderBody
import com.xfs.fsyuncai.art.base.data.manager.PublicUploadFileManager
import com.xfs.fsyuncai.art.base.network.retrofit.callback.HttpOnNextListener
import com.xfs.fsyuncai.art.base.network.retrofit.exception.ApiErrorModel
import com.xfs.fsyuncai.art.base.network.retrofit.http.HttpManager
import com.xfs.fsyuncai.art.base.common.service.body.QueryFileBody
import com.xfs.fsyuncai.art.base.data.accont.AccessManager
import com.xfs.fsyuncai.art.base.utils.ToastUtil
import com.xfs.fsyuncai.attachmentfile.data.OrderAttachmentListBean
import com.xfs.fsyuncai.attachmentfile.data.OrderQuerySucessFile
import com.xfs.fsyuncai.attachmentfile.data.UploadFileSucessEntity
import org.json.JSONObject
import kotlin.collections.ArrayList

/**
 * Created by kangf on 2018/8/18.
 * 附件上传接口访问 p层
 */
class AttachmentFilePresenter(val mView: AttachmentFileContract.View, val fragment: AttachmentFileFragment)
    : AttachmentFileContract.Presenter {


    private val httpManager = HttpManager.instance()

    init {
        mView.setPresenter(this)
    }


    override fun subscribe() {//
    }

    override fun unsubscribe() {//
    }

    /**
     * 订单详情页进入查询附件
     *
     */
    override fun getDetail(jsonData: String?) {
        if (jsonData == null) return
        val orderBody = QueryFileBody()
        orderBody.order_id = jsonData
        httpManager.doHttpDeal(
                fragment.context!!,
                httpManager.createService(CommonService::class.java).queryFIle(orderBody),
                object : HttpOnNextListener() {
                    override fun onNext(json: String) {
                        val orderQuerySucessFile = Gson().fromJson(json, OrderQuerySucessFile::class.java)
                        mView.sucessQuery(orderQuerySucessFile)
                    }
                })
    }

    /**
     * 上传文件  文件路径即可
     */
    override fun uploadFile(path: String) {

        PublicUploadFileManager.instance.uploadFile(fragment, path, object : PublicUploadFileManager.OnUploadListener {
            override fun upSuccess(json: String) {
                val jsonString = json.substring(json.indexOf('[') + 1, json.lastIndexOf(']'))
                val uploadFileSucessEntity = Gson().fromJson(jsonString, UploadFileSucessEntity::class.java)
                uploadFileSucessEntity.originPath = path
                val orderAttachmentListBean = OrderAttachmentListBean()
                orderAttachmentListBean.attachment_name = uploadFileSucessEntity.fileName
                orderAttachmentListBean.attachment_url = uploadFileSucessEntity.fullPath
                orderAttachmentListBean.created_at = uploadFileSucessEntity.time
                orderAttachmentListBean.originPath = path
                orderAttachmentListBean.netOrLocal = true
                orderAttachmentListBean.login_account = AccessManager.instance().loginAccount()
                mView.uploadSucess(orderAttachmentListBean)
            }

            override fun upError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
                mView.uploadError(statusCode, apiErrorModel)
            }
        })
    }

    /**
     * 绑定附件至订单
     */
    override fun bindFileToOrder(orderId: String?, localList: ArrayList<OrderAttachmentListBean>?) {
        val bindFileToOrderBody = BindFileToOrderBody()
        bindFileToOrderBody.order_id = orderId
        bindFileToOrderBody.login_account = AccessManager.instance().loginAccount()
        bindFileToOrderBody.member_id = AccessManager.instance().memberId()
        val attachmentINFList = arrayListOf<BindFileToOrderBody.AttachmentInfo>()
        if (localList != null) {
            localList.forEach {
                val attachmentInfo = BindFileToOrderBody.AttachmentInfo()
                attachmentInfo.attachment_name = it.attachment_name
                attachmentInfo.attachment_url = it.attachment_url
                attachmentInfo.created_at = it.created_at
                attachmentINFList.add(attachmentInfo)
            }
            bindFileToOrderBody.attachment_info = attachmentINFList
        }
        httpManager.doHttpDeal(fragment.activity(),
                httpManager.createService(CommonService::class.java).bindFileToOrder(bindFileToOrderBody),
                object : HttpOnNextListener() {
                    override fun onNext(json: String) {
                        try {
                            val jsonObject = JSONObject(json)
                            val code = jsonObject.getString("code")
                            if ("0" == code) {
                                mView.bindSucess()
                            }
                        } catch (e: Exception) {

                        }
                    }
                })
    }

    /**
     * 远端删除file
     */
    override fun deleteFileFromOrder(deleteList: ArrayList<OrderAttachmentListBean>, orderId: String) {

        val deleteFileToOrderBody = DeleteFileToOrderBody()
        deleteFileToOrderBody.login_account = AccessManager.instance().loginAccount()
        deleteFileToOrderBody.member_id = AccessManager.instance().memberId()
        deleteFileToOrderBody.order_id = orderId
        val idsList = ArrayList<Int>()
        deleteList.forEach {
            if (!it.netOrLocal) {
                idsList.add(it.id)
            }
        }
        deleteFileToOrderBody.ids = idsList
        httpManager.doHttpDeal(fragment.activity(), httpManager.createService(CommonService::class.java).deleteFileFromOrder(deleteFileToOrderBody), object : HttpOnNextListener() {
            override fun onNext(json: String) {
                try {
                    val jsonObject = JSONObject(json)
                    val code = jsonObject.getString("code")
                    val msg = jsonObject.getString("msg")
                    if ("0" == code) {
                        mView.deleteSucess()
                    } else {
                        ToastUtil.showCustomToast("$msg")
                    }
                } catch (e: Exception) {
//
                }
            }
        })
    }
}
