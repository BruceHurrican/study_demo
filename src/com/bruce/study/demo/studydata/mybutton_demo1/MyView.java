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

package com.bruce.study.demo.studydata.mybutton_demo1;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by BruceHurrican on 2015/9/29.
 */
public class MyView extends LinearLayout implements Runnable {
    public static final String TAG = "水波纹效果View -->";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int targetWidth;
    private int minBetweenWidthAndHeight;
    private int maxRadius;
    private int radiusGap;
    private int radius = 0;
    private float centerX, centerY;
    private int[] locationInScreen = new int[2];
    private boolean shouldDoAnimation = false;
    private boolean isPressed = false;
    private int invalidate_duration = 40;
    private View touchTarget;
    private DispatchUpTouchEventRunnable dispatchUpTouchEventRunnable = new DispatchUpTouchEventRunnable();

    public MyView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setWillNotDraw(false);
        paint.setColor(Color.GREEN);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        getLocationOnScreen(locationInScreen);
    }

    private void initParametersForChild(MotionEvent event, View view) {
        centerX = event.getX();
        centerY = event.getY();
        targetWidth = view.getMeasuredWidth();
        int targetHeight = view.getMeasuredHeight();
        minBetweenWidthAndHeight = Math.min(targetWidth, targetHeight);
        int maxBetweenWidthAndHeight = Math.max(targetWidth, targetHeight);
        radius = 0;
        shouldDoAnimation = true;
        isPressed = true;
        radiusGap = minBetweenWidthAndHeight / 8;
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0] - locationInScreen[0];
        int transformedCenterX = (int) centerX - left;
        maxRadius = Math.max(transformedCenterX, targetWidth - transformedCenterX);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!shouldDoAnimation || targetWidth <= 0 || touchTarget == null) {
            return;
        }
        if (radius > minBetweenWidthAndHeight / 2) {
            radius += radiusGap * 4;
        } else {
            radius += radiusGap;
        }
        getLocationOnScreen(locationInScreen);
        int[] location = new int[2];
        touchTarget.getLocationOnScreen(location);
        int left = location[0] - locationInScreen[0];
        int top = location[1] - locationInScreen[1];
        int right = left + touchTarget.getMeasuredWidth();
        int bottom = top + touchTarget.getMeasuredHeight();

        canvas.save();
        canvas.clipRect(left, top, right, bottom);
        canvas.drawCircle(centerX, centerY, radius, paint);
        canvas.restore();
        if (radius <= maxRadius) {
            postInvalidateDelayed(invalidate_duration, left, top, right, bottom);
        } else if (!isPressed) {
            shouldDoAnimation = false;
            postInvalidateDelayed(invalidate_duration, left, top, right, bottom);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View touchTarget2 = getTouchTarget(this, x, y);
            if (touchTarget2 != null && touchTarget2.isClickable() && touchTarget2.isEnabled()) {
                touchTarget = touchTarget2;
                initParametersForChild(ev, touchTarget2);
                postInvalidateDelayed(invalidate_duration);
            }
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            isPressed = false;
            postInvalidateDelayed(invalidate_duration);
            dispatchUpTouchEventRunnable.event = ev;
            postDelayed(dispatchUpTouchEventRunnable, 40);
            return true;
        } else if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
            isPressed = false;
            postInvalidateDelayed(invalidate_duration);
        }
        return super.dispatchTouchEvent(ev);
    }

    private View getTouchTarget(View view, int x, int y) {
        View target = null;
        List<View> views = view.getTouchables();
        for (int i = 0; i < views.size(); i++) {
            if (isTouchPointInView(views.get(i), x, y)) {
                target = views.get(i);
                break;
            }
        }
        return target;
    }

    @Override
    public boolean performClick() {
        postDelayed(this, 400);
        return true;
    }

    @Override
    public void run() {
        super.performClick();
    }

    private boolean isTouchPointInView(View view, int x, int y) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        if (view.isClickable() && y >= top && y <= bottom && x >= left && x <= right) {
            return true;
        }
        return false;
    }

    private class DispatchUpTouchEventRunnable implements Runnable {
        private MotionEvent event;

        @Override
        public void run() {
            if (touchTarget == null || !touchTarget.isEnabled()) {
                return;
            }
            if (isTouchPointInView(touchTarget, (int) event.getRawX(), (int) event.getRawY())) {
                touchTarget.performClick();
            }
        }
    }
}
