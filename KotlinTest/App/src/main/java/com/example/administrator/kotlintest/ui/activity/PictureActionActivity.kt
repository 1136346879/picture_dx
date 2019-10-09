package com.example.administrator.kotlintest.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.CursorLoader
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.*
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import com.blankj.ALog
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.picture.CropImageActivity
import com.example.administrator.kotlintest.picture.UploadActivity
import com.example.baselibrary.widgets.ToastUtilKt
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.xfs.fsyuncai.bridge.retrofit.callback.HttpOnNextListener
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorModel
import com.example.administrator.kotlintest.bridge.retrofit.http.HttpManager
import com.example.administrator.kotlintest.bridge.retrofit.http.RequestOption
import com.example.administrator.kotlintest.entity.AppUpdateEntity
import com.example.administrator.kotlintest.listener.BaseCommonInterface
import com.example.administrator.kotlintest.options.UpdateAppOptions
import com.example.administrator.kotlintest.update.AppUpdateUtil
import com.example.administrator.kotlintest.update.UpdateDialog
import com.example.administrator.kotlintest.util.SPUtils
import com.google.gson.Gson
import com.plumcookingwine.network.callback.INetworkCallback
import com.plumcookingwine.network.cookie.CookieResultListener
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.xfs.fsyuncai.bridge.retrofit.service.CommonService
import com.xfs.fsyuncai.bridge.retrofit.service.OrderService
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.util.AttrsUtils
import com.yalantis.ucrop.util.PictureMimeType
import kotlinx.android.synthetic.main.activitu_picture_action.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.intentFor
import java.io.File
import java.io.IOException

class PictureActionActivity() : RxAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitu_picture_action)
        //Ucrop矩形图片裁剪
        tv4.setOnClickListener { pickPhotoUcrop() }//打开相册
        //方形裁剪
        tv5.setOnClickListener { pickPhoto() }//打开相册
        //系统裁剪
        tv6.setOnClickListener { startActivity(this!!.intentFor<UploadActivity>().putExtra("", "").putExtra("", "")) }

    }
    /**
     * 使用相册中的图片
     */
    private val SELECT_PHOTO = 3
    private val UCROP_SELECT_PHOTO = 33
    private var picWith = 310
    private var picHeight = 220
    private var photoUri: Uri? = null
    //图片根目录
    private var sdPath: String? = null
    /***
     * 裁剪图片请求
     */
    val CROP_PHOTO = 2
    /***
     * 从Intent获取图片路径的KEY
     */
    val KEY_PHOTO_PATH = "photo_path"

    private fun pickPhoto() {
//        photoUri = getImageUri()
        val intent = Intent(Intent.ACTION_PICK, null)
        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(intent, SELECT_PHOTO)
    }

    private fun pickPhotoUcrop() {
//        photoUri = getImageUri()
        val intent = Intent(Intent.ACTION_PICK, null)
        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(intent, UCROP_SELECT_PHOTO)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            //选中图片
            SELECT_PHOTO -> if (data != null) {
                if (resultCode == Activity.RESULT_OK) {
                    val imageUri = data.data
                    val selectPhoto = getRealPathFromUri(this, imageUri)
                    Log.e("selectPhoto:", selectPhoto)
//                    startActivity(activity!!.intentFor<ImageDiscernActivity>()
//                            .putExtra("image_path",selectPhoto))
                    //打开裁剪页面
                    startActivity(intentFor<CropImageActivity>()
                            .putExtra("image_path", selectPhoto))
//                        if (selectPhoto != null) {
//                            loadUpImg(selectPhoto)
//                        }
//                    ivAvatar.setImageURI(Uri.fromFile(File(selectPhoto)))
                }
//系统裁剪
//                startPhotoZoom(data.data,picWith,picHeight)
            } else {
//                ToastUtil.showToast("获取图片失败")
                setResult(Activity.RESULT_CANCELED, intent)
//                activity?.finish()
            }
            UCROP_SELECT_PHOTO -> if (data != null) {
                if (resultCode == Activity.RESULT_OK) {
                    val imageUri = data.data
                    val selectPhoto = getRealPathFromUri(this@PictureActionActivity, imageUri)
                    Log.e("selectPhoto:", selectPhoto)
                    //图片识别上传至服务器
//                    startActivity(activity!!.intentFor<ImageDiscernActivity>()
//                            .putExtra("image_path",selectPhoto))
                    //自定义的图片裁剪，正方形裁剪
//                    startActivity(intentFor<CropImageActivity>()
//                            .putExtra("image_path",selectPhoto))
                    startCrop(selectPhoto!!)
                }
            } else {
//                ToastUtil.showToast("获取图片失败")
                setResult(Activity.RESULT_CANCELED, intent)
//                activity?.finish()
            }

            //系统裁剪之后--裁剪图片--上传图片
            CROP_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                intent.putExtra(KEY_PHOTO_PATH, photoUri?.path)
                setResult(Activity.RESULT_OK, intent)
//                activity?.finish()
//                tv5.setImageURI(Uri.parse(photoUri?.path))
                loadUpImg(Uri.parse(photoUri?.path).toString())
            } else {
//                ToastUtil.showCustomToast("取消图片设置!")
                setResult(Activity.RESULT_CANCELED, intent)
//                activity?.finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }



    /**
     * 去裁剪
     *
     * @param originalPath
     */
    @SuppressLint("Range")
    protected fun startCrop(originalPath: String) {
        val options = UCrop.Options()
        val toolbarColor = AttrsUtils.getTypeValueColor(this, -1)
        val statusColor = AttrsUtils.getTypeValueColor(this, -1)
        val titleColor = AttrsUtils.getTypeValueColor(this, -1)
        options.setToolbarColor(toolbarColor)//状态栏背景
        options.setStatusBarColor(statusColor)
        options.setToolbarWidgetColor(titleColor)
        options.setCircleDimmedLayer(false)
        options.setShowCropFrame(true)//显示裁剪框
        options.setShowCropGrid(true)//显示裁剪框网格
        options.setDragFrameEnabled(true)//裁剪框拖拽
        options.setScaleEnabled(true)//图片缩放
        options.setRotateEnabled(true)//图片旋转
        options.setCompressionQuality(70)//图片质量
        options.setHideBottomControls(true)
        options.setFreeStyleCropEnabled(true)//裁剪
        val isHttp = false
        val imgType = PictureMimeType.getLastImgType(originalPath)
        val uri = if (isHttp) Uri.parse(originalPath) else Uri.fromFile(File(originalPath))
        UCrop.of(uri, Uri.fromFile(File(getDiskCacheDir(this), System.currentTimeMillis().toString() + "" + imgType)))
                .withAspectRatio(0F, 0F)
                .withMaxResultSize(0, 0)
                .withOptions(options)
                .start(this)
//        finish()
    }

    private fun getRealPathFromUri(mainActivity: PictureActionActivity, imageUri: Uri?): String? {


        if (mainActivity == null || imageUri == null) {
            return null
        }
        if ("file".equals(imageUri.scheme, true)) {
            return getRealPathFromUri_Byfile(imageUri)
        } else if ("content".equals(imageUri.scheme, true)) {
            return getRealPathFromUri_Api11To18(mainActivity, imageUri)
        }
        return getRealPathFromUri_AboveApi19(mainActivity, imageUri)//没用到
    }

    //针对图片URI格式为Uri:: file:///storage/emulated/0/DCIM/Camera/IMG_20170613_132837.jpg
    private fun getRealPathFromUri_Byfile(uri: Uri): String {
        val uri2Str = uri.toString()
        return uri2Str.substring(uri2Str.indexOf(":") + 3)
    }

    /**
     * 适配api19以上,根据uri获取图片的绝对路径
     */
    @SuppressLint("NewApi")
    private fun getRealPathFromUri_AboveApi19(context: Context, uri: Uri): String? {
        var filePath: String? = null
        var wholeID: String?
        wholeID = DocumentsContract.getDocumentId(uri)
        // 使用':'分割
        val id = wholeID!!.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val selection = MediaStore.Images.Media._ID + "=?"
        val selectionArgs = arrayOf(id)
        val cursor = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                selection, selectionArgs, null)
        val columnIndex = cursor.getColumnIndex(projection[0])
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex)
        }
        cursor.close()
        return filePath
    }

    /**
     * //适配api11-api18,根据uri获取图片的绝对路径。
     * 针对图片URI格式为Uri:: content://media/external/images/media/1028
     */
    private fun getRealPathFromUri_Api11To18(context: Context, uri: Uri): String? {
        var filePath: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(context, uri, projection, null, null, null)
        val cursor = loader.loadInBackground()
        if (cursor != null) {
            cursor!!.moveToFirst()
            filePath = cursor!!.getString(cursor!!.getColumnIndex(projection[0]))
            cursor!!.close()
        }
        return filePath
    }


    /**
     * 图片路径传过来
     *
     *
     */
    private fun loadUpImg(path: String) {
        val httpManger = HttpManager.instance()
//        val file = File(BitmapUtils.compressImageUpload(path))
        val file = File(path)
        val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        httpManger.doHttpDeal(this, httpManger.createService(OrderService::class.java)!!.pictureSearch(body),
                object : HttpOnNextListener() {
                    override fun onNext(json: String) {
                        ToastUtilKt.showCustomToast("Image upload sucessed :$json")
                    }

                    override fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
                        super.onError(statusCode, apiErrorModel)
                        ToastUtilKt.showCustomToast("Image upload failed")
                    }
                })
    }

    /**
     * 系统裁剪
     * uri
     * 宽 本地写死
     * 高 本地写死
     *
     */
    private fun startPhotoZoom(data: Uri?, picWith: Int, picHeight: Int) {

        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(data, "image/*")
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true")
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1.4)
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", picWith)
        intent.putExtra("outputY", picHeight)
        intent.putExtra("scale", true)
        photoUri = getImageUri()
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        intent.putExtra("return-data", false)
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG)
        intent.putExtra("noFaceDetection", true) // no face detection
        ALog.e("TAKE_PHOTO", "TAKE_PHOTO-zoom-CROP_PHOTO")
        startActivityForResult(intent, CROP_PHOTO)
    }

    private fun getImageUri(): Uri? {
        var outputImage: File? = null
        val t = System.currentTimeMillis()
        outputImage = File(sdPath, t.toString() + "xfsImage.jpg")
        if (outputImage!!.exists()) {
            outputImage.delete()
        }
        try {
            outputImage.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Uri.fromFile(outputImage)
    }

    /**
     * @param context
     * @return
     */
    fun getDiskCacheDir(context: Context): String {
        var cachePath: String? = null
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable()) {
            cachePath = context.externalCacheDir!!.path
        } else {
            cachePath = context.cacheDir.path
        }
        return cachePath
    }
    constructor(parcel: Parcel) : this() {
        picWith = parcel.readInt()
        picHeight = parcel.readInt()
        photoUri = parcel.readParcelable(Uri::class.java.classLoader)
        sdPath = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<PictureActionActivity> {
        override fun createFromParcel(parcel: Parcel): PictureActionActivity {
            return PictureActionActivity(parcel)
        }

        override fun newArray(size: Int): Array<PictureActionActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onResume() {
        super.onResume()
        checkAppVersion()
    }

    /**
     * 版本检测
     */
    private fun checkAppVersion() {
        com.plumcookingwine.network.manager.HttpManager.instance.doHttpDeal(
                UpdateAppOptions(),
                object : INetworkCallback<String>(BaseCommonInterface(this)) {

                    override fun onSuccess(obj: String, cookieListener: CookieResultListener) {
                        val entity = Gson().fromJson(obj, AppUpdateEntity::class.java)
                        onCheckAppVersioResult(entity)

                    }

                    override fun onError(err: com.plumcookingwine.network.exception.ApiErrorModel?) {
                        onCheckAppVersioResult(null)
                    }
                }
        )
    }

    /**
     * 版本检测
     */
//    private fun checkAppVersion() {
//        HttpManager.instance().apply {
//            setOption(RequestOption().apply {
//                isShowProgress = false
//            })
//            doHttpDeal(this@PictureActionActivity,
//                    createService(CommonService::class.java).getAppUpdateVersionData().bindToLifecycle(this@PictureActionActivity),
//                    object : HttpOnNextListener() {
//                        override fun onNext(json: String) {
//                            val entity = Gson().fromJson(json, AppUpdateEntity::class.java)
//                            onCheckAppVersioResult(entity)
//                        }
//
//                        override fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
//                            super.onError(statusCode, apiErrorModel)
//                            onCheckAppVersioResult(null)
//                        }
//                    })
//        }
//    }
    /* 更新对话框 */
    private var mUpdateDia: UpdateDialog? = null
    /**
     * 版本检测的回调，为空的时候表示没有更新
     */
    fun onCheckAppVersioResult(result: AppUpdateEntity?) {
        //异常状态
        if (result == null || result.code.isNullOrBlank() || result.code!!.toInt() != 0) {
            AppUpdateUtil.removeOldApk(this@PictureActionActivity)
            return
        }
        //不升级
        if (result.updateType == 0) {
            AppUpdateUtil.removeOldApk(this@PictureActionActivity)
            return
        }
        //用户点击取消升级后的判断逻辑。本地和线上不一致的时候提示升级
        val localSaveVersion = SPUtils.getObjectForKey(SPUtils.CURRENT_VERSION_CODE, "") as String
        if (localSaveVersion.isNotEmpty() && localSaveVersion == result.latest_version_code) {
            AppUpdateUtil.removeOldApk(this@PictureActionActivity)
            return
        }

        if (mUpdateDia == null) {
            mUpdateDia = UpdateDialog(this@PictureActionActivity, result)
            if (!mUpdateDia!!.isShowing) {
                mUpdateDia!!.show()
            }
            mUpdateDia!!.setOnDismissListener {
                mUpdateDia = null
            }
        }
    }
}