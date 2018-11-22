/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xfs.qrcode_module.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.xfs.qrcode_module.util.TaskQueue;

import java.io.ByteArrayOutputStream;

/**
 * @author yangyi 2018年8月9日10:12:51
 */
public final class PreviewCallback implements Camera.PreviewCallback {

    private static final String TAG = PreviewCallback.class.getSimpleName();
    public static Bitmap previewBitmap;
    private final CameraConfigurationManager configManager;
    private Handler previewHandler;
    private int previewMessage;
    private YuvImage image;
    private ByteArrayOutputStream stream;
    private Rect rect;
    private BitmapFactory.Options options;

    PreviewCallback(CameraConfigurationManager configManager) {
        this.configManager = configManager;
    }

    void setHandler(Handler previewHandler, int previewMessage) {
        this.previewHandler = previewHandler;
        this.previewMessage = previewMessage;
    }

    @Override
    public void onPreviewFrame(final byte[] data, final Camera camera) {
//    Log.e(TAG, "\n\n onPreviewFrame ");
//    Log.e(TAG, Log.getStackTraceString(new Throwable()));

        Point cameraResolution = configManager.getCameraResolution();
        Handler thePreviewHandler = previewHandler;
        if (cameraResolution != null && thePreviewHandler != null) {
            //add by tancolo
            Point screenResolution = configManager.getScreenResolution();
            Message message;
            if (screenResolution.x < screenResolution.y) {
                // portrait
                message = thePreviewHandler.obtainMessage(previewMessage, cameraResolution.y,
                        cameraResolution.x, data);
            } else {
                // landscape
                message = thePreviewHandler.obtainMessage(previewMessage, cameraResolution.x,
                        cameraResolution.y, data);
            }
//      Message message = thePreviewHandler.obtainMessage(previewMessage, cameraResolution.x,
//          cameraResolution.y, data);
            //end add

            message.sendToTarget();
            previewHandler = null;
        } else {
            Log.d(TAG, "Got preview callback, but no handler or resolution available");
        }

        //放入线程池中执行
//        TaskQueue.getInstance().enqueue(new Runnable() {
//            @Override
//            public void run() {
//                if (camera == null) {
//                    return;
//                }
//                if (camera.getParameters() == null) {
//                    return;
//                }
//                if (camera.getParameters().getPreviewSize() == null) {
//                    return;
//                }
//                Camera.Size size = camera.getParameters().getPreviewSize();
//                try {
//                    if (image == null) {
//                        image = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
//                    }
//                    if (stream == null) {
//                        stream = new ByteArrayOutputStream();
//                    }
//                    if (rect == null) {
//                        rect = new Rect(0, 0, size.width, size.height);
//                    }
//                    image.compressToJpeg(rect, 80, stream);
//                    //计算采样率压缩图片
//                    if (options == null) {
//                        options = new BitmapFactory.Options();
//                    }
//                    options.inJustDecodeBounds = true;
//                    try {
//                        BitmapFactory.decodeByteArray(stream.toByteArray(),
//                                0,
//                                stream.size(),
//                                options);
//                    } catch (OutOfMemoryError error) {
////                        LogUtil.e(error);
//                        recyclerBitmap();
//                    }
//                    int reqWidth = options.outWidth;
//                    int reqHeight = options.outHeight;
//                    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//                    options.inJustDecodeBounds = false;
//                    //先保存在内存中，在需要的时候拿取
//                    try {
//                        previewBitmap = BitmapFactory.decodeByteArray(stream.toByteArray(),
//                                0,
//                                stream.size(),
//                                options);
//                    } catch (OutOfMemoryError error) {
////                        LogUtil.e(error);
//                        recyclerBitmap();
//                    }
//                    stream.close();
//                } catch (Exception ex) {
//                    Log.e("Sys", "Error:" + ex.getMessage());
//                }
//            }
//        });

    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void recyclerBitmap() {
        if (previewBitmap != null && !previewBitmap.isRecycled()) {
            previewBitmap.recycle();
        }
    }
}
