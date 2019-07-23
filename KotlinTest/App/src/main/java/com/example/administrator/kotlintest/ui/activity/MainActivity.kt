package com.example.administrator.kotlintest.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.CursorLoader
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Parcel
import android.os.Parcelable
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.View.inflate
import android.widget.LinearLayout
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.ALog
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.administrator.kotlintest.LogConfig
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.area.AreaSelectorDialog
import com.example.administrator.kotlintest.channel.ChannelActivity
import com.example.administrator.kotlintest.channel.SQLHelper
import com.example.administrator.kotlintest.dateyearmonthday.AttendviewActivity
import com.example.administrator.kotlintest.dbutil.MeiziDaoUtils
import com.example.administrator.kotlintest.entity.address.AddressAreaEntity
import com.example.administrator.kotlintest.entity.daoentity.Meizi
import com.example.administrator.kotlintest.picture.CropImageActivity
import com.example.administrator.kotlintest.picture.UploadActivity
import com.example.administrator.kotlintest.smashzhadan.smashzhadan
import com.example.administrator.kotlintest.ui.entity.PersonControlDao
import com.example.administrator.kotlintest.ui.entity.学生
import com.example.administrator.kotlintest.widget.DevicesUtils.getSQLHelper
import com.example.baselibrary.MyApplication
import com.example.baselibrary.widgets.ToastUtilKt
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.xfs.fsyuncai.bridge.retrofit.callback.HttpOnNextListener
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorModel
import com.xfs.fsyuncai.bridge.retrofit.http.HttpManager
import com.xfs.fsyuncai.bridge.retrofit.service.OrderService
import com.xfs.qrcode_module.manager.LightManager
import com.xfs.qrcode_module.recycleview.RecycleviewActivity
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.util.AttrsUtils
import com.yalantis.ucrop.util.PictureMimeType
import jsc.kit.keyboard.KeyBroadActivity
//import jsc.kit.keyboard.KeyBoardView
//import jsc.kit.keyboard.KeyUtils
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.intentFor
import java.io.File
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity() : RxAppCompatActivity() {


    private val listData = arrayListOf<PersonControlDao>()
    private var areaSelectorDialog: AreaSelectorDialog? = null
    private val selectedArea = ArrayList<AddressAreaEntity.ListBean>()//已选的区域

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSQLHelper()
        //log 初始化
        LogConfig.initLog(application)
        multipleStatusView?.showLoading()
        listData.add(PersonControlDao("跳转至下一activity", null))
        listData.add(PersonControlDao("日历", null))
        listData.add(PersonControlDao("进入recycleview", null))
        listData.add(PersonControlDao("个人中心", null))
        listData.add(PersonControlDao("进入数据库页面", null))
        listData.add(PersonControlDao("频道管理页面", null))
        listData.add(PersonControlDao("点击粉碎当前view", null))
        listData.add(PersonControlDao("点击城市区域选择", null))
        listData.add(PersonControlDao("键盘", null))
        listData.add(PersonControlDao("键盘fragment", null))
        listData.add(PersonControlDao("图片操作", null))
        listData.add(PersonControlDao("等", null))
        listData.add(PersonControlDao("等", null))
        listData.add(PersonControlDao("等", null))
        listData.add(PersonControlDao("等", null))
        listData.add(PersonControlDao("等", null))
        listData.add(PersonControlDao("等", null))

        multipleStatusView.showContent()
        multipleStatusView.setOnClickListener { ToastUtilKt.showCustomToast("点击重新加载") }
        val mainAdapter = MainAdapter(listData, this@MainActivity)

        recycleview_list_all.let {
            it.adapter = mainAdapter
            it.layoutManager = LinearLayoutManager(this)
        }

        mainAdapter.setOnClickItem {
            when (it) {
                0 -> startActivity(this.intentFor<ThirdPartBannerZxingAcitivity>())
                1 -> startActivity(this.intentFor<AttendviewActivity>())
                2 -> startActivity(this.intentFor<RecycleviewActivity>())
                3 -> startActivity(this.intentFor<MineActivity>())
                4 -> startActivity(this.intentFor<DbShowActivity>())
                5 -> startActivity(this.intentFor<ChannelActivity>())
                6 -> startActivity(this.intentFor<smashzhadan>())
                7 -> selectCity()
                8 -> startKeyBOard()
                9 -> startKeyBOardFragmentToActivity()
                10 -> startActivity(this.intentFor<PictureActionActivity>())
            }
        }
        //view拖拽功能
        dragview.setImageResource(R.drawable.icon_app)
//        mDragView.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495193578123&di=1356056ae967c04aa8b2d75a8634e7a0&imgtype=0&src=http%3A%2F%2Fs15.sinaimg.cn%2Fmw690%2F001MXOZUgy6DUbyFxgy7e%26690");
        dragview.setOnClickListener { Toast.makeText(this@MainActivity, "Clicked me", Toast.LENGTH_SHORT).show() }



        tv1.setOnClickListener { startActivity(this!!.intentFor<AttendviewActivity>()) }
        // 跳转至下一activity
        RxView.clicks(tv0).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe {
                    tv1.text = "你好"
                    startActivity(this!!.intentFor<ThirdPartBannerZxingAcitivity>())
                }
        //日历
        //进入recycleview
        tv2.setOnClickListener {
            //            startActivity(this!!.intentFor<WebbrowserActivity4>())
            val 学生 = 学生("丁", 17)
            val (name, age) = 学生
//            ToastUtilKt.showToast("--》${学生.component1()}")
//            ToastUtilKt.showCustomToast("--》${学生.component2()}")
            ToastUtilKt.showToast("--》$name")
            ToastUtilKt.showCustomToast("--》$age")
            startActivity(this!!.intentFor<RecycleviewActivity>())
//            startActivity(this!!.intentFor<com.xfs.fsyuncai.mavendemo.MainActivity>())

        }
        //个人中心
        tv3.setOnClickListener { startActivity(this!!.intentFor<MineActivity>()) }
        tv7.setOnClickListener { startActivity(this.intentFor<DbShowActivity>()) }//进入数据库页面
        tv8.setOnClickListener { startActivity(this.intentFor<ChannelActivity>()) }//频道管理页面
        tv9.setOnClickListener { startActivity(this.intentFor<smashzhadan>()) }//点击粉碎当前view
        tv10.setOnClickListener {
            selectCity()
        }//点击城市区域选择
        ruan.setOnClickListener { startActivity(this!!.intentFor<RuanActivity>()) }
        ying.setOnClickListener { startActivity(this!!.intentFor<YingActivity>()) }
    }

    private fun startKeyBOardFragmentToActivity() {
        startActivity(this!!.intentFor<EmptyFragmentActivity>())
    }

    private fun startKeyBOard() {
//        startActivity(this.intentFor<KeyBroadActivity>())
        ARouter.getInstance().build(RouterApi.KeboardLibrary.ROUTER_KEYBOARD_ACTIVITY_URL)
//                .withInt("select", 4)
                .navigation()
    }

    private fun selectCity() {
        ToastUtilKt.showCustomToast("城市选择")

        areaSelectorDialog = AreaSelectorDialog(this@MainActivity, object : AreaSelectorDialog.ResultCallBack {
            override fun onDismiss() {
            }

            override fun onDismissForResult(dataList: ArrayList<AddressAreaEntity.ListBean>?) {
                selectedArea.clear()
                selectedArea.addAll(dataList!!)
                if (selectedArea.size != 0) {
                    var area = ""
                    selectedArea.map {
                        area += it.name
                    }
                    tv10.text = area
                } else {
                    tv10.text = ""
                }
            }
        }, this@MainActivity)
        areaSelectorDialog!!.show()
    }


}

class MainAdapter(option: ArrayList<PersonControlDao>, mineActivity: MainActivity)
    : BaseRvAdapter<PersonControlDao>(option, R.layout.item_person_control, mineActivity) {
    override fun onBindView(holder: Companion.BaseRvHolder, data: PersonControlDao) {
        data.text.let {
            holder.setText(R.id.tvText, it)
        }
        data.hint?.let {
            holder.setText(R.id.tvHint, it)
        }
    }
}
