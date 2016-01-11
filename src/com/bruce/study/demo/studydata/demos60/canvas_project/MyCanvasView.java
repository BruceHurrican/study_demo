/*
 * BruceHurrican
 * Copyright (c) 2016.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *    This document is Bruce's individual learning the android demo, wherein the use of the code from the Internet, only to use as a learning exchanges.
 *   And where any person can download and use, but not for commercial purposes.
 *   Author does not assume the resulting corresponding disputes.
 *   If you have good suggestions for the code, you can contact BurrceHurrican@foxmail.com
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.studydata.demos60.canvas_project;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.log.Logs;

/**
 * 自定义View
 * Created by BruceHurrican on 2015/8/8.
 */
public class MyCanvasView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag; // 线程消亡的标识位
    // 设置画面绘图无锯齿
    private PaintFlagsDrawFilter pfdf = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public MyCanvasView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.GREEN);
        setFocusable(true);
    }

    private void myDraw() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.setDrawFilter(pfdf); // 设置绘图无锯齿
                canvas.drawColor(Color.BLACK);
                canvas.drawText("canvas练习", 10, 10, paint);
                canvas.drawPoint(10, 20, paint); // 绘制像素点
                canvas.drawPoints(new float[]{10, 30, 30, 30}, paint); // 绘制多个像素点
                canvas.drawLine(10, 30, 40, 40, paint); // 绘制直线
                canvas.drawLines(new float[]{10, 20, 30, 40, 20, 50, 70, 90}, paint); // 绘制多条直线
                canvas.drawRect(10, 60, 30, 80, paint);
                canvas.drawRect(new Rect(30, 50, 80, 100), paint);
                canvas.drawRoundRect(new RectF(40, 60, 100, 120), 20, 20, paint); // 绘制圆角矩形
                canvas.drawCircle(10, 20, 30, paint);
                canvas.drawArc(new RectF(20, 30, 40, 50), 0, 230, true, paint); // 绘制弧形
                canvas.drawOval(new RectF(30, 40, 50, 80), paint); // 绘制椭圆
                Path path = new Path(); // 绘制指定路径图形
                path.moveTo(120, 130);
                path.lineTo(230, 440); // 线路1
                path.lineTo(500, 700); // 线路2
                path.close(); // 路径结束
                canvas.drawPath(path, paint);
                Path circlePath = new Path();
                circlePath.addCircle(20, 50, 30, Path.Direction.CCW); // 逆时针方向画圆
                canvas.drawTextOnPath("圆圈路径", circlePath, 10, 20, paint); // 绘制带圆形的路径文本
            }
        } catch (Exception e) {
            Logs.e("CanvasActivity -->", e.toString());
        } finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        screenHeight = getHeight();
//        screenWidth = getWidth();
        flag = true;
        Thread th = new Thread(this);
        th.start();
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
                Logs.e("CanvasActivity -->", e.toString());
            }
        }
    }
}
