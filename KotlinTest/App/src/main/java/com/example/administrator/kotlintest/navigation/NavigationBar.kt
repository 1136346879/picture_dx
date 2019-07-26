package com.example.administrator.kotlintest.navigation

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.kotlintest.R
import com.xfs.fsyuncai.extend.isLogin
import com.xfs.fsyuncai.main.navigation.NavigationEntity

/**
 * Created by kangf on 2018/6/18.
 */
class NavigationBar (info: NavigationEntity) {

    private var mInfo: NavigationEntity? = info

    private var container: ViewGroup? = null

    private var mAdapter: FragmentPagerAdapter? = null

    private var tabView: View? = null

    private var tabTv: TextView? = null

    private var tabIv: ImageView? = null

    private var tvHint: TextView? = null

    private var position = 0


    private var tablayout: TabLayout? = null
    //默认为0
    private var mIndex = 0

    private var mCurrentIndex = 0

    fun setNavigation(tabLayout: TabLayout, container: ViewGroup,mIndex : Int) {
        this.tablayout = tablayout
        this.container = container
        this.mIndex = mIndex
        this.mCurrentIndex = mIndex
        tabLayout.addOnTabSelectedListener(mSelectedTab)
        tabLayout.tabMode = TabLayout.MODE_FIXED
        val len = mInfo?.getTabs()?.size
        for (i in 0 until len!!) {
            tabView = LayoutInflater.from(mInfo?.getContext()).inflate(R.layout.tab_navigation_layout, null)
            tabTv = tabView!!.findViewById(R.id.tabText)
            tabIv = tabView!!.findViewById(R.id.tabImage)
            if (i == 1) {
                tvHint = tabView!!.findViewById(R.id.tvHint)
            }
            tabTv!!.setText(mInfo?.getTabs()!![i])
            tabIv!!.setImageResource(mInfo?.getImgs()!![i])
            val tab = tabLayout.newTab()
            tab.customView = tabView
            tabLayout.addTab(tab, i == mIndex)
        }
    }

    fun showHint(num: String) {
        if (tvHint != null) {
            tvHint!!.visibility = View.VISIBLE
            tvHint!!.text = num
        }
    }

    fun HideHint() {
        if (tvHint != null) {
            tvHint!!.visibility = View.GONE
        }
    }

    fun hintIsVisi(): Boolean {
        return if (tvHint != null) tvHint!!.visibility == View.VISIBLE else false
    }

    private val mSelectedTab = object : TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab) {
            position = if(mCurrentIndex == 0){
                tab.position
            }else{
                mCurrentIndex
            }
            if (position == 3 || position == 4) {
                mInfo?.getContext()?.let {
                    isLogin ({
                        val fragment = mAdapter?.instantiateItem(container!!, position) as Fragment
                        mIndex = position
                        mAdapter?.setPrimaryItem(container!!, position, fragment)
                        mAdapter?.finishUpdate(container!!)
                    }, {
                        tablayout?.getTabAt(0)?.select()
                    })
                }
                mCurrentIndex = 0
                return
            }
            val fragment = mAdapter?.instantiateItem(container!!, position) as Fragment
            mAdapter?.setPrimaryItem(container!!, position, fragment)
            mIndex = position
            mAdapter?.finishUpdate(container!!)
            mCurrentIndex = 0
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {

        }

        override fun onTabReselected(tab: TabLayout.Tab) {

        }
    }

    init {
        mAdapter = info.getAdapter()
    }
    fun getCurrentIndex():Int{
        return mIndex
    }
}