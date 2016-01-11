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

package com.bruce.study.demo.studydata.demos60.game_view_framework_project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.bruce.study.demo.log.Logs;

/**
 * 游戏View
 * Created by BruceHurrican on 2015/7/19.
 */
public class GameView extends View {
    private static final String TAG = "GameView -- >";
    private int textX = 25; // 文本初始位置
    private int textY = 25; // 文本初始位置

    /**
     * 重写父类构造函数
     *
     * @param context
     */
    public GameView(Context context) {
        super(context);
//        setFocusable(true);
//        setFocusableInTouchMode(true);
    }


    /**
     * 重写父类绘图函数
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint(); // 创建一个画笔的实例
        paint.setColor(Color.GREEN); // 设置画笔的颜色
        paint.setAntiAlias(true);
        paint.setTextSize(30f);
        canvas.drawText("游戏框架View", textX, textY, paint); // 绘制文本
        super.onDraw(canvas);
    }

    /**
     * 重写按键按下事件函数
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判定用户按下的键值是否为方向键的“上下左右”键
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            // “上”按键被点击，让文本的Y坐标变小
            textY -= 2;
            Logs.d(TAG, "“上”按键被点击");
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            // “下”按键被点击，让文本的Y坐标变大
            textY += 2;
            Logs.d(TAG, "“下”按键被点击");
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            // “左”按键被点击，让文本的X坐标变小
            textX -= 2;
            Logs.d(TAG, "“左”按键被点击");
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            // “右”按键被点击，让文本的X坐标变大
            textX += 2;
            Logs.d(TAG, "“右”按键被点击");
        }
        invalidate(); // 重绘画布
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 重写按键抬起事件函数
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textX = (int) event.getX(); // 获取用户手指触屏的X坐标赋值与文本的X坐标
        textY = (int) event.getY(); // 获取用户手指触屏的Y坐标赋值与文本的Y坐标
        invalidate();
        return true;
    }
}
