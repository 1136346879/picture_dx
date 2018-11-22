package com.example.administrator.kotlintest.ui.activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.ui.entity.PersonControlDao
import com.example.administrator.kotlintest.widget.ToastUtilKt
import com.example.baselibrary.ui.activity.BaseActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.mine_layout.*

class MineActivity :BaseActivity(){

   private val option = arrayListOf<PersonControlDao>()
    private lateinit var personAdapter : PersonAdapter
    private val CALL_PHONE = "13361665161"

    override fun initLayout(): Int {

        return R.layout.mine_layout

    }

    override fun initView() {


        option.add(PersonControlDao("我的优惠券",null))
        option.add(PersonControlDao("我的地址",""))
        option.add(PersonControlDao("客服电话",CALL_PHONE))
        option.add(PersonControlDao("清除缓存",""))
        option.add(PersonControlDao("设置",""))
      personAdapter = PersonAdapter(option,this)

        rvPerson.let {
            it.adapter = personAdapter
            it.layoutManager = LinearLayoutManager(this)
        }
        personAdapter.setOnClickItem {
            when(it){
                0 -> ToastUtilKt.showCustomToast(""+it)
                1 -> ToastUtilKt.showCustomToast(""+it)
                2 -> callPhone(CALL_PHONE)
                3 -> ToastUtilKt.showCustomToast(""+it)
                4 -> ToastUtilKt.showCustomToast(""+it)
            }
        }
        my_coupon?.let {
            it.setOnClickListener {

                ToastUtilKt.showCustomToast("点击我的优惠券")
            }
        }
    }

    private fun callPhone(phone:String) {
        if (CALL_PHONE.isEmpty()) {
            return
        }
        val rxpermission = RxPermissions(this)
        rxpermission.request(Manifest.permission.CALL_PHONE)
                .subscribe {
                    if(it){
                        val intent = Intent(Intent.ACTION_DIAL)
                        val data = Uri.parse("tel:${phone.replace("-","")}")
                        intent.data = data
                        startActivity(intent)

                    }
                }



    }

    override fun initData() {

//        option[0].hint = "无可用"
//        personAdapter.notifyDataSetChanged()
    }

}

class PersonAdapter(option: ArrayList<PersonControlDao>, mineActivity: MineActivity)
    :BaseRvAdapter<PersonControlDao>(option,R.layout.item_person_control,mineActivity){
    override fun onBindView(holder: Companion.BaseRvHolder, data: PersonControlDao) {
        data.text.let {
            holder.setText(R.id.tvText, it)
        }
        data.hint?.let {
            holder.setText(R.id.tvHint,it)
        }
    }

}
