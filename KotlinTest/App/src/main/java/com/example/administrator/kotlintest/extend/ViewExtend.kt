package com.xfs.fsyuncai.extend

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by kangf on 2018/9/23.
 */
inline fun View.popSoftKeyboard(isShow: Boolean) {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (isShow) {
        this.requestFocus()
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
    } else {
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }
}