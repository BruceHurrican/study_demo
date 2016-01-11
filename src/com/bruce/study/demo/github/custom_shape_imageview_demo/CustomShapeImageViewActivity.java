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

package com.bruce.study.demo.github.custom_shape_imageview_demo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.github.custom_shape_imageview_demo.widget.CustomShapeImageView;
import com.bruce.study.demo.github.custom_shape_imageview_demo.widget.CustomShapeSquareImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BruceHurrican on 2015/8/22.
 */
public class CustomShapeImageViewActivity extends BaseActivity {
    @Override
    public String getTAG() {
        return CustomShapeImageViewActivity.class.getSimpleName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_shape_imageview_activity);
        GridView gridView = (GridView) findViewById(R.id.gv_csiv);
        gridView.setAdapter(new SvgImagesAdapter(this));
    }

    private static class SvgImagesAdapter extends BaseAdapter {
        private List<Integer> mSvgRawResourceIds = new ArrayList<>(5);
        private Context mContext;

        public SvgImagesAdapter(Context mContext) {
            this.mContext = mContext;
            mSvgRawResourceIds.add(R.raw.csiv_demo_shape_star);
            mSvgRawResourceIds.add(R.raw.csiv_demo_shape_heart);
            mSvgRawResourceIds.add(R.raw.csiv_demo_shape_flower);
            mSvgRawResourceIds.add(R.raw.csiv_demo_shape_star_2);
            mSvgRawResourceIds.add(R.raw.csiv_demo_shape_star_3);
            mSvgRawResourceIds.add(R.raw.csiv_demo_shape_circle_2);
            mSvgRawResourceIds.add(R.raw.csiv_demo_shape_5);
        }

        @Override
        public int getCount() {
            return mSvgRawResourceIds.size();
        }

        @Override
        public Integer getItem(int position) {
            return mSvgRawResourceIds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return mSvgRawResourceIds.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return new CustomShapeSquareImageView(mContext, R.drawable.itheima_popwindow_i1, CustomShapeImageView.Shape.SVG, getItem(position));// It is just a sample
        }
    }
}
