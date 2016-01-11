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

package com.bruce.study.demo.studydata.demos60.bitmap_active_project;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.log.Logs;

/**
 * 自定义动态 Bitmap
 * Created by BruceHurrican on 2015/8/16.
 */
public class MyBitmapView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "MyBitmapView -- >";
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag;
    private Bitmap bitmap; // 声明一张波浪位图
    private int bmpX, bmpY; // 声明波浪位图的X,Y坐标

    public MyBitmapView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        setFocusable(true);
    }

    private void myDraw() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.drawColor(Color.WHITE);
                paint.setColor(Color.BLUE);
                canvas.drawBitmap(bitmap, bmpX, bmpY, paint);
            }
        } catch (Exception e) {
            Logs.e(TAG, e.toString());
        } finally {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void logic() {
        bmpX += 5;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demos60_water);
        bmpX = this.getWidth() - bitmap.getWidth();
        bmpY = this.getHeight() - bitmap.getHeight();
        flag = true;
        Thread thread = new Thread(this);
        thread.start();
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
            logic();
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
