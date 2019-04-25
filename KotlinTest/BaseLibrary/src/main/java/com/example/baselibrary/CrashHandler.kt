package com.example.baselibrary

import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Thread.UncaughtExceptionHandler
import java.text.SimpleDateFormat
import java.util.Date
import java.util.HashMap

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.NameNotFoundException
import android.os.Build
import android.os.Environment
import android.os.Looper
import android.util.Log
import android.widget.Toast

/**
 * Created by Kevin on 2019/2/25
 *
 * 保证只有一个CrashHandler实例
 */
class CrashHandler private constructor() : UncaughtExceptionHandler {

    // 系统默认的UncaughtException处理类
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null
    // 程序的Context对象
    private var mContext: Context? = null
    // 用来存储设备信息和异常信息
    private val infos = HashMap<String, String>()

    // 用于格式化日期,作为日志文件名的一部分
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")

    /**
     * 初始化
     *
     * @param context cxt
     */
    fun init(context: Context) {
        mContext = context
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    override fun uncaughtException(thread: Thread, ex: Throwable?) {
        if (ex?.cause != null
                && ex.cause!!.message != null
                && ex.cause!!.message!!.contains("PDF file is corrupted")) {
            Log.e(TAG, "error : " + ex.cause!!.message)
            Looper.prepare()
            Toast.makeText(mContext, "该文件已经损坏，请检查！",
                    Toast.LENGTH_SHORT).show()
            Looper.loop()
            // 收集设备参数信息
            collectDeviceInfo(mContext!!)
            // 保存日志文件
            saveCrashInfo2File(ex)
        } else {
            if (!handleException(ex) && mDefaultHandler != null) {
                // 如果用户没有处理则让系统默认的异常处理器来处理
                mDefaultHandler!!.uncaughtException(thread, ex)
            } else {
                try {
                    Thread.sleep(3000)
                } catch (e: InterruptedException) {
                    Log.e(TAG, "error : ", e)
                }

                //                Intent intent = new Intent(mContext.getApplicationContext(),
                //                        MainActivity.class);
                //                PendingIntent restartIntent = PendingIntent.getActivity(
                //                        mContext.getApplicationContext(), 0, intent,
                //                        PendingIntent.FLAG_NO_CREATE);
                //                // 退出程序
                //                AlarmManager mgr = (AlarmManager) mContext
                //                        .getSystemService(Context.ALARM_SERVICE);
                //                if(mgr == null) {
                //                    return;
                //                }
                //                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                //                        restartIntent); // 1秒钟后重启应用
                // 退出程序
                AppManager.instance().exitApp(1)
            }
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private fun handleException(ex: Throwable?): Boolean {
        if (ex == null) {
            return false
        }
        // 使用Toast来显示异常信息
        object : Thread() {
            override fun run() {
                Looper.prepare()
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.",
                        Toast.LENGTH_SHORT).show()
                Looper.loop()
            }
        }.start()
        // 收集设备参数信息
        collectDeviceInfo(mContext!!)
        // 保存日志文件
        saveCrashInfo2File(ex)
        return true
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx ctx
     */
    private fun collectDeviceInfo(ctx: Context) {
        try {
            val pm = ctx.packageManager
            val pi = pm.getPackageInfo(ctx.packageName,
                    PackageManager.GET_ACTIVITIES)
            if (pi != null) {
                val versionName = if (pi.versionName == null)
                    "null"
                else
                    pi.versionName
                @Suppress("DEPRECATION")
                val versionCode = pi.versionCode.toString() + ""
                infos["versionName"] = versionName
                infos["versionCode"] = versionCode
            }
        } catch (e: NameNotFoundException) {
            Log.e(TAG, "an error occured when collect package info", e)
        }

        val fields = Build::class.java.declaredFields
        for (field in fields) {
            try {
                field.isAccessible = true
                infos[field.name] = field.get(null).toString()
                Log.d(TAG, field.name + " : " + field.get(null))
            } catch (e: Exception) {
                Log.e(TAG, "an error occured when collect crash info", e)
            }

        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex ex
     */
    private fun saveCrashInfo2File(ex: Throwable) {

        val sb = StringBuilder()
        for ((key, value) in infos) {
            sb.append(key).append("=").append(value).append("\n")
        }

        val writer = StringWriter()
        val printWriter = PrintWriter(writer)
        ex.printStackTrace(printWriter)
        var cause: Throwable? = ex.cause
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        printWriter.close()
        val result = writer.toString()
        sb.append(result)
        try {
            val timestamp = System.currentTimeMillis()
            val time = formatter.format(Date())
            val fileName = "crash-$time-$timestamp.log"
            if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                val path = Environment
                        .getExternalStorageDirectory().path + "/BUG收集/crash" + "/"
                val dir = File(path)
                if (!dir.exists()) {

                    dir.mkdirs()
                }
                val fos = FileOutputStream(path + fileName)
                fos.write(sb.toString().toByteArray())
                fos.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, "an error occured while writing file...", e)
        }

    }

    companion object {
        const val TAG = "CrashHandler"
        // CrashHandler实例
        /**
         * 获取CrashHandler实例 ,单例模式
         */
        @SuppressLint("StaticFieldLeak")
        val instance = CrashHandler()
    }
}

