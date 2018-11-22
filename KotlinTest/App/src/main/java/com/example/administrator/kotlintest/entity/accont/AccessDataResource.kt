package com.xfs.fsyuncai.entity.accont

import com.xfs.fsyuncai.entity.AccountEntity

/**
 * Created by kangf on 2018/7/16.
 *
 * @description 抽象用户接口
 */
interface AccessDataResource {

    /**
     * 获取用户所有信息
     */
    fun getUser(): AccountEntity?

    /**
     * 保存用户信息
     */
    fun saveUser(user: AccountEntity?)

    /**
     * 保存用户信息
     */
    fun saveUser(jsonStr: String)

    /*更新用户信息*/
    fun updateUser(entity: AccountEntity)

    /**
     * 更新登录数据
     */
    fun updateLoginDate()

    /**
     * 删除用户
     */
    fun deleteUser()

    /**
     * 删除所有用户
     */
    fun deleteAllUser()

    /**
     * 是否登录
     */
    fun isLogin(): Boolean

    /**
     * 获取token
     */
    fun token(): String

    /**
     * 获取 memberId
     */
    fun memberId(): Int

    /**
     * 获取 customerId
     */
    fun customerId(): String

    /**
     * 获取 customerCode
     */
    fun customerCode(): String

    /**
     * customerName
     */
    fun customerName(): String

    /**
     * 登录账户名
     */
    fun loginAccount(): String

    /**
     * 登录手机号
     */
    fun mobilePhone(): String

    /**
     * 账户类型
     * @return  10 ：代表签约用户 20 ：代表普通用户 30 认证
     */
    fun accountType(): Int

    /**
     * 是否可以下单(是否附带下单权限)
     */
    fun canOrder(): Boolean

    /**
     * 是否有结算权限
     */
    fun canPay(): Boolean

    /**
     *单纯检查（是否有审批权限）
     */
    fun isEnableAudit(): Boolean

    /**
     *单纯检查（是否有下单权限）
     */
    fun isEnableOrder(): Boolean

    /**
     *单纯检查（是否有结算权限）
     */
    fun isEnableSettle(): Boolean

    /**
     * 当前用户的权限
     */
    fun getAuthors():String

    /**
     *  是否是三权合一的账号
     */
    fun getAllSingleUser():Int

    /**
     * 关联结算用户集
     */
    fun getRelationSettle():List<AccountEntity.AccountContractInfoBean.ResultListBean.AccountReleationTypeListBean.SettleAccountBean>?

    /**
     * 关联审批用户集
     */
    fun getRelationAudit():List<AccountEntity.AccountContractInfoBean.ResultListBean.AccountReleationTypeListBean.AuditAccountBean>?

    /**
     * 当前下单用户集
     */
    fun getRelationOrder():List<AccountEntity.AccountContractInfoBean.ResultListBean.AccountReleationTypeListBean.OrderAccountBean>?

    /**
     *  是否是分销用户
     */
    fun isHasshopCustomerAddress():Boolean

}