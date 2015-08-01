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

package com.bruce.study.demo.studydata.demos60.checkbox_project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import com.bruce.study.demo.R;

/**
 * CheckBox 练习
 * Created by BruceHurrican on 2015/7/4.
 */
public class CheckBoxActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox cb1, cb2, cb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos60_activity_checkboxproject);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // buttonView 选中状态发生改变的那个按钮
        if (cb1.equals(buttonView) || cb2.equals(buttonView) || cb3.equals(buttonView)) {
            if (isChecked) {
                Toast.makeText(CheckBoxActivity.this, buttonView.getText() + "选中", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CheckBoxActivity.this, buttonView.getText() + "取消选中", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
