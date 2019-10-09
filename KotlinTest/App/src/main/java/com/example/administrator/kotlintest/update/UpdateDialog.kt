@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.example.administrator.kotlintest.update

import android.Manifest
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.net.Uri
import android.os.IBinder
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.entity.AppUpdateEntity
import com.example.administrator.kotlintest.util.SPUtils
import com.example.baselibrary.common.ToastUtil
import com.example.baselibrary.widgets.TLog
import com.example.baselibrary.widgets.UIUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xfs.fsyuncai.main.appupdate.Constant
import kotlinx.android.synthetic.main.dialog_app_update.*
import java.io.File

/**
 *@author : HaoBoy
 *@date : 2018/11/27
 *@description :版本更新提示框
 **/

class UpdateDialog(val mContext: Context?, val result: AppUpdateEntity) : Dialog(mContext, R.style.ActionSheetDialogStyle) {

    private var progressBar: ProgressBar? = null

    private var tvProgress: TextView? = null

    private var rlProgress: RelativeLayout? = null

    private var isBindService: Boolean = false

    init {
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(true)
        @SuppressLint("InflateParams")
        val contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_app_update, null, false)
        this.setContentView(contentView)
        val params = window?.attributes
        params?.width = UIUtils.getScreenWidth() / 100 * 75
        params?.height = UIUtils.getScreenHeight() / 100 * 55
        window?.attributes = params
        val llMustUpdate = contentView.findViewById<LinearLayout>(R.id.llMustUpdate)
        val llNotMustUpdate = contentView.findViewById<LinearLayout>(R.id.llNotMustUpdate)
        val tvMustUpdate = contentView.findViewById<TextView>(R.id.tvMustUpdate)
        val tvOk = contentView.findViewById<TextView>(R.id.tvOk)
        val tvContent = contentView.findViewById<TextView>(R.id.tvContent)
        val tvCancel = contentView.findViewById<TextView>(R.id.tvCancel)
        val tvVersion = contentView.findViewById<TextView>(R.id.tvVersion)
        rlProgress = contentView.findViewById(R.id.rlProgress)
        progressBar = contentView.findViewById(R.id.progressBar)
        tvProgress = contentView.findViewById(R.id.tvProgress)
        @SuppressLint("SetTextI18n")
        tvVersion.text = "发现新版本 ${result.fileName}"
        // 设置文本内容
        if (result.title == null || result.title!!.isEmpty()) {
            tvContent.text = "新版本更新"
        } else {
            var content = ""
            result.title!!.map {
                content += (it + "\n")
            }
            tvContent.text = content
        }
        //是强制更新
        if (result.updateType == 20) {
            llMustUpdate.visibility = View.VISIBLE
        } else {
            llNotMustUpdate.visibility = View.VISIBLE
        }
        //点击取消
        tvCancel.setOnClickListener {
            dismiss()
            SPUtils.setObject(SPUtils.CURRENT_VERSION_CODE, result.latest_version_code ?: "")
            AppUpdateUtil.removeOldApk(mContext)
        }
        //点击升级
        tvOk.setOnClickListener {
            updateNow(true)
        }
        //强制更新
        tvMustUpdate.setOnClickListener {
            updateNow(false)
        }
        ivClose.setOnClickListener {
            if (result.updateType != 20) {
                dismiss()
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun updateNow(isShowClose: Boolean) {
        RxPermissions(mContext as FragmentActivity)
                .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { // will emit 2 Permission objects
                    permission ->
                    if (permission.granted) {
                        val path: String = SPUtils.getObjectForKey(Constant.SP_DOWNLOAD_PATH, "") as String
                        val fileName = File(path)
                        if (fileName.exists() && fileName.isFile) {
                            AppUpdateUtil.installApk(mContext, Uri.parse("file://$fileName"))
                        } else {
                            isBindService = AppUpdateUtil.bindsService(mContext, result.download_path, conn)
                            ToastUtil.showCustomToast("新版本正在下载")
                            rlProgress!!.visibility = View.VISIBLE
                            if (isShowClose) {
                                ivClose.visibility = View.VISIBLE
                            }
                        }
                    } else if (permission.shouldShowRequestPermissionRationale) {
                    } else {
                    }
                }
    }

    private val conn = object : ServiceConnection {

        @SuppressLint("SetTextI18n")
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as DownloadService.DownloadBinder
            val downloadService = binder.service
            //接口回调，下载进度
            downloadService.setOnProgressListener { fraction ->
//                TLog.i("下载进度：" + (fraction * 100).toInt())
                //                    bnp.setProgress((int)(fraction * 100));、1
                val p = (fraction * 100).toInt()
                progressBar!!.progress = p
                tvProgress!!.text = "下载中请稍后   " + (if (p >= 100) 100 else p).toString() + "%"
                //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                if (fraction == DownloadService.UNBIND_SERVICE && isBindService) {
                    TLog.i("下载完成！")
                    mContext!!.unbindService(this)
                    isBindService = false
                    rlProgress!!.visibility = View.GONE
                }
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {//
        }

    }

}