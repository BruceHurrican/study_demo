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

package com.bruce.study.demo.studydata.demos60.seekbar_project;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.log.Logs;

/**
 * SeekBarActivity 练习
 * Created by BruceHurrican on 2015/7/5.
 */
public class SeekBarActivity extends BaseActivity {
    private TextView tv_sb;

    @Override
    public String getTAG() {
        return "SeekBarActivity -- >";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos60_activity_seekbarproject);
        SeekBar sb = (SeekBar) findViewById(R.id.sb);
        tv_sb = (TextView) findViewById(R.id.tv_sb);
        sb.setSecondaryProgress(20);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_sb.setText("当前<拖动条>的值为：" + progress);
                Logs.i(getTAG(), "当前<拖动条>的值为：" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv_sb.setText("拖动中...");
                Logs.i(getTAG(), "拖动中...");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_sb.setText("<拖动条>完成拖动");
                Logs.i(getTAG(), "<拖动条>完成拖动");
            }
        });
    }
}
