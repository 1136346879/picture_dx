package com.example.administrator.kotlintest.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.widget.SystemDialog
import com.xfs.fsyuncai.entity.CategoryTopEntity
import com.xfs.fsyuncai.entity.accont.AccessManager
import java.util.*

/**
 * Created by kangf on 2018/6/18.
 */
class HomeFragment : BaseTabFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
    }

    override fun layoutResId(): Int = R.layout.fragment_home

    private var mData: ArrayList<CategoryTopEntity> = ArrayList()

    private var selectAddrDialog: SystemDialog? = null

    private var onSelectCategory: OnSelectCategory? = null


    private var isLoadTopCategory = false

    // 主要用于用户从未登录变为登录时重新请求banner
    private var isLogin = AccessManager.instance().isLogin()
    private var isShowedPaypwd = false


    fun setOnSelectCategory(onSelectCategory: OnSelectCategory?) {
        this.onSelectCategory = onSelectCategory
    }

    interface OnSelectCategory {
        fun selected(pos: Int)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0x01 && resultCode == 0x01) {
            //
            SystemDialog.Builder(context!!)
                    .setTitle("扫描失败")
                    .setMessage("仅支持商品二维码扫描，请点击“确定”重试")
                    .setCancelBtn("确定", null)
                    .build().show()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun init() {
    }

    override fun logic() {
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}