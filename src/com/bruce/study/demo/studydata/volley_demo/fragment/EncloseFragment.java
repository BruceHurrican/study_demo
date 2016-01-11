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

package com.bruce.study.demo.studydata.volley_demo.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.bruce.study.demo.DemoApplication;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;
import com.bruce.study.demo.studydata.volley_demo.utils.MyVolleyListener;
import com.bruce.study.demo.studydata.volley_demo.utils.VolleyRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 针对Volley二次封装
 * Created by BruceHurrican on 2015/9/13.
 */
public class EncloseFragment extends BaseFragment implements View.OnClickListener {
    private Button btn_volley_get_string, btn_volley_post_string;
    private TextView tv_volley_fragment_enclose;
    private String requestTag = "";

    @Override
    public String getTAG() {
        return "VolleyDemo-EncloseFragment-->";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logD("onCreateView");
        return inflater.inflate(R.layout.volley_fragment_enclose, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        logD("onActivityCreated");
        btn_volley_get_string.setOnClickListener(this);
        btn_volley_post_string.setOnClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        logD("onViewCreated");
        tv_volley_fragment_enclose = (TextView) view.findViewById(R.id.tv_volley_fragment_enclose);
        btn_volley_get_string = (Button) view.findViewById(R.id.btn_volley_get_string);
        btn_volley_post_string = (Button) view.findViewById(R.id.btn_volley_post_string);
        requestTag = getTAG();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_volley_get_string:
                method_GET_String(true);
//                    method_GET_String(false);
                break;
            case R.id.btn_volley_post_string:
                method_POST_String(true);
//                    method_POST_String(false);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (TextUtils.isEmpty(requestTag)) {
            DemoApplication.getHttpQueues().cancelAll(requestTag);
        }
    }

    private void method_GET_String(boolean isOKUrl) {
        tv_volley_fragment_enclose.setText("调用封装Get String");
        String okUrl = "http://ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166";
        String wrongUrl = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166";
        VolleyRequest.requestGET(isOKUrl ? okUrl : wrongUrl, requestTag, new MyVolleyListener() {
            @Override
            public void onMySuccessListener(String result) {
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                    tv_volley_fragment_enclose.setText("GET_result=" + json.toString());
                } catch (JSONException e) {
                    logE(e.toString());
                    tv_volley_fragment_enclose.setText("GET获取数据JSON异常");
                }
            }

            @Override
            public void onMyErrorListener(VolleyError error) {
                tv_volley_fragment_enclose.setText("网络请求GET异常-" + error.toString());
            }
        });
    }

    private void method_POST_String(boolean isOKUrl) {
        tv_volley_fragment_enclose.setText("调用封装Post String");
        String okUrl = "http://ip.taobao.com/service/getIpInfo.php?";
        String wrongUrl = "ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166";
        Map<String, String> map = new HashMap<String, String>(5);
        map.put("ip", "202.96.128.166");
        VolleyRequest.requestPOST(isOKUrl ? okUrl : wrongUrl, requestTag, map, new MyVolleyListener() {
            @Override
            public void onMySuccessListener(String result) {
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                    tv_volley_fragment_enclose.setText("POST_result=" + json.toString());
                } catch (JSONException e) {
                    logE(e.toString());
                    tv_volley_fragment_enclose.setText("POST获取数据JSON异常");
                }
            }

            @Override
            public void onMyErrorListener(VolleyError error) {
                tv_volley_fragment_enclose.setText("网络请求POST异常-" + error.toString());
            }
        });
    }
}
