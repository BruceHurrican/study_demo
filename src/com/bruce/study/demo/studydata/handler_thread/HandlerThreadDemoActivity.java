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

package com.bruce.study.demo.studydata.handler_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bruce.study.demo.base.BaseActivity;

/**
 * HandlerThread 练习
 * Created by BruceHurrican on 2015/8/2.
 */
public class HandlerThreadDemoActivity extends BaseActivity {
    @Override
    public String getTAG() {
        return "HandlerThreadDemoActivity -->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandlerThread thread = new HandlerThread("handler_thread_demo");
        thread.start();
        final Handler handler = new Handler(thread.getLooper()) {

            @Override
            public void handleMessage(Message msg) {
                logD("当前线程--" + Thread.currentThread() + "+msg.what+" + msg.what);
                super.handleMessage(msg);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    handler.sendMessage(Message.obtain());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        logE(e.toString());
                    }
                }
            }
        }).start();

        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setText("测试HandlerThread");
        tv.setTextSize(25);
        setContentView(tv);
    }
}
