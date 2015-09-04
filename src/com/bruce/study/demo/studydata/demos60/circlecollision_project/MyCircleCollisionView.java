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

package com.bruce.study.demo.studydata.demos60.circlecollision_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.log.Logs;

/**
 * Created by BruceHurrican on 2015/9/4.
 */
public class MyCircleCollisionView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "MyCircleCollisionView -->";
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag;
    // 定义两个圆形的半径与坐标
    private int r1 = 20, r2 = 20;
    private int x1 = 50, y1 = 100, x2 = 150, y2 = 100;
    // 定义一个碰撞标识位
    private boolean isCollision;

    public MyCircleCollisionView(Context context) {
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
                if (isCollision) {
                    paint.setColor(Color.RED);
                    paint.setTextSize(50);
                    canvas.drawText("圆形碰撞发生~", 0, 50, paint);
                } else {
                    paint.setColor(Color.WHITE);
                }
                canvas.drawCircle(x1, y1, r1, paint);
                canvas.drawCircle(x2, y2, r2, paint);
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
        x1 = (int) event.getX();
        y1 = (int) event.getY();
        isCollision = isCollisionWithCircle(x1, y1, x2, y2, r1, r2);
        return true;
    }

    /**
     * 圆形碰撞
     *
     * @param x1 圆形1的圆心X坐标
     * @param y1 圆形1的圆心Y坐标
     * @param x2 圆形2的圆心X坐标
     * @param y2 圆形2的圆心Y坐标
     * @param r1 圆形1的半径
     * @param r2 圆形2的半径
     * @return
     */
    private boolean isCollisionWithCircle(int x1, int y1, int x2, int y2, int r1, int r2) {
        // Math.sqrt 开平方
        // Math.pow(double x, double y) X的Y次方
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)) <= r1 + r2;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {
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
