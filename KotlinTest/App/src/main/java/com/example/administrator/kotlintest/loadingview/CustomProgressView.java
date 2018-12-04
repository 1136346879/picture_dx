package com.example.administrator.kotlintest.loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hexun on 2017/7/5.
 */

public class CustomProgressView extends View {

    private Point[] positions = new Point[4];
    private Point[] trianglePositions = new Point[3];
    private float[][] params = new float[3][2];
    private int lineWidth = 0;
    private Paint paint = new Paint();
    private Path path = new Path();
    private RectF leftR, rightR;
    private int progressX;
    private boolean loop = true;

    public CustomProgressView(Context context) {
        super(context);
        init();
    }

    public CustomProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init() {
        if (leftR != null) {
            return;
        }
        paint.setAntiAlias(true);
        for (int i = 0;i<positions.length;i++) {
            positions[i] = new Point();
        }
        for (int i = 0;i<trianglePositions.length;i++) {
            trianglePositions[i] = new Point();
        }
        leftR = new RectF();
        rightR = new RectF();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            final int w = getWidth();
            final int h = getHeight();
            float slope = h / (w * 0.75f);
            lineWidth = (int)(w / 6f /(1 + (1/slope)));
            paint.setStrokeWidth(lineWidth);

            positions[0].x = 0;
            positions[0].y = h + (int)(w / 12f * slope);

            positions[1].x = (int)(w / 3f);
            positions[1].y = h - (int)(w / 3f * slope * 0.75f);

            positions[2].x = (int)(w / 2f);
            positions[2].y = 2*h - positions[0].y;

            slope = h / (w / 2f);

            positions[3].x = w/2 + (int) (positions[2].y/slope);
            positions[3].y = 0;
            createTriangle(slope);

            leftR.left = w/6f;
            leftR.top = h * 0.22f;
            leftR.right = leftR.left + lineWidth;
            leftR.bottom = leftR.top + lineWidth * 3f;

            rightR.left = w/3f;
            rightR.top = h * 0.1f;
            rightR.right = rightR.left + lineWidth;
            rightR.bottom = rightR.top + lineWidth * 3f;
        }
    }

    private void createTriangle(float slope) {
        params[0] = calParams(positions[0], positions[1]);
        params[1] = calParams(positions[1], positions[2]);
        params[2] = calParams(positions[2], positions[3]);

        trianglePositions[0].x = (int) (positions[3].x * 0.9f + 0.5f);
        trianglePositions[0].y = getY(trianglePositions[0].x);

        positions[3].x = (int)(trianglePositions[0].x - (2 * lineWidth / Math.sqrt (1 + (slope * slope))));
        positions[3].y = getY(positions[3].x);

        float sw = lineWidth * 0.88f;

        trianglePositions[1].x = positions[3].x - (int)(sw * 0.92f + 0.5f);
        trianglePositions[1].y = positions[3].y - (int)(sw * 0.4f + 0.5f);

        trianglePositions[2].x = positions[3].x + (int)(sw * 0.92f + 0.5f);
        trianglePositions[2].y = positions[3].y + (int)(sw * 0.4f + 0.5f);

    }

    private float[] calParams(Point xy1, Point xy2) {
        float[] param = new float[2];
        param[0] = 1.0f * (xy1.y - xy2.y)/(xy1.x - xy2.x);
        param[1] = xy1.y - (xy1.x * param[0]);
        return param;
    }

    private int calY(int x, float[] params) {
        return (int) (x * params[0] + params[1] + 0.5f);
    }

    private int getY(int rx) {
        int x = rx;
        if (x < 0) {
            x = 0;
        } else if (x > positions[3].x) {
            x = positions[3].x;
        }

        if (x < positions[1].x) {
            return calY(x, params[0]);
        } else if (x < positions[2].x) {
            return calY(x, params[1]);
        } else {
            return calY(x, params[2]);
        }
    }

    private void fillPath(int x, Path path) {
        path.reset();
        path.moveTo(positions[0].x, positions[0].y);
        if (x > positions[1].x) {
            path.lineTo(positions[1].x, positions[1].y);
        }
        if (x > positions[2].x) {
            path.lineTo(positions[2].x, positions[2].y);
        }
        if (x > positions[3].x) {
            path.lineTo(positions[3].x, positions[3].y);
        }
        path.lineTo(x, getY(x));
    }

    public void showProgress(float ratio) {
        float rat = ratio;
        if (rat < 0) {
            rat = 0;
        }
        loop = false;
        progressX = (int)(positions[3].x * rat);
        invalidate();
    }

    public void showProgress() {
        loop = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBG(canvas);
        drawKLine(canvas);
        drawProgress(canvas);
        if (loop) {
            if (progressX > positions[3].x) {
                progressX = 0;
            } else {
                progressX += getWidth()/60f + 1;
            }
            invalidate();
        }
    }

    private void drawBG(Canvas canvas) {
        paint.setColor(0xffffcccc);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        path.reset();
        path.moveTo(positions[0].x, positions[0].y);
        path.lineTo(positions[1].x, positions[1].y);
        path.lineTo(positions[2].x, positions[2].y);
        path.lineTo(positions[3].x, positions[3].y);
        canvas.drawPath(path,paint);
        drawTriangle(canvas, paint);
    }

    private void drawProgress(Canvas canvas) {
        final int x = this.progressX > positions[3].x ? positions[3].x : this.progressX;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);
        paint.setColor(Color.RED);
        fillPath(x, path);
        canvas.drawPath(path, paint);
        if (x >= positions[3].x) {
            drawTriangle(canvas, paint);
        }
    }

    private void drawTriangle(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        path.reset();
        path.moveTo(trianglePositions[0].x, trianglePositions[0].y);
        path.lineTo(trianglePositions[1].x, trianglePositions[1].y);
        path.lineTo(trianglePositions[2].x, trianglePositions[2].y);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawKLine(Canvas canvas) {
        paint.setStrokeWidth((int)(lineWidth*0.2 + 0.5));
        paint.setStyle(Paint.Style.FILL);
        float centerX, halfLine = lineWidth/2f;
        if ((progressX/(getWidth()/6))%2 == 1) {
            paint.setColor(Color.RED);
            centerX = leftR.centerX();
            canvas.drawLine(centerX, leftR.top - halfLine, centerX, leftR.bottom + halfLine, paint);
            canvas.drawRect(leftR, paint);
            paint.setColor(0xff44bb88);
            centerX = rightR.centerX();
            canvas.drawLine(centerX, rightR.top - halfLine, centerX, rightR.bottom + halfLine, paint);
            canvas.drawRect(rightR, paint);
        } else {
            paint.setColor(0xff44bb88);
            centerX = leftR.centerX();
            canvas.drawLine(centerX, leftR.top - halfLine, centerX, leftR.bottom + halfLine, paint);
            canvas.drawRect(leftR, paint);
            paint.setColor(Color.RED);
            centerX = rightR.centerX();
            canvas.drawLine(centerX, rightR.top - halfLine, centerX, rightR.bottom + halfLine, paint);
            canvas.drawRect(rightR, paint);
        }
    }

}
