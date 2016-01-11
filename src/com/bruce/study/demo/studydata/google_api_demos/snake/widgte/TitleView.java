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

package com.bruce.study.demo.studydata.google_api_demos.snake.widgte;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.bruce.study.demo.R;

/**
 * TitleView: a View-variant designed for handling arrays of "icons" or other drawables.
 * Created by BruceHurrican on 2015/10/10.
 */
public class TitleView extends View {
    protected static int mTitleSize;
    protected static int mXTitleCount;
    protected static int mYTitleCount;
    protected static int mXOffset;
    protected static int mYOffset;
    private final Paint mPaint = new Paint();
    private Bitmap[] mTitleArray;
    private int[][] mTileGrid;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        mTitleSize = a.getInt(R.styleable.TitleView_tileSize, 12);
        a.recycle();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleView);
        mTitleSize = a.getInt(R.styleable.TitleView_tileSize, 12);
        a.recycle();
    }

    /**
     * Resets the internal array of Bitmaps used for drawing tiles, and
     * sets the maximum index of tiles to be inserted
     *
     * @param tilecount
     */
    public void resetTiles(int tilecount) {
        mTitleArray = new Bitmap[tilecount];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mXTitleCount = (int) Math.floor(w / mTitleSize);
        mYTitleCount = (int) Math.floor(h / mTitleSize);

        mXOffset = ((w - (mTitleSize * mXTitleCount)) / 2);
        mYOffset = ((w - (mTitleSize * mYTitleCount)) / 2);

        mTileGrid = new int[mXTitleCount][mYTitleCount];
        clearTitles();
    }

    /**
     * Resets all tiles to 0(empty).
     */
    public void clearTitles() {
        for (int i = 0; i < mXTitleCount; i++) {
            for (int j = 0; j < mYTitleCount; j++) {
                setTile(0, i, j);
            }
        }
    }

    /**
     * Used to indicate that a particular tile (set with loadTile and referenced
     * by an integer) should be drawn at the given x/y coordinates during the next
     * invalidate/draw cycle.
     *
     * @param tileIndex
     * @param i
     * @param j
     */
    public void setTile(int tileIndex, int i, int j) {
        mTileGrid[i][j] = tileIndex;
    }

    /**
     * Function to set the specified Drawable as the tile for a particular
     *
     * @param key
     * @param tile
     */
    public void loadTile(int key, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTitleSize, mTitleSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTitleSize, mTitleSize);
        tile.draw(canvas);
        mTitleArray[key] = bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mXTitleCount; i += 1) {
            for (int j = 0; j < mYTitleCount; j++) {
                if (mTileGrid[i][j] > 0) {
                    canvas.drawBitmap(mTitleArray[mTileGrid[i][j]], mXOffset + i * mTitleSize, mYOffset + j * mTitleSize, mPaint);
                }
            }
        }
    }
}
