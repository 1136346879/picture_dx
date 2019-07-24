package com.example.administrator.kotlintest.ui.divider;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
*@author : HaoBoy
*@date : 2018/8/18
*@description :
**/
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildLayoutPosition(view) != -1)
            outRect.bottom = space;
        if(parent.getChildLayoutPosition(view) != -1 && parent.getChildLayoutPosition(view) == 0)
            outRect.top = space;
    }
}