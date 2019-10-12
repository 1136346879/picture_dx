package com.example.administrator.kotlintest.navigation

import android.content.Context
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by kangf on 2018/6/18.
 */
interface NavigationView {

    fun tabs():IntArray

    fun tabImages():IntArray

    fun tabAdapter(): androidx.fragment.app.FragmentPagerAdapter

    fun context():Context

    fun clearNum()

}