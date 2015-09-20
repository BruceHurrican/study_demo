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

package com.bruce.study.demo.studydata.recyclerview_demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 练习
 * 参照慕课网练习
 * Created by BruceHurrican on 2015/9/20.
 */
public class RecyclerActivity extends BaseActivity {

    @Override
    public String getTAG() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        List<String> datas = new ArrayList<>(5);
        for (int i = 'A'; i < 'z'; i++) {
            datas.add("" + (char) i);
        }
        recyclerView.setAdapter(new MyAdapter(this, datas));
        // 设置RecyclerView的布局管理
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private LayoutInflater inflater;
        private Context context;
        private List<String> datas;

        public MyAdapter(Context context, List<String> datas) {
            this.context = context;
            this.datas = datas;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = inflater.inflate(R.layout.recyclerview_item, viewGroup, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, int pos) {
            myViewHolder.tv.setText(datas.get(pos));
        }

        @Override
        public int getItemCount() {
            return datas != null ? datas.size() : 0;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_recyclerview);
        }
    }
}
