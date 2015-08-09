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

package com.bruce.study.demo.util_demo;

import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.utils.PublicUtil;
import com.bruce.study.demo.utils.StorageUtil;

/**
 * 获取手机设备信息、手机中所有应用的每个应用的包名和权限名称
 * 、查询手机内所有支持分享的应用、获取手机内非系统应用、判断手机是否已经root、获取屏幕宽高(像素)密度
 * 、判断当前手机是否联网、调用系统浏览器打开网址
 * Created by BruceHurrican on 2015/7/11.
 */
public class PhoneInfoActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_utildemo;

    @Override
    public String getTAG() {
        return "PhoneInfoActivity -->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.utildemo_activity);
        findViewById(R.id.btn_utildemo_build_factory);
        findViewById(R.id.btn_utildemo_package_permission);
        findViewById(R.id.btn_utildemo_share_apps);
        findViewById(R.id.btn_utildemo_non_system_app);
        findViewById(R.id.btn_utildemo_root_permission);
        findViewById(R.id.btn_utildemo_screen);
        findViewById(R.id.btn_utildemo_network);
        findViewById(R.id.btn_utildemo_browser);
        findViewById(R.id.btn_utildemo_sdcardinfo);
        tv_utildemo = (TextView) findViewById(R.id.tv_utildemo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_utildemo_build_factory:
                tv_utildemo.setText(PublicUtil.getPhoneInfo(PhoneInfoActivity.this));
                break;
            case R.id.btn_utildemo_package_permission:
                tv_utildemo.setText(PublicUtil.getAllAppPackageNameAndPermission(PhoneInfoActivity.this));
                break;
            case R.id.btn_utildemo_share_apps:
                tv_utildemo.setText(PublicUtil.getShareApps(PhoneInfoActivity.this));
                break;
            case R.id.btn_utildemo_non_system_app:
                tv_utildemo.setText(PublicUtil.getAllNonSystemApps(PhoneInfoActivity.this));
                break;
            case R.id.btn_utildemo_root_permission:
                tv_utildemo.setText(PublicUtil.isRootSystem());
                break;
            case R.id.btn_utildemo_screen:
                tv_utildemo.setText(PublicUtil.getScreenInPixels(PhoneInfoActivity.this));
                break;
            case R.id.btn_utildemo_network:
                tv_utildemo.setText(PublicUtil.isNetWorkAvailable(PhoneInfoActivity.this));
                break;
            case R.id.btn_utildemo_browser:
                openSystemBrowser();
                break;
            case R.id.btn_utildemo_sdcardinfo:
                tv_utildemo.setText(getStorageInfo());
                break;
        }
    }

    /**
     * 调用系统浏览器打开百度
     */
    public void openSystemBrowser() {
        PublicUtil.openSystemBrowser(PhoneInfoActivity.this, "https://www.baidu.com");
    }

    /**
     * 获取手机储存信息
     *
     * @return String
     */
    private String getStorageInfo() {
//        return "SDCard 是否存在 ?" + (StorageUtil.isSDcardExists() ? "SDCard 存在" : "SDCard 不存在") + "\n手机内部总储存空间-" +
//                (StorageUtil.getTotalInternalMemorySize() >> 10 >> 10) + " MB\n手机内部剩余储存空间-" + (StorageUtil
//                .getAvailableInternalMemorySize() >> 10 >> 10) + " MB\n手机SDCard 总储存空间-" + (StorageUtil
//                .getTotalExternalMemorySize() >> 10 >> 10) + " MB\n手机SDCard 剩余储存空间-" + (StorageUtil
//                .getAvailableExternalMemorySize() >> 10 >> 10) + " MB";
        return "SDCard 是否存在 ?" + (StorageUtil.isSDcardExists() ? "SDCard 存在" : "SDCard 不存在") +
                "\n手机内部总储存空间-" + Formatter.formatFileSize(this, StorageUtil.getTotalInternalMemorySize()) +
                "\n手机内部剩余储存空间-" + Formatter.formatFileSize(this, StorageUtil.getAvailableInternalMemorySize())
                + "\n手机SDCard 总储存空间-" + Formatter.formatFileSize(this, StorageUtil.getTotalExternalMemorySize())
                + "\n手机SDCard 剩余储存空间-" + Formatter.formatFileSize(this, StorageUtil.getAvailableExternalMemorySize());
    }
}
