package com.example.administrator.kotlintest.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.administrator.kotlintest.R;
import com.example.baselibrary.ui.activity.BaseActivity;

import java.io.File;

/**
 * 裁剪图片
 */
public class CropImageActivity extends BaseActivity {

    private int lefttopX = 1;
    private int lefttopY = 1;
    private int borderLength;
    private Bitmap bitmap;

/**
 * 按正方形裁切图片
 */
   // 裁剪图片的方法：
    public Bitmap ImageCrop(Bitmap bitmaps) {
        return Bitmap.createBitmap(bitmaps, lefttopX, lefttopY, borderLength== 0 ?bitmap.getWidth()-1:borderLength, borderLength== 0 ?bitmap.getHeight()-1:borderLength, null, false);
    }

    @Override
    public int initLayout() {
        return R.layout.crop_image_activity;
    }

    @Override
    public void initView() {
        ZoomImageView zoomImageView =   findViewById(R.id.zoom_image_view);
        ChoiceBorderView zoom_choiceborder_view =   findViewById(R.id.zoom_choiceborder_view);

        String sourcePath = getIntent().getStringExtra("image_path");
        File file = new File(sourcePath);
        if(file.exists()){
            bitmap = BitmapFactory.decodeFile(sourcePath);
            zoomImageView.setImageBitmap(bitmap);
        }
        zoom_choiceborder_view.setonImageDetailsSizeChangged((x, y, length) -> {
            lefttopX = x;
            lefttopY = y + 50;
            borderLength = length ;

        });


        ImageView imageView =  findViewById(R.id.testimg);
        Button confirm =  findViewById(R.id.confirm);
        ImageView certification_returnbtn =  findViewById(R.id.certification_returnbtn);
        certification_returnbtn.setOnClickListener(v -> finish());
        confirm.setOnClickListener(v -> imageView.setImageBitmap(ImageCrop(bitmap)));
    }

    @Override
    public void initData() {

    }
}
