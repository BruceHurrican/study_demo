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

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
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

/**
 * 服务端
 * Created by BruceHurrican on 2015/10/10.
 */
public class ServerFragment extends BaseFragment {
    private BluetoothAdapter mBluetoothAdapter;
    private TextView output;
    private Button btn_start;
    private UIHandler handler;

    public ServerFragment() {
    }

    @Override
    public String getTAG() {
        return null;
    }

    @Override
    public void handleUIMessage(Message msg) {
        output.append(msg.getData().getString("msg"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gh_bt_fragment_server, container, false);
        output = (TextView) view.findViewById(R.id.tv_gh_bt_sv_output);
        btn_start = (Button) view.findViewById(R.id.btn_gh_bt_start_server);
        initUIHandler();
        handler = getUIHandler();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (null == mBluetoothAdapter) {
            output.append("没有蓝牙设备\n");
            btn_start.setEnabled(false);
        }

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.append("Starting server\n");
                startServer();
            }
        });
        return view;
    }

    public void mkmsg(String string) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("msg", string);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    public void startServer() {
        new Thread(new AcceptThread()).start();
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * or until cancelled.
     */
    private class AcceptThread extends Thread {
        private BluetoothServerSocket mmBluetoothServerSocket;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;
            try {
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(BTDemo1Activity.NAME, BTDemo1Activity.MY_UUID);
            } catch (IOException e) {
                logE(e.toString());
                mkmsg("Failed to start server\n");
            }
            mmBluetoothServerSocket = tmp;
        }

        @Override
        public void run() {
            mkmsg("waiting on accept");
            BluetoothSocket socket = null;
            try {
                socket = mmBluetoothServerSocket.accept();
            } catch (IOException e) {
                logE(e.toString());
                mkmsg("Failed to accept\n");
            }
            if (null != socket) {
                mkmsg("Connection made\n");
                mkmsg("Remote device address: " + socket.getRemoteDevice().toString() + "\n");
                try {
                    mkmsg("Attempting to receive a message ...\n");
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String str = in.readLine();
                    mkmsg("received a message: \n" + str + "\n");
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    mkmsg("Attempting to send message ...\n");
                    out.println("Response from Bluetooth Demo Server");
                    out.flush();
                    mkmsg("Message send ...\n");
                    mkmsg("We are done, closing connection\n");
                } catch (IOException e) {
                    logE(e.toString());
                    mkmsg("Error happened sending/receiving\n");
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        logE(e.toString());
                        mkmsg("Unable to close socket " + e.getMessage() + "\n");
                    }
                }
            } else {
                mkmsg("made connection,but socket is null\n");
            }
            mkmsg("Server ending \n");
        }

        public void cancel() {
            try {
                mmBluetoothServerSocket.close();
            } catch (IOException e) {
                logE(e.toString());
            }
        }
    }
}
