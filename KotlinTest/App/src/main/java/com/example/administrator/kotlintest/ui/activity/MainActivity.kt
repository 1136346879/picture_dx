package com.example.administrator.kotlintest.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.administrator.kotlintest.FirstActivity
import com.example.administrator.kotlintest.HomeActiivty
import com.example.administrator.kotlintest.LogConfig
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.area.AreaSelectorDialog
import com.example.administrator.kotlintest.bridge.retrofit.ApiConstants
import com.example.administrator.kotlintest.channel.ChannelActivity
import com.example.administrator.kotlintest.common.ActiveResultDef
import com.example.administrator.kotlintest.common.IntentDataDef
import com.example.administrator.kotlintest.dateyearmonthday.AttendviewActivity
import com.example.administrator.kotlintest.entity.address.AddressAreaEntity
import com.example.administrator.kotlintest.expand.ExpandActivity
import com.example.administrator.kotlintest.location.CitySelectActivity
import com.example.administrator.kotlintest.net.NetWatchdogUtils
import com.example.administrator.kotlintest.removebg.RemovebgActivity
import com.example.administrator.kotlintest.slidablelayout.SlidableActivity
import com.example.administrator.kotlintest.smashzhadan.smashzhadan
import com.example.administrator.kotlintest.ui.entity.PersonControlDao
import com.example.administrator.kotlintest.util.BDLocationUtils
import com.example.administrator.kotlintest.util.SpManager
import com.example.administrator.kotlintest.videorecorde.CameraActivity
import com.example.administrator.kotlintest.videorecorde.MainCameraActivity
import com.example.administrator.kotlintest.widget.DevicesUtils.getSQLHelper
import com.example.administrator.kotlintest.widget.SystemDialog
import com.example.baselibrary.widgets.ToastUtilKt
import com.hexun.base.http.HeXunHttpClient
import com.hexun.caidao.hangqing.StockManager
import com.hexun.caidao.hangqing.TrainingApi
import com.plumcookingwine.network.helper.NetworkHelper
import com.theapache64.removebg.RemoveBg
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.PublicHeaderInterceptor
import com.xfs.fsyuncai.bridge.retrofit.http.interceptor.PublicParamsInterceptor
import com.xfs.qrcode_module.recycleview.RecycleviewActivity
import de.greenrobot.event.EventBus
//import jsc.kit.keyboard.KeyBoardView
//import jsc.kit.keyboard.KeyUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import java.util.*

class MainActivity : RxAppCompatActivity() {


    private val listData = arrayListOf<PersonControlDao>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSQLHelper()
        SpManager.init(application)
//        RemoveBg.init("H55t75AyMQF2FkGgsX6Mzb1g")
//        RemoveBg.init("efFu9zZn5DjWXZAvUgSB1ft5")//1136346879@qq.com
        RemoveBg.init("aecGJ4NemsijtvbDkGDPEd4m")//3385675579@qq.com
        HeXunHttpClient.init(this)
        NetworkHelper.init(
                this,
                ApiConstants.BASE_URL,
//                isDebug,
                false,
                mutableListOf(PublicParamsInterceptor(), PublicHeaderInterceptor()),
                null
        )
//        TrainingApi.getInstance().init(this)


//        StockManager.getInstance().init(this)

//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this)
//        }
        //log 初始化
        LogConfig.initLog(application)
        multipleStatusView?.showLoading()
        listData.add(PersonControlDao(0,"跳转至下一activity", null))
        listData.add(PersonControlDao(1,"日历", null))
        listData.add(PersonControlDao(2, "进入recycleview", null))
        listData.add(PersonControlDao(3,"个人中心", null))
        listData.add(PersonControlDao(4,"进入数据库页面", null))
        listData.add(PersonControlDao(5,"点击粉碎当前view", null))
        listData.add(PersonControlDao(6,"键盘", null))
        listData.add(PersonControlDao(7,"键盘fragment", null))
        listData.add(PersonControlDao(8,"图片操作", null))
        listData.add(PersonControlDao(9,"进入首页1--可切换", null))
        listData.add(PersonControlDao(10,"进入首页2--可切换", null))
        listData.add(PersonControlDao(11,"相机视频-拍照", null))
        listData.add(PersonControlDao(12,"Edittext", null))
        listData.add(PersonControlDao(13,"removeBG", null))
        listData.add(PersonControlDao(14,"expand", null))
        listData.add(PersonControlDao(15,"slidablelayout", null))

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
                5 -> startActivity(this.intentFor<smashzhadan>())
                6 -> startKeyBOard()
                7 -> startKeyBOardFragmentToActivity()
                8 -> startActivity(this.intentFor<PictureActionActivity>())
                9 -> startActivity(this.intentFor<HomeActiivty>())
                10 -> startActivity(this.intentFor<FirstActivity>())
                11 -> startActivity(this.intentFor<MainCameraActivity>())
                12 -> startActivity(this.intentFor<EdittextActivity>())
                13 -> startActivity(this.intentFor<RemovebgActivity>())
                14 -> startActivity(this.intentFor<ExpandActivity>())
                15 -> startActivity(this.intentFor<SlidableActivity>())
            }
        }
        //view拖拽功能
        dragview.setImageResource(R.drawable.icon_app)
//        mDragView.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495193578123&di=1356056ae967c04aa8b2d75a8634e7a0&imgtype=0&src=http%3A%2F%2Fs15.sinaimg.cn%2Fmw690%2F001MXOZUgy6DUbyFxgy7e%26690");
        dragview.setOnClickListener { Toast.makeText(this@MainActivity, "Clicked me", Toast.LENGTH_SHORT).show() }
        ruan.setOnClickListener { startActivity(this!!.intentFor<RuanActivity>()) }
        ying.setOnClickListener { startActivity(this!!.intentFor<YingActivity>()) }
        channel_mannger.setOnClickListener { startActivity(this!!.intentFor<ChannelActivity>()) }
        location.setOnClickListener { startActivity(this!!.intentFor<CitySelectActivity>()) }
        locationData()
        //检测有网络时在回调中开启上传
        initNetWatchdog()
    }
    private var mWatchdog: NetWatchdogUtils? = null
    private val TAG = MainActivity::class.java!!.simpleName
    private fun initNetWatchdog() {
        mWatchdog =  NetWatchdogUtils(this);
        mWatchdog!!.startWatch()
        mWatchdog!!.setNetChangeListener(object :NetWatchdogUtils.NetChangeListener{

           override fun onWifiTo4G() {
                Log.e(TAG, "onWifiTo4G")
            }

            override fun on4GToWifi() {
                Log.e(TAG, "on4GToWifi")

            }

            override fun onReNetConnected(isReconnect:Boolean) {
                Log.e(TAG, "isReconnect+ $isReconnect")
            }

            override fun onNetUnConnected() {
                Log.e(TAG, "onNetUnConnected")
            }
        })
    }

    override fun onStart() {
        super.onStart()
        mWatchdog!!.startWatch()

    }

    override fun onStop() {
        super.onStop()
        mWatchdog!!.stopWatch()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mWatchdog != null) {
            mWatchdog!!.stopWatch()
        }
    }
    private fun startKeyBOardFragmentToActivity() {
        startActivity(this.intentFor<EmptyFragmentActivity>())
    }
    private fun startKeyBOard() {
        ARouter.getInstance().build(RouterApi.KeboardLibrary.ROUTER_KEYBOARD_ACTIVITY_URL)
                .navigation()
    }

    private fun locationA() {

//        if (ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(activity!!, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ToastUtil.showToast("您拒绝了定位，请在设置-权限中允许")
//            tvLocation.text = (resources.getText(R.string.location_default))
//            return
//        }

        /**定位**/
        BDLocationUtils.instance.startLocation(this, object : BDLocationUtils.ResultCallback {

            override fun fail() {
                location.text = (resources.getText(R.string.location_default))
                BDLocationUtils.instance.stopLocation()
                showSelectAddrDialog()
            }

            override fun onPermissionError() {
                location.text = (resources.getText(R.string.location_default))
                BDLocationUtils.instance.stopLocation()
                showSelectAddrDialog()
            }

            override fun success(city: String) {
                var addr = ""
                if (city.isEmpty()) {
                    location.text = (resources.getText(R.string.location_default))
                    showSelectAddrDialog()
                } else {
                    if (city.contains("市")) {
                        addr = city.substringBefore("市", city)
                    }
                    location?.text = if (addr.length > 2) addr.substring(0, 2) + "..." else addr
                    BDLocationUtils.instance.stopLocation()
                    hideSelectAddrDialog()
                }
            }
        })
    }
    private var selectAddrDialog: SystemDialog? = null
    private fun hideSelectAddrDialog() {
        if (selectAddrDialog != null && selectAddrDialog!!.isShowing)
            selectAddrDialog?.dismiss()
    }
    private fun showSelectAddrDialog() {
        ARouter.getInstance()
                .build(RouterApi.MainLibrary.ROUTER_MAIN_SELECT_CITY)
                .withString(IntentDataDef.LOCATION_KEY, location.text.toString().trim())
                .navigation(this@MainActivity, ActiveResultDef.ACTIVITY_RESULT_LOCATION)
    }
    /**
     * 定位
     */
    private fun locationData() {
            locationA()
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
