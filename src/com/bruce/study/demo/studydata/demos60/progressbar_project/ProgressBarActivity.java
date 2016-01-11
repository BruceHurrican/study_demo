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

package com.bruce.study.demo.studydata.demos60.progressbar_project;

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
        setContentView(R.layout.demos60_activity_progressbarproject);
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
