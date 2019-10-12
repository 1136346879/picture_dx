package com.example.administrator.kotlintest.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import android.view.*
import com.example.administrator.kotlintest.R

/**
 * Created by kangf on 2018/8/21.
 */
abstract class BaseBottomDialogFragment : androidx.fragment.app.DialogFragment() {

    override fun onStart() {
        super.onStart()
        val window = dialog.window
        val params = window?.attributes
        params?.gravity = Gravity.BOTTOM
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = params
        window?.setBackgroundDrawableResource(R.color.transparent)
        dialog.setTitle(null)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.ActionSheetDialogStyle)
        base()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId(), null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        logic()
    }

    override fun dismiss() {
        if (isShowing())
            super.dismiss()
    }

    override fun dismissAllowingStateLoss() {
        if (isShowing())
            super.dismissAllowingStateLoss()
    }

    abstract fun layoutResId(): Int

    abstract fun init()

    abstract fun logic()

    open fun base() {

    }


    fun isShowing(): Boolean {
        return dialog != null && dialog.isShowing
    }

    @SuppressLint("CommitTransaction")
    fun showDia(manager: androidx.fragment.app.FragmentManager, isResume: Boolean = true) {
        if (!isShowing()) {
            if (isResume) {
                if (!isAdded) {
                    show(manager, BaseBottomDialogFragment::class.java.simpleName)
                } else {
                    show(manager.beginTransaction(), BaseBottomDialogFragment::class.java.simpleName)
                }
            } else {
                manager.beginTransaction().apply {
                    if (!isAdded) {
                        add(this@BaseBottomDialogFragment, tag)
                    } else {
                        show(this@BaseBottomDialogFragment)
                    }
                    commitAllowingStateLoss()
                }

            }
        }
    }


    fun dissmissDia(isResume: Boolean) {
        if (isResume) dismiss()
        else dismissAllowingStateLoss()
    }
}