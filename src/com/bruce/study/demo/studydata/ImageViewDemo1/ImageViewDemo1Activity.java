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

package com.bruce.study.demo.studydata.imageviewdemo1;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

/**
 * ImageView 循环跑马灯的效果
 * Created by BruceHurrican on 2015/9/29.
 */
public class ImageViewDemo1Activity extends BaseActivity {
    private ImageView iv_demo1, iv2_demo1;
    private TextView tv_demo1;
    private boolean flag = true;
    private int images[] = new int[]{R.drawable.itheima_popwindow_i1, R.drawable.itheima_popwindow_i2, R.drawable.ic_launcher, R.drawable.icon_workdemo, R.drawable.itheima_popwindow_i5};
    private int count = 0;

    @Override
    public String getTAG() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iv_demo1_activity);
        iv_demo1 = (ImageView) findViewById(R.id.iv_demo1);
        iv2_demo1 = (ImageView) findViewById(R.id.iv2_demo1);
        tv_demo1 = (TextView) findViewById(R.id.tv_demo1);
        iv2_demo1.setVisibility(View.GONE);
        tv_demo1.setText(count + "");
        initUIHandler();
        AnimationSet as1 = new AnimationSet(true);
        AnimationSet as2 = new AnimationSet(true);
        if (flag) {
            getUIHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv2_demo1.setVisibility(View.VISIBLE);
                    TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                    ta.setDuration(2000);
                    as1.addAnimation(ta);
                    as1.setFillAfter(true);
                    ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f);
                    ta.setDuration(2000);
                    as2.addAnimation(ta);
                    as2.setFillAfter(true);
                    iv_demo1.startAnimation(as1);
                    iv2_demo1.startAnimation(as2);
                    iv_demo1.setBackgroundResource(images[count % 5]);
                    count++;
                    iv2_demo1.setBackgroundResource(images[count % 5]);
                    tv_demo1.setText(count + "");
                }
            }, 3000);
        }
    }

    @Override
    protected void onStop() {
        flag = false;
        super.onStop();
    }
}
