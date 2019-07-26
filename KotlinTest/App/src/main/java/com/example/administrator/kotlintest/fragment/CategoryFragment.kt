package com.example.administrator.kotlintest.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.bus.RxBus
import com.example.administrator.kotlintest.ui.adapter.BaseRvHeaderAndFooterWrapper
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.xfs.fsyuncai.entity.CategoryAllEntity
import com.xfs.fsyuncai.entity.CategoryTopEntity
import com.xfs.fsyuncai.entity.CitySelectEntityRxBus
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.toolbar_category.*
import org.jetbrains.anko.find

/**
 * Created by kangf on 2018/6/18.
 */
class CategoryFragment : BaseTabFragment() {

    override fun layoutResId(): Int = R.layout.fragment_category
    private var mTopCategoryDatas: ArrayList<CategoryTopEntity> = ArrayList()
    private var wrapper: BaseRvHeaderAndFooterWrapper? = null
    private lateinit var mHeaderView: View
    private lateinit var tvTopCategory: TextView
    //默认请求第一个分类
    private var position = 0
    val handler = Handler()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().toFlowable(CitySelectEntityRxBus::class.java)
                .bindToLifecycle(this)
                .subscribe {
                }
    }

    override fun init() {
    }

    override fun logic() {
        mEmptyView.setOnClickEmpty {
        }

        etSearch.setOnClickListener {
            val search = etSearch.text.toString().trim()
            ARouter.getInstance()
                    .build(RouterApi.GoodsCenter.ROUTER_GOODS_SEARCH)
                    .withString("search", search)
                    .navigation()
        }

        var isClickTop = true
        }
    companion object {
        fun newInstance() = CategoryFragment()
    }
}