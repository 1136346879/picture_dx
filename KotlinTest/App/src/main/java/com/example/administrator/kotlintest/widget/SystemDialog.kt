package com.example.administrator.kotlintest.widget

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import android.widget.TextView
import com.example.administrator.kotlintest.R

/**
 * Created by kangf on 2018/6/1.
 *
 * cancel为橙色按钮
 * confirm为白色按钮
 */
open class SystemDialog : Dialog {

    var tvTitle: TextView? = null
    var tvMessage: TextView? = null
    var ll_sg: LinearLayout? = null
    private var tvConfirm: TextView? = null
    private var tvCancel: TextView? = null
    private var liviewLayout: LinearLayout? = null

    private var mOnBackListener: OnBackListener? = null

    interface OnBackListener {
        fun back()
    }

//    fun setOnBackListener(action: () -> Unit) {
//        mOnBackListener = object : OnBackListener {
//            override fun back() {
//                action()
//            }
//        }
//    }

    private var view: View? = null

    constructor(context: Context) : super(context, R.style.SystemDialog) {
        init()
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    protected constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?)
            : super(context, cancelable, cancelListener)

    private fun init() {
        //获取当前Activity所在的窗体
        val dialogWindow = this.window
        // 隐藏标题栏
        dialogWindow!!.requestFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.base_dialog_system)
        dialogWindow.setGravity(Gravity.CENTER)
        //获得窗体的属性
        val lp = dialogWindow.attributes
        lp.gravity = Gravity.CENTER
        //       将属性设置给窗体
        dialogWindow.attributes = lp

        tvTitle = findViewById(R.id.tvTitle)
        tvMessage = findViewById(R.id.tvMessage)
        ll_sg = findViewById(R.id.ll_sg)
        tvConfirm = findViewById(R.id.tvConfirm)
        tvCancel = findViewById(R.id.tvCancel)
        liviewLayout = findViewById(R.id.listview_layout)

        view = findViewById(R.id.view)
    }

    /**
     *  显示列表
     */
    fun setListViewVisibliy() {
        tvTitle!!.visibility = View.VISIBLE
        ll_sg!!.visibility = View.GONE
        liviewLayout!!.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        mOnBackListener?.back()
        super.onBackPressed()
    }

    class Builder(private val context: Context) {

        private var systemDialog: SystemDialog? = null

        private var title = ""
        private var msg = ""
        private var cancel = ""
        private var confirm = ""
        private var view: View? = null
        private var cancelable = true

        private var cancelLis: View.OnClickListener? = null
        private var confirmLis: View.OnClickListener? = null

        fun setCancelAble(isCancel: Boolean): Builder {
            this.cancelable = isCancel
            return this
        }

        fun setTitle(text: String): Builder {
            this.title = text
            return this
        }

        fun setMessage(text: String): Builder {
            this.msg = text
            return this
        }

        fun setCancelBtn(text: String, listener: View.OnClickListener?): Builder {
            this.cancel = text
            this.cancelLis = listener
            return this
        }

        fun setConfirmBtn(text: String, listener: View.OnClickListener?): Builder {
            this.confirm = text
            this.confirmLis = listener
            return this
        }

        /**
         * 设置一个view到dialog的中间部位
         */
        fun setView(view: View): Builder {
            this.view = view
            return this
        }

        fun build(): SystemDialog {
            systemDialog = SystemDialog(context)
            if (TextUtils.isEmpty(title)) {
                systemDialog!!.tvTitle!!.visibility = View.GONE
            }
            if (TextUtils.isEmpty(msg)) {
                systemDialog!!.tvMessage!!.visibility = View.GONE
            }
            if (TextUtils.isEmpty(cancel)) {
                systemDialog!!.tvCancel!!.visibility = View.GONE
                systemDialog!!.view!!.visibility = View.GONE
                systemDialog!!.tvConfirm!!.setBackgroundResource(R.drawable.dia_ios_bottom_orange)
            }
            if (TextUtils.isEmpty(confirm)) {
                systemDialog!!.tvConfirm!!.visibility = View.GONE
                systemDialog!!.view!!.visibility = View.GONE
                systemDialog!!.tvCancel!!.setBackgroundResource(R.drawable.dia_ios_bottom_white)
            }
            if (view != null) {
                systemDialog!!.liviewLayout!!.addView(view)
            }
            systemDialog!!.tvTitle!!.text = title
            systemDialog!!.tvMessage!!.text = msg
            if (msg.length > 20 || title.isNotEmpty()) {
                systemDialog!!.tvMessage!!.textSize = 14f
            }
            systemDialog!!.tvCancel!!.text = cancel
            systemDialog!!.tvConfirm!!.text = confirm
            systemDialog!!.setCancelable(cancelable)
            systemDialog!!.tvConfirm!!.setOnClickListener { v ->
                if (confirmLis != null) {
                    confirmLis!!.onClick(v)
                }
                systemDialog!!.dismiss()

            }
            systemDialog!!.tvCancel!!.setOnClickListener { v ->
                if (cancelLis != null) {
                    cancelLis!!.onClick(v)
                    systemDialog!!.dismiss()
                }
                systemDialog!!.dismiss()
            }

            return systemDialog!!
        }
    }
}
