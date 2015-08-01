/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some lines from Internet. Everyone can download and use for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.studydata.demos60.baseadapter_project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseAdapter 练习
 * Created by BruceHurrican on 2015/7/18.
 */
public class BaseAdapterActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String STRING_FORMAT_1 = "点击了第 %S 个选项item";
    private static final String STRING_FORMAT_2 = "点击了第 %s 个按钮btn";

    @Override
    public String getTAG() {
        return "BaseAdapterActivity -->";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos60_activity_baseadapterproject);
        ListView lv_base_adapter = (ListView) findViewById(R.id.lv_base_adapter);
        List<MyListItemAttr> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            list.add(new MyListItemAttr(R.drawable.demos60_ic_baseadapter_1, "AA-" + i, "AA按钮-" + i));
            list.add(new MyListItemAttr(R.drawable.demos60_ic_baseadapter_2, "BB-" + i, "BB按钮-" + i));
            list.add(new MyListItemAttr(R.drawable.demos60_ic_baseadapter_3, "CC-" + i, "CC按钮-" + i));
        }
        MyAdapter adapter = new MyAdapter(this, list);
        lv_base_adapter.setAdapter(adapter);
        lv_base_adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, String.format(STRING_FORMAT_1, position), Toast.LENGTH_SHORT).show();
        logD(String.format(STRING_FORMAT_1, position));
    }

    private static class MyListItemAttr {
        public int imgID;
        public String text;
        public String btnText;

        public MyListItemAttr(int imgID, String text, String btnText) {
            this.imgID = imgID;
            this.text = text;
            this.btnText = btnText;
        }
    }

    private class MyAdapter extends BaseAdapter implements View.OnClickListener {
        private Context context;
        private List<MyListItemAttr> list;

        public MyAdapter(Context context, List<MyListItemAttr> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return null == list ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return null == list ? null : list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 实例化布局与组件以及设置组件数据
         *
         * @param position    绘制的位置
         * @param convertView 绘制的视图，这里指的是ListView中每一项的布局
         * @param parent      view 的父布局，这里这需要
         * @return view
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (null == convertView) {
                convertView = LayoutInflater.from(context).inflate(R.layout.demos60_item_baseadapterproject, null);
                viewHolder = new ViewHolder();
                viewHolder.iv_base_adapter = (ImageView) convertView.findViewById(R.id.iv_item_base_adapter);
                viewHolder.tv_base_adapter = (TextView) convertView.findViewById(R.id.tv_item_base_adapter);
                viewHolder.btn_base_adapter = (Button) convertView.findViewById(R.id.btn_item_base_adapter);
                viewHolder.btn_base_adapter.setOnClickListener(this);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.iv_base_adapter.setImageResource(list.get(position).imgID);
            viewHolder.tv_base_adapter.setText(list.get(position).text);
            viewHolder.btn_base_adapter.setText(list.get(position).btnText);
            viewHolder.btn_base_adapter.setTag(position);
            return convertView;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, String.format(STRING_FORMAT_2, v.getTag()), Toast.LENGTH_SHORT).show();
            logI(String.format(STRING_FORMAT_2, v.getTag()));
        }
    }

    private static class ViewHolder {
        private ImageView iv_base_adapter;
        private TextView tv_base_adapter;
        private Button btn_base_adapter;
    }
}
