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
package com.bruce.study.demo.github.bluetooth_demo1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragmentActivity;

import java.util.UUID;

/**
 * github蓝牙练习1
 * Created by BruceHurrican on 2015/10/9.
 * depend on
 */
public class BTDemo1Activity extends BaseFragmentActivity implements HelpFragment.Callbacks {
    public static final UUID MY_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    public static final String NAME = "BluetoothDemo";
    FragmentManager fragmentManager;

    @Override
    public String getTAG() {
        return "BTDemo1Activity -->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gh_bt_activity);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_gh_bt_container, new HelpFragment()).commit();
    }

    @Override
    public void onButtonSelected(int id) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (id == 2) { // client
            transaction.replace(R.id.fl_gh_bt_container, new ClientFragment());
        } else {
            transaction.replace(R.id.fl_gh_bt_container, new ServerFragment());
        }
        // and add the transaction to the back stack so the user can navigate back
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
