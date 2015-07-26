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

package com.bruce.study.demo.studydata.tab_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import com.bruce.study.demo.R;
import com.bruce.study.demo.log.Logs;

/**
 * TabActivity 练习
 * Created by BruceHurrican on 2015/7/5.
 */
public class MyTabActivity extends android.app.TabActivity {
    public static final String TAG = "TabActivity -- >";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = this.getTabHost(); // 分页菜单(tab的容器)
        // 利用 LayoutInflater 将布局与分页菜单一起显示
        LayoutInflater.from(this).inflate(R.layout.studydata_activity_tabproject, tabHost.getTabContentView());
        TabHost.TabSpec ts1 = tabHost.newTabSpec("tabOne");
        ts1.setIndicator("Tab01"); // 设置此分页显示的标题
        ts1.setContent(R.id.btn_tabproject); // 设置此分页的资源id
        // 设置此分页显示的标题和图标
        TabHost.TabSpec ts2 = tabHost.newTabSpec("tabTwo");
        ts2.setIndicator("Tab02", getResources().getDrawable(R.drawable.studydata_tabproject_icon));
        ts2.setContent(R.id.et_tabproject);
        TabHost.TabSpec ts3 = tabHost.newTabSpec("tabThree");
        ts3.setIndicator("Tab03");
        ts3.setContent(R.id.ll_tabproject);
        tabHost.addTab(ts1);
        tabHost.addTab(ts2);
        tabHost.addTab(ts3);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("tabOne")) {
                    Logs.i(TAG, "分页1");
                } else if (tabId.equals("tabTwo")) {
                    Logs.i(TAG, "分页2");
                } else if (tabId.equals("tabThree")) {
                    Logs.i(TAG, "分页3");
                }
            }
        });
    }

}
