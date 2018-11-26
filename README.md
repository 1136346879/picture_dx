# picture_dx
# 欢迎能来到这里

>* 该项目demo原本计划，及产生过程：
>* 本来我是用来练习kotlin的语法

>* 写了几个类练练手。然后是分模块来写的，后面慢慢加了好多功能。所以提交上来了，以便记录和学习。

>* 主工程：App（包含zxing扫一扫功能，view拖拽，）
>* 基础类库：BaseLibrary
>* 附属类库：Provider
>* 图片裁剪： uCrop   任意裁剪 [^code]
			 /**
     * 去裁剪  直接写这个方法即可  
     *
     * @param originalPath
     */
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
## 小心看图

![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/suibiancaijian.gif)
		CropImageActivity  按正方形裁切图片
			（参照该类即可）
		
		
	图片上传
	网络访问用的是
	com.squareup.retrofit2:retrofit:$retrofitVersion"
	 com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"）
	    /**
	     * 图片路径传过来
	     *
	     */
	    private fun loadUpImg(path:String) {
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
			},false)
	    }	
	    
扫一扫功能：qrode-module

个人中心：usercenter





//内存泄漏检测（打开扫一扫即可观察到现象，因类中包含了多个静态变量）

    debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.6.1'
    releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.6.1'
点击到相应的页面之后，如存在内存泄漏，该帮助类就会显示出来
会首先显示在通知栏中
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/leaks3.jpg)
通知栏里面的可以点击
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/leaks2.jpg)
最后在leaks这个app里面可以看到相应的内存泄漏详情
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/leaks1.jpg)




图片相关问题（显示裁剪压缩轮播上传等等）

系统裁剪图片调用
（1）进入相册（权限rxpremission
com.github.tbruyelle:rxpermissions
   
  	 val intent = Intent(Intent.ACTION_PICK, null)
        // 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        startActivityForResult(intent, UCROP_SELECT_PHOTO)

（2）在页面onActivityResult回调中，调用系统裁剪
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
        Log.e("TAKE_PHOTO", "TAKE_PHOTO-zoom-CROP_PHOTO")
        startActivityForResult(intent, CROP_PHOTO)


    }
    （3）再在回调onActivityResult中处理裁剪后的逻辑
   	 如上传，保存等。

	安卓交流群：qq   335042824
