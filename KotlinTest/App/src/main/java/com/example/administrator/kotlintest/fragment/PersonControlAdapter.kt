package com.example.administrator.kotlintest.fragment

import android.content.Context
import android.view.View
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.ui.entity.PersonControlDao

/**
*@author : HaoBoy
*@date : 2018/9/29
*@description : 个人中心条目
**/
class PersonControlAdapter(val list: ArrayList<PersonControlDao>, context: Context) :
        BaseRvAdapter<PersonControlDao>(list, R.layout.item_person_control, context) {

    override fun onBindView(holder: Companion.BaseRvHolder, data: PersonControlDao) {

        val personalLine = holder.findViewById<View>(R.id.personalLine)
        holder.setText(R.id.tvText, data.text)

        holder.setText(R.id.tvHint, data.hint)
        if(holder.layoutPosition == list.size-1){
            personalLine.visibility =View.GONE
        }else{
            personalLine.visibility =View.VISIBLE
        }

    }

}