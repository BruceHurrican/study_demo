/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some resources come from the Internet. Everyone can download and use it for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.studydata.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragmentActivity;
import com.bruce.study.demo.studydata.fragment.webview.WebViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * fragment 入口
 * Created by BruceHurrican on 2015/11/15.
 */
public class FragmentsActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener{
    private List< Fragment> fragments;
    private List<String> fragmentNamesList;
    private FragmentTransaction fragmentTransaction;
    @Override
    public String getTAG() {
        return "FragmentsActivity->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        initContainer();
    }

    private void initContainer() {
        fragments = new ArrayList<>(5);
        fragmentNamesList = new ArrayList<>(5);
        ListView lv_demo_list = (ListView) findViewById(R.id.lv_fragment_list);
        lv_demo_list.setAdapter(new ArrayAdapter<>(this, R.layout.main_item, fragmentNamesList));
        
        addFragment2Container(new WebViewFragment(),"WebView 练习");

        lv_demo_list.setOnItemClickListener(this);
        logI("加载 fragment 列表完成");
    }

    /**
     * @param fragment
     * @param fragmentName
     */
    private void addFragment2Container(Fragment fragment, String fragmentName) {
        fragmentNamesList.add(fragmentName);
        fragments.add(fragment);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        logI(String.format("你点击了第 %s 条Demo %s", position + 1, fragmentNamesList.get(position)));
        logI("当前线程为 -->" + Thread.currentThread());
        fragmentTransaction.replace(android.R.id.content,fragments.get(position));
        fragmentTransaction.commit();
    }
}
