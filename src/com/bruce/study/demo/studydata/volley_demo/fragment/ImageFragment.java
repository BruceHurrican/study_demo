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

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.bruce.study.demo.DemoApplication;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;
import com.bruce.study.demo.studydata.volley_demo.utils.VolleyBitmapCache;
import com.bruce.study.demo.studydata.volley_demo.widget.MyNetWorkImageView;

/**
 * 图片 Fragment
 * Created by BruceHurrican on 2015/9/13.
 */
public class ImageFragment extends BaseFragment implements View.OnClickListener {
    // 获取github demo 头像图片
    private static final String okURL = "https://www.baidu.com/img/bd_logo1.png";
    private static final String wrongURL = "http://www.baidu.com/123.jpg";
    private boolean isLoadOkURL;
    private TextView tv_volley_fragment_image;
    private ImageView iv_volley_fragment_image;
    private NetworkImageView niv_volley_fragment;
    private MyNetWorkImageView niv_volley_fragment2;
    private Button btn_volley_get_image, btn_volley_get_image_loader, btn_volley_get_image_network, btn_volley_get_image_network2;
    private ImageLoader loader;
    private String requestTag = "";

    @Override
    public String getTAG() {
        return "VolleyDemo-ImageFragment-->";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logD("onCreateView");
        return inflater.inflate(R.layout.volley_fragment_image, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logD("onActivityCreated");
        btn_volley_get_image.setOnClickListener(this);
        btn_volley_get_image_loader.setOnClickListener(this);
        btn_volley_get_image_network.setOnClickListener(this);
        btn_volley_get_image_network2.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logD("onViewCreated");
        tv_volley_fragment_image = (TextView) view.findViewById(R.id.tv_volley_fragment_image);
        iv_volley_fragment_image = (ImageView) view.findViewById(R.id.iv_volley_fragment_image);
        niv_volley_fragment = (NetworkImageView) view.findViewById(R.id.niv_volley_fragment);
        niv_volley_fragment2 = (MyNetWorkImageView) view.findViewById(R.id.niv_volley_fragment2);
        btn_volley_get_image = (Button) view.findViewById(R.id.btn_volley_get_image);
        btn_volley_get_image_loader = (Button) view.findViewById(R.id.btn_volley_get_image_loader);
        btn_volley_get_image_network = (Button) view.findViewById(R.id.btn_volley_get_image_network);
        btn_volley_get_image_network2 = (Button) view.findViewById(R.id.btn_volley_get_image_network2);
        isLoadOkURL = true;
        VolleyBitmapCache bitmapCache = new VolleyBitmapCache();
        loader = new ImageLoader(DemoApplication.getHttpQueues(), bitmapCache);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_volley_get_image:
                if (isLoadOkURL) {
                    logD("获取图片方法成功调用--okURL");
                    getImage(okURL);
                    isLoadOkURL = false;
                } else {
                    logD("获取图片方法成功调用--wrongURL");
                    getImage(wrongURL);
                    isLoadOkURL = true;
                }
                break;
            case R.id.btn_volley_get_image_loader:
                if (isLoadOkURL) {
                    logD("获取并且缓存图片方法成功调用--okURL");
                    getImageAndBufferImage(okURL);
                    isLoadOkURL = false;
                } else {
                    logD("获取并且缓存图片方法成功调用--wrongURL");
                    getImageAndBufferImage(wrongURL);
                    isLoadOkURL = true;
                }
                break;
            case R.id.btn_volley_get_image_network:
                if (isLoadOkURL) {
                    logD("使用NetworkImageView获取图片方法成功调用--okURL");
                    getNetworkImage(okURL);
                    isLoadOkURL = false;
                } else {
                    logD("使用NetworkImageView获取图片方法成功调用--wrongURL");
                    getImageAndBufferImage(wrongURL);
                    isLoadOkURL = true;
                }
                break;
            case R.id.btn_volley_get_image_network2:
                if (isLoadOkURL) {
                    logD("使用MyNetworkImageView获取图片方法成功调用--okURL");
                    getMyNetworkImage(okURL);
                    isLoadOkURL = false;
                } else {
                    logD("使用MyNetworkImageView获取图片方法成功调用--wrongURL");
                    getImageAndBufferImage(wrongURL);
                    isLoadOkURL = true;
                }
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

    /**
     * 获取图片
     *
     * @param url 下载图片地址
     */
    private void getImage(String url) {
        tv_volley_fragment_image.setText("开始获取图片");
        iv_volley_fragment_image.setImageBitmap(null);
        niv_volley_fragment.setImageBitmap(null);
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                tv_volley_fragment_image.setText("加载图片成功");
                iv_volley_fragment_image.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv_volley_fragment_image.setText("加载图片失败");
                iv_volley_fragment_image.setImageBitmap(null);
                logE(volleyError.toString());
            }
        });
        requestTag = getTAG();
        request.setTag(requestTag);
        DemoApplication.getHttpQueues().add(request);
    }

    /**
     * 获取并且缓存图片
     *
     * @param url 下载图片地址
     */
    private void getImageAndBufferImage(String url) {
        tv_volley_fragment_image.setText("开始并缓存图片");
        iv_volley_fragment_image.setImageBitmap(null);
        niv_volley_fragment.setImageBitmap(null);
        niv_volley_fragment2.setImageBitmap(null);
//        ImageLoader loader = new ImageLoader(DemoApplication.getHttpQueues(), new VolleyBitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv_volley_fragment_image, R.drawable.icon_workdemo, R.drawable.ic_launcher);
        loader.get(url, listener);
    }

    /**
     * 使用谷歌图片下载框架 NetworkImageView
     *
     * @param url 下载图片地址
     */
    private void getNetworkImage(String url) {
        tv_volley_fragment_image.setText("使用NetworkImageView开始加载图片");
        iv_volley_fragment_image.setImageBitmap(null);
        niv_volley_fragment.setImageBitmap(null);
        niv_volley_fragment2.setImageBitmap(null);
//        ImageLoader loader = new ImageLoader(DemoApplication.getHttpQueues(), new VolleyBitmapCache());
        niv_volley_fragment.setErrorImageResId(R.drawable.ic_launcher);
        niv_volley_fragment.setDefaultImageResId(R.drawable.icon_workdemo);
        niv_volley_fragment.setImageUrl("", loader); // 此处传入空url目的是取消之前的网络请求，否则会造成后面调用该方法时图片无法加载
        niv_volley_fragment.setImageUrl(url, loader);
    }

    /**
     * 使用自定义图片下载框架 MyNetworkImageView
     *
     * @param url 下载图片地址
     */
    private void getMyNetworkImage(String url) {
        tv_volley_fragment_image.setText("使用MyNetworkImageView开始加载图片");
        iv_volley_fragment_image.setImageBitmap(null);
        niv_volley_fragment2.setImageBitmap(null);
//        ImageLoader loader = new ImageLoader(DemoApplication.getHttpQueues(), new VolleyBitmapCache());
        niv_volley_fragment2.setErrorImageResId(R.drawable.ic_launcher);
        niv_volley_fragment2.setDefaultImageResId(R.drawable.icon_workdemo);
        niv_volley_fragment2.setImageUrl(url, loader);
    }
}
