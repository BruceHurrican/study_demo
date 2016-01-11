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

package com.bruce.study.demo.github.shape_loading_demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.bruce.study.demo.R;

/**
 * Created by BruceHurrican on 2015/6/7.
 */
public class ShapeLoadingView extends View {
    public boolean mIsLoading = false;
    private double genhao3 = 1.7320508075689;
    private Shape mShape = Shape.SHAPE_CIRCLE;
    /**
     * 用贝赛尔曲线画圆
     */
    private double mMagicNumber = 0.55228475f;
    private Paint mPaint;
    private double mControlX = 0;

    /* @TargetApi(Build.VERSION_CODES.LOLLIPOP)
     public ShapeLoadingView(Context context, AttributeSet attrs, int defStyleAttr,int defStyleRes) {
         super(context, attrs, defStyleAttr,defStyleAttr);
         init();
     }*/
    private double mControlY = 0;
    private double mAnimPercent;
    private double triangle2Circle = 0.25555555f;

    public ShapeLoadingView(Context context) {
        super(context);
        init();
    }

    public ShapeLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShapeLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.triangle));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        setBackgroundColor(getResources().getColor(R.color.view_bg));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mShape) {
            case SHAPE_TRIANGLE:
                if (mIsLoading) {
                    mAnimPercent += 0.1611113;
                    // trinangle to circle
                    Path path = new Path();
                    path.moveTo((float) relativeXFromView(0.5), (float) relativeYFromView(0));
                    if (mAnimPercent >= 1) {
                        mShape = Shape.SHAPE_CIRCLE;
                        mIsLoading = false;
                        mAnimPercent = 1;
                    }
                    double controlX = mControlX - relativeXFromView(mAnimPercent * triangle2Circle) * genhao3;
                    double controlY = mControlY - relativeYFromView(mAnimPercent * triangle2Circle);

                    path.quadTo((float) (relativeXFromView(1) - controlX), (float) controlY, (float) relativeXFromView(0.5f + genhao3 / 4), (float) relativeYFromView(0.75f));
                    path.quadTo((float) relativeXFromView(0.5f), (float) relativeYFromView(0.75f + 2 * mAnimPercent * triangle2Circle), (float) relativeXFromView(0.5f - genhao3 / 4), (float) relativeYFromView(0.75f));
                    path.quadTo((float) controlX, (float) controlY, (float) relativeXFromView(0.5f), (float) relativeYFromView(0f));
                    path.close();
                    canvas.drawPath(path, mPaint);
                    invalidate();
                } else {
                    Path path = new Path();
                    mPaint.setColor(getResources().getColor(R.color.triangle));
                    path.moveTo((float) relativeXFromView(0.5f), (float) relativeYFromView(0f));
                    path.lineTo((float) relativeXFromView(1f), (float) relativeYFromView(genhao3 / 2f));
                    path.lineTo((float) relativeXFromView(0f), (float) relativeYFromView(genhao3 / 2f));

                    mControlX = relativeXFromView(0.5f - genhao3 / 8f);
                    mControlY = relativeYFromView(3 / 8f);
                    mAnimPercent = 0;
                    path.close();
                    canvas.drawPath(path, mPaint);
                }
                break;
            case SHAPE_CIRCLE:
                if (mIsLoading) {
                    double magicNumber = mMagicNumber + mAnimPercent;
                    mAnimPercent += 0.12;
                    if (magicNumber + mAnimPercent >= 1.9f) {
                        mShape = Shape.SHAPE_RECT;
                        mIsLoading = false;
                    }
                    Path path = new Path();
                    path.moveTo((float) relativeXFromView(0.5f), (float) relativeYFromView(0f));
                    path.cubicTo((float) relativeXFromView(0.5f + magicNumber / 2), (float) relativeYFromView(0f), (float) relativeXFromView(1f), (float) relativeYFromView(0.5f - magicNumber / 2), (float) relativeXFromView(1f), (float) relativeYFromView(0.5f));
                    path.cubicTo((float) relativeXFromView(1f), (float) relativeXFromView(0.5f + magicNumber / 2), (float) relativeXFromView(0.5f + magicNumber / 2), (float) relativeYFromView(1f), (float) relativeXFromView(0.5f), (float) relativeYFromView(1f));
                    path.cubicTo((float) relativeXFromView(0.5f - magicNumber / 2), (float) relativeXFromView(1f), (float) relativeXFromView(0), (float) relativeYFromView(0.5f + magicNumber / 2), (float) relativeXFromView(0f), (float) relativeYFromView(0.5f));
                    path.cubicTo((float) relativeXFromView(0f), (float) relativeXFromView(0.5f - magicNumber / 2), (float) relativeXFromView(0.5f - magicNumber / 2), (float) relativeYFromView(0f), (float) relativeXFromView(0.5f), (float) relativeYFromView(0f));
                    path.close();
                    canvas.drawPath(path, mPaint);
                    invalidate();
                } else {
                    mPaint.setColor(getResources().getColor(R.color.circle));
                    Path path = new Path();

                    double magicNumber = mMagicNumber;
                    path.moveTo((float) relativeXFromView(0.5f), (float) relativeYFromView(0f));
                    path.cubicTo((float) relativeXFromView(0.5f + magicNumber / 2), 0,
                            (float) relativeXFromView(1), (float) relativeYFromView(magicNumber / 2),
                            (float) relativeXFromView(1f), (float) relativeYFromView(0.5f));
                    path.cubicTo(
                            (float) relativeXFromView(1), (float) relativeXFromView(0.5f + magicNumber / 2),
                            (float) relativeXFromView(0.5f + magicNumber / 2), (float) relativeYFromView(1f),
                            (float) relativeXFromView(0.5f), (float) relativeYFromView(1f));
                    path.cubicTo((float) relativeXFromView(0.5f - magicNumber / 2), (float) relativeXFromView(1f),
                            (float) relativeXFromView(0), (float) relativeYFromView(0.5f + magicNumber / 2),
                            (float) relativeXFromView(0f), (float) relativeYFromView(0.5f));
                    path.cubicTo((float) relativeXFromView(0f), (float) relativeXFromView(0.5f - magicNumber / 2),
                            (float) relativeXFromView(0.5f - magicNumber / 2), (float) relativeYFromView(0),
                            (float) relativeXFromView(0.5f), (float) relativeYFromView(0f));
                    mAnimPercent = 0;

                    path.close();
                    canvas.drawPath(path, mPaint);
                }
                break;
            case SHAPE_RECT:


                if (mIsLoading) {


                    mAnimPercent += 0.15;
                    if (mAnimPercent >= 1) {
                        mShape = Shape.SHAPE_TRIANGLE;
                        mIsLoading = false;
                        mAnimPercent = 1;
                    }
                    Path path = new Path();
                    path.moveTo((float) relativeXFromView(0.5f * mAnimPercent), 0);
                    path.lineTo((float) relativeYFromView(1 - 0.5f * mAnimPercent), 0);
                    double distanceX = (mControlX) * mAnimPercent;
                    double distanceY = (relativeYFromView(1f) - mControlY) * mAnimPercent;

                    path.lineTo((float) (relativeXFromView(1f) - distanceX), (float) (relativeYFromView(1f) - distanceY));
                    path.lineTo((float) (relativeXFromView(0f) + distanceX), (float) (relativeYFromView(1f) - distanceY));

                    path.close();


                    canvas.drawPath(path, mPaint);
                    invalidate();

                } else {
                    mPaint.setColor(getResources().getColor(R.color.rect));
                    mControlX = relativeXFromView(0.5f - genhao3 / 4);
                    mControlY = relativeYFromView(0.75f);
                    Path path = new Path();
                    path.moveTo((float) relativeXFromView(0f), (float) relativeYFromView(0f));
                    path.lineTo((float) relativeXFromView(1f), (float) relativeYFromView(0f));
                    path.lineTo((float) relativeXFromView(1f), (float) relativeYFromView(1f));
                    path.lineTo((float) relativeXFromView(0f), (float) relativeYFromView(1f));
                    path.close();
                    mAnimPercent = 0;
                    canvas.drawPath(path, mPaint);
                }
                break;
        }
    }

    private double relativeXFromView(double percent) {
        return getWidth() * percent;
    }

    private double relativeYFromView(double percent) {
        return getHeight() * percent;
    }

    public void changeShape() {
        mIsLoading = true;
        invalidate();
    }

    public Shape getShape() {
        return mShape;
    }

    public enum Shape {
        SHAPE_TRIANGLE, SHAPE_RECT, SHAPE_CIRCLE
    }
}
