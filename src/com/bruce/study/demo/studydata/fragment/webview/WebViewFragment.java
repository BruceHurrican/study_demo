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

package com.bruce.study.demo.studydata.fragment.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;

/**
 * WebView 练习
 * Created by BruceHurrican on 2015/11/15.
 */
public class WebViewFragment extends BaseFragment implements View.OnClickListener {
    public static final String URL_LOCA = "http://192.168.31.127:8080/demos/";
    private WebView webView;
    private Button btn_local_1, btn_local_2, btn_local_3, btn_remote_1, btn_remote_2, btn_remote_3;

    @Override
    public String getTAG() {
        return "WebViewFragment->";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = (WebView) view.findViewById(R.id.webview);
        btn_local_1 = (Button) view.findViewById(R.id.btn_local_1);
        btn_local_2 = (Button) view.findViewById(R.id.btn_local_2);
        btn_local_3 = (Button) view.findViewById(R.id.btn_local_3);
        btn_remote_1 = (Button) view.findViewById(R.id.btn_remote_1);
        btn_remote_2 = (Button) view.findViewById(R.id.btn_remote_2);
        btn_remote_3 = (Button) view.findViewById(R.id.btn_remote_3);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
////                super.onProgressChanged(view, newProgress);
//               getActivity().setProgress(newProgress);
//            }
//        });
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_local_1.setOnClickListener(this);
        btn_local_2.setOnClickListener(this);
        btn_local_3.setOnClickListener(this);
        btn_remote_1.setOnClickListener(this);
        btn_remote_2.setOnClickListener(this);
        btn_remote_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_local_1:
                webView.loadUrl("file:///android_asset/h5canvas_fruit_ninja/index.html");
                break;
            case R.id.btn_local_2:
                webView.loadUrl("file:///android_asset/h5canvas_leaves/index.html");
                break;
            case R.id.btn_local_3:
                webView.loadUrl("file:///android_asset/h5canvas_loading/index.html");
                break;
            case R.id.btn_remote_1:
                webView.loadUrl(URL_LOCA + "h5canvas_fruit_ninja/index.html");
                break;
            case R.id.btn_remote_2:
                webView.loadUrl(URL_LOCA + "h5canvas_leaves/index.html");
                break;
            case R.id.btn_remote_3:
                webView.loadUrl(URL_LOCA + "h5canvas_loading/index.html");
                break;
        }
        logI("当前加载URL->" + webView.getUrl());
    }
}
