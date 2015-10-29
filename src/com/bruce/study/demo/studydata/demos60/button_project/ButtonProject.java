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

package com.bruce.study.demo.studydata.demos60.button_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Button 监听练习,json解析练习
 * Created by BruceHurrican on 2015/6/28.
 */
public class ButtonProject extends BaseActivity {
    private TextView tv_btnproject_hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos60_activity_btnproject);
        tv_btnproject_hello = (TextView) findViewById(R.id.tv_btnproject_hello);
        Button btn_btnproject_ok = (Button) findViewById(R.id.btn_btnproject_ok);
        Button btn_btnproject_cancel = (Button) findViewById(R.id.btn_btnproject_cancel);
        btn_btnproject_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_btnproject_hello.setText("OK btn is clicked!");
                jsonDemo();
            }
        });
        btn_btnproject_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_btnproject_hello.setText("Cancel btn is clicked!");
            }
        });
    }

    @Override
    public String getTAG() {
        return null;
    }

    /**
     * 练习json解析方法
     */
    private void jsonDemo() {
        String json = "{\"aa\":\"11\",\"bb\":\"22\"}";
        String json2 = "[{\"aa\":\"112\",\"bb\":\"221\"},{'aa':'w2','bb':'s2'}]";
        try {
            JSONObject object = new JSONObject(json);
            String na = object.getString("aa");
            logD("aa--" + na + "-bb-" + object.getString("bb"));
            JSONArray array = new JSONArray(json2);
            JSONObject object1 = array.getJSONObject(0);
            for (int i = 0; i < array.length(); i++) {
                object1 = array.getJSONObject(i);
                logD("i-" + i + "-aa-" + object1.getString("aa") + "-bb-" + object1.getString("bb"));
            }
            logD("aa-" + object1.getString("aa") + "+bb+" + object1.getString("bb"));
        } catch (JSONException e) {
            logE(e.toString());
        }
    }
}
