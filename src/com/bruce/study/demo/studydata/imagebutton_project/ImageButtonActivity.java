/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some lines from Internet. Everyone can download and use for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.studydata.imagebutton_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import com.bruce.study.demo.R;

/**
 * ImageButton 练习
 * Created by BruceHurrican on 2015/6/28.
 */
public class ImageButtonActivity extends Activity{
        ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studydata_activity_imgbtnproject);
        imgbtn = (ImageButton) findViewById(R.id.imgbtn);
        imgbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    imgbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.studydata_imgbtnproject_press));
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    imgbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.studydata_imgbtnproject_nopress));
                }
                return false;
            }
        });
    }
}
