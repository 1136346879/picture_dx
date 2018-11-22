package com.xfs.fsyuncai.bridge.retrofit.service

import com.xfs.fsyuncai.bridge.retrofit.ApiConstants
import com.xfs.fsyuncai.bridge.retrofit.service.body.TokenBody
import com.xfs.fsyuncai.entity.accont.AccessManager
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by kangf on 2018/7/13.
 */
interface AccountService {

    /**
     * 普通用户 -  登录
     */
    @FormUrlEncoded
    @POST(ApiConstants.USER_LOGIN)
    fun login(
            @Field("code") code: String,
            @Field("loginAccount") loginAccount: String,
            @Field("password") password: String,
            @Field("uuid") uid: String,
            @Field("platform") platform: String? = "20"
    ): Observable<String>


    /**
     * 登录请求验证码
     */
    @POST(ApiConstants.CODE_LOGIN)
    fun loginCode(): Observable<String>


    /**
     * 获取手机验证码
     *
     * type 登录是4 ， 支付5
     */
    @FormUrlEncoded
    @POST(ApiConstants.CODE_MOBILE)
    fun mobileCode(
            @Field("mobile") mobile: String?,
            @Field("account_type") account_type: Int,
            @Field("memberId") memberId: String,
            @Field("templateId") templateId: Int,
            @Field("veryfyType") veryfyType: String


    ): Observable<String>

    /**
     * 校验手机验证码
     *
     * type 登录是4 ， 支付5
     */
    @FormUrlEncoded
    @POST(ApiConstants.CODE_MOBILE_CHECK)
    fun checkMobileCode(
            @Field("mobile") mobile: String?,
            @Field("veryfyCode") verifyCode: String?,
            @Field("veryfyType") veryfyType: String?
    ): Observable<String>

    /**
     * 设置支付密码
     *
     * @param token
     * @param paidPassword 支付密码（BASE64编码）
     * @param platform 平台
     */
    @FormUrlEncoded
    @POST(ApiConstants.SET_PAY_PWD)
    fun setPayPwd(
            @Field("paidPassword") paidPassword: String?,
            @Field("paidPasswordConfirm") paidPasswordConfirm: String?,
            @Field("token") token: String = AccessManager.instance().token(),
            @Field("platform") platform: String = "20",
            @Field("loginAccount") account: String = AccessManager.instance().loginAccount()
    ): Observable<String>

    /**
     * 修改支付密码
     */
    @FormUrlEncoded
    @POST(ApiConstants.UPDATE_PAY_PWD)
    fun updatePayPwd(
            @Field("paidPassword") paidPassword: String?,
            @Field("paidPasswordConfirm") paidPasswordConfirm: String?,
            @Field("token") token: String = AccessManager.instance().token(),
            @Field("platform") platform: String = "20",
            @Field("loginAccount") account: String = AccessManager.instance().loginAccount()
    ): Observable<String>

    /**
     * 是否设置支付密码
     */
    @FormUrlEncoded
    @POST(ApiConstants.HAS_SET_PAY_PWD)
    fun hasSetPayPwd(
            @Field("memberId") memberId: String?,
            @Field("token") token: String?
    ): Observable<String>

    /**
     * 校验支付密码
     */
    @FormUrlEncoded
    @POST(ApiConstants.CHECK_PAY_PWD)
    fun checkPayPwd(
            @Field("memberId") memberId: String,
            @Field("payPwd") payPwd: String
    ): Observable<String>

    /**
     * 根据token获取用户信息
     */
    @POST(ApiConstants.GET_USER_INFO_BY_TOKEN)
    fun getUserInfoByToken(
            @Body body: TokenBody
    ): Observable<String>

    /**
     * 根据MemberId获取用户信息
     */
    @FormUrlEncoded
    @POST(ApiConstants.GET_USER_INFO_BY_MEMBER_ID)
    fun getUserInfoByMemBerId(
            @Field("memberId") memberId: Int
    ): Observable<String>


}