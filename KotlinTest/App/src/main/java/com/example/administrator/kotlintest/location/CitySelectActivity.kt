package com.example.administrator.kotlintest.location

import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.entity.CityEntity
import com.example.administrator.kotlintest.entity.WareHouseEntity
import com.example.administrator.kotlintest.entity.date.AreaSelectedHistoryData
import com.example.administrator.kotlintest.ui.adapter.BaseRvHeaderAndFooterWrapper
import com.example.administrator.kotlintest.ui.divider.GridDividerItemDecoration
import com.example.administrator.kotlintest.util.BDLocationUtils
import com.example.administrator.kotlintest.util.PermissionPageUtils
import com.example.administrator.kotlintest.util.SPUtils
import com.example.administrator.kotlintest.widget.EmptyView
import com.example.administrator.kotlintest.widget.SystemDialog
import com.example.baselibrary.common.ToastUtil
import com.example.baselibrary.widgets.UIUtils
import com.google.gson.Gson
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.xfs.fsyuncai.art.base.view.activity.BaseActivity
import com.xfs.fsyuncai.bridge.database.AreaHistoryDbUtil
import com.xfs.fsyuncai.bridge.retrofit.callback.HttpOnNextListener
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorModel
import com.example.administrator.kotlintest.bridge.retrofit.http.HttpManager
import com.example.administrator.kotlintest.bridge.retrofit.http.RequestOption
import com.xfs.fsyuncai.bridge.retrofit.service.CommonService
import com.xfs.fsyuncai.entity.CityInfoByName
import com.xfs.fsyuncai.entity.CitySelectEntityRxBus
import com.xfs.fsyuncai.entity.accont.AccessManager
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.header_city_search.view.*
import kotlinx.android.synthetic.main.toolbar_location.*

/**
 *@author : HaoBoy
 *@date : 2018/8/18
 *@description :城市选择
 **/
@Route(path = RouterApi.MainLibrary.ROUTER_MAIN_SELECT_CITY)
class CitySelectActivity : BaseActivity() {

    /**主列表**/
    private lateinit var mCitySelectAdapter: CitySelectAdapter
    private var sourceDataList: ArrayList<CityEntity.DataBean.ListBean> = ArrayList()
    /**搜索列表**/
    private lateinit var mCitySearchAdapter: CitySearchAdapter
    private var searchDataList: ArrayList<CityEntity.DataBean.ListBean> = ArrayList()
    /**历史记录**/
    private lateinit var mCityHistoryAdapter: CityHistoryAdapter
    private var historyDataList: ArrayList<CityEntity.DataBean.ListBean> = ArrayList()

    private var httpManager: HttpManager = HttpManager.instance()

    private lateinit var headerView: View
    private lateinit var wrapper: BaseRvHeaderAndFooterWrapper
    private lateinit var tvHistory: TextView
    private var address = ""
    private var isBack = false//是否点击定位,返回

    private var settingDialog: SystemDialog? = null

    override fun resLayout() = R.layout.activity_location

    override fun init() {

        ivBack.visibility = View.VISIBLE
        location()
        getCity()
        //所有地址
        mCitySelectAdapter = CitySelectAdapter(sourceDataList, this@CitySelectActivity)
        headerView = LayoutInflater.from(this).inflate(R.layout.header_city_search, null, false)
        wrapper = BaseRvHeaderAndFooterWrapper(mCitySelectAdapter)
        headerView.let {
            it.layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT
                    , RelativeLayout.LayoutParams.WRAP_CONTENT)
            wrapper.addHeaderView(it)
        }
        rv_location_area.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = wrapper

            mCitySelectAdapter.setOnClickItem {
                saveHistoryData(sourceDataList[it])
            }
        }

        //搜索记录
        mCitySearchAdapter = CitySearchAdapter(searchDataList, this@CitySelectActivity)
        rv_city_search.let {
            it.layoutManager = LinearLayoutManager(this)
            it.setHasFixedSize(true)
            it.adapter = mCitySearchAdapter
            mCitySearchAdapter.setOnClickItem {
                val data = searchDataList[it]
                saveHistoryData(data)
            }
        }
        mCityHistoryAdapter = CityHistoryAdapter(historyDataList, this@CitySelectActivity)
        headerView.rv_history_area.let {
            it.layoutManager = GridLayoutManager(this, 3)
            it.addItemDecoration(GridDividerItemDecoration(UIUtils.dip2px(14), R.color.white))
            it.setHasFixedSize(true)
            it.adapter = mCityHistoryAdapter
            mCityHistoryAdapter.setOnClickItem {
                getWarehouseByCity(historyDataList[it])
//                saveHistoryData(historyDataList[it])
            }
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        bindLetter()
    }

    override fun logic() {
        getHistoryData()
        tvHistory = headerView.findViewById<TextView>(R.id.tv_city_history)
        if (historyDataList.size == 0) {
            tvHistory.visibility = View.GONE
        } else {
            tvHistory.visibility = View.VISIBLE
        }

        ll_city_location.setOnClickListener {
            //定位失败或者正在定位中的点击逻辑（之所以正在定位中也加上点击，是避免定位失败未返回的问题）
            if (tv_city_location.text == resources.getText(R.string.location_fail)
                    || tv_city_location.text == resources.getText(R.string.hint_location)) {
                tv_city_location.text = resources.getText(R.string.hint_location)
                location()
            } else {
                //选择城市已经是定位城市
                if (tv_city_location.text!="正在定位中") {
                    finish()
                } else {
                    isBack = true
                    getLocation()
                }
            }
        }

        et_city_search.let { it ->
            it.setOnClickRightDrawable({
                et_city_search.setText("")
            }, {
                if (it > 0) {
                    et_city_search.setRightImage(R.drawable.clear)
                } else {
                    et_city_search.setRightImage(0)
                }
            })
            it.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(editable: Editable?) {
                    val str = editable.toString().trim()
                    if (str.isEmpty()) {
                        rv_city_search.visibility = View.GONE
                        emptyView.visibility = View.GONE
                        searchDataList.clear()
                        mCitySearchAdapter.notifyDataSetChanged()

                        if (sourceDataList.size == 0) {
                            emptyView.setView(EmptyView.TYPE.ERROR)
                        }
                    } else {
                        sourceDataList.map {
                            if (it.name.contains(str)) {
                                if (searchDataList.indexOf(it) == -1)
                                    searchDataList.add(it)
                            }
                        }
                        //
                        if (searchDataList.size > 0) {
                            rv_city_search.visibility = View.VISIBLE
                            emptyView.visibility = View.GONE
                            mCitySearchAdapter.notifyDataSetChanged()
                        } else {
                            rv_city_search.visibility = View.GONE
                            emptyView.visibility = View.VISIBLE
                            emptyView.setView(EmptyView.TYPE.EMPTY)
                            emptyView.visibility = View.VISIBLE
                            emptyView.setErrorMsg(getString(R.string.no_search_any_result))
                        }
                    }
                }
            })
        }
        emptyView?.setOnClickListener {
            location()
            getCity()
            getHistoryData()
        }
        setLetterData()


    }

    /**定位**/
    private fun location() {

        isBack = false
        BDLocationUtils.instance.stopLocation()
        BDLocationUtils.instance.startLocation(this@CitySelectActivity, object : BDLocationUtils.ResultCallback {

            override fun onPermissionError() {
                tv_city_location.text = (resources.getText(R.string.location_fail))
                BDLocationUtils.instance.stopLocation()
//                ToastUtil.showCustomToast("定位失败,请在-设置中打开应用的定位权限")
                goSettings()
            }

            override fun success(city: String) {
                if (city.isEmpty()) {
                    tv_city_location.text = (resources.getText(R.string.location_fail))
                    BDLocationUtils.instance.stopLocation()
                    goSettings()
                } else {
                    if (city.contains("市")) {
                        address = city.substringBefore("市", city)
                    }
                    tv_city_location.text = address
                    BDLocationUtils.instance.stopLocation()
                    //定位成功后如果城市列表是空则去请求
                    if (sourceDataList.size == 0) {
                        getCity()
                    }
                    getLocation()
                }
            }

            override fun fail() {
//                ToastUtil.showToast("未打开GPS或者未授予应用定位权限")
                tv_city_location.text = (resources.getText(R.string.location_fail))
                BDLocationUtils.instance.stopLocation()
                goSettings()
            }
        })

    }

    private fun goSettings() {
        if (settingDialog == null) {
            settingDialog = SystemDialog.Builder(this@CitySelectActivity)
                    .setTitle("定位服务已关闭")
                    .setMessage("请到设置->隐私->定位服务中开启[方盛云采]定位服务，以便帮您获得周边门店信息")
                    .setCancelBtn("确定", null)
                    .setConfirmBtn("去设置", View.OnClickListener {
                        // 申请权限
                        PermissionPageUtils(this@CitySelectActivity).jumpPermissionPage()
                    })
                    .build()


        }

        if (settingDialog != null && settingDialog!!.isShowing.not()) {
            settingDialog!!.show()
        }

    }

    /**定位成功后,列表中的城市变颜色---成功定位后调一次,列表数据请求成功后调一次**/
    private fun updateListByLocation() {
        if (sourceDataList.size == 0) return
        sourceDataList.mapIndexed { index, b ->
            if (b.name !="") {
                b.isLocationCity = true
                sourceDataList[index] = b
                mCitySelectAdapter.notifyItemChanged(index)
                return
            }
        }
    }

    private fun getLocation() {
        HttpManager.instance().apply {
            setOption(RequestOption().apply {
                isShowProgress = true
            })
            doHttpDeal(
                    this@CitySelectActivity,
                    createService(CommonService::class.java).getInfoByCity(address).compose(bindUntilEvent(ActivityEvent.DESTROY)),
                    getInfoCityListener
            )
        }
    }

    private val getInfoCityListener = object : HttpOnNextListener() {

        override fun onNext(json: String) {
            val gson = Gson()
            val cityName = gson.fromJson<CityInfoByName>(json, CityInfoByName::class.java) ?: return

            if (cityName.data == null) {
                ToastUtil.showCustomToast("您所在城市没有仓库，请选择其他城市")
                return
            }
            val addressId = cityName.data?.address_id ?: 0
            val wc = if ((cityName.data?.warehouse_code
                            ?: "0").isEmpty()) "0" else cityName.data?.warehouse_code ?: "0"
            val warehouseCode = Integer.parseInt(wc)
            val name = cityName.data?.name ?: ""
            val code = cityName.data?.code ?: ""

            val locationData = CityEntity.DataBean.ListBean(addressId, warehouseCode, code, name)
            if (isBack) {
                saveHistoryData(locationData)
            }
        }

        override fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
            ToastUtil.showCustomToast("获取所在仓库信息失败")
        }
    }

    /**添加字母**/
    private fun bindLetter() {
        INDEX_STRING.map {
            val t = TextView(this)
            t.text = it
            t.setPadding(0, 0, UIUtils.dip2px(1), 0)
            t.textSize = UIUtils.dip2px(3).toFloat()
            t.setTextColor(ContextCompat.getColor(this, R.color.color_blue_dark))
            t.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1.0f)
            t.gravity = Gravity.CENTER
            letter_content.addView(t)
        }
    }

    /**设置字母UI和逻辑**/
    private fun setLetterData() {
        seek_br_location.max = INDEX_STRING.size - 1
        seek_br_location.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (INDEX_STRING[p1] == "#") return
                showLitter.visibility = View.VISIBLE
                showLitter.text = INDEX_STRING[p1]
                sourceDataList.mapIndexed { index, listBean ->
                    if (listBean.sectionTag != null && listBean.sectionTag == INDEX_STRING[p1]) {
                        rv_location_area.scrollToPosition(index)
                        val mLayoutManager = rv_location_area.layoutManager as LinearLayoutManager
                        mLayoutManager.scrollToPositionWithOffset(index, 1)
                        return
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        seek_br_location.setOnTouchListener { _, p1 ->
            if (p1!!.action == MotionEvent.ACTION_UP) {
                showLitter.visibility = View.GONE
                true
            } else {
                false
            }
        }
    }

    /**获取历史记录**/
    private fun getHistoryData() {
        historyDataList.clear()
        val data = AreaHistoryDbUtil.instance().queryDataAllOrderByTime()
                .filterIndexed { i, _ -> i < 6 }

        data.map {
            historyDataList.add(CityEntity.DataBean.ListBean(
                    it.cityId, it.wareHouserId, it.code.toString(), it.name))
        }
        if (historyDataList.size > 0) {
            headerView.rv_history_area.visibility = View.VISIBLE
        } else {
            headerView.rv_history_area.visibility = View.GONE
        }
        mCityHistoryAdapter.notifyDataSetChanged()
    }

    /**保存一条历史记录**/
    private fun saveHistoryData(data: CityEntity.DataBean.ListBean) {
        //大于6条移除第一条
//        val dataList = AreaHistoryDbUtil.instance().queryDataAllOrderByTime()
//        if (dataList?.size == 6) {  AreaHistoryDbUtil.instance().deleteData(dataList[5]) }
        AreaHistoryDbUtil.instance()
                .saveData(AreaSelectedHistoryData(
                        data.address_id,
                        data.warehouse_id,
                        data.code,
                        data.name,
                        System.currentTimeMillis())
                )
//        ToastUtil.showCustomToast("数据库添加-" + data.name + "成功")
        SPUtils.setObject(SPUtils.CITY_ID, data.address_id)
//        val userWareHoseCode = AccessManager.instance().wareHoseCode()
//        if (userWareHoseCode == 0) {
//            SPUtils.setObject(SPUtils.WAREHOUSE_ID, data.warehouse_id)
//        } else {
//            SPUtils.setObject(SPUtils.WAREHOUSE_ID, userWareHoseCode)
//        }

        SPUtils.setObject(SPUtils.CITY_NAME, data.name)
        SPUtils.setObject(SPUtils.CITY_CODE, data.code)
        tvHistory.visibility = View.VISIBLE
        finish()
    }


    /**获取城市列表**/
    private fun getCity() {
        httpManager.doHttpDeal(this,
                httpManager.createService(CommonService::class.java).getAllCity().bindToLifecycle(this),
                httpOnNextListener)
    }

    private val httpOnNextListener = object : HttpOnNextListener() {
        override fun onNext(json: String) {
            val gson = Gson()
            try {
                val resultDao = gson.fromJson<CityEntity>(json, CityEntity::class.java)

                if (resultDao.errorCode != 0) {
                    onError(resultDao.errorCode, ApiErrorModel(resultDao.errorCode, resultDao.errorMessage))
                    return
                }
                //每一组数据
                val list = resultDao.data
                list.map {
                    it.list.mapIndexed { index, listBean ->
                        if (index == 0) {
                            listBean.sectionTag = it.item
                            sourceDataList.add(listBean)
                        } else {
                            sourceDataList.add(listBean)
                        }
                    }
                }
                mCitySelectAdapter.notifyDataSetChanged()
                updateListByLocation()
                emptyView.visibility = View.GONE
            } catch (e: Exception) {
                onError(1001, ApiErrorModel(1001, "服务器错误"))
            }
        }

        override fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
            super.onError(statusCode, apiErrorModel)
            emptyView.visibility = View.VISIBLE
            emptyView.setView(EmptyView.TYPE.ERROR)
        }
    }

    /**
     * 获取城市对应的仓
     */
    private fun getWarehouseByCity(bean: CityEntity.DataBean.ListBean) {
        HttpManager.instance().apply {
            //            setOption(RequestOption().apply {
//                isShowProgress = true
//                showProgress("网络不好,请耐心等待~")
//            })
            doHttpDeal(
                    this@CitySelectActivity,
                    createService(CommonService::class.java).getWarehouseByCity(bean.code)
                            .bindToLifecycle(this@CitySelectActivity),
                    object : HttpOnNextListener() {
                        override fun onNext(json: String) {
                            Gson().let {
                                val entity = it.fromJson<WareHouseEntity>(json, WareHouseEntity::class.java)
                                if (entity.errorCode != 0) {
                                    ToastUtil.showCustomToast(entity.errorMessage!!)
                                    return
                                }
                                if (entity.data != null) {
                                    //城市所在仓发生了变化
                                    if (bean.warehouse_id.toString() != entity.data!!.warehouse_code) {
                                        bean.warehouse_id = Integer.parseInt(entity.data!!.warehouse_code
                                                ?: "")
                                        bean.name = if (entity.data!!.warehouse_name!!.contains("仓")) {
                                            entity.data!!.warehouse_name!!.replace("仓", "")
                                        } else {
                                            entity.data!!.warehouse_name!!
                                        }
                                    }
                                    saveHistoryData(bean)

                                }
                            }
                        }

                        override fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
                            super.onError(statusCode, apiErrorModel)
                            ToastUtil.showCustomToast("网络异常，请检查网络")
                        }
                    }
            )
        }
    }

    override fun onStop() {
        super.onStop()
        BDLocationUtils.instance.stopLocation()
    }

    override fun onBackPressed() {

        super.onBackPressed()

    }

    companion object {
        val INDEX_STRING: Array<String> = arrayOf(
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#")
    }

}

