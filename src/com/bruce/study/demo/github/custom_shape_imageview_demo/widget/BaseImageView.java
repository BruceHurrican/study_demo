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

package com.bruce.study.demo.github.custom_shape_imageview_demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Mostafa Gazar on 11/2/13.
 */
public abstract class BaseImageView extends ImageView {
    private static final String TAG = BaseImageView.class.getSimpleName();
    private static final Xfermode sXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    protected Context mContext;
    //    private BitmapShader mBitmapShader;
    private Bitmap mMaskBitmap;
    private Paint mPaint;
    private WeakReference<Bitmap> mWeakBitmap;

    public BaseImageView(Context context) {
        super(context);
        sharedConstructor(context);
    }

    public BaseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context);
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context);
    }

    private void sharedConstructor(Context context) {
        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void invalidate() {
        mWeakBitmap = null;
        if (mMaskBitmap != null) {
            mMaskBitmap.recycle();
        }
        super.invalidate();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            int i = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(),
                    null, Canvas.ALL_SAVE_FLAG);
            try {
                Bitmap bitmap = mWeakBitmap != null ? mWeakBitmap.get() : null;
                // Bitmap not loaded.
                if (bitmap == null || bitmap.isRecycled()) {
                    Drawable drawable = getDrawable();
                    if (drawable != null) {
                        // Allocation onDraw but it's ok because it will not always be called.
                        bitmap = Bitmap.createBitmap(getWidth(),
                                getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas bitmapCanvas = new Canvas(bitmap);
                        drawable.setBounds(0, 0, getWidth(), getHeight());
                        drawable.draw(bitmapCanvas);

                        // If mask is already set, skip and use cached mask.
                        if (mMaskBitmap == null || mMaskBitmap.isRecycled()) {
                            mMaskBitmap = getBitmap();
                        }

                        // Draw Bitmap.
                        mPaint.reset();
                        mPaint.setFilterBitmap(false);
                        mPaint.setXfermode(sXfermode);
//                        mBitmapShader = new BitmapShader(mMaskBitmap,
//                                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//                        mPaint.setShader(mBitmapShader);
                        bitmapCanvas.drawBitmap(mMaskBitmap, 0.0f, 0.0f, mPaint);

                        mWeakBitmap = new WeakReference<Bitmap>(bitmap);
                    }
                }

                // Bitmap already loaded.
                if (bitmap != null) {
                    mPaint.setXfermode(null);
//                    mPaint.setShader(null);
                    canvas.drawBitmap(bitmap, 0.0f, 0.0f, mPaint);
                    return;
                }
            } catch (Exception e) {
                System.gc();

                Log.e(TAG, String.format("Failed to draw, Id :: %s. Error occurred :: %s", getId(), e.toString()));
            } finally {
                canvas.restoreToCount(i);
            }
        } else {
            super.onDraw(canvas);
        }
    }

    public abstract Bitmap getBitmap();

}
