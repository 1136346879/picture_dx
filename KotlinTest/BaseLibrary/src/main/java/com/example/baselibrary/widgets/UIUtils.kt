package com.example.baselibrary.widgets

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.EditText
import com.example.baselibrary.MyApplication

/**
 * Created by kangf on 2018/6/19.
 */
object UIUtils {

    @JvmStatic
    fun context(): Context = MyApplication.cxt;

    @JvmStatic
    fun resources(): Resources = MyApplication.resources

    @JvmStatic
    fun getColor(id: Int): Int {
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            @Suppress("DEPRECATION")
            resources().getColor(id)
        } else {
            context().getColor(id)
        }
    }

    @JvmStatic
    fun getResText(id: Int): CharSequence {
        return context().getText(id)
    }

    @JvmStatic
    fun getResDrawable(id: Int): Drawable {
        return if (Build.VERSION.SDK_INT < 21) {
            @Suppress("DEPRECATION")
            resources().getDrawable(id)
        } else {
            context().getDrawable(id)
        }
    }

    @JvmStatic
    fun getSpanned(text: String? = ""): Spanned? {
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            @Suppress("DEPRECATION")
            Html.fromHtml(text)
        } else {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }
    }

    @JvmStatic
    fun setPriceCss(price: String): String {

        var newPrice: String
        newPrice = if (price.startsWith("¥")) {
            price.replace("¥", "<small>¥</small>")
        } else {
            "<small>¥</small>$price"
        }
        val small = newPrice.split(".")
        if (small.size > 1) {
            newPrice = "${small[0]}.<small>${small[1]}</small>"

        }
        return newPrice
    }

    @JvmStatic
    fun setPriceRangeCss(priceRange: String? = ""): String? {
//        "¥329.00 ~ ¥1299.00"

        var price = priceRange?.split("~")
        var newPrice = ""
        price?.mapIndexed { i, it ->
            if (i == price.size - 1)
                newPrice += setPriceCss(it)
            else
                newPrice += "${setPriceCss(it)}~"
        }
        return newPrice
    }


    /**
     * dip 转 px
     *
     * @param dip
     * @return
     */
    @JvmStatic
    fun dip2px(dip: Int): Int {
        val metrics = resources().displayMetrics
        val density = metrics.density
        return (dip * density + 0.5f).toInt()
    }


    /**
     * px 转 dip
     *
     * @param px
     * @return
     */
    @JvmStatic
    fun px2dip(px: Int): Int {
        val metrics = resources().getDisplayMetrics()
        val density = metrics.density
        return (px / density + 0.5f).toInt()
    }

    @JvmStatic
    fun getScreenWidth(): Int {
        val dm = resources().getDisplayMetrics()
        return dm.widthPixels

    }

    @JvmStatic
    fun getScreenHeight(): Int {
        val dm = resources().displayMetrics
        return dm.heightPixels
    }

    @JvmStatic
    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen",
                "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }


    /**
     * 设置背景透明度
     * @param context
     * @param alpha
     */
    @JvmStatic
    fun setBackgroundAlpha(context: Context, alpha: Float) {
        if (alpha < 0 || alpha > 1) return
        val windowLP = (context as Activity).window.attributes
        windowLP.alpha = alpha
        if (alpha == 1f) {
            context.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            context.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        context.window.attributes = windowLP
    }

    /*
       输入框字数限制提示
     */
    fun limitEditTextLength(editText: EditText, length : Int){
        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val str = s.toString()
                if (str.length > length){
                    editText.setText(str.substring(0,length))
                    editText.setSelection(length)
                    ToastUtilKt.showIconToast("最多${length}个字", 0, false)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

}