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

package com.bruce.study.demo.studydata.demos60.rectcollision_project;

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
public class MyRectCollisionView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "MyRectCollisionView -->";
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag;
    // 定义两个矩形的宽高坐标
    private int x1 = 10, y1 = 110, w1 = 40, h1 = 40;
    private int x2 = 100, y2 = 110, w2 = 40, h2 = 40;
    // 便于观察是否发生了碰撞设置一个标识位
    private boolean isCollision;

    public MyRectCollisionView(Context context) {
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
                    paint.setTextSize(40);
                    canvas.drawText("矩形碰撞了~", 0, 50, paint);
                } else {
                    paint.setColor(Color.WHITE);
                }
                // 绘制两个矩形
                canvas.drawRect(x1, y1, x1 + w1, y1 + h1, paint);
                canvas.drawRect(x2, y2, x2 + w2, y2 + h2, paint);
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
        x1 = (int) event.getX() - w1 / 2;
        y1 = (int) event.getY() - h1 / 2;
        isCollision = isCollisionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
        return true;
    }

    private boolean isCollisionWithRect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        if (x1 >= x2 && x1 >= x2 + w2) {
            return false;
        } else if (x1 <= x2 && x1 + w1 <= x2) {
            return false;
        } else if (y1 >= y2 && y1 >= y2 + h2) {
            return false;
        } else if (y1 <= y2 && y1 + h1 <= y2) {
            return false;
        }
        return true;
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
