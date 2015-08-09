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

package com.bruce.study.demo.studydata.demos60.paint_project;

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
 * Created by BruceHurrican on 2015/8/9.
 */
public class MyPaintView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder surfaceHolder;
    private Thread th;
    private boolean flag;
    private Canvas canvas;
    private int screenW, screenH;

    public MyPaintView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenW = getWidth();
        screenH = getHeight();
        flag = true;
        th = new Thread(this);
        th.start();
    }

    private void myDraw() {
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.drawColor(Color.WHITE);
                // 设置画笔无锯齿
                Paint paint1 = new Paint();
                canvas.drawCircle(140, 330, 50, paint1);
                paint1.setAntiAlias(true);
                canvas.drawCircle(400, 330, 50, paint1);
                // 设置画笔的透明度
                canvas.drawText("无透明度无锯齿", 100, 70, new Paint());
                Paint paint2 = new Paint();
                paint2.setAlpha(0x77);
                canvas.drawText("半透明度默认有锯齿", 320, 70, paint2);
                // 设置绘制文本的锚点
                canvas.drawText("锚点", 20, 90, new Paint());
                // 设置文本的中心点绘制
                Paint paint3 = new Paint();
                paint3.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("锚点2", 20, 105, paint3);
                // 获取文本的长度
                Paint paint4 = new Paint();
                float length = paint4.measureText("文本宽度：");
                canvas.drawText("文本长度：" + length, 20, 130, new Paint());
                // 设置画笔样式
                canvas.drawRect(new Rect(20, 140, 40, 160), new Paint());
                Paint paint5 = new Paint();
                // 设置画笔不填充
                paint5.setStyle(Paint.Style.STROKE);
                canvas.drawRect(new Rect(60, 140, 80, 160), paint5);
                // 设置画笔颜色
                Paint paint6 = new Paint();
                paint6.setColor(Color.GRAY);
                // 设置画笔的粗细程度
                canvas.drawLine(20, 200, 70, 200, new Paint());
                Paint paint7 = new Paint();
                paint7.setStrokeWidth(7f);
                canvas.drawLine(20, 220, 70, 220, paint7);
                // 设置画笔绘制文本的字体粗细
                Paint paint8 = new Paint();
                paint8.setTextSize(20);
                canvas.drawText("文字尺寸", 20, 260, paint8);
                // 设置画笔的ARGB分量
                Paint paint9 = new Paint();
                paint9.setARGB(0x77, 0xff, 0x00, 0x00);
                canvas.drawText("红色半透明", 20, 290, paint9);
            }
        } catch (Exception e) {
            Logs.e("MyPaintView -- >", e.toString());
        } finally {
            if (null != canvas) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
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
                Logs.e("MyPaintView -- >", e.toString());
            }
        }
    }
}
