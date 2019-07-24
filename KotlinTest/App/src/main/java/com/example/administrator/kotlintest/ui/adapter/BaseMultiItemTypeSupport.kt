package com.example.administrator.kotlintest.ui.adapter

/**
 * Created by Kangfan on 2017/11/10.
 */

interface BaseMultiItemTypeSupport<T> {
    fun getLayoutId(itemType: Int): Int

    fun getItemViewType(position: Int, t: T): Int
}