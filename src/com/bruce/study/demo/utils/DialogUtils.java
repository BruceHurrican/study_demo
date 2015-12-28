/*
 * BruceHurrican
 * Copyright (c) 2015.
 *    This document is Bruce's individual learning the android demo, wherein the use of the code from the Internet, only to use as a learning exchanges.
 *   And where any person can download and use, but not for commercial purposes.
 *   Author does not assume the resulting corresponding disputes.
 *   If you have good suggestions for the code, you can contact BurrceHurrican@foxmail.com
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.bruce.study.demo.R;

/**
 * 对话框工具类
 * Created by BruceHurrican on 2015/12/14.
 */
public class DialogUtils {
    public static void showTxtDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("title");
        builder.setMessage("testMessage");
        builder.setPositiveButton("btn1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogUtils.d("positive btn clicked");
            }
        });
        builder.setNegativeButton("btn2", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogUtils.d("negative btn clicked");
            }
        });
        builder.setNeutralButton("btn3", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LogUtils.d("neutral btn clicked");
            }
        });
        builder.show();
    }

    // todo base 类 ProgressDialog 放入此文件下
}
