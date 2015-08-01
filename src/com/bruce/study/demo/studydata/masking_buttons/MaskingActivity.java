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

package com.bruce.study.demo.studydata.masking_buttons;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.studydata.masking_buttons.widget.MyButton;

/**
 * 屏蔽手机物理键盘练习
 * Created by BruceHurrican on 2015/8/1.
 */
public class MaskingActivity extends BaseActivity {
    @Override
    public String getTAG() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        LinearLayout container = new LinearLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(params);
        container.setOrientation(LinearLayout.VERTICAL);
        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setText("点击退出按钮即可退出");
        tv.setPadding(20, 20, 20, 20);
        tv.setTextSize(22);
        tv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MaskingActivity.this.logD("退出MaskingActivity成功");
                finish();
                return true;
            }
        });

        MyButton btn = new MyButton(this);
        btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        container.addView(tv);
        container.addView(btn);
        setContentView(container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                logD("屏蔽菜单键成功");
                break;
            case KeyEvent.KEYCODE_BACK:
                logD("屏蔽返回键成功");
                break;
            case KeyEvent.KEYCODE_HOME:
                logD("屏蔽HOME键成功"); // 4.0以上无效果
                break;
        }
        showToastShort("请点击关闭按钮退出");
        return true;
    }
}
