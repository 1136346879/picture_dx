package com.xfs.fsyuncai.bridge.retrofit

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.widget.TLog

/**
 * Created by kangf on 2018/6/15.
 */
object LoadingDialog {

    private var dialog: Dialog? = null

    //loading 是否覆盖整个页面
    fun show(context: Context, msg: String,coverScreen:Boolean) {
        if (dialog != null) {
            dialog!!.dismiss()
            dialog = null
        }
        dialog = createLoadingDialog(context, msg,coverScreen)
        try {
            dialog!!.show()
        } catch (e: Exception) {
            TLog.i("loading_error>>>>>>>>>>" + e.message)
        }
    }

    fun dissmiss() {
        if (dialog != null && dialog!!.isShowing)
            dialog?.dismiss()
    }

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    fun createLoadingDialog(context: Context, msg: String,coverScreen: Boolean): Dialog {
        val inflater = LayoutInflater.from(context)
        var view : View?= null
        view = if(coverScreen)
            inflater.inflate(R.layout.dialog_progress_cover_screen, null)
        else
            inflater.inflate(R.layout.dialog_progress, null)

        val layout = view.findViewById(R.id.dialog_view) as FrameLayout// 加载布局
        val text = view.findViewById(R.id.loadingTv) as TextView
        if (TextUtils.isEmpty(msg)) text.visibility = View.GONE
        else text.text = msg
        val loadingImage = view.findViewById(R.id.loadingIv) as ImageView
        val animationDrawable = AnimationUtils.loadAnimation(context, R.anim.load_animation)
        loadingImage.startAnimation(animationDrawable)
        val mDialog = Dialog(context, R.style.loading_dialog)
        mDialog.setContentView(layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT))
        mDialog.setCanceledOnTouchOutside(false)
        return mDialog

    }


}