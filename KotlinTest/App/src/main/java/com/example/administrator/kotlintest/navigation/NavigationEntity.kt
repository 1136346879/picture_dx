package com.xfs.fsyuncai.main.navigation

import android.content.Context
import androidx.fragment.app.FragmentPagerAdapter
import com.example.administrator.kotlintest.navigation.NavigationView

/**
 * Created by kangf on 2018/6/18.
 */
class NavigationEntity(navigationView: NavigationView) {

    private var tabs: IntArray? = null

    private var imgs: IntArray? = null

    private var adapter: androidx.fragment.app.FragmentPagerAdapter? = null

    private var context: Context? = null

    fun getTabs(): IntArray? {
        return tabs
    }

    fun setTabs(tabs: IntArray) {
        this.tabs = tabs
    }

    fun getImgs(): IntArray? {
        return imgs
    }

    fun setImgs(imgs: IntArray) {
        this.imgs = imgs
    }

    fun getAdapter(): androidx.fragment.app.FragmentPagerAdapter? {
        return adapter
    }

    fun setAdapter(adapter: androidx.fragment.app.FragmentPagerAdapter) {
        this.adapter = adapter
    }

    fun getContext(): Context? {
        return context
    }

    fun setContext(context: Context) {
        this.context = context
    }

    init {
        this.tabs = navigationView.tabs()
        this.imgs = navigationView.tabImages()
        this.adapter = navigationView.tabAdapter()
        this.context = navigationView.context()
    }

}