package com.xfs.fsyuncai.attachmentfile.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.*
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xfs.fsyuncai.art.base.common.ActiveResultDef
import com.xfs.fsyuncai.art.base.common.IntentDataDef
import com.xfs.fsyuncai.art.base.utils.FileSizeUtils
import com.xfs.fsyuncai.art.base.utils.ToastUtil
import com.xfs.fsyuncai.art.base.utils.UIUtils
import com.xfs.fsyuncai.art.base.view.fragment.BaseFragment
import com.xfs.fsyuncai.art.base.weiget.EmptyView
import com.xfs.fsyuncai.art.base.weiget.recyclerview.divider.SpaceItemDecoration
import com.xfs.fsyuncai.attachmentfile.data.AddFileInfo
import com.xfs.fsyuncai.attachmentfile.R
import com.xfs.fsyuncai.attachmentfile.ui.adapter.FileListAdapter
import kotlinx.android.synthetic.main.fragment_word_pdf_excel_file.*
import kotlinx.android.synthetic.main.toolbar_common_file.*
import java.io.File
import java.util.*

class WordPdfExcelFileImageListFragment : BaseFragment() {
    private var rxPermissions: RxPermissions? = null
    private lateinit var adapter: FileListAdapter
    private val list = ArrayList<AddFileInfo>()
    private val filePath = Environment.getExternalStorageDirectory().toString() + File.separator
    private var mFileListListener: IBindFileListListener? = null

    interface IBindFileListListener {

        fun readOutFile(filePath: String)

        /**
         * @param filePath 文件夹路径
         * todo fileSuffix 条件，用于过滤
         */
        fun readFile(filePath: String)
    }

    override fun layoutResId(): Int {
        return R.layout.fragment_word_pdf_excel_file
    }

    override fun init() {
        rxPermissions = RxPermissions(this)
        tvTitle.text = "文档选择"
        tvTitle.visibility = View.VISIBLE
        iv_right.visibility = View.GONE
        tv_right.visibility = View.GONE
        getFileListData()
        adapter = FileListAdapter(list, this.context!!)
        fileList.let {
            val manager = LinearLayoutManager(this.context)
            it.addItemDecoration(SpaceItemDecoration(UIUtils.dip2px(1)))
            it.layoutManager = manager
            it.adapter = adapter
        }
        mEmptyView.setOnClickEmpty { mFileListListener?.readFile(filePath) }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IBindFileListListener) {
            mFileListListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement BindFileListListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mFileListListener = null
    }

    @SuppressLint("CheckResult")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private fun getFileListData() {
        rxPermissions!!.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe {
                    if (it) {
                        // 如果有权限就去读取所有文件
                        mFileListListener?.readFile(filePath)
                    }
                }
    }

    /**
     * 获取文件回调函数
     */
    fun getFiles(files: List<File>) {
        if (files.isEmpty()) {
            mEmptyView.setView(EmptyView.TYPE.EMPTY)
            ToastUtil.showCustomToast("没有文件")
            return
        }
        mEmptyView.setView(EmptyView.TYPE.NO_ERROR)
        files.map { file ->
            val info = AddFileInfo(file.name, FileSizeUtils.getAutoFileOrFilesSize(file), file.absolutePath)
            list.add(info)
        }
        adapter.notifyDataSetChanged()
    }

    /**
     * 读取文件失败回调函数
     */
    fun readError(err: String?) {
        mEmptyView?.setView(EmptyView.TYPE.ERROR)
        mEmptyView?.setErrorMsg(err ?: "读取文件失败")
    }

    override fun logic() {
        ivBack.setOnClickListener { activity!!.finish() }
        adapter.setOnClickItem {
            finishResultActivity(it)
        }
    }

    private fun finishResultActivity(it: Int) {
        val intent = Intent()
        intent.putExtra(IntentDataDef.FILE_UPLOAD_TYPE, list[it])
        this.activity!!.setResult(ActiveResultDef.FILE_RESULT_CODE, intent)
        this.activity!!.finish()
    }

    companion object {
        fun newInstance(): WordPdfExcelFileImageListFragment {
            val fragment = WordPdfExcelFileImageListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}



