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

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;

import java.util.Set;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass
 * Created by BruceHurrican on 2015/10/9.
 */
public class HelpFragment extends BaseFragment implements View.OnClickListener{
    private Callbacks mCallbacks = sDummyCallbacks;

    @Override
    public String getTAG() {
        return "HelpFragment -->";
    }

    public interface Callbacks {
        public void onButtonSelected(int id);
    }

    BluetoothAdapter mBluetoothAdapter = null;
    private static final int REQUEST_ENABLE_BT = 2;
    Button btn_client, btn_server;
    TextView logger;

    public HelpFragment() {
    }

    public void mkmsg(String msg) {
        logger.append(msg + "\n");
    }

    public void startbt() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            mkmsg("当前设备不支持蓝牙");
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            mkmsg("当前设备蓝牙已关闭");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableIntent);
        } else {
            mkmsg("当前设备蓝牙已打开");
            querypaired();
        }
    }

    public void querypaired() {
        mkmsg("查询到可配对设备：");
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            final BluetoothDevice[] blueDev = new BluetoothDevice[pairedDevices.size()];
            String item;
            int i = 0;
            for (BluetoothDevice device1 : pairedDevices) {
                blueDev[i] = device1;
                item = blueDev[i].getName() + ": " + blueDev[i].getAddress();
                mkmsg("设备号：" + item);
                i++;
            }
        } else {
            mkmsg("没有可配对设备");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gh_bt_fragment_help, container, false);
        logger = (TextView) view.findViewById(R.id.tv_gh_bt_logger1);
        btn_server = (Button) view.findViewById(R.id.btn_gh_bt1);
        btn_client = (Button) view.findViewById(R.id.btn_gh_bt2);
        btn_client.setOnClickListener(this);
        btn_server.setOnClickListener(this);
        startbt();
        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_gh_bt1:
                mCallbacks.onButtonSelected(1);
                break;
            case R.id.btn_gh_bt2:
                mCallbacks.onButtonSelected(2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_OK) {
            mkmsg("蓝牙已打开");
            querypaired();
        } else {
            mkmsg("请打开蓝牙");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onButtonSelected(int id) {
            // do noting
        }
    };
}
