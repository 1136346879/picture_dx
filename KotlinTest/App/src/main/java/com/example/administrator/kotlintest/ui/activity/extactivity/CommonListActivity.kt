package com.example.administrator.kotlintest.ui.activity.extactivity

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.administrator.kotlintest.ui.activity.extactivity.adapter.CommonAdapter
import com.example.administrator.kotlintest.ui.activity.extactivity.bean.ItemBean
import com.example.ktx.ext.view.itemPadding
import kotlinx.android.synthetic.main.activity_common_list.*
import kotlinx.android.synthetic.main.title_layout.*

/**
 * Created by luyao
 * on 2019/6/18 10:27
 */
abstract class CommonListActivity : BaseActivity() {

    open val dataList = ArrayList<ItemBean>()
    private val commonAdapter by lazy { CommonAdapter() }

    override fun resLayout(): Int {
        return R.layout.activity_common_list
    }
    protected fun startActivity(z: Class<*>) {
        startActivity(Intent(this, z))
    }

    protected fun startActivity(z: Class<*>, name: String, value: Boolean) {
        val intent = Intent(this, z).putExtra(name, value)
        startActivity(intent)
    }
    override fun init() {
        initList()
        mToolbar.run {
            title = getToolbarTitle()
            setNavigationIcon(R.drawable.arrow_back)
        }
        initRecycleView()
    }

    override fun logic() {
        commonAdapter.setNewData(dataList)
    }

    private fun initRecycleView() {

        commonAdapter.setOnItemClickListener { _, _, position ->
            dataList[position].z?.let { startActivity(it) }
            handleClick(position)
        }

        commonRecycleView.run {
            itemPadding(5, 5, 10, 10)
            layoutManager = LinearLayoutManager(this@CommonListActivity)
            adapter = commonAdapter
        }
    }

    abstract fun getToolbarTitle(): String
    abstract fun initList()
    open fun handleClick(position:Int) {}
}