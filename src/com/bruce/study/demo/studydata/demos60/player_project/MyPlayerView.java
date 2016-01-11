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

package com.bruce.study.demo.studydata.demos60.player_project;

import android.content.Context;
import android.graphics.*;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.log.Logs;

/**
 * 游戏主角View
 * Created by BruceHurrican on 2015/9/3.
 */
public class MyPlayerView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "MyPlayerView-->";
    // 机器人的方向常量
    public static final int DIR_LEFT = 0;
    public static final int DIR_RIGHT = 1;
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag;
    private Bitmap robotBitmap; // 机器人位图
    // 机器人当前的方向(默认朝右方向)
    private int dir = DIR_RIGHT;
    // 动作帧下标
    private int currentFrame;
    // 机器人的X,Y位置
    private int robot_x, robot_y;
    // 处理按键卡现象
    private boolean isUp, isDown, isLeft, isRight;

    public MyPlayerView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        setFocusable(true);
        robotBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demos60_robot);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * 绘制帧
     *
     * @param currentFrame 当前帧
     * @param canvas       画布
     * @param paint        画笔
     */
    private void drawFrame(int currentFrame, Canvas canvas, Paint paint) {
        int frameW = robotBitmap.getWidth() / 6;
        int frameH = robotBitmap.getHeight() / 2;
        // 得到位图的列数
        int col = robotBitmap.getWidth() / frameW;
        // 得到当前帧相对于位图的X坐标
        int x = currentFrame % col * frameW;
        // 得到当前帧相对于位图的Y坐标
        int y = currentFrame / col * frameH;
        canvas.save();
        // 设置一个宽高与机器人每帧相同大小的可视区域
        canvas.clipRect(robot_x, robot_y, robot_x + robotBitmap.getWidth() / 6, robot_y + robotBitmap.getHeight() / 2);
        if (dir == DIR_LEFT) { // 如果是向左侧移动
            // 镜像操作
            canvas.scale(-1, 1, robot_x - x + robotBitmap.getWidth() / 2, robot_y - y + robotBitmap.getHeight() / 2);
        }
        canvas.drawBitmap(robotBitmap, robot_x - x, robot_y - y, paint);
        canvas.restore();
    }

    /**
     * 游戏绘图
     */
    private void myDraw() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.drawColor(Color.BLACK);
                drawFrame(currentFrame, canvas, paint);
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
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        Logs.e(TAG, "keyCode -->" + keyCode);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            isUp = true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            isDown = true;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            isLeft = true;
            dir = DIR_LEFT;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            isRight = true;
            dir = DIR_RIGHT;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            isUp = false;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            isDown = false;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            isLeft = false;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            isRight = false;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void logic() {
        if (isUp) {
            robot_y -= 5;
        }
        if (isDown) {
            robot_y += 5;
        }
        if (isLeft) {
            robot_x -= 5;
        }
        if (isRight) {
            robot_x += 5;
        }
        currentFrame++;
        if (currentFrame >= 12) {
            currentFrame = 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
