# picture_dx
# 欢迎能来到这里（详情见demo，代码可直接拿到）

>* 1，主工程：App（**包含zxing扫一扫功能，view拖拽，**）
>* 2，基础类库：BaseLibrary
>* 3，测试类练市搭建：myInstalledPkg
>* 4，附属类库：Provider
>* 5， 扫一扫功能：qrode-module(直接可以引入该module)
>* 7， 个人中心：usercenter
>* 6， 图片裁剪： uCrop   任意裁剪 
>* 8， loadingview页面，banner轮播图
>* 9， recycleview列表数据，刷新及加载更多
>* 10，自定义view拖拽功能，
>* 11，数据库操作（SQlite和Greendao）
>*,12， 日历，页面半透明
>* 13， 内存泄漏集成
>* 14， 频道管理页面（高仿头条等新闻app）
>* 15，点击view出现粉碎炸掉的动画及viewpage左右滑动含titilebar一块滑动

客服  机器人  美洽第三方完美接入      魔窗（待接入） 

app
追求的理念;
apk包大小越小越好
app打开耗费资源少
UI图不是越炫酷越好 还要考虑性能 及对app的影响
如 以前加了个功能  打开pdf  加了个控件  apk直接加大了6M（上一版本12M，增加50%  相当于一个app，对用户什么感觉  直接就不下载了）
一个提示可以解决的  就别整个弹框（开发写出来就分分钟的事），但是代码量增加，代码是一个字母一个字母，一个字节字节堆起来的，会让apk无端端的增大，而不知道原因
着重点搞清楚：app这个是为了什么，功能还是UI还是效果 。为了UI效果耗时耗力耗财  功能影响了  

## 该项目demo原本计划，及产生过程：
## 本来我是用来练习kotlin的语法

** 写了几个类练练手。
			然后是分模块（1个主工程+6个依赖module）来写的，后面慢慢加了好多功能。所以提交上来了，以便记录和学习。**
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/project.jpg)

## 加入recycleview列表数据，
				包含下拉刷新，上拉加载更多，
				网络访问是okgo，(该功能已经封装好了，零bug，本计划单另一个module，由于需要引用一个jar包所以放在了扫一扫目录下，recycleview文件夹下，拉出来就可以直接使用)

		列表加载，刷新，加载更多
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/loadingview_refresh_loadmore.gif) 

## RecycleView列表带吸附的标题  纯手工打造 源码可见  原理是在定义  StickyItemDecoration 继承 RecyclerView.ItemDecoration 

## 数据库引用操作，生成表格后 增删改查（GreenDao）
	如何引入，如何使用，在代码中已经写得很清楚了，可下载代码查看.
 
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/StickyRec.gif)
上方标号与下方一至：
## 分模块（多module）编写的项目目录，解耦性大，任意一模块都可以随意拆卸，安装不影响其他模块内容
>* 1，主工程：App（**包含zxing扫一扫功能，view拖拽，**）
>* 2，基础类库：BaseLibrary
>* 3，测试类练市搭建：myInstalledPkg
>* 4，附属类库：Provider
>* 5， 扫一扫功能：qrode-module(直接可以引入该module)

[^code]

    implementation 'com.google.zxing:core:3.3.0'
    implementation 'com.google.zxing:android-core:3.3.0'
    
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/zxing_saoyisao.gif)
>* 7， 个人中心：usercenter
>* 6， 图片裁剪： uCrop   任意裁剪 
	图片裁剪可用于：用户头像展示裁剪，图片搜索功能（如淘宝，京东，百度等搜索）

[^code]

	/**
     * 去裁剪  直接写这个方法即可  
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
		用于app头像裁剪,用户图像裁剪
			（参照该类即可）
			
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/squre_crop.gif)			
		
##	图片上传
##	网络访问用的是 
 [^code]

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
	    
# //内存泄漏检测（（ThirdPartBannerZxingAcitivity）打开扫一扫即可观察到现象，因类中包含了多个静态变量）
   扫一扫（自定义仿照微信界面）
    implementation 'com.google.zxing:core:3.3.0'
    implementation 'com.google.zxing:android-core:3.3.0'
    
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/zxing.gif)
[^code]
    debugImplementation 'com.squareup.leakcanary:leakcanary-android:1.6.1'
    releaseImplementation 'com.squareup.leakcanary:leakcanary-android-no-op:1.6.1'
	点击到相应的页面之后，如存在内存泄漏，该帮助类就会显示出来
	
## 会首先显示在通知栏中

![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/leaks3.png)

## 通知栏可以点击显示详情

![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/leaks2.png)

## 最后在leaks这个app里面可以看到相应的内存泄漏详情

![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/leaks1.png)

## 图片相关问题（显示裁剪压缩轮播上传等等）

	##系统裁剪图片调用
	###（1）进入相册
	[^code]
	
	（权限rxpremission com.github.tbruyelle:rxpermissions
		 val intent = Intent(Intent.ACTION_PICK, null)
			// 如果朋友们要限制上传到服务器的图片类型时可以直接写如：image/jpeg 、 image/png等的类型
			intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
			startActivityForResult(intent, UCROP_SELECT_PHOTO)

	###（2）在页面onActivityResult回调中，调用系统裁剪 
	优点：稳定性高，无BUG，利于app
	缺点：裁剪页面的UI不可以修改是固定的
	
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/system_crop.gif)	

	调用系统裁剪
	[^code]
	
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
		  
    ###（3）再在回调onActivityResult中处理裁剪后的逻辑
			如上传，保存等。
## 图片轮播用了两种写法：（第三方和自定义）
	### （ThirdPartBannerZxingAcitivity）第三方     //轮播图（新闻列表头部展示）
			
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/lubotu.gif)
		
	[^code]		
				implementation 'com.youth.banner:banner:1.4.10'
				使用也很简单： homeBanner//设置banner样式
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                //设置图片加载器
                .setImageLoader(GlideImageLoader())
                //设置图片集合
                .setImages(list_path)
                //设置banner动画效果
                .setBannerAnimation(Transformer.Default)
                //设置标题集合（当banner样式有显示title时）
                .setBannerTitles(list_title)
                //设置轮播时间
                .setDelayTime(1500)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.CENTER)
                //banner设置方法全部调用完毕时最后调用
                .start()
				
	### （CustomerBannerActivityJava）自定义的   ImageBanner（图片加载用的picasso）
				直接设置数据就可以
				
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/imageBannergif.gif)

   [^code]
			  imageBanner.setList(imageArray,imageTitle);
			  
			  
CustomerBannerActivityJava 该类中加入自定义loadingview  **动画及图案纯手动打造**
					
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/loadingview.gif)


自定义view拖拽功能，（DragView）悬浮在页面之上，自动吸附在两侧，可以点击

![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/dragview.gif)

日历（完全自定义，可以编写相应日期，添加日程）
该页面用半透明style可以模糊看到后面的页面

[^code]
		
	类集成Activity  style风格（日历页面应用）
     <style name="Transparent">
            <item name="android:windowBackground">@color/transparent_background</item>
            <item name="android:windowNoTitle">true</item>
            <item name="android:windowIsTranslucent">true</item>
        </style>
		
	类集成RxAppCompatActivity  style风格(数据库页面应用)
    <style name="TranslucentActivityTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:colorBackgroundCacheHint">@null</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowAnimationStyle">@android:style/Animation</item>
        <item name="android:windowContentOverlay">@null</item>
    </style>
	
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/carlanderDate.gif)


## 添加数据库GreenDao页面，包含完整逻辑，增删改查。相应的数据可以在log日志页面查看



##  多种状态切换的view（loadingview，error，empty，content）
[^code]

		在布局文件中引入
		<com.classic.common.MultipleStatusView
			android:id="@+id/multipleStatusView"
			android:layout_width="match_parent"
			android:layout_height="match_parent"
			app:emptyView="@layout/layout_empty_view"（传入相应布局）
			app:errorView="@layout/layout_error_view"（传入相应布局）
			app:loadingView="@layout/layout_loading_view"（传入相应布局）
			app:noNetworkView="@layout/layout_network_view">（传入相应布局）
			
			<LinearLayout
				android:focusable="true"
				android:focusableInTouchMode="true"
				android:layout_width="match_parent"
				android:layout_height="match_parent"
				android:orientation="vertical">
			</LinearLayout>
		</com.classic.common.MultipleStatusView>
		
		在代码中
			//展示内容
            mLayoutStatusView?.showContent()
            //展示空内容
            mLayoutStatusView?.showEmpty()
            //展示错误页面
            mLayoutStatusView?.showError()
		//错误页面点击重新加载
        mLayoutStatusView?.setOnClickListener { ToastUtilKt.showToast("点击重新加载") }
		
		
##  频道管理页面（高仿头条等新闻app）
>* 频道名称数据是保存在Sqlite数据库中
		DragGrid  继承 GridView    
>* 我的频道页面 点击删除频道，长按拖拽顺序
		OtherGridView  继承  GridView   
>* 更多频道  点击添加频道

![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/channelPage.gif)

## 点击view出现粉碎炸掉的动画及viewpage左右滑动含titilebar一块滑动
![image](https://github.com/1136346879/picture_dx/blob/master/image_flod/smashZhandan.gif)


## 	**安卓高级开发交流群：qq   335042824**
