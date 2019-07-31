package com.example.administrator.kotlintest.videorecorde

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.adapter.BaseRvAdapter
import com.example.administrator.kotlintest.ui.entity.PersonControlDao
import com.example.baselibrary.widgets.ToastUtilKt
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import java.util.ArrayList

class MainCameraActivity : RxAppCompatActivity(){

    private val listData = arrayListOf<PersonControlDao>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_camera)
        multipleStatusView?.showLoading()
        listData.add(PersonControlDao(0,"视频录制", null))
        listData.add(PersonControlDao(1,"CAMARE 拍照", null))

        multipleStatusView.showContent()
        multipleStatusView.setOnClickListener { ToastUtilKt.showCustomToast("点击重新加载") }
        val mainAdapter = MainAdapter(listData, this@MainCameraActivity)

        recycleview_list_all.let {
            it.adapter = mainAdapter
            it.layoutManager = LinearLayoutManager(this)
        }

        mainAdapter.setOnClickItem {
            when (it) {
                0 ->  startActivity(this.intentFor<CameraActivity>()
                        .putExtra(CameraActivity.TYPE_TAG, CameraActivity.TYPE_RECORD))
                1 ->  startActivity(this.intentFor<CameraActivity>()
                        .putExtra(CameraActivity.TYPE_TAG, CameraActivity.TYPE_CAPTURE))
            }
        }
    }

}
class MainAdapter(option: ArrayList<PersonControlDao>, mineActivity: MainCameraActivity)
    : BaseRvAdapter<PersonControlDao>(option, R.layout.item_person_control, mineActivity) {
    override fun onBindView(holder: Companion.BaseRvHolder, data: PersonControlDao) {
        data.text.let {
            holder.setText(R.id.tvText, it)
        }
        data.hint?.let {
            holder.setText(R.id.tvHint, it)
        }
    }
}