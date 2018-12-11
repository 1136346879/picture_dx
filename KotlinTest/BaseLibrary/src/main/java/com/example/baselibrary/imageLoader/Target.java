package com.example.baselibrary.imageLoader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.RequestCreator;

/**
 * Created by hexun on 2017/8/14.
 */

public interface Target {

    /**
     * Callback when an image has been successfully loaded.
     * <p>
     * <strong>Note:</strong> You must not recycle the bitmap.
     */
    void onBitmapLoaded(Bitmap bitmap, ImageLoader.LoadedFrom from);

    /**
     * Callback indicating the image could not be successfully loaded.
     * <p>
     * <strong>Note:</strong> The passed {@link Drawable} may be {@code null} if none has been
     * specified via {@link RequestCreator#error(Drawable)}
     * or {@link RequestCreator#error(int)}.
     */
    void onBitmapFailed(Drawable errorDrawable);

    /**
     * Callback invoked right before your request is submitted.
     * <p>
     * <strong>Note:</strong> The passed {@link Drawable} may be {@code null} if none has been
     * specified via {@link RequestCreator#placeholder(Drawable)}
     * or {@link RequestCreator#placeholder(int)}.
     */
    void onPrepareLoad(Drawable placeHolderDrawable);

}
