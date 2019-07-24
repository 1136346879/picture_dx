package com.example.administrator.kotlintest.location

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.entity.CityEntity

/**
*@author : HaoBoy
*@date : 2018/8/24
*@description :主目录adapter
**/
class CitySelectAdapter(list: ArrayList<CityEntity.DataBean.ListBean>, context: Context) :
        BaseRvAdapter<CityEntity.DataBean.ListBean>(list, R.layout.item_select_location_city, context) {

    override fun onBindView(holder: Companion.BaseRvHolder, data: CityEntity.DataBean.ListBean) {
        //分组标记
        val sectionTag = holder.findViewById<TextView>(R.id.tvItemCitySectionName)
        if(data.sectionTag != null){
            sectionTag.text = data.sectionTag
            sectionTag.visibility = View.VISIBLE
        }else{
            sectionTag.visibility = View.GONE
        }
        val cityName = holder.findViewById<TextView>(R.id.tvItemCityName)
        cityName.text = data.name

        if(data.isLocationCity){
            cityName.setTextColor(ContextCompat.getColor(context,R.color.color_orange))
        }else{
            cityName.setTextColor(ContextCompat.getColor(context,R.color.text_color_light))
        }
    }

}