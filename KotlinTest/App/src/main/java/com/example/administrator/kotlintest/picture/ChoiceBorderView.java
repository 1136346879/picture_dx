package com.example.administrator.kotlintest.picture;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.kotlintest.R;


/**
 * 相册选择框的View
 * Created by nangua on 2016/7/21.
 */
public class ChoiceBorderView extends View {
    private int scale = (int) this.getResources().getDisplayMetrics().density;  //屏幕像素密度
    private float borderHeight;   //总高
    private float borderWith; //总宽
    private float borderLength = 200 * scale; //边框长度
    private int RECT_BORDER_WITH = 3 * scale; //长方形框框粗
    private int RECT_CORNER_WITH = 6 * scale; //四个角的粗
    private int RECT_CORNER_HEIGHT = 20 * scale; //四个角的长度

    //四个点坐标
    private static float[][] four_corner_coordinate_positions;

    private static int NOW_MOVE_STATE = 1; //移动状态，默认为1，Y轴=1，X轴=2

    private static boolean MOVE_OR_ZOOM_STATE = true; //移动或缩放状态， true 为移动

    public ChoiceBorderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        init();
    }

    /**
     * 初始化布局
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        borderHeight = this.getHeight();
        borderWith = this.getWidth();
        //初始化四个点的坐标
        four_corner_coordinate_positions = new float[][]{
                {(borderWith - borderLength) / 2, (borderHeight - borderLength) / 2}, //左上
                {(borderWith + borderLength) / 2, (borderHeight - borderLength) / 2}, //右上
                {(borderWith - borderLength) / 2, (borderHeight + borderLength) / 2}, //左下
                {(borderWith + borderLength) / 2, (borderHeight + borderLength) / 2}, //右上
        };
    }

    private void init() {

    }

    private int temp1 = (RECT_CORNER_WITH - RECT_BORDER_WITH) / 2;  //长方形的粗半距
    private int temp2 = (RECT_CORNER_WITH + RECT_BORDER_WITH) / 2;  //四个角的粗半距

    /**
     * RECT_CORNER_WITH = 6
     * RECT_BORDER_WITH  =3
     *
     * @param canvas
     */
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paintRect = new Paint();  //初始化画笔
        //画边框的画笔
        paintRect.setColor(getResources().getColor(R.color.color_orange));    //颜色
        paintRect.setStrokeWidth(RECT_BORDER_WITH);    //宽度
        paintRect.setAntiAlias(true);   //抗锯齿
        paintRect.setStyle(Paint.Style.STROKE); //设置空心
        canvas.drawRect(four_corner_coordinate_positions[0][0],
                four_corner_coordinate_positions[0][1],
                four_corner_coordinate_positions[3][0],
                four_corner_coordinate_positions[3][1], paintRect);
        //画四个角的画笔
        paintRect.setColor(R.color.color_orange);
        paintRect.setStrokeWidth(RECT_CORNER_WITH);
        paintRect.setAntiAlias(true);
        //左上角的两根
        canvas.drawLine(four_corner_coordinate_positions[0][0] - temp2,
                four_corner_coordinate_positions[0][1] - temp1,
                four_corner_coordinate_positions[0][0] - temp1 + RECT_CORNER_HEIGHT,
                four_corner_coordinate_positions[0][1] - temp1,
                paintRect);
        canvas.drawLine(four_corner_coordinate_positions[0][0] - temp1,
                four_corner_coordinate_positions[0][1] - temp2,
                four_corner_coordinate_positions[0][0] - temp1,
                four_corner_coordinate_positions[0][1] - temp1 + RECT_CORNER_HEIGHT,
                paintRect);
        //左下角的两根
        canvas.drawLine(four_corner_coordinate_positions[2][0] - temp2,
                four_corner_coordinate_positions[2][1] + temp1,
                four_corner_coordinate_positions[2][0] - temp1 + RECT_CORNER_HEIGHT,
                four_corner_coordinate_positions[2][1] + temp1,
                paintRect);
        canvas.drawLine(four_corner_coordinate_positions[2][0] - temp1,
                four_corner_coordinate_positions[2][1] + temp1,
                four_corner_coordinate_positions[2][0] - temp1,
                four_corner_coordinate_positions[2][1] + temp1 - RECT_CORNER_HEIGHT,
                paintRect);
        //右上角的两根
        canvas.drawLine(four_corner_coordinate_positions[1][0] + temp1,
                four_corner_coordinate_positions[1][1] - temp1,
                four_corner_coordinate_positions[1][0] + temp1 - RECT_CORNER_HEIGHT,
                four_corner_coordinate_positions[1][1] - temp1,
                paintRect);
        canvas.drawLine(four_corner_coordinate_positions[1][0] + temp1,
                four_corner_coordinate_positions[1][1] - temp2,
                four_corner_coordinate_positions[1][0] + temp1,
                four_corner_coordinate_positions[1][1] - temp1 + RECT_CORNER_HEIGHT
                , paintRect);
        //右下角的两根
        canvas.drawLine(four_corner_coordinate_positions[3][0] + temp2,
                four_corner_coordinate_positions[3][1] + temp1,
                four_corner_coordinate_positions[3][0] + temp1 - RECT_CORNER_HEIGHT,
                four_corner_coordinate_positions[3][1] + temp1,
                paintRect);
        canvas.drawLine(four_corner_coordinate_positions[3][0] + temp1,
                four_corner_coordinate_positions[3][1] + temp1,
                four_corner_coordinate_positions[3][0] + temp1,
                four_corner_coordinate_positions[3][1] + temp1 - RECT_CORNER_HEIGHT,
                paintRect);
        //画扫描线
        if (IF_SCANNING_SHOW) {
            paintRect.setColor(R.color.color_orange);
            paintRect.setStrokeWidth(1);
            paintRect.setAntiAlias(true);
            paintRect.setStyle(Paint.Style.STROKE);
            //共四根线
            //竖1
            canvas.drawLine(four_corner_coordinate_positions[0][0] + borderLength / 3,
                    four_corner_coordinate_positions[0][1] + temp1,
                    four_corner_coordinate_positions[2][0] + borderLength / 3,
                    four_corner_coordinate_positions[2][1] - temp1,
                    paintRect);
            //竖2
            canvas.drawLine(four_corner_coordinate_positions[1][0] - borderLength / 3,
                    four_corner_coordinate_positions[1][1] + temp1,
                    four_corner_coordinate_positions[3][0] - borderLength / 3,
                    four_corner_coordinate_positions[3][1] - temp1,
                    paintRect);
            //横1
            canvas.drawLine(four_corner_coordinate_positions[0][0] + temp1,
                    four_corner_coordinate_positions[0][1] + borderLength / 3,
                    four_corner_coordinate_positions[1][0] - temp1,
                    four_corner_coordinate_positions[1][1] + borderLength / 3,
                    paintRect);
            //横2
            canvas.drawLine(four_corner_coordinate_positions[2][0] + temp1,
                    four_corner_coordinate_positions[2][1] - borderLength / 3,
                    four_corner_coordinate_positions[3][0] - temp1,
                    four_corner_coordinate_positions[3][1] - borderLength / 3,
                    paintRect);
        }
    }

    private boolean IF_SCANNING_SHOW = false;
    private int lastX = 0;  //上次按下的X位置
    private int lastY = 0;  //上次按下的Y位置
    private int offsetX = 0;    //X轴偏移量
    private int offsetY = 0;    //Y轴偏移量
    static int point = -1;// 用户按下的点
    private int POINT_STATE = -1; //判断用户是缩小还是放大 0放大 1缩小

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                IF_SCANNING_SHOW = true;//显示扫描线
                if (isInTheCornerCircle(event.getX(), event.getY()) != -1) {
                    //开始缩放操作
                    MOVE_OR_ZOOM_STATE = false; //设置false为缩放状态
                    point = isInTheCornerCircle(event.getX(), event.getY());
                }
                lastX = x;
                lastY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = x - lastX;
                offsetY = y - lastY;
                //判断当前是扩大还是缩小操作
                judgementXandY();
                //限定移动范围
                //移动状态：只有在移动状态下才能移动
                if (MOVE_OR_ZOOM_STATE) {
                    getoffsetXandoffsetY();
                    //四个点的坐标信息也要随之改变
                    for (int i = 0; i < four_corner_coordinate_positions.length; i++) {
                        four_corner_coordinate_positions[i][0] += offsetX;
                        four_corner_coordinate_positions[i][1] += offsetY;

                        //更新回调接口
                        onImageDetailsSizeChanggedl.onBorderSizeChangged(
                                (int) four_corner_coordinate_positions[0][0],
                                (int) four_corner_coordinate_positions[0][1],
                                (int) borderLength
                        );

                        invalidate();
                    }
                    // this.scrollBy(-offsetX, -offsetY);   //这里弃用，后面改用了四点坐标移动代替背景移动
                }
                //在缩放状态下
                else {
                    //按住某一个点，该点的坐标改变，其他2个点坐标跟着改变，对点坐标不变
                    max = Math.abs(offsetX) >= Math.abs(offsetY) ? Math.abs(offsetX) : Math.abs(offsetY);
                    //只有在扩大操作才进行边界范围判断
                    if (POINT_STATE == 0) {
                        getoffsetXandoffsetY(); //边界范围判断
                    }
                    //缩小操作时进行边界不能太小判断
                    else if (POINT_STATE == 1) {
                        //如果边长+max太小，直接返回
                        if (borderLength - max <= RECT_CORNER_HEIGHT*2-temp2) {
                            max = 0;
                        }
                    }

                    //改变坐标
                    changgeFourCoodinatePosition(point, offsetX, offsetY);
                    //更新边长
                    notifyNowborderLength();
                    //更新回调接口
                    onImageDetailsSizeChanggedl.onBorderSizeChangged(
                            (int) four_corner_coordinate_positions[0][0],
                            (int) four_corner_coordinate_positions[0][1],
                            (int) borderLength
                    );
                    invalidate();
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                IF_SCANNING_SHOW = false; //不显示扫描线
                MOVE_OR_ZOOM_STATE = true; //回归为默认的移动状态
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 更新矩形框边长的方法
     */
    private void notifyNowborderLength() {
        float a = four_corner_coordinate_positions[0][0];
        float b = four_corner_coordinate_positions[0][1];
        float c = four_corner_coordinate_positions[1][0];
        float d = four_corner_coordinate_positions[1][1];
        float temp1 = (float) Math.pow(a - c, 2);
        float temp2 = (float) Math.pow(b - d, 2);
        borderLength = (float) Math.sqrt(temp1 + temp2);
    }

    /**
     * POINT_STATE 为0放大， 1缩小
     */
    private void judgementXandY() {
        switch (point) {
            case 0:
                if ((offsetX <= 0 && offsetY <= 0) || (offsetX <= 0 && offsetY >= 0)) {
                    POINT_STATE = 0;//扩大
                } else {
                    POINT_STATE = 1;//缩小
                }
                break;
            case 1:
                if ((offsetX >= 0 && offsetY <= 0) || (offsetX >= 0 && offsetY >= 0)) {
                    POINT_STATE = 0;
                } else {
                    POINT_STATE = 1;
                }
                break;
            case 2:
                if ((offsetX <= 0 && offsetY >= 0) || (offsetX <= 0 && offsetY <= 0)) {
                    POINT_STATE = 0;
                } else {
                    POINT_STATE = 1;
                }
                break;
            case 3:
                if ((offsetX >= 0 && offsetY >= 0) || (offsetX >= 0 && offsetY <= 0)) {
                    POINT_STATE = 0;
                } else {
                    POINT_STATE = 1;
                }
                break;
        }
    }

    /**
     * 防止X和Y溢出边界的算法
     */
    private void getoffsetXandoffsetY() {
        //如果是移动状态
        if (MOVE_OR_ZOOM_STATE) {
            if ((four_corner_coordinate_positions[0][0] + offsetX <= 0) ||
                    (four_corner_coordinate_positions[1][0] + offsetX >= borderWith)
                    ) {
                offsetX = 0;
            }

            if ((four_corner_coordinate_positions[0][1] + offsetY <= 0) ||
                    (four_corner_coordinate_positions[2][1] + offsetY >= borderHeight)
                    ) {
                offsetY = 0;
            }
        }
        //如果是缩放状态
        else {
            switch (point) {
                case 0:
                    if ((four_corner_coordinate_positions[0][0] - max <= 0) ||
                            (four_corner_coordinate_positions[0][1] - max <= 0)
                            ) {
                        max = 0;
                    }
                    break;
                case 1:
                    if ((four_corner_coordinate_positions[1][0] + max >= borderWith) ||
                            (four_corner_coordinate_positions[1][1] - max <= 0)
                            ) {
                        max = 0;
                    }
                    break;
                case 2:
                    if ((four_corner_coordinate_positions[2][0] - max <= 0) ||
                            (four_corner_coordinate_positions[2][1] + max >= borderHeight)
                            ) {
                        max = 0;
                    }
                    break;
                case 3:
                    if ((four_corner_coordinate_positions[3][0] + max >= borderWith) ||
                            (four_corner_coordinate_positions[3][1] + max >= borderHeight)
                            ) {
                        max = 0;
                    }
                    break;
            }
        }
    }


    static int max;

    /**
     * 扩大缩放方法
     * 根据用户传来的点改变其他点的坐标
     * 按住某一个点，该点的坐标改变，其他2个点坐标跟着改变，对点坐标不变
     * 点阵示意：
     * 0   1
     * 2   3
     *
     * @param point   用户按的点
     * @param offsetX X轴偏移量
     * @param offsetY Y轴偏移量
     */
    private void changgeFourCoodinatePosition(int point, int offsetX, int offsetY) {

        switch (point) {
            case 0:
                if (offsetX > 0 && offsetY < 0) {
                    //变化0点的位置   suoxiao
                    four_corner_coordinate_positions[0][0] += max;
                    four_corner_coordinate_positions[0][1] += max;
                    //变化1点的Y轴
                    four_corner_coordinate_positions[1][1] += max;
                    //变化2点的X轴
                    four_corner_coordinate_positions[2][0] += max;
                } else if (offsetX < 0 && offsetY > 0) {
                    //变化0点的位置   kuoda
                    four_corner_coordinate_positions[0][0] -= max;
                    four_corner_coordinate_positions[0][1] -= max;
                    //变化1点的Y轴
                    four_corner_coordinate_positions[1][1] -= max;
                    //变化2点的X轴
                    four_corner_coordinate_positions[2][0] -= max;
                } else if (offsetX < 0 && offsetY < 0) {
                    //变化0点的位置   kuoda
                    four_corner_coordinate_positions[0][0] -= max;
                    four_corner_coordinate_positions[0][1] -= max;
                    //变化1点的Y轴
                    four_corner_coordinate_positions[1][1] -= max;
                    //变化2点的X轴
                    four_corner_coordinate_positions[2][0] -= max;
                } else if (offsetX > 0 && offsetY > 0) {
                    //变化0点的位置   suoxiao
                    four_corner_coordinate_positions[0][0] += max;
                    four_corner_coordinate_positions[0][1] += max;
                    //变化1点的Y轴
                    four_corner_coordinate_positions[1][1] += max;
                    //变化2点的X轴
                    four_corner_coordinate_positions[2][0] += max;
                }
                break;
            case 1:
                if (offsetX > 0 && offsetY < 0) {
                    //变化1点的位置
                    four_corner_coordinate_positions[1][0] += max;
                    four_corner_coordinate_positions[1][1] -= max;
                    //变化0点的Y轴
                    four_corner_coordinate_positions[0][1] -= max;
                    //变化3点的X轴
                    four_corner_coordinate_positions[3][0] += max;
                } else if (offsetX < 0 && offsetY > 0) {
                    //变化1点的位置
                    four_corner_coordinate_positions[1][0] -= max;
                    four_corner_coordinate_positions[1][1] += max;
                    //变化0点的Y轴
                    four_corner_coordinate_positions[0][1] += max;
                    //变化3点的X轴
                    four_corner_coordinate_positions[3][0] -= max;
                } else if (offsetX < 0 && offsetY < 0) {
                    //变化1点的位置
                    four_corner_coordinate_positions[1][0] -= max;
                    four_corner_coordinate_positions[1][1] += max;
                    //变化0点的Y轴
                    four_corner_coordinate_positions[0][1] += max;
                    //变化3点的X轴
                    four_corner_coordinate_positions[3][0] -= max;
                } else if (offsetX > 0 && offsetY > 0) {
                    //变化1点的位置
                    four_corner_coordinate_positions[1][0] += max;
                    four_corner_coordinate_positions[1][1] -= max;
                    //变化0点的Y轴
                    four_corner_coordinate_positions[0][1] -= max;
                    //变化3点的X轴
                    four_corner_coordinate_positions[3][0] += max;
                }
                break;
            case 2:
                if (offsetX > 0 && offsetY < 0) {
                    //变化2点的位置   suoxiao
                    four_corner_coordinate_positions[2][0] += max;
                    four_corner_coordinate_positions[2][1] -= max;
                    //变化0点的X轴
                    four_corner_coordinate_positions[0][0] += max;
                    //变化3点的Y轴
                    four_corner_coordinate_positions[3][1] -= max;
                } else if (offsetX < 0 && offsetY > 0) {
                    //变化2点的位置   kuoda
                    four_corner_coordinate_positions[2][0] -= max;
                    four_corner_coordinate_positions[2][1] += max;
                    //变化0点的X轴
                    four_corner_coordinate_positions[0][0] -= max;
                    //变化3点的Y轴
                    four_corner_coordinate_positions[3][1] += max;
                } else if (offsetX < 0 && offsetY < 0) {
                    //变化2点的位置   kuoda
                    four_corner_coordinate_positions[2][0] -= max;
                    four_corner_coordinate_positions[2][1] += max;
                    //变化0点的X轴
                    four_corner_coordinate_positions[0][0] -= max;
                    //变化3点的Y轴
                    four_corner_coordinate_positions[3][1] += max;
                } else if (offsetX > 0 && offsetY > 0) {
                    //变化2点的位置   suoxiao
                    four_corner_coordinate_positions[2][0] += max;
                    four_corner_coordinate_positions[2][1] -= max;
                    //变化0点的X轴
                    four_corner_coordinate_positions[0][0] += max;
                    //变化3点的Y轴
                    four_corner_coordinate_positions[3][1] -= max;
                }
                break;
            case 3:
                if (offsetX > 0 && offsetY < 0) {
                    //变化3点的位置   kuoda
                    four_corner_coordinate_positions[3][0] += max;
                    four_corner_coordinate_positions[3][1] += max;
                    //变化1点的X轴
                    four_corner_coordinate_positions[1][0] += max;
                    //变化2点的Y轴
                    four_corner_coordinate_positions[2][1] += max;
                } else if (offsetX < 0 && offsetY > 0) {
                    //变化3点的位置   suoxiao
                    four_corner_coordinate_positions[3][0] -= max;
                    four_corner_coordinate_positions[3][1] -= max;
                    //变化1点的X轴
                    four_corner_coordinate_positions[1][0] -= max;
                    //变化2点的Y轴
                    four_corner_coordinate_positions[2][1] -= max;
                } else if (offsetX < 0 && offsetY < 0) {
                    //变化3点的位置   suoxiao
                    four_corner_coordinate_positions[3][0] -= max;
                    four_corner_coordinate_positions[3][1] -= max;
                    //变化1点的X轴
                    four_corner_coordinate_positions[1][0] -= max;
                    //变化2点的Y轴
                    four_corner_coordinate_positions[2][1] -= max;
                } else if (offsetX > 0 && offsetY > 0) {
                    //变化3点的位置   kuoda
                    four_corner_coordinate_positions[3][0] += max;
                    four_corner_coordinate_positions[3][1] += max;
                    //变化1点的X轴
                    four_corner_coordinate_positions[1][0] += max;
                    //变化2点的Y轴
                    four_corner_coordinate_positions[2][1] += max;
                }
                break;
        }

    }

    /**
     * 判断按下的点在圆圈内
     *
     * @param x 按下的X坐标
     * @param y 按下的Y坐标
     * @return 返回按到的是哪个点, 没有则返回-1
     * 点阵示意：
     * 0   1
     * 2   3
     */
    private int isInTheCornerCircle(float x, float y) {
        for (int i = 0; i < four_corner_coordinate_positions.length; i++) {
            float a = four_corner_coordinate_positions[i][0];
            float b = four_corner_coordinate_positions[i][1];
            float temp1 = (float) Math.pow((x - a), 2);
            float temp2 = (float) Math.pow((y - b), 2);
            if (((float) RECT_CORNER_HEIGHT) >= Math.sqrt(temp1 + temp2)) {
                return i;
            }
        }
        return -1;
    }

    public interface onImageDetailsSizeChangged {
        void onBorderSizeChangged(int x, int y, int length);
    }

    public onImageDetailsSizeChangged onImageDetailsSizeChanggedl;

    public void setonImageDetailsSizeChangged(onImageDetailsSizeChangged onImageDetailsSizeChangged) {
        this.onImageDetailsSizeChanggedl = onImageDetailsSizeChangged;
    }

}