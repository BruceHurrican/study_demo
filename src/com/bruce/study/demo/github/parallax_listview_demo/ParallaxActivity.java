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

package com.bruce.study.demo.github.parallax_listview_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.github.parallax_listview_demo.widget.ParallaxScrollListView;

/**
 * 下拉刷新头图片放大
 * Created by BruceHurrican on 2015/6/13.
 */
public class ParallaxActivity extends Activity{
    private ParallaxScrollListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parallax_activity);
        mListView = (ParallaxScrollListView) findViewById(R.id.pslv);
        View header = LayoutInflater.from(this).inflate(R.layout.parallax_view_listview_header,null);
        ImageView mImageView = (ImageView) header.findViewById(R.id.iv_header_image);

        mListView.setParallaxImageView(mImageView);
        mListView.addHeaderView(header);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, new String[]{"First Item","Second Item","Third Item","Forth Item","Fifth Item","Sixth Item","First Item","Second Item","Third Item","Forth Item","Fifth Item","Sixth Item","First Item","Second Item","Third Item","Forth Item","Fifth Item","Sixth Item"});
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parallax_menu,menu);
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            mListView.setViewsBounds(ParallaxScrollListView.ZOOM_X2);
        }
    }
}
