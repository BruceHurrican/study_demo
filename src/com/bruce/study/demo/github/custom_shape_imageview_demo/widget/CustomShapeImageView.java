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
import android.graphics.Bitmap;
import android.util.AttributeSet;
import com.bruce.study.demo.R;

/**
 * Created by Mostafa Gazar on 11/2/13.
 */
public class CustomShapeImageView extends BaseImageView {

    private int mShape = Shape.CIRCLE;
    private int mSvgRawResourceId;
    public CustomShapeImageView(Context context) {
        super(context);
    }

    public CustomShapeImageView(Context context, int resourceId, int shape, int svgRawResourceId) {
        this(context);

        setImageResource(resourceId);
        mShape = shape;
        mSvgRawResourceId = svgRawResourceId;
    }

    public CustomShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sharedConstructor(context, attrs);
    }

    public CustomShapeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        sharedConstructor(context, attrs);
    }

    private void sharedConstructor(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView);
        mShape = a.getInt(R.styleable.CustomShapeImageView_shape, Shape.CIRCLE);
        mSvgRawResourceId = a.getResourceId(R.styleable.CustomShapeImageView_svg_raw_resource, 0);
        a.recycle();
    }

    @Override
    public Bitmap getBitmap() {
        switch (mShape) {
            case Shape.CIRCLE:
                return CircleImageView.getBitmap(getWidth(), getHeight());
            case Shape.RECTANGLE:
                return RectangleImageView.getBitmap(getWidth(), getHeight());
            case Shape.SVG:
                return SvgImageView.getBitmap(mContext, getWidth(), getHeight(), mSvgRawResourceId);
        }
        return null;
    }

    public static class Shape {
        public static final int CIRCLE = 1;
        public static final int RECTANGLE = 2;
        public static final int SVG = 3;
    }

}
