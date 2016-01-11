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

package com.bruce.study.demo.studydata.bluetooth_demo1;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

import java.util.Set;

/**
 * 蓝牙练习1
 * Created by BruceHurrican on 2015/10/6.
 */
public class BlueTooth1Activity extends BaseActivity {

    public static final int REQ_BLUETOOTH = 0x001;

    @Override
    public String getTAG() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        Button btn = new Button(this);
        btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btn.setText("蓝牙测试1");
        ll.addView(btn);
        TextView tv = new TextView(this);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setText("蓝牙设备");
        ll.addView(tv);
        ListView lv = new ListView(this);
        lv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ll.addView(lv);
        setContentView(ll);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.main_item);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        btn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter == null) {
                    logD("当前设备不支持蓝牙");
                } else {
                    logD("当前设备支持蓝牙,蓝牙设备状态--" + (bluetoothAdapter.isEnabled() ? "已打开" : "未打开"));
                    if (!bluetoothAdapter.isEnabled()) {
                        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(intent, REQ_BLUETOOTH);
                    } else {
                        adapter.clear();
                        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                        logD("当前设备是否支持低功耗蓝牙-->" + (getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE) ? "支持" : "不支持"));
                        logD("当前已连接蓝牙设备数量为：" + pairedDevices.size());
                        if (pairedDevices.size() > 0) {
                            for (BluetoothDevice device : pairedDevices) {
                                adapter.add(device.getName() + "--" + device.getAddress() + "--" + device.getBondState() + "--" + device.getType());
                                logD(device.getName() + "--" + device.getAddress() + "--" + device.getBondState() + "--" + device.getType());
                                for (int i = 0; i < device.getUuids().length; i++) {
                                    logD("UUID--" + device.getUuids()[i]);
                                }
                            }
                            lv.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        logD("requestCode--" + requestCode + "-resultCode-" + resultCode);
        // resultCode 为0表示用户拒绝打开蓝牙，为-1表示用户同意打开蓝牙
        // 注意 此resultCode对应的0和-1是针对红米note上的测试结果，其他机型可能不是这个数字
        if (requestCode == REQ_BLUETOOTH && resultCode == -1) {
            logD("打开蓝牙成功");
        }
    }
}
