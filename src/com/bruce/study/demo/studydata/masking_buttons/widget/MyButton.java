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

package com.bruce.study.demo.studydata.masking_buttons.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Button;
import com.bruce.study.demo.base.BaseActivity;

/**
 * 自定义退出按钮
 * Created by BruceHurrican on 2015/8/1.
 */
public class MyButton extends Button {
    private int textX = 125;
    private int textY = 125;
    private BaseActivity mContext;
    private int screenWidth;
    private int screenHeight;

    public MyButton(Context context) {
        super(context);
        mContext = (BaseActivity) context;
        screenWidth = mContext.getWindowManager().getDefaultDisplay().getWidth();
        screenHeight = mContext.getWindowManager().getDefaultDisplay().getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(false);
        paint.setStrokeWidth(15);
        paint.setTextSize(40);
        canvas.drawText("退出", textX, textY, paint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textX = (int) (event.getX() + screenWidth * 0.1);
        textY = (int) (event.getY() + screenHeight * 0.1);
        if (textX > screenWidth * 0.8 || textY > screenHeight * 0.8) {
            textX = textX > screenWidth * 0.8 ? 100 : textX;
            textY = textY > screenHeight * 0.8 ? 100 : textY;
        }
        mContext.showToastShort("没有点中");
        invalidate();
        return false; // 如果为true 则该方法会被执行2次
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            mContext.showToastShort("按钮被点击");
        }
        invalidate();
        return super.onKeyDown(keyCode, event);
    }
}
