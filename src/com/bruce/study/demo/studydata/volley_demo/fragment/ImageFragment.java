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

package com.bruce.study.demo.studydata.volley_demo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
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

/**
 * 图片 Fragment
 * Created by BruceHurrican on 2015/9/13.
 */
public class ImageFragment extends BaseFragment implements View.OnClickListener {
    // 获取github demo 头像图片
    private final String okURL = "https://avatars0.githubusercontent.com/u/8604716?v=3&s=460";
    private final String wrongURL = "http://www.baidu.com/123.jpg";
    private boolean isLoadOkURL;
    private VolleyBitmapCache bitmapCache;
    private TextView tv_volley_fragment_image;
    private ImageView iv_volley_fragment_image;
    private NetworkImageView niv_volley_fragment;
    private Button btn_volley_get_image, btn_volley_get_image_loader, btn_volley_get_image_network;
    private ImageLoader loader;

    @Override
    public String getTAG() {
        return "VolleyDemo-ImageFragment";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logD("onCreateView");
        return inflater.inflate(R.layout.volley_fragment_image, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        logD("onActivityCreated");
        btn_volley_get_image.setOnClickListener(this);
        btn_volley_get_image_loader.setOnClickListener(this);
        btn_volley_get_image_network.setOnClickListener(this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        logD("onViewCreated");
        tv_volley_fragment_image = (TextView) view.findViewById(R.id.tv_volley_fragment_image);
        iv_volley_fragment_image = (ImageView) view.findViewById(R.id.iv_volley_fragment_image);
        niv_volley_fragment = (NetworkImageView) view.findViewById(R.id.niv_volley_fragment);
        btn_volley_get_image = (Button) view.findViewById(R.id.btn_volley_get_image);
        btn_volley_get_image_loader = (Button) view.findViewById(R.id.btn_volley_get_image_loader);
        btn_volley_get_image_network = (Button) view.findViewById(R.id.btn_volley_get_image_network);
        isLoadOkURL = true;
        bitmapCache = new VolleyBitmapCache();
        loader = new ImageLoader(DemoApplication.getHttpQueues(), bitmapCache);
        super.onViewCreated(view, savedInstanceState);
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
//        ImageLoader loader = new ImageLoader(DemoApplication.getHttpQueues(), new VolleyBitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv_volley_fragment_image, R.drawable.icon_workdemo, R.drawable.ic_launcher);
        loader.get(url, listener);
    }

    /**
     * 使用谷歌图片下载框架 NetworkImageView
     * TODO 多次加载图片会失败
     *
     * @param url 下载图片地址
     */
    private void getNetworkImage(String url) {
        tv_volley_fragment_image.setText("使用NetworkImageView开始加载图片");
        iv_volley_fragment_image.setImageBitmap(null);
        niv_volley_fragment.setImageBitmap(null);
//        ImageLoader loader = new ImageLoader(DemoApplication.getHttpQueues(), new VolleyBitmapCache());
        niv_volley_fragment.setErrorImageResId(R.drawable.ic_launcher);
        niv_volley_fragment.setDefaultImageResId(R.drawable.icon_workdemo);
        niv_volley_fragment.setImageUrl(url, loader);
    }
}
