package com.xfs.fsyuncai.attachmentfile.ui

import com.xfs.fsyuncai.art.base.common.IntentDataDef
import com.xfs.fsyuncai.art.base.view.activity.BaseActivity
import com.xfs.fsyuncai.attachmentfile.R
import com.xfs.fsyuncai.attachmentfile.data.OrderAttachmentListBean

//@Route(path = RouterApi.AttachMentFile.ROUTER_ATTACHMENTFILE)
class AttachmentFileActivity : BaseActivity() {
    private var fragment: AttachmentFileFragment? = null
    private var fileList: ArrayList<OrderAttachmentListBean>? = null
    private var fromType: String? = null
    private var orderId: String? = null

    override fun resLayout(): Int {
        return R.layout.activity_file_upload
    }

    override fun init() {
        @Suppress("UNCHECKED_CAST")
        fileList = intent.getSerializableExtra(IntentDataDef.ADD_FILE_PARAM) as ArrayList<OrderAttachmentListBean>?
        fromType = intent.getStringExtra(IntentDataDef.FROM_ADD_FILE_PARAM)
        orderId = intent.getStringExtra(IntentDataDef.ORDER_ID)
    }

    override fun logic() {
        if (fragment == null) {
            fragment = AttachmentFileFragment.newInstance()
            fragment!!.setData(fileList, fromType, orderId)
        }
        supportFragmentManager.beginTransaction().add(
                R.id.container, fragment!!, AttachmentFileFragment::class.java.simpleName
        ).commit()

        AttachmentFilePresenter(fragment!!, fragment!!)
    }

    override fun onBackPressed() {
        fragment!!.finishResult()
    }
}
