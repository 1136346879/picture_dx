package com.example.administrator.kotlintest.navigation

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

/**
 * Created by kangf on 2018/6/18.
 */
class TabAdapter(fm: androidx.fragment.app.FragmentManager, private val fragments: ArrayList<androidx.fragment.app.Fragment>) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {

    }
}