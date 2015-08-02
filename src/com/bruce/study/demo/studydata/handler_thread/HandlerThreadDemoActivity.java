/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some resources from the Internet. Everyone can download and use it for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
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
