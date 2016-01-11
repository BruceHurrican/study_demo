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

package com.bruce.study.demo.studydata.demos60.surfaceview_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.log.Logs;

/**
 * 自定义SurfaceView
 * Created by BruceHurrican on 2015/7/19.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    /**
     * 用于控制 SurfaceView
     */
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private int textX = 40;
    private int textY = 40;
    private boolean threadDeadFlag;
//    private int screenHeight, screenWidth;

    public MySurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this); // 为surfaceview增加状态监听
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(30);
        setFocusable(true);
    }


    /**
     * SurfaceView 视图创建，响应此函数
     *
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
//        screenWidth = getWidth();
//        screenHeight = getHeight();
        threadDeadFlag = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * SurfaceView 视图状态发生改变，响应此函数
     *
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * SurfaceView 视图消亡时，响应此函数
     *
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        threadDeadFlag = false;
    }

    public void myDraw() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.drawARGB(121, 124, 211, 112);
                canvas.drawText("SurfaceView练习", textX, textY, paint);
            }
        } catch (Exception e) {
            Logs.e("MySurfaceView -->", e.toString());
        } finally {
            if (null != canvas) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textX = (int) event.getX();
        textY = (int) event.getY();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 游戏逻辑
     */
    private void logic() {

    }

    @Override
    public void run() {
        while (threadDeadFlag) {
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                Logs.e("MySurfaceView -- >", e.toString());
            }
        }
    }
}
