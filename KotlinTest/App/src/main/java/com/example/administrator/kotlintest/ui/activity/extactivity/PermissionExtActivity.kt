package com.example.administrator.kotlintest.ui.activity.extactivity

import android.Manifest
import com.afollestad.materialdialogs.MaterialDialog
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.ktx.ext.executeCmd
import com.example.ktx.ext.goToAppInfoPage
import com.example.ktx.ext.permission.PermissionRequest
import com.example.ktx.ext.permission.request
import com.example.ktx.ext.toast
import com.example.ktx.ext.view.notEmpty
import kotlinx.android.synthetic.main.activity_permission_ext.*
import kotlinx.android.synthetic.main.title_layout.*

/**
 * Created by luyao
 * on 2019/6/21 14:28
 */
class PermissionExtActivity : BaseActivity() {
    override fun resLayout(): Int {
        return R.layout.activity_permission_ext
    }

    override fun init() {
        mToolbar.title = "PermissionExt"
        requestCamera.setOnClickListener { requestCameraPermission() }
        requestCalendarAndAudio.setOnClickListener { requestCalendarAndAudioPermission() }
    }

    override fun logic() {

        commandBt.setOnClickListener {
            commandEt.notEmpty(
                    {
                        commandTv.text = executeCmd(text.toString())
                    },
                    {
                        toast("Please input command")
                    }
            )

        }
    }


    private fun requestCameraPermission() {
        request(Manifest.permission.CAMERA) {
            onGranted { toast("onGranted") }
            onDenied { toast("onDenied") }
            onShowRationale { showRationale(it) }
            onNeverAskAgain { goToAppInfoPage() }
        }
    }

    private fun showRationale(request: PermissionRequest) {
        MaterialDialog(this).show {
            title = "Request Permission"
            message(text = "We need permission to provide normal service. Do you agree ?")
            positiveButton(R.string.agree) { request.retry() }
            negativeButton(R.string.disagree)
        }
    }

    private fun requestCalendarAndAudioPermission() {
        request(Manifest.permission.READ_CALENDAR, Manifest.permission.RECORD_AUDIO) {
            onGranted { toast("onGranted") }
            onDenied { toast("onDenied") }
            onShowRationale { showRationale(it) }
            onNeverAskAgain { goToAppInfoPage() }
        }
    }

}