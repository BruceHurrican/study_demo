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

package com.bruce.study.demo.studydata.progressbar_project;

import android.os.Bundle;
import android.widget.ProgressBar;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

/**
 * ProgressBar 练习
 * Created by BruceHurrican on 2015/7/5.
 */
public class ProgressBarActivity extends BaseActivity implements Runnable {
    private ProgressBar pb;
    private boolean stateChange; // 标识进度值最大最小的状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studydata_activity_progressbarproject);
        pb = (ProgressBar) findViewById(R.id.progressbar);
        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public String getTAG() {
        return "ProgressBarActivity -- >";
    }

    @Override
    public void run() {
        while (true) {
            int current = pb.getProgress();
            int currentMax = pb.getMax();
//            int secondCurrent = pb.getSecondaryProgress();
            // 以下代码实现进度值越来越大，越来越小的一个动态效果
            if (!stateChange) {
                if (current >= currentMax) {
                    stateChange = true;
                } else {
                    // 设置进度值
                    pb.setProgress(current + 1);
                    // 设置底层进度值
                    pb.setSecondaryProgress(current + 1);
                }
            } else {
                if (current <= 0) {
                    stateChange = false;
                } else {
                    pb.setProgress(current - 1);
                    pb.setSecondaryProgress(current - 1);
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                logE(e.toString());
            }
        }
    }
}
