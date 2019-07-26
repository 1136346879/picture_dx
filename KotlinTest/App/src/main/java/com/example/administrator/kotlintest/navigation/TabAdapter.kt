package com.example.administrator.kotlintest.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.ArrayList

/**
 * Created by kangf on 2018/6/18.
 */
class TabAdapter(fm: FragmentManager, private val fragments: ArrayList<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return Constants.tabs.get(position)
//    }
}