package com.xfs.fsyuncai.main.ui.online

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.WebUrlDef
import com.example.administrator.kotlintest.fragment.BaseTabFragment
import com.tencent.smtt.sdk.*
import com.xfs.fsyuncai.entity.accont.AccessManager
import kotlinx.android.synthetic.main.fragment_online.*
import kotlinx.android.synthetic.main.include_main_no_network.*
import kotlinx.android.synthetic.main.toolbar_online.*

/**
 * Created by kangf on 2018/6/18.
 */
class OnlineFragment : BaseTabFragment() {

    private var toUrl: String? = null
    private var isRootPage: Boolean = false
    private var personOnline: String? = "在线客服"

    private val FILE_SELECT_CODE = 0
    private val REQUEST_SELECT_FILE = 1
    private val FILECHOOSER_RESULTCODE = 1
    private var mFilePathCallback: ValueCallback<Uri>? = null
    private var mFilePathCallbacks: ValueCallback<Array<Uri>>? = null

    override fun layoutResId(): Int {
        return R.layout.fragment_online
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        val showBack = arguments?.getInt(ARG_SHOW_BACK, View.GONE)
        ivBack.visibility = showBack ?: View.GONE

        ivBack.setOnClickListener {
            activity?.finish()
        }
        tvReload.setOnClickListener {
            my_webview.reload()
        }
        my_webview.requestFocus()
        val settings = my_webview.settings
        @Suppress("DEPRECATION")
        settings.javaScriptEnabled = true
        settings.useWideViewPort = true
        settings.defaultTextEncodingName = "UTF-8"
        settings.loadWithOverviewMode = true
        // 支持缩放
        settings.setSupportZoom(false)
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.setAppCacheEnabled(false)
        settings.domStorageEnabled = true
        settings.blockNetworkImage = false//解决图片不显示
        settings.useWideViewPort = true
        settings.allowContentAccess = true // 是否可访问Content Provider的资源，默认值 true
        settings.allowFileAccess = true
        my_webview.setOnKeyListener { _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK && my_webview.canGoBack()) {  //表示按返回键 时的操作
                    my_webview.goBack()   //后退
                    true  //已处理     返回true表示被处理否则返回false
                } else {
                    false
                }
            } else {
                false
            }
        }

        my_webview.webViewClient = object : WebViewClient() {
            var errorUrl: String? = null
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                errorUrl = null
                webProgressBar!!.visibility = View.VISIBLE
                isRootPage = toUrl!!.startsWith(url!!)
                super.onPageStarted(view, url, favicon)
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url != null && url != errorUrl) {
                    //android4.4.4加载错误界面data:text/html,chromewebdata
                    if (url.startsWith("http") || url.startsWith("https")) {
                        inNoNetWork!!.visibility = View.GONE
                        my_webview!!.visibility = View.VISIBLE
                    }
                }
            }

            @Suppress("DEPRECATION")
            override fun onReceivedError(view: WebView?, errorCode: Int,
                                         description: String, failingUrl: String) {
                errorUrl = failingUrl
                super.onReceivedError(view, errorCode, description, failingUrl)
                inNoNetWork!!.visibility = View.VISIBLE
                my_webview!!.visibility = View.INVISIBLE
            }
        }

        my_webview.webChromeClient = object : WebChromeClient() {

            fun openFileChooser(uploadMsg: ValueCallback<Uri>) {
                mFilePathCallback = uploadMsg
                val i = Intent(Intent.ACTION_GET_CONTENT)
                i.addCategory(Intent.CATEGORY_OPENABLE)
                i.type = "image/*"
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE)
            }
            // Android 3.0+
            fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String) {
                mFilePathCallback = uploadMsg
                val i = Intent(Intent.ACTION_GET_CONTENT)
                i.addCategory(Intent.CATEGORY_OPENABLE)
                i.type = "*/*"
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE)
            }

            // Android 4.1+ 会调用这个
            override fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String, capture: String) {
                mFilePathCallback = uploadMsg
                val i = Intent(Intent.ACTION_GET_CONTENT)
                i.addCategory(Intent.CATEGORY_OPENABLE)
                i.type = "image/*"
                startActivityForResult(
                        Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                webProgressBar?.let {
                    if (newProgress == 100) {
                        webProgressBar!!.visibility = View.GONE
                    } else {
                        if (View.GONE == webProgressBar!!.visibility && newProgress > 0) {
                            webProgressBar!!.visibility = View.VISIBLE
                        }
                        webProgressBar!!.progress = newProgress
                    }
                }
                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                tvTitle!!.text = "在线客服"
            }
        }

        isRootPage = true
    }

    override fun logic() {
        tvTitle.text = personOnline
//        var parma = ""
//        if (AccessManager.instance().isLogin()) {
//            parma = "&account=" + AccessManager.instance().loginAccount()
//        }
//        toUrl = WebUrlDef.ONLINE_CONTACTS_URL + parma
//        my_webview!!.loadUrl(toUrl)
    }

    override fun lazyLoad() {
        var parma = ""
        if (AccessManager.instance().isLogin()) {
            parma = "&account=" + AccessManager.instance().loginAccount()
        }
        toUrl = WebUrlDef.ONLINE_CONTACTS_URL + parma
        my_webview!!.loadUrl(toUrl)
    }

    override fun onDestroy() {
        my_webview?.webViewClient = null
        my_webview?.webChromeClient = null
        my_webview?.destroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        mFilePathCallback = if (requestCode == FILECHOOSER_RESULTCODE && resultCode == RESULT_OK) {
            val uri = intent?.data
            //将图片显示到webView
            mFilePathCallback?.onReceiveValue(uri)
            null
        } else if (requestCode == REQUEST_SELECT_FILE) {
            mFilePathCallback?.onReceiveValue(null)
            null
        } else {
            mFilePathCallback?.onReceiveValue(null)
            null
        }
    }

    companion object {

        const val ARG_SHOW_BACK = "ARG_SHOW_BACK"

        fun newInstance(showBack: Int) = OnlineFragment().apply {

            arguments = Bundle().apply {
                putInt(ARG_SHOW_BACK, showBack)
            }
        }
    }
}