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

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import com.bruce.study.demo.R;
import com.bruce.study.demo.github.custom_shape_imageview_demo.svg_android.SVG;
import com.bruce.study.demo.github.custom_shape_imageview_demo.svg_android.SVGParser;

/**
 * Created by Mostafa Gazar on 11/2/13.
 */
public class SvgImageView extends BaseImageView {

    private int mSvgRawResourceId;

    public SvgImageView(Context context) {
        super(context);
    }

    public SvgImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context, attrs);
    }

    public SvgImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context, attrs);
    }

    public static Bitmap getBitmap(Context context, int width, int height, int svgRawResourceId) {
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);

        if (svgRawResourceId > 0) {
            SVG svg = SVGParser.getSVGFromInputStream(
                    context.getResources().openRawResource(svgRawResourceId), width, height);
            canvas.drawPicture(svg.getPicture());
        } else {
            canvas.drawRect(new RectF(0.0f, 0.0f, width, height), paint);
        }

        return bitmap;
    }

    private void sharedConstructor(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView);
        mSvgRawResourceId = a.getResourceId(R.styleable.CustomShapeImageView_svg_raw_resource, 0);
        a.recycle();
    }

    @Override
    public Bitmap getBitmap() {
        return getBitmap(mContext, getWidth(), getHeight(), mSvgRawResourceId);
    }
}
