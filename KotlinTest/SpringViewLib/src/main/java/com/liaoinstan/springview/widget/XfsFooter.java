package com.liaoinstan.springview.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.liaoinstan.springview.R;
import com.liaoinstan.springview.container.BaseFooter;

/**
 * Created by kangf on 2018/7/24.
 */
public class XfsFooter extends BaseFooter {

    private Context context;
    private TextView footerTitle;
    private ImageView footerProgressbar;

    public XfsFooter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.loading_xfs_footer, viewGroup, true);
        footerTitle = (TextView) view.findViewById(R.id.xfs_footer_title);
        footerProgressbar = (ImageView) view.findViewById(R.id.xfs_footer_progressbar);
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
        footerTitle.setText("上拉加载更多");
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (upORdown) {
            footerTitle.setText("松开载入更多");
        } else {
            footerTitle.setText("查看更多");
        }
    }

    @Override
    public void onStartAnim() {
//        footerTitle.setText("加载中...");
        footerTitle.setText("正在加载...");
        footerTitle.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.load_animation);
        footerProgressbar.startAnimation(animation);
        footerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
//        footerTitle.setText("上拉加载");
        footerTitle.setText("上拉加载更多");
        footerTitle.setVisibility(View.VISIBLE);
        footerProgressbar.clearAnimation();
        footerProgressbar.setVisibility(View.GONE);
    }
}
