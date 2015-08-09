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

package com.bruce.study.demo.studydata.demos60.clip_canvas_project;

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
public class MyClipCanvasView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static final String TAG = "MyClipCanvasView -- >";
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private boolean flag;
    private Bitmap bitmap;

    public MyClipCanvasView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        setFocusable(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.demos60_ic_baseadapter_1);
    }

    public void myDraw() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (null != canvas) {
                canvas.drawColor(Color.BLACK);
                // 利用Region设置可视化区域
                canvas.save(); // 将当前状态保存在栈中，方便后面restore
                Region region = new Region();
                region.op(new Rect(20, 20, 100, 100), Region.Op.UNION);
                region.op(new Rect(40, 20, 80, 150), Region.Op.XOR);
                canvas.clipRegion(region);
                canvas.drawBitmap(bitmap, 0, 0, paint);
                canvas.restore();
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
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
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
        if (null != bitmap) {
            bitmap.recycle();
            bitmap = null;
            Logs.i(TAG, "bitmap资源已经释放");
        }
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            myDraw();
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
