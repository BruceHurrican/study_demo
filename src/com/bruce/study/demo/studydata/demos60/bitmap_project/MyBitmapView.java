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

package com.bruce.study.demo.studydata.demos60.bitmap_project;

import android.content.Context;
import android.graphics.*;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.log.Logs;

/**
 * Created by BruceHurrican on 2015/8/9.
 */
public class MyBitmapView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Bitmap bitmap;

    public MyBitmapView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        setFocusable(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demos60_ic_baseadapter_1);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Thread thread = new Thread(this);
        thread.start();
    }

    private void myDrawy() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawBitmap(bitmap, 0, 0, paint);
            Matrix matrix = new Matrix();
            matrix.postScale(1.5f, 1f, 0, 0);
            matrix.postTranslate(bitmap.getWidth(), 0); // 将图片移动到目的坐标
            canvas.drawBitmap(bitmap, matrix, paint);
//            canvas.drawBitmap(bitmap, 0, 0, paint);
            Matrix matrix2 = new Matrix();
            // 第1个参数表示 沿X轴缩放倍数，第2个参数表示 沿Y轴缩放倍数，正数正向缩放，负数反向缩放，第3、4个参数表示缩放锚点坐标
            matrix2.postScale(1f, -2f, 0, 0);
            matrix2.postTranslate(300, 800);
            canvas.drawBitmap(bitmap, matrix2, paint);
            Matrix matrix3 = new Matrix();
            matrix3.postScale(1f, 2f);
            matrix3.postTranslate(bitmap.getWidth() * 2.5f, 0);
            canvas.drawBitmap(bitmap, matrix3, paint);
        } catch (Exception e) {
            Logs.e("MyBitmapView -->", e.toString());
        } finally {
            if (null != canvas) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (null != bitmap) {
            bitmap.recycle();
            bitmap = null;
            Logs.i("MyBitmapView -->", "图片资源已经释放");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void run() {
        myDrawy();
    }
}
