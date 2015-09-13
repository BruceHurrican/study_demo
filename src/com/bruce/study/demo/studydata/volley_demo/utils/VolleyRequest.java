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

package com.bruce.study.demo.studydata.volley_demo.utils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bruce.study.demo.DemoApplication;

import java.util.Map;

/**
 * 封装Volley网络请求
 * Created by BruceHurrican on 2015/9/13.
 */
public final class VolleyRequest {
    private VolleyRequest() {

    }

    /**
     * 封装GET方法
     *
     * @param url
     * @param tag
     * @param listener
     */
    public static void requestGET(String url, String tag, MyVolleyListener listener) {
        DemoApplication.getHttpQueues().cancelAll(tag);
//        stringRequest = new StringRequest(Request.Method.GET,url,listener.onMySuccess(),listener.onMyError());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onMySuccessListener(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onMyErrorListener(error);
            }
        });
        stringRequest.setTag(tag);
        DemoApplication.getHttpQueues().add(stringRequest);
    }

    /**
     * 封装POST方法
     *
     * @param url
     * @param tag
     * @param params   为Map 类型，按照google推荐的性能优化方案， 调用该方法时应传入 ArrayMap类型的参数
     * @param listener
     */
    public static void requestPOST(String url, String tag, Map<String, String> params, MyVolleyListener listener) {
        DemoApplication.getHttpQueues().cancelAll(tag);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onMySuccessListener(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onMyErrorListener(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
//        stringRequest = new StringRequest(Request.Method.POST,url,listener.onMySuccess(),listener.onMyError()){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//        };
        stringRequest.setTag(tag);
        DemoApplication.getHttpQueues().add(stringRequest);
    }
}
