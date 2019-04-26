package jsc.kit.keyboard
import android.content.pm.ActivityInfo
import com.alibaba.android.arouter.facade.annotation.Route
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.baselibrary.ui.activity.BaseUIActivity
import jsc.kit.keyboard.R

@Route(path = RouterApi.KeboardLibrary.ROUTER_KEYBOARD_ACTIVITY_URL)
class KeyBroadActivity : BaseUIActivity(){
    val EXTRA_LANDSCAPE = "landscape"
    private var fragment: KeyBoardFragment? = null
    override fun initLayout(): Int {
        return R.layout.activity_keyboard
    }

    override fun initView() {
    }

    override fun initData() {
        if (fragment == null) {
            fragment = KeyBoardFragment.newInstance()
        }
        supportFragmentManager.beginTransaction().add(
                R.id.container, fragment!!, KeyBoardFragment::class.java!!.simpleName
        ).commit()
    }

    override fun onResume() {
        if (intent.getBooleanExtra(EXTRA_LANDSCAPE, false) && requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        super.onResume()
    }
}