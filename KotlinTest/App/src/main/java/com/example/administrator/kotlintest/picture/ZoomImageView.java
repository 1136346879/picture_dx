package com.example.administrator.kotlintest.picture;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by nangua on 2016/7/20.
 */
public class ZoomImageView extends View {

    /**
     * 初始化状态常量
     */
    public static final int STATUS_INIT = 1;


    /**
     * 用于对图片进行移动和缩放变换的矩阵
     */
    private Matrix matrix = new Matrix();

    /**
     * 待展示的Bitmap对象
     */
    private Bitmap sourceBitmap;

    /**
     * 记录当前操作的状态，可选值为STATUS_INIT、STATUS_ZOOM_OUT、STATUS_ZOOM_IN和STATUS_MOVE
     */
    private int currentStatus;

    /**
     * ZoomImageView控件的宽度
     */
    private int width;

    /**
     * ZoomImageView控件的高度
     */
    private int height;


    /**
     * ZoomImageView构造函数，将当前操作状态设为STATUS_INIT。
     *
     * @param context
     * @param attrs
     */
    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        currentStatus = STATUS_INIT;
    }

    /**
     * 将待展示的图片设置进来。
     *
     * @param bitmap 待展示的Bitmap对象
     */
    public void setImageBitmap(Bitmap bitmap) {
        sourceBitmap = bitmap;
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            // 分别获取到ZoomImageView的宽度和高度
            width = getWidth();
            height = getHeight();
        }
    }

    /**
     * 根据currentStatus的值来决定对图片进行什么样的绘制操作。
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initBitmap(canvas);
        canvas.drawBitmap(sourceBitmap, matrix, null);
    }


    float translateY;//Y轴偏移量
    float translateX;//X轴偏移量
    /**
     * @param canvas
     * @autohr nangua
     * 对图片进行初始化操作，包括让图片居中，以及当图片大于屏幕宽高时对图片进行压缩。
     * <p>
     * 1.当图片宽度大于显示宽度、图片高度小于显示宽度：
     * 设置图片宽度为显示宽度，高度缩放*(图片宽度/显示宽度)
     * <p>
     * 2.当图片宽度小于显示宽度、图片高度大于显示宽度：
     * 设置图片高度为显示高度，宽度缩放*(图片高度/显示高 度)
     * <p>
     * 3.当图片宽度大于显示宽度，图片高度大于显示宽度：
     * 选取差度更大的一边进行压缩，另一边等比缩放
     * <p>
     * 4.当图片宽度小于显示宽度，图片高度小于显示宽度：
     * 选取差度更大的一边进行压缩，另一边等比缩放
     */
    private void initBitmap(Canvas canvas) {
        if (sourceBitmap != null) {
            matrix.reset(); //重置矩阵
            int bitmapWidth = sourceBitmap.getWidth();  //得到源图片宽
            int bitmapHeight = sourceBitmap.getHeight();    //得到源图片高

            //如果原图片大小大于控件宽高
            if (bitmapWidth > width || bitmapHeight > height) {
                //如果宽和高都比屏幕大，选择差度大的一边缩小，另一边等比缩小
                if (bitmapWidth > width && bitmapHeight > height) {
                    int distanceX = Math.abs(width - bitmapWidth);
                    int distanceY = Math.abs(height - bitmapHeight);
                    float ratio;
                    //找出差值大的一边，进行缩小
                    if (distanceX >= distanceY) {
                        ratio = width / (bitmapWidth * 1.0f);
                        matrix.postScale(ratio, ratio);
                        //此时横轴铺满，只需要对竖轴进行平移
                        translateY = (height - sourceBitmap.getHeight() * ratio) / 2f;
                        matrix.postTranslate(0, translateY);
                    } else {
                        ratio = height / (bitmapHeight * 1.0f);
                        matrix.postScale(ratio, ratio);
                        //此时竖轴铺满，只需要对横轴进行平移
                        translateX = (width - sourceBitmap.getWidth() * ratio) / 2f;
                        matrix.postTranslate(translateX, 0);    //在横纵轴上进行平移
                    }


                    //当图片宽度大于显示宽度、图片高度小于显示宽度：
                } else if (bitmapWidth > width) {
                    // 当图片宽度大于屏幕宽度时，将图片等比例压缩，使它可以完全显示出来
                    float ratio = width / (bitmapWidth * 1.0f); //压缩比例
                    matrix.postScale(ratio, ratio);
                    translateY = (height - (bitmapHeight * ratio)) / 2f;
                    // 在纵坐标方向上进行偏移，以保证图片居中显示
                    matrix.postTranslate(0, translateY);

                    //当图片宽度小于显示宽度、图片高度大于显示宽度：
                } else if (bitmapHeight > height) {
                    // 当图片高度大于屏幕高度时，将图片等比例压缩，使它可以完全显示出来
                    float ratio = height / (bitmapHeight * 1.0f);   //压缩比例
                    matrix.postScale(ratio, ratio);
                    translateX = (width - (bitmapWidth * ratio)) / 2f;
                    // 在横坐标方向上进行偏移，以保证图片居中显示
                    matrix.postTranslate(translateX, 0);
                }

            } else {
                // 当图片的宽高都小于屏幕宽高时，选择差度小的一边铺满，另一边等比扩大
                //计算长和宽的差值
                int distanceX = Math.abs(width - bitmapWidth);
                int distanceY = Math.abs(height - bitmapHeight);
                float ratio;
                //找出差值小的一边，进行扩大
                if (distanceX <= distanceY) {
                    ratio = width / (bitmapWidth * 1.0f);
                    matrix.postScale(ratio, ratio);
                    //此时横轴铺满，只需要对竖轴进行平移
                    translateY = (height - sourceBitmap.getHeight() * ratio) / 2f;
                    matrix.postTranslate(0, translateY);
                } else {
                    ratio = height / (bitmapHeight * 1.0f);
                    matrix.postScale(ratio, ratio);
                    //此时竖轴铺满，只需要对横轴进行平移
                    translateX = (width - sourceBitmap.getWidth() * ratio) / 2f;
                    matrix.postTranslate(translateX, 0);    //在横纵轴上进行平移
                }
            }
            //进行绘制
            canvas.drawBitmap(sourceBitmap, matrix, null);
        }
    }

}
