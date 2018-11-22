# picture_dx
图片相关问题（显示裁剪压缩轮播上传等等）

系统裁剪图片调用
（1）进入相册
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
