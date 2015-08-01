/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some lines from Internet. Everyone can download and use for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
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
