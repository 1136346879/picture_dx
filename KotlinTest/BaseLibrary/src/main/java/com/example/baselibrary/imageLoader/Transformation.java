package com.example.baselibrary.imageLoader;

import android.graphics.Bitmap;

/**
 * Created by hexun on 2017/8/14.
 */

public interface Transformation {

    Bitmap transform(Bitmap source);

    String key();

}
