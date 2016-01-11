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

package com.bruce.study.demo.studydata.demos60.tab_project;

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
        LayoutInflater.from(this).inflate(R.layout.demos60_activity_tabproject, tabHost.getTabContentView());
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
