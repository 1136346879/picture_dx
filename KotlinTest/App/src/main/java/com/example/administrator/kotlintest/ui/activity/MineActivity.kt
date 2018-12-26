package com.example.administrator.kotlintest.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import com.classic.common.MultipleStatusView
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.ui.entity.PersonControlDao
import com.example.baselibrary.widgets.ToastUtilKt
import com.example.baselibrary.ui.activity.BaseUIActivity
import com.example.usercenter.ui.activity.UserCenterActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.mine_layout.*
import org.jetbrains.anko.intentFor
import java.util.*
import android.telephony.TelephonyManager


class MineActivity :BaseUIActivity(){

   private val option = arrayListOf<PersonControlDao>()
    private lateinit var personAdapter : PersonAdapter
    private val CALL_PHONE = "13361665161"
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null
    override fun initLayout(): Int {

        return R.layout.mine_layout

    }

    override fun initView() {

        mLayoutStatusView = multipleStatusView
        mLayoutStatusView?.showLoading()

        option.add(PersonControlDao("我的优惠券",null))
        option.add(PersonControlDao("我的地址",""))
        option.add(PersonControlDao("客服电话",CALL_PHONE))
        option.add(PersonControlDao("清除缓存",""))
        option.add(PersonControlDao("设置",""))



        android.os.Handler().postDelayed(Runnable {

            val rand = Random()
            val type =1+ rand.nextInt(3)
//           var type =  (1+Math.random()*(3-1+1))as Int
                //展示内容
            if(type ==1){
                mLayoutStatusView?.showContent()
            }else if(type ==2){
                //展示空内容
                mLayoutStatusView?.showEmpty()
            }else if(type ==3){
                //展示错误页面
                 mLayoutStatusView?.showError()
            }

            ToastUtilKt.showToast(""+type)

        },1000)


        mLayoutStatusView?.setOnClickListener { ToastUtilKt.showToast("点击重新加载") }
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
//                4 -> ToastUtilKt.showCustomToast(""+it)
                4 -> getMoreId()
            }
        }
        my_coupon?.let {
            it.setOnClickListener {

                ToastUtilKt.showCustomToast("点击我的优惠券")
                startActivity(this!!.intentFor<UserCenterActivity>())
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getMoreId(){
     val rxPermissions  =  RxPermissions(this)
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE).subscribe {
            if(it){
                val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val deviceid = tm!!.deviceId   //取 IMEI或者MEID
                val tel = tm!!.line1Number     //取出用户手机号码,手机没有安装SIM卡，值为null
                val imsi = tm!!.subscriberId     //取出IMSI,手机没有安装SIM卡，值为null
                val imei = tm!!.simSerialNumber
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
