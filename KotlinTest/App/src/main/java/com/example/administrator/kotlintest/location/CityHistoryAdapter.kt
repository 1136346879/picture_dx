package com.example.administrator.kotlintest.location

import android.content.Context
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.entity.CityEntity

/**
*@author : HaoBoy
*@date : 2018/8/20
*@description : 城市历史纪录
**/
class CityHistoryAdapter(list: ArrayList<CityEntity.DataBean.ListBean>, context: Context) :
        BaseRvAdapter<CityEntity.DataBean.ListBean>(list, R.layout.item_city_history, context) {

    override fun onBindView(holder: Companion.BaseRvHolder, data: CityEntity.DataBean.ListBean) {
        holder.setText(R.id.tvText, data.name)
    }

}