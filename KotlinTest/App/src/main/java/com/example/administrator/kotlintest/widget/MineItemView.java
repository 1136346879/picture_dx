package com.example.administrator.kotlintest.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;


/**
 * Created by Zhangpeiyuan on 2015/12/3 0003.
 * 我的页面的Item View
 */
public class MineItemView extends LinearLayout {

    /**
     * 左边图片，右边箭头或图片
     */
    private ImageView leftImg, rightImg;
    /**
     * 消息红点
     */
    private ImageView newMessageIcon;
    /**
     * 文本
     */
    private TextView textInfo;
    /**
     * 灰线
     */
    private View itemUnderline;
    private CheckBox checkBox;
    private TextView textExplainView;
    private LinearLayout mineItemLayout;

    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MineItemView(Context context) {
        super(context);
    }

    public MineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //填充布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_mine_item, this, true);
        leftImg = (ImageView) view.findViewById(R.id.left_img);
        rightImg = (ImageView) view.findViewById(R.id.right_arrow);
        newMessageIcon = (ImageView) view.findViewById(R.id.new_message_icon);
        textInfo = (TextView) view.findViewById(R.id.text_info);
        itemUnderline = view.findViewById(R.id.item_underline);
        checkBox = (CheckBox) view.findViewById(R.id.checkbox_switch);
        textExplainView = (TextView) view.findViewById(R.id.text_explain);
        mineItemLayout = (LinearLayout)view.findViewById(R.id.mineItemLayout);

        //填充自定义样式
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MineItemView);
        Drawable drawable = typedArray.getDrawable(
                R.styleable.MineItemView_imgsrc);
        Drawable rightDrawable = typedArray.getDrawable(
                R.styleable.MineItemView_imgright);
        String text = (String) typedArray.getText(
                R.styleable.MineItemView_text);
        Boolean showUnderline = typedArray.getBoolean(
                R.styleable.MineItemView_underline,
                true);
        Boolean showIcon = typedArray.getBoolean(
                R.styleable.MineItemView_showIcon,
                false);
        float underlinePaddingLeft = typedArray.getDimension(
                R.styleable.MineItemView_underlinePaddingLeft,
                0);
        String textExplain = typedArray.getString(
                R.styleable.MineItemView_textExplain);
        Boolean textExplainIsShow = typedArray.getBoolean(
                R.styleable.MineItemView_textExplainIsShow, false);
        float imgSrcWidth = typedArray.getDimension(
                R.styleable.MineItemView_imgsrcWidth, 0);
        float textSize = typedArray.getDimension(
                R.styleable.MineItemView_textTitleSize, 0);
        float textLeftMargin = typedArray.getDimension(
                R.styleable.MineItemView_textLeftMargin, 0);

        Drawable checkboxSelected = typedArray.getDrawable(R.styleable.MineItemView_checkboxSelected);

        if (checkboxSelected != null) {
            checkBox.setButtonDrawable(checkboxSelected);
        } else {
            checkBox.setVisibility(View.GONE);
        }

        if (drawable != null) {
            leftImg.setImageDrawable(drawable);
            ViewGroup.LayoutParams lp = leftImg.getLayoutParams();
            if (imgSrcWidth > 0) {
                int width = (int) imgSrcWidth;
                lp.width = width;
                lp.height = width;
                leftImg.setLayoutParams(lp);
            }
        } else {
            leftImg.setVisibility(View.GONE);
        }

        if (rightDrawable != null) {
            rightImg.setImageDrawable(rightDrawable);
        } else {
            rightImg.setVisibility(View.INVISIBLE);
        }
        textInfo.setText(text);
        if (textSize > 0) {
            textInfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        if (textLeftMargin > 0) {
            textInfo.setLeft((int) textLeftMargin);
        }
        if (showUnderline) {
            itemUnderline.setVisibility(VISIBLE);
            MarginLayoutParams lp = (MarginLayoutParams) itemUnderline.getLayoutParams();
            lp.setMargins((int) underlinePaddingLeft, 0, 0, 0);
            itemUnderline.setLayoutParams(lp);
        } else {
            itemUnderline.setVisibility(GONE);
        }

        if (showIcon) {
            newMessageIcon.setVisibility(VISIBLE);
        } else {
            newMessageIcon.setVisibility(GONE);
        }

        if (textExplainIsShow) {
            textExplainView.setText(textExplain);
            textExplainView.setVisibility(VISIBLE);
        } else {
            textExplainView.setVisibility(GONE);
        }

        typedArray.recycle();
    }


    public void setShowIcon(boolean isShow) {
        if (isShow) {
            newMessageIcon.setVisibility(VISIBLE);
        } else {
            newMessageIcon.setVisibility(GONE);
        }
    }

    public LinearLayout getMineItemLayout() {
        return mineItemLayout;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextView getTextInfoView() {
        return textInfo;
    }

    public TextView getTextExplainView() {
        return textExplainView;
    }

}
