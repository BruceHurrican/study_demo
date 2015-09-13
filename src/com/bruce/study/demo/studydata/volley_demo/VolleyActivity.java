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

package com.bruce.study.demo.studydata.volley_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import com.bruce.study.demo.DemoApplication;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragmentActivity;
import com.bruce.study.demo.studydata.volley_demo.fragment.EncloseFragment;
import com.bruce.study.demo.studydata.volley_demo.fragment.ImageFragment;

/**
 * volley 练习
 * Created by BruceHurrican on 2015/9/13.
 */
public class VolleyActivity extends BaseFragmentActivity implements View.OnClickListener {
    private String requestTag = "";
    private FragmentManager fragmentManager;

    @Override
    public String getTAG() {
        return "VolleyActivity -->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_activity);
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (TextUtils.isEmpty(requestTag)) {
            DemoApplication.getHttpQueues().cancelAll(requestTag);
        }
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.btn_volley_image:
                fragment = new ImageFragment();
                break;
            case R.id.btn_volley_enclose:
                fragment = new EncloseFragment();
                break;
        }
        if (null != fragment) {
            switchFragment(fragment);
        }
    }


    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_volley, fragment);
        transaction.commit();
    }
}
