package com.example.administrator.kotlintest.ui.activity.extactivity

import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.activity.BaseActivity
import com.example.ktx.ext.afterMeasured
import com.example.ktx.ext.toast
import kotlinx.android.synthetic.main.activity_listener.*
import kotlinx.android.synthetic.main.title_layout.*
import luyao.util.ktx.ext.listener.onProgressBarChanged
import luyao.util.ktx.ext.listener.queryTextListener
import luyao.util.ktx.ext.listener.textWatcher

/**
 * Created by luyao
 * on 2019/7/9 16:24
 */
class ListenerExtActivity : BaseActivity() {
    override fun resLayout(): Int {
        return R.layout.activity_listener
    }

    override fun init() {
        mToolbar.title = "ListenerExtActivity"

    }

    override fun logic() {
        listenerEt.afterMeasured {
            toast("$width $height")
        }
        listenerEt.textWatcher {
            onTextChanged { s, _, _, _ ->
                toast(s.toString())
            }

            afterTextChanged { toast(it.toString()) }
        }

        seekBar.onProgressBarChanged { progress, fromUser ->
            toast("$progress $fromUser")
        }

        searchView.queryTextListener {
            onQueryTextChange { query -> query?.let { toast(it) } }
            onQueryTextSubmit { newText -> newText?.let { toast(it) } }
        }
    }


}