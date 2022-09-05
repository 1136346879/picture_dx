package com.example.administrator.kotlintest.ui.qrcode

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.hardware.*
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import com.google.zxing.Result
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.ui.qrcode.zxing.camera.CameraManager
import com.example.administrator.kotlintest.ui.qrcode.zxing.decode.DecodeThread
import com.example.administrator.kotlintest.ui.qrcode.zxing.utils.BeepManager
import com.example.administrator.kotlintest.ui.qrcode.zxing.utils.CaptureActivityHandler
import com.example.administrator.kotlintest.ui.qrcode.zxing.utils.InactivityTimer
import com.example.baselibrary.widgets.ToastUtilKt
import com.google.gson.Gson
import com.xfs.fsyuncai.extend.fromJson
import kotlinx.android.synthetic.main.activity_capture.*
import java.io.IOException

class CaptureActivity : Activity(), SurfaceHolder.Callback {

    internal var Tag = 40
    @Suppress("DEPRECATION")
    private var camera: Camera? = null
    var cameraManager: CameraManager? = null
    private var handler: CaptureActivityHandler? = null
    private var inactivityTimer: InactivityTimer? = null
    private var beepManager: BeepManager? = null

    private var scanPreview: SurfaceView? = null
    private var scanContainer: RelativeLayout? = null
    private var scanCropView: RelativeLayout? = null
    private var scanLine: ImageView? = null

    var cropRect: Rect? = null
        private set
    private var isHasSurface = false

    private val statusBarHeight: Int
        get() {
            try {
                val c = Class.forName("com.android.internal.R\$dimen")
                val obj = c.newInstance()
                val field = c.getField("status_bar_height")
                val x = Integer.parseInt(field.get(obj).toString())
                return resources.getDimensionPixelSize(x)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return 0
        }


    internal var listener: SensorEventListener = object : SensorEventListener {

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            //当传感器精度发生变化时
        }

        override fun onSensorChanged(event: SensorEvent) {
            //当传感器监测到的数值发生变化时
            val value = event.values[0]
            if (value < Tag) {
                mCbFlash.visibility = View.VISIBLE
            } else {
                if (mCbFlash.isChecked) {
                    return
                }
                mCbFlash.visibility = View.GONE
            }
        }

    }

    fun getHandler(): Handler? {
        return handler
    }

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        setContentView(R.layout.activity_capture)
        scanPreview = findViewById<View>(R.id.capture_preview) as SurfaceView
        scanContainer = findViewById<View>(R.id.capture_container) as RelativeLayout
        scanCropView = findViewById<View>(R.id.capture_crop_view) as RelativeLayout
        scanLine = findViewById<View>(R.id.capture_scan_line) as ImageView
        inactivityTimer = InactivityTimer(this)
        beepManager = BeepManager(this)

        val animation = TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                1.0f)
        animation.duration = 3000
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.RESTART
        scanLine!!.startAnimation(animation)

        /**
         * 光线传感器
         */
        val senserManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = senserManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        senserManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        ivBack.setOnClickListener { finish() }

//        mTvCode.setOnClickListener { startActivity(intentFor<CodeActivity>()) }

        // 手动开启手电筒和关闭手电筒
        mCbFlash.setOnCheckedChangeListener { _, isChecked ->
            camera = cameraManager!!.camera

            try {
                val parameters = camera!!.parameters
                if (isChecked) {
                    if (TextUtils.equals(parameters.flashMode, "torch")) {
                        return@setOnCheckedChangeListener
                    } else {
                        parameters.flashMode = "torch"
                        mCbFlash.text = "轻触关闭"
                    }
                } else {
                    if (TextUtils.equals(parameters.flashMode, "off")) {
                        return@setOnCheckedChangeListener
                    } else {
                        parameters.flashMode = "off"
                        mCbFlash.text = "轻触点亮"
                    }
                }
                camera!!.parameters = parameters
            } catch (e: Exception) {
                finishFlashUtils()
            }
        }
    }


    private fun finishFlashUtils() {
        if (camera != null) {
            camera!!.stopPreview()
            camera!!.release()
        }
        camera = null
    }

    override fun onResume() {
        super.onResume()
        if (cameraManager == null) {
            cameraManager = CameraManager(application)
        }


        handler = null

        if (isHasSurface) {
            initCamera(scanPreview!!.holder)
        } else {
            scanPreview!!.holder.addCallback(this)
        }

        inactivityTimer!!.onResume()
    }

    override fun onPause() {
        if (handler != null) {
            handler!!.quitSynchronously()
            handler = null
        }
        inactivityTimer!!.onPause()
        beepManager!!.close()
        cameraManager!!.closeDriver()
        if (!isHasSurface) {
            scanPreview!!.holder.removeCallback(this)
        }
        super.onPause()
    }

    override fun onDestroy() {
        inactivityTimer!!.shutdown()
        super.onDestroy()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        isHasSurface = false
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!")
        }
        if (!isHasSurface) {
            isHasSurface = true
            initCamera(holder)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    fun handleDecode(result: Result?, bundle: Bundle) {
        ToastUtilKt.showCustomToast(result.toString())
        inactivityTimer!!.onActivity()
        beepManager!!.playBeepSoundAndVibrate()

        if (result == null) {
            error()
            return
        }

        // 获取解析的二维码信息
        val resultString = result.text
        // 解析失败
        if (TextUtils.isEmpty(resultString)) {
            error()
            return
        }
        try {
            val entity: QRCodeEntity? = Gson().fromJson<QRCodeEntity>(resultString)
            if (entity == null) {
                error()
                return
            }

            val spuId = entity.spuId
            if (spuId == null) {
                error()
                return
            }
            success(spuId)
        } catch (e: Exception) {
            error()
        }
    }


    private fun error() {
        ToastUtilKt.showCustomToast("抱歉，找不到相关匹配内容")
        finish()
    }

    private fun success(spuId: Int) {
//        startActivity(intentFor<DetailActivity>
//        (
//                IntentDataDef.DETAIL_SPU_ID_KEY to spuId
//        ))
ToastUtilKt.showCustomToast("扫码成功，spuID是$spuId")
        finish()
    }


    private fun initCamera(surfaceHolder: SurfaceHolder?) {
        if (surfaceHolder == null) {
            throw IllegalStateException("No SurfaceHolder provided") as Throwable
        }
        if (cameraManager!!.isOpen) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?")
            return
        }
        try {
            cameraManager!!.openDriver(surfaceHolder)
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
            if (handler == null) {
                handler = CaptureActivityHandler(this, cameraManager!!, DecodeThread.ALL_MODE)
            }

            initCrop()
        } catch (ioe: IOException) {
            Log.w(TAG, ioe)
            displayFrameworkBugMessageAndExit()
        } catch (e: RuntimeException) {
            Log.w(TAG, "Unexpected error initializing camera", e)
            displayFrameworkBugMessageAndExit()
        }

    }

    private fun displayFrameworkBugMessageAndExit() {
    }

    fun restartPreviewAfterDelay(delayMS: Long) {
        if (handler != null) {
            handler!!.sendEmptyMessageDelayed(R.id.restart_preview, delayMS)
        }
    }

    /**
     * 初始化截取的矩形区域
     */
    private fun initCrop() {
        val cameraWidth = cameraManager!!.cameraResolution.y
        val cameraHeight = cameraManager!!.cameraResolution.x

        /* 获取布局中扫描框的位置信息 */
        val location = IntArray(2)
        scanCropView!!.getLocationInWindow(location)

        val cropLeft = location[0]
        val cropTop = location[1] - statusBarHeight

        val cropWidth = scanCropView!!.width
        val cropHeight = scanCropView!!.height

        /* 获取布局容器的宽高 */
        val containerWidth = scanContainer!!.width
        val containerHeight = scanContainer!!.height

        /* 计算最终截取的矩形的左上角顶点x坐标 */
        val x = cropLeft * cameraWidth / containerWidth
        /* 计算最终截取的矩形的左上角顶点y坐标 */
        val y = cropTop * cameraHeight / containerHeight

        /* 计算最终截取的矩形的宽度 */
        val width = cropWidth * cameraWidth / containerWidth
        /* 计算最终截取的矩形的高度 */
        val height = cropHeight * cameraHeight / containerHeight

        /* 生成最终的截取的矩形 */
        cropRect = Rect(x, y, width + x, height + y)
    }

    companion object {
        /*  我设置的光线传感器阈值
           小于40会可以出现打开手电筒的标志   点击可以打开手电筒（可以在稍微黑暗的环境的时候就会出现图标）
         */
        private val TAG = CaptureActivity::class.java.simpleName
    }


}