package com.example.administrator.kotlintest.ui.activity.extactivity.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.ui.activity.extactivity.bean.ItemBean

/**
 * Created by luyao
 * on 2019/6/10 10:57
 */
class CommonAdapter(layoutResId: Int = R.layout.item_common) : BaseQuickAdapter<ItemBean, com.chad.library.adapter.base.BaseViewHolder>(layoutResId) {

    override fun convert(helper: com.chad.library.adapter.base.BaseViewHolder, item: ItemBean) {
        helper.setText(R.id.itemTv, item.name)
    }
}