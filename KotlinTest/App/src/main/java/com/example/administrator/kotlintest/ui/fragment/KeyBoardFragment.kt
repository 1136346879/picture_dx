package com.example.administrator.kotlintest.ui.fragment

import android.view.View
import com.example.administrator.kotlintest.R
import jsc.kit.keyboard.KeyUtils
import jsc.kit.keyboard.KeyBoardView

class KeyBoardFragment : BaseAppFragment(), View.OnClickListener {
    override fun onClick(v: View?) {

        if (v!!.getId() == R.id.dragview) {
//            showDialog()
        } else {
            keyboardView!!.toggleVisibility()
        }

    }

    private var keyboardView: KeyBoardView? = null
    override fun layoutResId(): Int {
        return R.layout.fragment_keyboard
    }

    override fun init() {
        keyboardView =  KeyBoardView(view!!.context)
        keyboardView!!.addAllInputView(view)
        KeyUtils.init(activity!!.window, keyboardView!!)
    }

    override fun logic() {
    }

    companion object {
        fun newInstance() = KeyBoardFragment()
    }

    override fun onResume() {
        super.onResume()
        keyboardView!!.onResume()
    }


    override fun onPause() {
        keyboardView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        keyboardView!!.onDestroy()
        super.onDestroy()
    }
}