package jsc.kit.keyboard
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.dx.banner.newbaselibrary.routerapi.RouterApi
import com.example.baselibrary.ui.fragment.BaseLibraryFragment

/**
 * 自定义键盘可以本模块调用，也可以跨模块调用直接将fragment加入即可
 */
@Route (path = RouterApi.KeboardLibrary.ROUTER_KEYBOARD_FRAGMENT_URL)
class KeyBoardFragment : BaseLibraryFragment(), View.OnClickListener {
    override fun onClick(v: View?) {

        if (v!!.getId() == 45) {
//            showDialog()
        } else {
            keyboardView!!.toggleVisibility()
        }

    }

    private var keyboardView: KeyBoardView? = null
    override fun layoutResId(): Int {
        return R.layout.fragment_keyboard
    }

    override fun init() {
        keyboardView =  KeyBoardView(view!!.context)
        keyboardView!!.addAllInputView(view)
        KeyUtils.init(activity!!.window, keyboardView!!)
    }

    override fun logic() {
    }

    companion object {
        fun newInstance() = KeyBoardFragment()
    }

    override fun onResume() {
        super.onResume()
        keyboardView!!.onResume()
    }


    override fun onPause() {
        keyboardView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        keyboardView!!.onDestroy()
        super.onDestroy()
    }
}