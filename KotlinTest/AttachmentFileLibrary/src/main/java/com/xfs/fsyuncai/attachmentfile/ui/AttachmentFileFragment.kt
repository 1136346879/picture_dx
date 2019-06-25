package com.xfs.fsyuncai.attachmentfile.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.ImageView
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xfs.fsyuncai.art.base.common.ActiveResultDef
import com.xfs.fsyuncai.art.base.common.IntentDataDef
import com.xfs.fsyuncai.art.base.network.retrofit.exception.ApiErrorModel
import com.xfs.fsyuncai.art.base.utils.ToastUtil
import com.xfs.fsyuncai.art.base.utils.UIUtils
import com.xfs.fsyuncai.art.base.view.adapter.BaseRvAdapter
import com.xfs.fsyuncai.art.base.view.fragment.BaseFragment
import com.xfs.fsyuncai.art.base.weiget.recyclerview.divider.SpaceItemDecoration
import com.xfs.fsyuncai.attachmentfile.R
import com.xfs.fsyuncai.attachmentfile.data.AddFileInfo
import kotlinx.android.synthetic.main.fragment_attachment_file_upload.*
import kotlinx.android.synthetic.main.toolbar_common_file.*

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.xfs.fsyuncai.art.base.view.gallery.GalleryActivity
import com.xfs.fsyuncai.art.base.weiget.EmptyView
import com.xfs.fsyuncai.art.base.weiget.SystemDialog
import com.xfs.fsyuncai.attachmentfile.data.OrderAttachmentListBean
import com.xfs.fsyuncai.attachmentfile.data.OrderQuerySucessFile
import kotlin.collections.ArrayList

/**
 * 附件上传
 */
class AttachmentFileFragment : BaseFragment(), AttachmentFileContract.View {


    private var presenter: AttachmentFileContract.Presenter? = null

    override fun activity(): RxAppCompatActivity {
        return activity as RxAppCompatActivity
    }

    override fun setPresenter(presenter: AttachmentFileContract.Presenter?) {
        this.presenter = presenter
    }

    private var tvRight: CheckBox? = null
    private var fromType: String? = null
    private var orderId: String? = null
    private var rxPermissions: RxPermissions? = null
    private var operateFlag: Boolean = false
    private lateinit var operatAdapter: ListApapter
    private lateinit var unOperatAdapter: NoCLickListApapter
    private var unOpreatList = ArrayList<OrderAttachmentListBean>()
    private var localList = ArrayList<OrderAttachmentListBean>()
    private var operatList = ArrayList<OrderAttachmentListBean>()
    private var deleteList = ArrayList<OrderAttachmentListBean>()
    private var deleteLocalList = ArrayList<OrderAttachmentListBean>()
    private var deleteNetList = ArrayList<OrderAttachmentListBean>()

    override fun layoutResId(): Int {
        return R.layout.fragment_attachment_file_upload
    }

    override fun init() {
        rxPermissions = RxPermissions(this)
        tvRight = getViewById(R.id.tv_right)
        tvRight!!.visibility = View.GONE
        tvTitle.text = "附件"
        if (operatList.size > 0) {
            tvRight!!.visibility = View.VISIBLE
        }
        unOperatAdapter = NoCLickListApapter(unOpreatList, this.context!!)
        has_upload_file_list_not_click.let {
            val manager = LinearLayoutManager(this.context)
            it.addItemDecoration(SpaceItemDecoration(UIUtils.dip2px(1)))
            it.layoutManager = manager
            it.adapter = unOperatAdapter
        }
        operatAdapter = ListApapter(operatList, this.context!!)
        has_upload_file_list.let {
            val manager = LinearLayoutManager(this.context)
            it.addItemDecoration(SpaceItemDecoration(UIUtils.dip2px(1)))
            it.layoutManager = manager
            it.adapter = operatAdapter
        }
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.isSmoothScrollbarEnabled = true
        layoutManager.isAutoMeasureEnabled = true
        has_upload_file_list_not_click.layoutManager = layoutManager
        has_upload_file_list_not_click.setHasFixedSize(true)
        has_upload_file_list_not_click.isNestedScrollingEnabled = false

        val layoutManager2 = LinearLayoutManager(this.context)
        layoutManager2.isSmoothScrollbarEnabled = true
        layoutManager2.isAutoMeasureEnabled = true
        has_upload_file_list.layoutManager = layoutManager2
        has_upload_file_list.setHasFixedSize(true)
        has_upload_file_list.isNestedScrollingEnabled = false
        if ("balance".equals(fromType)) {//确认订单添加附件
            confirm_upload.visibility = View.GONE
            ll_add_flie.visibility = View.VISIBLE
        } else { //获取订单已绑定的附件
            presenter!!.getDetail(orderId!!)
        }
    }

    fun finishResult() {
        val intent = Intent()
        intent.putExtra(IntentDataDef.ADD_FILE_PARAM, operatList)
        activity!!.setResult(ActiveResultDef.FILE_ADD_PAGE, intent)
        activity!!.finish()
    }

    /**
     * 编辑
     */
    private fun editFileList() {
        tvRight!!.isChecked = !tvRight!!.isChecked
        if (tvRight!!.isChecked) {
            tvRight!!.text = "编辑"
            tvRight!!.isChecked = false
            cancle_delete_ll.visibility = View.GONE
            ll_add_flie_noclick.visibility = View.GONE
            operatList.forEach {
                it.checkBox = false
            }
            if (localList.size > 0 && !"balance".equals(fromType)) {
                confirm_upload.visibility = View.VISIBLE
            }
        } else {
            tvRight!!.text = "取消"
            tvRight!!.isChecked = true
            cancle_delete_ll.visibility = View.VISIBLE
            ll_add_flie_noclick.visibility = View.VISIBLE
            confirm_upload.visibility = View.GONE
        }
        deleteList.clear()
        deleteBntStatus()
        operatAdapter.notifyDataSetChanged()
        unOperatAdapter.notifyDataSetChanged()
    }

    private fun cancelEdite() {
        if (tvRight!!.isChecked) {
            tvRight!!.text = "编辑"
            tvRight!!.isChecked = false
            cancle_delete_ll.visibility = View.GONE
            ll_add_flie_noclick.visibility = View.GONE
            operatList.forEach {
                it.checkBox = false
            }
            if (localList.size > 0 && !"balance".equals(fromType)) {
                confirm_upload.visibility = View.VISIBLE
            }
        }
        deleteList.clear()
        deleteBntStatus()
    }

    override fun logic() {
        ll_add_flie_noclick.setOnClickListener { return@setOnClickListener }
        ivBack.setOnClickListener { v -> finishResult() }
        add_image!!.setOnClickListener { v -> pickPhoto() }
        add_file!!.setOnClickListener { v -> gotoFileList() }
        tv_right!!.setOnClickListener { v -> editFileList() }
        operatAdapter.setOnClickItem {
            if (tvRight!!.isChecked) {
                operatList.get(it).checkBox = !operatList.get(it).checkBox
                if (operatList.get(it).checkBox) {
                    deleteList.add(operatList.get(it))
                } else {
                    deleteList.remove(operatList.get(it))
                }
                deleteBntStatus()
                operatAdapter.notifyDataSetChanged()
            } else {
                previewFileAndImage(it, operatList)
            }
        }
        unOperatAdapter.setOnClickItem {
            if (!tvRight!!.isChecked || !operateFlag) {
                previewFileAndImage(it, unOpreatList)
            }
        }
        cancle.setOnClickListener {
            cancelEdite()
            operatAdapter.notifyDataSetChanged()
        }
        delete.setOnClickListener {
            if (deleteList.isEmpty()) {
                return@setOnClickListener
            }

            deleteList.forEach {
                if (it.netOrLocal) {
                    deleteLocalList.add(it)
                } else {
                    deleteNetList.add(it)
                }
            }

            if (deleteLocalList.size > 0) {
                deleteFiles(deleteLocalList)
            }
            if (deleteNetList.size > 0) {
                presenter!!.deleteFileFromOrder(deleteNetList, orderId ?: "")
            }
        }
        confirm_upload.setOnClickListener {
            bindFileToOrder()
        }
    }

    /**
     * 删除已上传文件
     */
    private fun deleteFiles(deleteLocalList: ArrayList<OrderAttachmentListBean>) {
        operatList.removeAll(deleteLocalList)
        localList.removeAll(deleteLocalList)
        if (operatList.size == 0) {
            tvRight!!.visibility = View.GONE
        }
        cancelEdite()
        if (localList.size == 0) {
            confirm_upload.visibility = View.GONE
        }
        unOperatAdapter.notifyDataSetChanged()
        operatAdapter.notifyDataSetChanged()
    }

    /**
     * 删除按钮状态
     */
    private fun deleteBntStatus() {
        if (deleteList.isEmpty()) {
            delete.setBackgroundColor(UIUtils.getColor(R.color.orange_50))
        } else {
            delete.setBackgroundColor(UIUtils.getColor(R.color.color_orange))
        }
    }

    /**
     * 本地上传附件 绑定到订单
     */
    private fun bindFileToOrder() {
        presenter!!.bindFileToOrder(orderId, localList)
    }

    /**
     * 预览附件
     */
    private fun previewFileAndImage(it: Int, list: ArrayList<OrderAttachmentListBean>) {

        rxPermissions!!.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { aBoolean ->
                    if (aBoolean!!) {
                        val path = list.get(it).attachment_url
                        if (path.toUpperCase().endsWith("BMP")
                                || path.toUpperCase().endsWith("JPG")
                                || path.toUpperCase().endsWith("JPEG")
                                || path.toUpperCase().endsWith("PNG")
                        ) {
                            val intent = Intent(activity!!, GalleryActivity::class.java)
                            intent.putStringArrayListExtra(IntentDataDef.SEE_DETAIL_IMAGE, arrayListOf(list[it].attachment_url))
                            intent.putExtra(IntentDataDef.FROM_WHERE, true)
                            intent.putExtra(IntentDataDef.SEE_DETAIL_IMAGE_INDEX, 0)
                            startActivity(intent)
                        } else {
                            if (list.get(it).originPath != null && !"".equals(list.get(it).originPath)) {
                                FileDisplayActivity.show(this.context!!, list.get(it).originPath)//本地路径
                            } else {
                                FileDisplayActivity.show(this.context!!, list.get(it).attachment_url)//网络路径（需先下载然后打开）
                            }
                        }
                    }else{
                        ToastUtil.showCustomToast("请去设置页打开存储权限")
                    }
                }
    }

    /**
     * 打开相册
     */
    @SuppressLint("CheckResult")
    private fun pickPhoto() {
        if (unOpreatList.size + operatList.size >= 99) {
            ToastUtil.showCustomToast("附件上传最多99个")
            return
        }
        rxPermissions!!.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { aBoolean ->
                    if (aBoolean!!) {
                        val intent = Intent(Intent.ACTION_PICK, null)
                        // 如果要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
                        startActivityForResult(intent, ActiveResultDef.SELECT_PHOTO)
                    }
                }
    }

    /**
     * 手机文档列表
     */
    private fun gotoFileList() {
        if (unOpreatList.size + operatList.size >= 99) {
            ToastUtil.showCustomToast("附件上传最多99个")
            return
        }
        WordPdfExcFileImageListActivity.wordPdfExcFileListActivityResult(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ActiveResultDef.FILE_RESULT_CODE && resultCode == ActiveResultDef.FILE_RESULT_CODE) {
            val addFileInfo = data!!.getSerializableExtra(IntentDataDef.FILE_UPLOAD_TYPE) as AddFileInfo
            presenter!!.uploadFile(addFileInfo.path)
        } else if (requestCode == ActiveResultDef.SELECT_PHOTO) {
            if (data == null) return
            try {
                val selectedImage = data.data!!
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = activity!!.contentResolver.query(selectedImage, filePathColumn, null, null, null)!!
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val picturePath = cursor.getString(columnIndex)
                cursor.close()
                presenter!!.uploadFile(picturePath)
//                ToastUtil.showToast("路径--》$picturePath")
            } catch (e: Exception) {
//
            }
        }
    }

    /**
     * 设置数据
     */
    fun setData(fileList: ArrayList<OrderAttachmentListBean>?, fromType: String?, orderId: String?) {
        if (fileList != null) {
            operatList = fileList
        }
        this.fromType = fromType
        this.orderId = orderId
    }

    companion object {
        fun newInstance(): AttachmentFileFragment {
            val fragment = AttachmentFileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    override fun uploadError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
        if (statusCode == 499) {
        } else {
            SystemDialog.Builder(activity())
                    .setTitle("提示")
                    .setMessage("1.文档附件仅支持WORD、EXCEL、PDF。\n" +
                            "2.图片附件格式支持BMP、JPG、JPEG、PNG。\n" +
                            "3.文件大小需小于10M。")
                    .setConfirmBtn("知道了", null)
                    .build()
                    .show()
        }

    }

    /**
     * 上传成功
     */
    override fun uploadSucess(orderAttachmentListBean: OrderAttachmentListBean) {
        ToastUtil.showIconToast(com.xfs.fsyuncai.art.base.R.drawable.toast_done)
        try {
            operatList.add(0, orderAttachmentListBean)
            localList.add(0, orderAttachmentListBean)
            if (operatList.size > 0) {
                tvRight!!.visibility = View.VISIBLE
            }
            if (localList.size > 0 && !"balance".equals(fromType)) {
                confirm_upload.visibility = View.VISIBLE
            }
            operatAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            Log.e("异常解析：", "" + e)
        }
    }

    /**
     * 订单附件列表查询成功
     */
    override fun sucessQuery(orderQuerySucessFile: OrderQuerySucessFile) {
        if (orderQuerySucessFile.errorCode == 0) {
            this.operateFlag = orderQuerySucessFile.data.isOperate_flag
            unOpreatList.addAll(orderQuerySucessFile.data.unOperateOrderAttachmentList)
            operatList.addAll(orderQuerySucessFile.data.operateOrderAttachmentList)

            if (!operateFlag && unOpreatList.isEmpty() && operatList.isEmpty()) {
                emptyView.setView(EmptyView.TYPE.EMPTY)
                emptyView.visibility = View.VISIBLE
                has_upload_file_list_not_click.visibility = View.GONE
                has_upload_file_list.visibility = View.GONE
                emptyView.setErrorMsg("没有上传的附件")
                emptyView.setErrorImg(R.drawable.error_no_result)
                return
            }
            if (orderQuerySucessFile.data.isOperate_flag) {//可以编辑操作
                ll_add_flie.visibility = View.VISIBLE
            } else {//已下单，已审批，已结算，已取消  不可以添加，删除
                ll_add_flie.visibility = View.GONE
                ll_add_flie_noclick.visibility = View.GONE
            }

            unOperatAdapter.notifyDataSetChanged()
            operatAdapter.notifyDataSetChanged()
            if (operatList.size > 0) {
                tvRight!!.visibility = View.VISIBLE
            }
            if (localList.size > 0 && !"balance".equals(fromType)) {
                confirm_upload.visibility = View.VISIBLE
            }

        }
    }

    /**
     * 本地上传附件绑定成功
     */
    override fun bindSucess() {
        activity!!.finish()
    }

    override fun deleteSucess() {
        deleteFiles(deleteNetList)
    }

    inner class ListApapter(list: ArrayList<OrderAttachmentListBean>, cnt: Context)
        : BaseRvAdapter<OrderAttachmentListBean>(list, R.layout.item_has_upload_file_list, cnt) {
        override fun onBindView(holder: Companion.BaseRvHolder, data: OrderAttachmentListBean) {
            try {
                val checkBox = holder.findViewById<CheckBox>(R.id.chckedfile)
                if (tvRight!!.isChecked) {
                    checkBox.visibility = View.VISIBLE
                } else {
                    checkBox.visibility = View.GONE
                    checkBox.isChecked = false
                }
                checkBox.isChecked = data.checkBox
                val item_file_img = holder.findViewById<ImageView>(R.id.item_file_img)
                if (data.attachment_name.endsWith("BMP", true)
                        || data.attachment_name.endsWith("JPG", true)
                        || data.attachment_name.endsWith("JPEG", true)
                        || data.attachment_name.endsWith("PNG", true)
                ) {
                    item_file_img.setImageResource(R.drawable.image_icon)
                } else {
                    item_file_img.setImageResource(R.drawable.file_icon)
                }
                holder.setText(R.id.item_file_name, data.attachment_name)
                holder.setText(R.id.upload_account_num, "上传账号： " + data.login_account)
                holder.setText(R.id.item_file_time, "上传时间： ${data.created_at}")
            } catch (e: Exception) {
//
            }
        }
    }

    inner class NoCLickListApapter(list: ArrayList<OrderAttachmentListBean>, cnt: Context)
        : BaseRvAdapter<OrderAttachmentListBean>(list, R.layout.item_has_upload_file_list_no_click, cnt) {
        override fun onBindView(holder: Companion.BaseRvHolder, data: OrderAttachmentListBean) {
            try {
                val item_file_img = holder.findViewById<ImageView>(R.id.item_file_img_noclick)
                if (data.attachment_name.endsWith("BMP", true)
                        || data.attachment_name.endsWith("JPG", true)
                        || data.attachment_name.endsWith("JPEG", true)
                        || data.attachment_name.endsWith("PNG", true)
                ) {
                    item_file_img.setImageResource(R.drawable.image_icon)
                } else {
                    item_file_img.setImageResource(R.drawable.file_icon)
                }
                holder.setText(R.id.item_file_name_noclick, data.attachment_name)
                holder.setText(R.id.upload_account_num_noclick, "上传账号： " + data.login_account)
                holder.setText(R.id.item_file_time_noclick, "上传时间： ${data.created_at}")
                if (!operateFlag || !tvRight!!.isChecked) {
                    item_file_img.alpha = 1F
                    holder.findViewById<TextView>(R.id.item_file_name_noclick).setTextColor(UIUtils.getColor(R.color.text_color_light))
                    holder.findViewById<TextView>(R.id.upload_account_num_noclick).setTextColor(UIUtils.getColor(R.color.text_color_light))
                    holder.findViewById<TextView>(R.id.item_file_time_noclick).setTextColor(UIUtils.getColor(R.color.text_color_light))
                } else {
                    item_file_img.alpha = 0.5F
                    holder.findViewById<TextView>(R.id.item_file_name_noclick).setTextColor(UIUtils.getColor(R.color.btn_border_color))
                    holder.findViewById<TextView>(R.id.upload_account_num_noclick).setTextColor(UIUtils.getColor(R.color.btn_border_color))
                    holder.findViewById<TextView>(R.id.item_file_time_noclick).setTextColor(UIUtils.getColor(R.color.btn_border_color))
                }
            } catch (e: Exception) {
                //
            }

        }
    }
}
