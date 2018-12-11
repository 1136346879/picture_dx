package com.example.administrator.kotlintest.picture
import android.net.Uri
import com.example.administrator.kotlintest.R
import com.example.baselibrary.ui.activity.BaseUIActivity
import kotlinx.android.synthetic.main.image_desirn_activity.*
import java.io.File

class ImageDiscernActivity : BaseUIActivity(){
    override fun initLayout(): Int {

        return R.layout.image_desirn_activity
    }

    override fun initView() {
        close.setOnClickListener { finish() }

        val intent= intent
        val selectPath = intent.getStringExtra("image_path")
        if (selectPath != null) {
            image_view.setImageURI(Uri.fromFile(File(selectPath)))
            loadUpImg(selectPath)
        }
    }

    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    private var httpManager: HttpManager = HttpManager.instance()
    private fun loadUpImg(path:String) {
//        val file = File(path)
//        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
//        httpManager.doHttpDeal(this, httpManager.createService(OrderService::class.java)!!.pictureSearch(body),
//                object : HttpOnNextListener() {
//                    override fun onNext(json: String) {
//                        online_desirn.text = "识别成功"
//                        close.visibility = GONE
//                        ToastUtil.showCustomToast("Image upload sucessed :$json")
//
//                    }
//
//                    override fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
//                        super.onError(statusCode, apiErrorModel)
//                        ToastUtil.showCustomToast("Image upload failed")
//                    }
//                })
    }
}