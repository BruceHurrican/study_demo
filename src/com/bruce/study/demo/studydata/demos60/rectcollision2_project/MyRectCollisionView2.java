/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some resources come from the Internet. Everyone can download and use it for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.studydata.demos60.rectcollision2_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.log.Logs;

/**
 * Created by BruceHurrican on 2015/9/4.
 */
public class MyRectCollisionView2 extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "MyRectCollisionView -- >";
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag;
    // 定义两个矩形的宽高坐标
    private int rectX1 = 10, rectY1 = 10, rectW1 = 40, rectH1 = 40;
    private int rectX2 = 100, rectY2 = 110, rectW2 = 40, rectH2 = 40;
    private boolean isCollision;
    // 定义第一个矩形的矩形碰撞数组
    private Rect clipRect1 = new Rect(0, 0, 15, 15);
    private Rect clipRect2 = new Rect(rectW1 - 15, rectH1 - 15, rectW1, rectH1);
    private Rect[] arrayRect1 = new Rect[]{clipRect1, clipRect2};
    // 定义第二个矩形的矩形碰撞数组
    private Rect clipRect3 = new Rect(0, 0, 15, 15);
    private Rect clipRect4 = new Rect(rectW2 - 15, rectH2 - 15, rectW2, rectH2);
    private Rect[] arrayRect2 = new Rect[]{clipRect3, clipRect4};

    public MyRectCollisionView2(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    private void myDraw() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.drawColor(Color.BLACK);
                paint.setColor(Color.WHITE);
                paint.setStyle(Paint.Style.FILL);
                if (isCollision) {
                    paint.setTextSize(50);
                    canvas.drawText("多矩形碰撞~", 0, 50, paint);
                }
                canvas.drawRect(rectX1, rectY1, rectX1 + rectW1, rectY1 + rectH1, paint);
                canvas.drawRect(rectX2, rectY2, rectX2 + rectW2, rectY2 + rectH2, paint);
                // 绘制碰撞区域使用非填充，并设置画笔颜色为红色
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.RED);
                // 绘制第一个矩形的所有矩形碰撞区域
                for (int i = 0; i < arrayRect1.length; i++) {
                    canvas.drawRect(arrayRect1[i].left + this.rectX1, arrayRect1[i].top + this.rectY1, arrayRect1[i].right + this.rectX1, arrayRect1[i].bottom + this.rectY1, paint);
                }
                // 绘制第二个矩形的所有矩形碰撞区域
                for (int i = 0; i < arrayRect2.length; i++) {
                    canvas.drawRect(arrayRect2[i].left + this.rectX2, arrayRect2[i].top + this.rectY2, arrayRect2[i].right + this.rectX2, arrayRect2[i].bottom + this.rectY2, paint);
                }
            }
        } catch (Exception e) {
            Logs.e(TAG, e.toString());
        } finally {
            if (null != canvas) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 让矩形1随着触屏位置移动
        rectX1 = (int) event.getX() - rectW1 / 2;
        rectY1 = (int) event.getY() - rectH1 / 2;
        isCollision = isCollisionWithRect(arrayRect1, arrayRect2);
        return true;
    }

    private boolean isCollisionWithRect(Rect[] rectArray, Rect[] rect2Array) {
        Rect rect = null;
        Rect rect2 = null;
        for (int i = 0; i < rectArray.length; i++) {
            // 依次取出第一个矩形数组的每个矩形实例
            rect = rectArray[i];
            // 获取到第一个矩形数组中每个矩形元素的属性值
            int x1 = rect.left + this.rectX1;
            int y1 = rect.top + this.rectY1;
            int w1 = rect.right - rect.left;
            int h1 = rect.bottom - rect.top;
            for (int j = 0; j < rect2Array.length; j++) {
                // 依次取出第二个矩形数组的每个矩形实例
                rect2 = rect2Array[j];
                // 获取到第二个矩形数组中每个矩形元素的属性值
                int x2 = rect2.left + this.rectX2;
                int y2 = rect.top + this.rectY2;
                int w2 = rect.right - rect.left;
                int h2 = rect.bottom - rect.top;
                // 进行循环遍历两个矩形碰撞数组所有元素之间的位置关系
                if (x1 >= x2 && x1 >= x2 + w2) {
                } else if (x1 <= x2 && x1 + w1 <= x2) {

                } else if (y1 >= y2 && y1 >= y2 + h2) {

                } else if (y1 <= y2 && y1 + h1 <= y2) {

                } else {
                    // 只要有一个碰撞矩形数组与另一个碰撞矩形数组发生碰撞则认为碰撞
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = true;
    }

    @Override
    public void run() {
        Logs.d(TAG, "当前线程为:" + Thread.currentThread());
        while (flag) {
            long start = System.currentTimeMillis();
            myDraw();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                Logs.e(TAG, e.toString());
            }
        }
    }
}
