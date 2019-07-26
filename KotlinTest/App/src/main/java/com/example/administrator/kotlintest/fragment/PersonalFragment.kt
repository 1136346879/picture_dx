package com.example.administrator.kotlintest.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.ui.entity.PersonControlDao
import com.example.administrator.kotlintest.widget.SystemDialog
import com.example.baselibrary.common.ToastUtil
import com.google.gson.Gson
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.entity.accont.AccessManager
import kotlinx.android.synthetic.main.fragment_mine_fs.*
import kotlinx.android.synthetic.main.layout_personal_order.*

/**
 * Created by kangf on 2018/7/27.
 */
class PersonalFragment : BaseTabFragment() {

    private lateinit var mAdapter: PersonControlAdapter

    private val options = arrayListOf<PersonControlDao>()

    private var contacts: Boolean = false
    private var headerAccountView: View? = null
    private var headerOrderView: View? = null
    private var mIntegralLevel: String? = ""
    private var mIntegralNum: String? = ""

    private lateinit var rxPermissions: RxPermissions

    private var status: String? = null

    override fun layoutResId() = R.layout.fragment_mine_fs

    @SuppressLint("InflateParams")
    override fun init() {
        options.add(PersonControlDao(1, "我的优惠券", ""))
        options.add(PersonControlDao(2, "我的地址", ""))
        options.add(PersonControlDao(3, "客服电话", CALL_PHONE))
        options.add(PersonControlDao(4, "清除缓存", ""))
        options.add(PersonControlDao(5, "设置", if (!ApiConstants.BASE_URL.contains("www.fsyuncai")) "测试${ApiConstants.BASE_URL}"
        else ""))
        mAdapter = PersonControlAdapter(options, context!!)

        headerAccountView = context.let {
            LayoutInflater.from(it).inflate(R.layout.header_personal_account, null, false)
        }

        headerOrderView = context.let {
            LayoutInflater.from(it).inflate(R.layout.header_personal_order, null, false)
        }

        rvPerson.let {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(true)
            it.adapter = mAdapter
        }
        //  设置数据
        // setAccountType(AccessManager.instance().accountType())

        rlExamine.setOnClickListener {
        }
        all_orders.setOnClickListener {
        }
        will_pay_orders.setOnClickListener {
        }
        will_send_orders.setOnClickListener {
        }
        will_receiver_orders.setOnClickListener {
        }
        my_order.setOnClickListener {
            //我的订单页面  需要的参数   login_account   member_id    token    Contract        参数就按这个格式写

        }
        tvPersonUpVip.setOnClickListener {
        }
    }

    override fun logic() {
//        getOrderNum()
//        getUserInfoByToken()
        // 获取当前缓存大小
        options.map {
        }

        mAdapter.notifyDataSetChanged()

        mAdapter.setOnClickItem {
            when (options[it].id) {
                // 0 -> goToMember()
                1 -> goToAddress()
                2 -> goToAddress()
                3 -> callService(CALL_PHONE)
                4 -> clearCache()
                5 -> goToSetting()
            }
        }
        rlHeader.setOnClickListener {
            goToSetting()
        }
    }

//    private fun goToMember() {
//        val opt = options[0]
//        if (opt.id != 0) {
//            return
//        }
//        when (opt.hint) {
//            UNVERIFIED -> {
//                ARouter.getInstance().build(RouterApi.UserCenter.ROUTER_USER_APPLY_VERIFY_MEMBER).navigation()
//            }
//            else -> {
//                ARouter.getInstance().build(RouterApi.UserCenter.ROUTER_USER_MEMBER_DETAIL).navigation()
//            }
//        }
//    }

    @SuppressLint("CheckResult")
    private fun callService(phone: String) {
        if (phone.isEmpty()) return
        rxPermissions = RxPermissions(this)
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe {
                    if (it) {
                    }
                }
    }


    private fun clearCache() {
        SystemDialog.Builder(context!!)
                .setMessage("确定清除本地缓存？")
                .setCancelBtn("取消", null)
                .setConfirmBtn("确定", View.OnClickListener {
                    Thread {
                        run {
                            //清理图片磁盘缓存
                            Glide.get(activity!!).clearDiskCache()
                        }
                    }.start()
                    options.map {
                        if (it.id == 4) {
                            it.hint = "0.00M"
                        }
                    }
                    mAdapter.notifyDataSetChanged()
                    ToastUtil.showCustomToast("清除完毕")

                })
                .build().show()
    }

    private fun goToAddress() {
    }

    private fun goToSetting() {
    }


    /*重新获取用户信息，根据memberId*/

    @SuppressLint("SetTextI18n")
    private fun setAccountType(accountType: Int, status: String?) {
        this.status = status
        when (accountType) {
            10 -> {
                // tvPersonUpVip.visibility = View.GONE
                if (options[0].id == 0) {
                    options.removeAt(0)
                    mAdapter.notifyDataSetChanged()
                }
                tvType.visibility = View.VISIBLE
                tvType.text = "权限：${AccessManager.instance().getAuthors()}"
            }
            30 -> {
                tvType.visibility = View.GONE
                //tvType.text = "类型:认证会员"
                // tvPersonUpVip.visibility = View.GONE
                rlExamine.visibility = View.GONE
                all_orders.visibility = View.VISIBLE
                //  setMemberInfo(status)
            }
            20 -> {
                tvType.visibility = View.GONE
                rlExamine.visibility = View.GONE
                all_orders.visibility = View.VISIBLE
                // tvPersonUpVip.visibility = View.GONE
                //tvType.text = "类型:普通会员"

                //  setMemberInfo(status)
            }

        }
        contacts = accountType == 10

    }

    /**
     *  设置会员状态
     */
//    private fun setMemberInfo(status: String?) {
//        this.status = status
//        if (options[0].id == 0) {
//            options.removeAt(0)
//        }
//        when (status) {
//            "10" -> { // 审核中
//                options.add(0, PersonControlDao(0, "认证会员", VERIFY_AUDIT))
//            }
//            "20" -> { // 认证成功
//                options.add(0, PersonControlDao(0, "认证会员", VERIFIED))
//            }
//            "30" -> { // 审核未通过
//                options.add(0, PersonControlDao(0, "认证会员", VERIFY_AUDIT_UN_PASS))
//            }
//            else -> {
//                options.add(0, PersonControlDao(0, "认证会员", UNVERIFIED))
//            }
//        }
//        mAdapter.notifyDataSetChanged()
//    }


    /**
     * 获取订单数量
     */

    companion object {

        private const val CALL_PHONE = "400-650-2828"

//        private const val VERIFIED = "已认证"
//        private const val VERIFY_AUDIT = "认证中"
//        private const val VERIFY_AUDIT_UN_PASS = "认证失败"
//        private const val UNVERIFIED = "未认证"

        fun newInstance() = PersonalFragment()
    }

    override fun onStart() {
        super.onStart()
        if (AccessManager.instance().isLogin()) {
        }
    }


    @SuppressLint("SetTextI18n")
    override fun lazyLoad() {
        super.lazyLoad()
//        SettingManager.instance.getShowOrderAudit(activity)
//        getOrderNum()
//        getUserInfoByToken()
//        showOrderStatus()

        val number = AccessManager.instance().getUser()?.member?.login_account
        tvAccount.text = if (number.isNullOrEmpty()) {
            "账号:"
        } else {
            "账号:$number"
        }
        //有下单权限或者非签约会员展示入口
        if (AccessManager.instance().accountType() != 10) {
            MemberShip_Grade?.visibility = View.VISIBLE
            tvLevel?.visibility = View.VISIBLE
            tvIntegral?.visibility = View.VISIBLE
            tvLevel?.text = mIntegralLevel ?: ""
            tvIntegral?.text = "积分$mIntegralNum"
        } else {
            tvType.visibility = View.VISIBLE
            tvType.text = "权限：${AccessManager.instance().getAuthors()}"
        }
    }

    /**
     * 是否显示待审批
     */
}