package com.example.administrator.kotlintest.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.example.administrator.kotlintest.util.EmojiFilter
import com.example.baselibrary.widgets.UIUtils


/**
 * Created by kangf on 2018/6/22.
 *
 * 可全部删除文字的editText
 */
class XEditText : EditText {

    private val DRAWABLE_LEFT = 0
    private val DRAWABLE_TOP = 1
    private val DRAWABLE_RIGHT = 2
    private val DRAWABLE_BOTTOM = 3

    private var rightDrawableRes = 0

    var rightDrawable: Drawable? = null
    var rightDrawable2: Drawable? = null
    var leftDrawable: Drawable? = null

    private var onClickRightDrawable: OnClickRightDrawable? = null
    private var mOnClickImeOption: OnClickImeOption? = null

    private var mSupportEmoji: Boolean = false

    fun setOnClickImeOption(ime: (Int) -> Unit) {
        mOnClickImeOption = object : OnClickImeOption {
            override fun onClick(actionId: Int) {
                ime(actionId)
            }
        }
    }

    fun setOnClickRightDrawable(view: (View) -> Unit, count: (Int) -> Unit) {
        onClickRightDrawable = object : OnClickRightDrawable {

            override fun onClick(view: View) {
                view(view)
            }

            override fun textChanged(count: Int) {
                count(count)
            }

        }
    }


    interface OnClickRightDrawable {
        fun onClick(view: View)

        fun textChanged(count: Int)
    }

    interface OnClickImeOption {
        fun onClick(actionId: Int)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {

        init()
        leftDrawable = compoundDrawables[DRAWABLE_LEFT]

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_UP -> {
                val drawableRight = compoundDrawables[DRAWABLE_RIGHT]
                if (drawableRight != null &&
                        event.x > (width - paddingRight - drawableRight.intrinsicWidth)) {
                    onClickRightDrawable?.onClick(this)
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        setSelection(this@XEditText.text.toString().length)
    }

    fun setRightImage(resId: Int) {
        if (resId != 0) {
            rightDrawable = UIUtils.getResDrawable(resId)
            rightDrawable!!.setBounds(0, 0, rightDrawable!!.minimumWidth,
                    rightDrawable!!.minimumHeight)
            setCompoundDrawables(leftDrawable, null, rightDrawable, null)

        } else {
            setCompoundDrawables(leftDrawable, null, null, null)
        }
    }

    fun setImeOpt(opt: Int): XEditText {
        this.imeOptions = opt
        return this
    }

    fun setUnSupportEmoji(support: Boolean) {
        this.mSupportEmoji = support
    }

    private fun init() {
        if(mSupportEmoji.not()) {
            this.filters = arrayOf(EmojiFilter())
        }
        this.imeOptions = EditorInfo.IME_ACTION_SEARCH
        this.setOnEditorActionListener { _, actionId, _ ->
            mOnClickImeOption?.onClick(actionId)
            true
        }

        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                onClickRightDrawable?.textChanged(s!!.length)
            }
        })
    }
}