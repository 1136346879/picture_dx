package com.xfs.fsyuncai.bridge.retrofit.callback

import android.text.TextUtils
import com.example.administrator.kotlintest.widget.TLog
import com.example.administrator.kotlintest.widget.ToastUtilKt
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorModel
import com.xfs.fsyuncai.entity.accont.AccessManager

/**
 * Created by kangf on 2018/6/16.
 */
abstract class HttpOnNextListener {


    open fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {

        apiErrorModel?.message?.let {
            ToastUtilKt.showToast(it)
            TLog.i(it)
        }
        //token过期处理
        if (apiErrorModel != null &&//不为空
                apiErrorModel.status == 12 &&//错误码12
                TextUtils.isEmpty(apiErrorModel.message).not()&&//错误信息不为空
                apiErrorModel.message.contains("token信息不合法")){//错误信息是指定信息
//            AppManager.instance().finishAllActivity()
            AccessManager.instance().deleteUser()
//            FsYuncaiApp.cxt!!.startActivity(FsYuncaiApp.cxt!!.intentFor<LoginActivity>())
        }
    }

    open fun onCache(jsonResponse: String) {
        onNext(jsonResponse)
    }

    abstract fun onNext(json: String)


}