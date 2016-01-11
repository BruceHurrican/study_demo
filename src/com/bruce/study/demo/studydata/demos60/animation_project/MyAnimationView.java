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

package com.bruce.study.demo.studydata.demos60.animation_project;

import android.content.Context;
import android.graphics.*;
import android.media.ThumbnailUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.*;
import com.bruce.study.demo.R;
import com.bruce.study.demo.log.Logs;

/**
 * Created by BruceHurrican on 2015/8/15.
 */
public class MyAnimationView extends View implements Animation.AnimationListener {
    public static final String TAG = "MyAnimationView -- >";
    private Paint paint;
    private Bitmap bmp;

    public MyAnimationView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.parallax_header);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logs.d(TAG, "getWidth()--" + getWidth() + "\ngetHeight()--" + getHeight());
        bmp = ThumbnailUtils.extractThumbnail(bmp, 400, 400);
        canvas.drawColor(Color.LTGRAY);
        paint.setTextSize(30f);
        paint.setColor(Color.WHITE);
        canvas.drawText("点击屏幕左上角区域显示--渐变透明度动画效果", 30, 80, paint);
        paint.setColor(Color.BLUE);
        canvas.drawText("点击屏幕右上角区域显示--渐变尺寸伸缩动画效果", 30, 120, paint);
        paint.setColor(Color.RED);
        canvas.drawText("点击屏幕左下角区域显示--位置移动动画效果", 30, 160, paint);
        paint.setColor(Color.GREEN);
        canvas.drawText("点击屏幕右下角区域显示--旋转动画效果", 30, 200, paint);
        canvas.drawBitmap(bmp, 10, 220, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float tempX = event.getX();
                float tempY = event.getY();
                Logs.d(TAG, "tempX --> " + tempX + "+tempY --> " + tempY);
                if (tempX >= getWidth() / 2 && tempY >= getHeight() / 2) {
                    Logs.d(TAG, "move to down_right");
                    Animation mRotateAnimation = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    mRotateAnimation.setAnimationListener(this);
                    mRotateAnimation.setDuration(3000);
                    startAnimation(mRotateAnimation);
                    return true;
                } else if (tempX < getWidth() / 2 && tempY >= getHeight() / 2) {
                    Logs.d(TAG, "move to down_left");
                    Animation mTranslateAnimation = new TranslateAnimation(0, 300, 0, 300);
                    mTranslateAnimation.setAnimationListener(this);
                    mTranslateAnimation.setDuration(4000);
                    startAnimation(mTranslateAnimation);
                    return true;
                } else if (tempX < getWidth() / 2 && tempY < getHeight() / 2) {
                    Logs.d(TAG, "move to up_left");
                    Animation mAlphaAnimation = new AlphaAnimation(0.1f, 1.0f);
                    mAlphaAnimation.setAnimationListener(this);
                    mAlphaAnimation.setDuration(3000);
                    startAnimation(mAlphaAnimation);
                    return true;
                } else if (tempX >= getWidth() / 2 && tempY < getHeight() / 2) {
                    Logs.d(TAG, "move to up_right");
                    Animation mScaleAnimation = new ScaleAnimation(0.0f, 2.0f, 0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    mScaleAnimation.setAnimationListener(this);
                    mScaleAnimation.setDuration(3000);
                    startAnimation(mScaleAnimation);
                    return true;
                }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Logs.d(TAG, "动画效果开始");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Logs.d(TAG, "动画效果结束");
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Logs.d(TAG, "动画效果重复");
    }
}
