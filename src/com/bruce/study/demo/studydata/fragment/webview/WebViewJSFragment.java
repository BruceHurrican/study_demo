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
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;
import com.bruce.study.demo.utils.LogUtils;

/**
 * 测试 webview 和 js交互
 * Created by BruceHurrican on 2015/12/8.
 */
public class WebViewJSFragment extends BaseFragment {
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.sv)
    ScrollView sv;

    @Override
    public String getTAG() {
        return "WebViewJSFragment->";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview_js, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                logI("当前加载进度+" + newProgress);
            }
        });
        webview.loadUrl("file:///android_asset/jstest.html");
//        webview.loadUrl("http://192.168.0.25:8080/JL_BT_LOCK/page/style/login.jsp");
        webview.addJavascriptInterface(this, "androiddemo");
    }

    @JavascriptInterface
    public void method() {
        showToastShort("231");
    }

    @JavascriptInterface
    public void method(String msg) {
        showToastShort(msg);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        LogUtils.i("当前线程是：" + Thread.currentThread());
//        LogUtils.log2file(LogUtils.PATH_LOG_INFO, "bruce", "当前线程是：" + Thread.currentThread());
//        webview.loadUrl("javascript:showResult('20','haha你好','ddaa123')");
//        webview.loadUrl("javascript:aa('haha你好','虎虎人')");
        String aa = "网页交互测试";
        webview.loadUrl("javascript:jsmethod1()");
        webview.loadUrl(String.format("javascript:jsmethod2('%s')", aa));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
