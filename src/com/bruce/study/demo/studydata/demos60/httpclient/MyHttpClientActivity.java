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

package com.bruce.study.demo.studydata.demos60.httpclient;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 自定义网络请求 练习
 * Created by BruceHurrican on 2015/9/12.
 */
public class MyHttpClientActivity extends BaseActivity {
    private static final String ADDRESS = "http://www.baidu.com";
    private TextView tv_info;
    private ByteArrayBuffer byteArrayBuffer;

    @Override
    public String getTAG() {
        return "MyHttpClientActivity -->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos60_activity_httpclient);
        Button btn_connect = (Button) findViewById(R.id.btn_connect);
        tv_info = (TextView) findViewById(R.id.tv_info);
        initWorkerHandler("自定义网络请求");
        initUIHandler();
//        getWorkerHandler();
//        getUIHandler();
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHttpClientActivity.this.sendWorkerMessageEmpty(0x01);
            }
        });
    }

    @Override
    public void handleWorkerMessage(Message msg) {
        if (msg.what == 0x01){
            logD("workhandler 成功执行");
            getNetInfo();
        }
    }

    @Override
    public void handleUIMessage(Message msg) {
        if (msg.what == 0x02){
            logD("UIHandler 成功执行");
            tv_info.setText(EncodingUtils.getString(byteArrayBuffer.toByteArray(), "UTF-8"));
        }
    }

    /**
     * 获取网络信息，需在子线程中进行
     */
    private void getNetInfo() {
        DataInputStream dataInputStream = null;
        try {
            URL url = new URL(ADDRESS); // 服务器地址
            URLConnection urlConnection = url.openConnection(); // http连接
            // 获取输入流
            dataInputStream = new DataInputStream(urlConnection.getInputStream());
            // 获取输出流
//                    DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream())
            // 获取服务器返回的数据
            int temp = 0;
            byteArrayBuffer = new ByteArrayBuffer(1000);
            while ((temp = dataInputStream.read()) != -1) {
                byteArrayBuffer.append(temp);
            }
            // 将服务器返回的信息显示
            MyHttpClientActivity.this.sendUIMessageEmpty(0x02);
        } catch (MalformedURLException e) {
            logE(e.toString());
        } catch (IOException e) {
            logE(e.toString());
        } finally {
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                logE(e.toString());
            }
        }
    }
}
