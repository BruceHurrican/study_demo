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

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;

import java.io.*;
import java.util.Set;

/**
 * 客户端
 * Created by BruceHurrican on 2015/10/10.
 */
public class ClientFragment extends BaseFragment implements View.OnClickListener{
    private TextView output;
    private Button btn_start, btn_device;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothDevice device;
    private UIHandler handler;

    public ClientFragment() {
    }

    @Override
    public String getTAG() {
        return "ClientFragment-->";
    }

    public void mkmsg(String string) {
        Message msg = new Message();
        Bundle b = new Bundle();
        b.putString("msg", string);
        msg.setData(b);
        handler.sendMessage(msg);
    }

    @Override
    public void handleUIMessage(Message msg) {
        logD("update UI");
        output.append(msg.getData().getString("msg"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gh_bt_fragment_client, container, false);
        initUIHandler();
        handler = getUIHandler();
        output = (TextView) view.findViewById(R.id.tv_gh_bt_ct_output);
        btn_device = (Button) view.findViewById(R.id.btn_gh_bt_which);
        btn_start = (Button) view.findViewById(R.id.btn_gh_bt_client);
        btn_device.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        btn_start.setEnabled(false);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            output.append("没有蓝牙设备\n");
            btn_start.setEnabled(false);
            btn_device.setEnabled(false);
        }
        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_gh_bt_which:
                queryPaired();
                break;
            case R.id.btn_gh_bt_client:
                output.append("Starting clent\n");
                startClient();
                break;
        }
    }

    public void queryPaired() {
        Set<BluetoothDevice> paredDevices = mBluetoothAdapter.getBondedDevices();
        if (paredDevices.size() > 0) {
            output.append("至少有一个设备配对\n");
            BluetoothDevice[] blueDev = new BluetoothDevice[paredDevices.size()];
            String[] items = new String[blueDev.length];
            int i = 0;
            for (BluetoothDevice device1 : paredDevices) {
                blueDev[i] = device1;
                items[i] = blueDev[i].getName() + ": " + blueDev[i].getAddress();
                output.append("Device: " + items[i] + "\n");
                i++;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("选择蓝牙设备:");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which >= 0 && which < blueDev.length) {
                        device = blueDev[which];
                        btn_device.setText("device: " + blueDev[which].getName());
                        btn_start.setEnabled(true);
                    }
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void startClient() {
        if (null != device) {
            new Thread(new ConnectThread(device)).start();
        }
    }

    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either succeeds or fails.
     */
    private class ConnectThread extends Thread {
        private BluetoothSocket socket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            this.mmDevice = device;
            BluetoothSocket tmp = null;

            //Get a BluetoothSocket for a connection with the given BluetoothDevice
            try {
                tmp = device.createRfcommSocketToServiceRecord(BTDemo1Activity.MY_UUID);
            } catch (IOException e) {
                logE(e.toString());
                mkmsg("Client connection failed: " + e.getMessage().toString() + "\n");
            }
            socket = tmp;
        }

        @Override
        public void run() {
            mkmsg("Client running\n");
            // Always cancel discovery because it will slow down a connection
            mBluetoothAdapter.cancelDiscovery();

            try {
                socket.connect(); // This is a blocking call and will only return on a successful connection or an exception.
            } catch (IOException e) {
                logE(e.toString());
                mkmsg("Connect failed\n");
                try {
                    socket.close();
                    socket = null;
                } catch (IOException e1) {
                    logE(e1.toString());
                    mkmsg("unable to close() socket during connection failure: " + e1.getMessage().toString() + "\n");
                }
            }
            if (null != socket) {
                mkmsg("Connection made\n");
                mkmsg("Remote device address: " + socket.getRemoteDevice().getAddress().toString() + "\n");
                try {
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    mkmsg("Attempting to send message ...\n");
                    out.println("hello from Bluetooth Demo Client");
                    out.flush();
                    mkmsg("Message send ...\n");
                    mkmsg("Attempting to receive a message ...\n");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String str = in.readLine();
                    mkmsg("received a message:\n" + str + "\n");
                    mkmsg("We are done, closing connection\n");
                } catch (IOException e) {
                    mkmsg("Error happended sending/receiving\n");
                    logE(e.toString());
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        logE(e.toString());
                        mkmsg("Unable to close socket" + e.getMessage() + "\n");
                    }
                }
            } else {
                mkmsg("Made connection, but socket is null \n");
            }
            mkmsg("Client ending \n");
        }

        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                logE(e.toString());
                mkmsg("close() of connect failed: " + e.getMessage().toString() + "\n");
            }
        }
    }
}
