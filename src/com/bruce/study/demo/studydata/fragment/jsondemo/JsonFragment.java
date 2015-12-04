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

package com.bruce.study.demo.studydata.fragment.jsondemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;
import com.bruce.study.demo.utils.JsonUtils;

/**
 * 解析json数据
 * Created by BruceHurrican on 2015/12/4.
 */
public class JsonFragment extends BaseFragment implements View.OnClickListener {
    private TextView tv_data;
    private JsonUtils.JsonData jsondata;

    @Override
    public String getTAG() {
        return "JsonFragment->";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_json, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_key = (Button) view.findViewById(R.id.btn_key);
        Button btn_data = (Button) view.findViewById(R.id.btn_data);
        tv_data = (TextView) view.findViewById(R.id.tv_data);
        jsondata = JsonUtils.getJsonData(getActivity(), "jsondemo.json");
        btn_key.setOnClickListener(this);
        btn_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_key:
                tv_data.setText(jsondata.keyMap.toString());
                logI(jsondata.keyMap.toString());
                break;
            case R.id.btn_data:
                tv_data.setText(jsondata.data.toString());
                logI(jsondata.data.toString());
                break;
        }
    }
}
