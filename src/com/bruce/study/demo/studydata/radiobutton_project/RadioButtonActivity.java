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

package com.bruce.study.demo.studydata.radiobutton_project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.bruce.study.demo.R;

/**
 * RadioButton 练习
 * Created by BruceHurrican on 2015/7/4.
 */
public class RadioButtonActivity extends Activity {
    private RadioButton rb1, rb2, rb3;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studydata_activity_radiobtnproject);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group == radioGroup) {
                    String rbName = null;
                    if (checkedId == rb1.getId()) {
                        rbName = rb1.getText().toString();
                    } else if (checkedId == rb2.getId()) {
                        rbName = rb2.getText().toString();
                    } else if (checkedId == rb3.getId()) {
                        rbName = rb3.getText().toString();
                    }
                    Toast.makeText(RadioButtonActivity.this, "选择了下标为 " + rbName + " 的单选按钮", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
