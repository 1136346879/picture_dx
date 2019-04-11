//package com.xfs.fsyuncai.bridge.retrofit.http.interceptor
//import com.example.administrator.kotlintest.area.SignUtils
//import com.example.administrator.kotlintest.widget.DevicesUtils
//import com.example.baselibrary.MyApplication
//import com.example.administrator.kotlintest.widget.StringUtils
//import com.xfs.fsyuncai.entity.accont.AccessManager
//import okhttp3.*
//import okio.Buffer
//import java.io.IOException
//
///**
// * Created by kangf on 2018/9/3.
// */
//class PublicParamsInterceptor : Interceptor {
//    private val key = "xfs7Alc74oCVpHXg97etTs"
//
//    companion object {
//        const val TAG = "LogInterceptor"
//    }
//
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//
//        val oldRequest = chain.request()
//        var newRequestBuild: Request.Builder? = oldRequest.newBuilder()
//        val method = oldRequest.method()
//        val postBodyString: String
//
//        val map = HashMap<String, Any?>()
//        map["member_id"] = AccessManager.instance().memberId()
//        map["account_type"] = AccessManager.instance().accountType()
//        map["token"] = AccessManager.instance().token()
//        map["timestamp"] = System.currentTimeMillis()
//        map["device_platform"] = "android"
//        //
////        map["version_code"] = DevicesUtils.getVerCode(MyApplication.cxt)
//        map["version_code"] = 102
//        map["push_token"] = ""
//        map["channel"] = DevicesUtils.getAppMetaData(MyApplication.cxt, "UMENG_CHANNEL")
//        map["device_id"] = DevicesUtils.getIMEI(MyApplication.cxt)
//        map["network"] = DevicesUtils.getAPNType(MyApplication.cxt)
//        map["device_brand"] = DevicesUtils.getMobileBrand()
//        map["os_version"] = DevicesUtils.getOsVersion()
//
//        if ("POST" == method) {
//            val oldBody = oldRequest.body()
//            when (oldBody) {
//                is FormBody -> {
//
//                    newRequestBuild = oldRequest.newBuilder()
//                    val newMap = HashMap<String, Any?>()
//                    for (i in 0 until oldBody.size()) {
//                        newMap[oldBody.name(i)] = oldBody.value(i)
//                    }
//                    for (obj in map) {
//                        newMap[obj.key] = "${obj.value}"
//                    }
//                    //生成签名后的参数
//                    postBodyString = SignUtils.createSign(newMap, key)
//                    newRequestBuild!!.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString))
//                }
//                is MultipartBody -> {
//                }
//                else -> {
//                    val oldMap = StringUtils.parseJSON2Map(bodyToString(oldBody))
////                    val oldMap = Gson().fromJson<Map<String,Any>>(bodyToString(oldBody))
//                    val map3 = HashMap<String, Any?>()
//                    if (oldMap != null) {
//                        map3.putAll(oldMap)
//                    }
//                    map3.putAll(map)
//                    //生成签名
//                    val sign = SignUtils.getSign(map3, key)
//                    map3["sign"] = sign
//                    postBodyString = StringUtils.getIntGson().toJson(map3)
//                    val lb = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), postBodyString)
//                    newRequestBuild = oldRequest.newBuilder()
//                    newRequestBuild.post(lb)
//                }
//            }
//        } else {
//            // 添加新的参数
//            val commonParamsUrlBuilder = oldRequest.url()
//                    .newBuilder()
//                    .scheme(oldRequest.url().scheme())
//                    .host(oldRequest.url().host())
//            for (obj in map) {
//                commonParamsUrlBuilder.addQueryParameter(obj.key, "${obj.value}")
//            }
//
//            newRequestBuild = oldRequest.newBuilder()
//                    .method(oldRequest.method(), oldRequest.body())
//                    .url(commonParamsUrlBuilder.build())
//        }
//        val newRequest = newRequestBuild!!
//                .addHeader("Accept", "application/json")
//                .addHeader("Accept-Language", "zh")
//                .addHeader("Content-Type", "application/json")
//                .build()
//
//        val response = chain.proceed(newRequest)
//        val mediaType = response.body()!!.contentType()
//        val content = response.body()!!.string()
//        return response
//                .newBuilder()
//                .body(okhttp3.ResponseBody.create(mediaType, content))
//                .header("application/json", "Content-Type")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
//                .addHeader("Access-Control-Max-Age", "3600")
//                .addHeader("Access-Control-Allow-Origin", "*")
//                .addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
//                .build()
//    }
//
//    private fun bodyToString(request: RequestBody?): String {
//        try {
//            val buffer = Buffer()
//            if (request != null)
//                request.writeTo(buffer)
//            else
//                return ""
//            return buffer.readUtf8()
//        } catch (e: IOException) {
//            return "did not work"
//        }
//
//    }
//
//}



package com.xfs.fsyuncai.bridge.retrofit.http.interceptor

import com.example.administrator.kotlintest.widget.DevicesUtils
import com.example.baselibrary.MyApplication
import com.example.administrator.kotlintest.widget.StringUtils
import com.xfs.fsyuncai.entity.accont.AccessManager
import okhttp3.*
import okio.Buffer
import java.io.IOException

/**
 * Created by kangf on 2018/9/3.
 */
class PublicParamsInterceptor : Interceptor {


    var TAG = "LogInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {

        val oldRequest = chain.request()
        var newRequestBuild: Request.Builder? = oldRequest.newBuilder()
        val method = oldRequest.method()
        var postBodyString = ""

        val map = HashMap<String, Any?>()
        map["member_id"] = AccessManager.instance().memberId()
        map["account_type"] = AccessManager.instance().accountType()
        map["token"] = AccessManager.instance().token()
        map["timestamp"] = System.currentTimeMillis()
        map["device_platform"] = "android"
        map["version_code"] = 102
        map["push_token"] = ""
        map["channel"] = DevicesUtils.getAppMetaData(MyApplication.cxt, "UMENG_CHANNEL")
        map["device_id"] = DevicesUtils.getIMEI(MyApplication.cxt)
        map["network"] = DevicesUtils.getAPNType(MyApplication.cxt)
        map["device_brand"] = DevicesUtils.getMobileBrand()
        map["os_version"] = DevicesUtils.getOsVersion()

        if ("POST" == method) {
            val oldBody = oldRequest.body()
            when (oldBody) {
                is FormBody -> {

                    val formBodyBuilder = FormBody.Builder()
                    for (obj in map) {
                        formBodyBuilder.add(obj.key, "${obj.value}")
                    }
                    newRequestBuild = oldRequest.newBuilder()

                    val formBody = formBodyBuilder.build()
                    postBodyString = bodyToString(oldRequest.body())
                    postBodyString += (if (postBodyString.isNotEmpty()) "&" else "") + bodyToString(formBody)
                    newRequestBuild!!.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString))
                }
                is MultipartBody -> {
//                    TLog.i("oldBody---MultipartBody----" + oldRequest.url())
//                    val oldPartList = oldBody.parts()
//                    val builder = MultipartBody.Builder()
//                    builder.setType(MultipartBody.FORM)
//                    val requestBody1 = RequestBody.create(MediaType.parse("text/plain"), Gson().toJson(map))
//                    for (part in oldPartList) {
//                        builder.addPart(part)
//                        postBodyString += bodyToString(part.body()) + "/n"
//                    }
//                    postBodyString += bodyToString(requestBody1) + "/n"
//                    builder.addPart(requestBody1)
//                    newRequestBuild = oldRequest.newBuilder()
//                    newRequestBuild.post(builder.build())
//                    Log.e(TAG, "MultipartBody," + oldRequest.url())
                }
                else -> {
                    val oldMap = StringUtils.parseJSON2Map(bodyToString(oldBody))
                    //不传参数的情况

//                    else {
                    val map3 = HashMap<String, Any?>()
                    if (oldMap != null) {
                        map3.putAll(oldMap)
                    }

                    map3.putAll(map)
                    postBodyString = StringUtils.getIntGson().toJson(map3)
                    val lb = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), postBodyString)
                    newRequestBuild = oldRequest.newBuilder()
                    newRequestBuild.post(lb)
//                    }
//                    newRequestBuild = oldRequest.newBuilder()
                }
            }
        } else {
            // 添加新的参数
            val commonParamsUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
            for (obj in map) {
                commonParamsUrlBuilder.addQueryParameter(obj.key, "${obj.value}")
            }

            newRequestBuild = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(commonParamsUrlBuilder.build())
        }
        val newRequest = newRequestBuild!!
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "zh")
                .addHeader("Content-Type", "application/json")
//                .addHeader("Content-Type", "application/json")
//                .header("Content-Type","application/json")
                .build()

//        val startTime = System.currentTimeMillis()
        val response = chain.proceed(newRequest)
//        val endTime = System.currentTimeMillis()
//        val duration = endTime - startTime
        val mediaType = response.body()!!.contentType()
        val content = response.body()!!.string()
//        val httpStatus = response.code()
//        val logSB = StringBuilder()
//        logSB.append("-------start:$method|")
//        logSB.append(newRequest.toString() + "\n|")
////        logSB.append(if (method.equals("POST", ignoreCase = true)) "post参数{$postBodyString}\n|" else "")
//        logSB.append("post参数{$postBodyString}\n|")
//        logSB.append("httpCode=$httpStatus;Response:$content\n|")
//        logSB.append("----------End:" + duration + "毫秒----------")
//        Log.d(TAG, logSB.toString())
        return response
                .newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .header("application/json", "Content-Type")
                .addHeader("Content-Type", "application/json")
                .addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept")
                .addHeader("Access-Control-Max-Age", "3600")
                .addHeader("Access-Control-Allow-Origin", "*")
                .addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                .build()
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }

}