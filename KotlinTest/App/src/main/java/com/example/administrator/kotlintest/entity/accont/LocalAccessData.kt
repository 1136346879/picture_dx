package com.xfs.fsyuncai.entity.accont

import com.example.administrator.kotlintest.bridge.retrofit.callback.CookieResulte
import com.google.gson.Gson
import com.xfs.fsyuncai.bridge.database.CookieDbUtil
import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.entity.AccountEntity

/**
 * Created by kangf on 2018/7/16.
 */

class LocalAccessData : AccessDataResource {

    override fun getAllSingleUser(): Int {
        return getUser()?.accountContractInfo?.resultList?.all_author_single_user?:20
    }

    override fun getRelationSettle(): List<AccountEntity.AccountContractInfoBean.ResultListBean.AccountReleationTypeListBean.SettleAccountBean>? {
        return getUser()?.accountContractInfo?.resultList?.account_releation_type_list?.settle_account
    }

    override fun getRelationAudit(): List<AccountEntity.AccountContractInfoBean.ResultListBean.AccountReleationTypeListBean.AuditAccountBean>? {
        return getUser()?.accountContractInfo?.resultList?.account_releation_type_list?.audit_account
    }

    override fun getRelationOrder(): List<AccountEntity.AccountContractInfoBean.ResultListBean.AccountReleationTypeListBean.OrderAccountBean>? {
        return getUser()?.accountContractInfo?.resultList?.account_releation_type_list?.order_account
    }

    override fun getAuthors(): String {
        var authors = ""
        if (isEnableOrder()) {
            authors += " 下单 "
        }
        if (isEnableAudit()) {
            authors += " 审批 "
        }
        if (isEnableSettle()) {
            authors += " 结算 "
        }
        return authors
    }

    override fun isEnableAudit(): Boolean {
        val authorityList = AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list
        val value = authorityList?.enable_audit ?: 20
        return value == 10
    }

    override fun isEnableOrder(): Boolean {
        val authorityList = AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list
        val value = authorityList?.enable_order ?: 20
        return value == 10
    }

    override fun isEnableSettle(): Boolean {
        val authorityList = AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list
        val value = authorityList?.enable_settle ?: 20
        return value == 10
    }

    override fun canPay(): Boolean {
        val authorityList = AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list
        val enablePay = authorityList?.enable_settle ?: 20
        return accountType() != 10 || (accountType() == 10 && enablePay == 10)
    }

    override fun canOrder(): Boolean {
        val authorityList = AccessManager.instance().getUser()?.accountContractInfo?.resultList?.authority_list
        val enableOrder = authorityList?.enable_order ?: 20
        return accountType() != 10 || (accountType() == 10 && enableOrder == 10)
    }

    override fun isLogin(): Boolean {
        return getUser() != null
    }

    override fun getUser(): AccountEntity? {
        val cookieResulte = CookieDbUtil.instance().queryCookieBy(ApiConstants.USER_LOGIN)
                ?: return null
        val json = cookieResulte.resulte
        val g = Gson()
        return g.fromJson<AccountEntity>(json, AccountEntity::class.java)
    }

    override fun saveUser(user: AccountEntity?) {
        if (user == null) return
        val g = Gson()
        val jsonStr = g.toJson(user)
        val cookieResult = CookieResulte(ApiConstants.USER_LOGIN, jsonStr, System.currentTimeMillis())
        CookieDbUtil.instance().saveCookie(cookieResult)
    }

    override fun saveUser(jsonStr: String) {
        if (jsonStr.isEmpty()) return
        val cookieResult = CookieResulte(ApiConstants.USER_LOGIN, jsonStr, System.currentTimeMillis())
        CookieDbUtil.instance().saveCookie(cookieResult)
    }

    override fun updateUser(entity: AccountEntity) {
        deleteUser()
        saveUser(entity)
    }

    override fun updateLoginDate() {
        val cookieResulte = CookieDbUtil.instance().queryCookieBy(ApiConstants.USER_LOGIN)
        if (cookieResulte != null) {
            cookieResulte.time = System.currentTimeMillis()
            CookieDbUtil.instance().saveCookie(cookieResulte)
        }
    }

    override fun deleteUser() {
        val cookieResult = CookieDbUtil.instance().queryCookieBy(ApiConstants.USER_LOGIN) ?: return
        CookieDbUtil.instance().deleteCookie(cookieResult)
    }

    override fun deleteAllUser() {

    }

    override fun token(): String {
        return getUser()?.token ?: return ""
    }

    override fun memberId(): Int {
        val memberId = getUser()?.member?.member_id ?: return 0
        return memberId
    }

    override fun customerId(): String {
        return getUser()?.accountContractInfo?.resultList?.customer_id ?: return ""
    }

    override fun customerCode(): String {
        return getUser()?.accountContractInfo?.resultList?.customer_code ?: ""
    }

    override fun customerName(): String {
        return getUser()?.member?.customer_name ?: ""
    }

    override fun loginAccount(): String {
        return getUser()?.member?.login_account ?: ""
    }

    override fun mobilePhone(): String {
        return getUser()?.member?.mobile_phone ?: ""
    }

    /**
     * @return  10 ：代表签约用户 20 ：代表普通用户
     */
    override fun accountType(): Int {
        return getUser()?.member?.account_type ?: 0
    }

    /**
     * 是否是分销用户
     * true 是分销用户
     * false 不是分销用户
     */
    override fun isHasshopCustomerAddress(): Boolean {
        return getUser()?.shopCustomerAddress !=null
    }


}