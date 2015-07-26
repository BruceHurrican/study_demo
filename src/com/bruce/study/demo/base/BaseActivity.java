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

package com.bruce.study.demo.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.bruce.study.demo.log.Logs;

/**
 * 基类Activity
 * Created by BruceHurrican on 2015/7/5.
 */
public abstract class BaseActivity extends Activity {
    private Context context;
    private final String TAG = getTAG();
    private String logsTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = BaseActivity.this;
//        TAG = getTAG();
        logsTag = getLocalClassName() + "-->";
    }

    public abstract String getTAG();

    public final void logV(String text) {
        Logs.v(logsTag, text);
    }

    public final void logD(String text) {
        Logs.d(logsTag, text);
    }

    public final void logI(String text) {
        Logs.i(logsTag, text);
    }

    public final void logW(String text) {
        Logs.w(logsTag, text);
    }

    public final void logE(String text) {
        Logs.e(logsTag, text);
    }

    public void showToastShort(String text) {
        if (null != context) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        } else {
            Logs.e(TAG, "打印日志出错");
        }
    }

    public void showToastLong(String text) {
        if (null != context) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        } else {
            Logs.e(TAG, "print log error");
        }
    }
}
