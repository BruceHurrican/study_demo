package com.bruce.study.demo.SwipeRefreshLayoutDemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bruce.study.demo.R;

import java.util.ArrayList;

/**
 * 下拉刷新组件效果和google now和oschina的效果相似
 * Created by BruceHurrican on 2015/6/7.
 */
public class SwipeRefreshLayoutActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData()));

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    private ArrayList<String> getData() {
        list.add("Hello");
        list.add("This is BruceHurrican");
        list.add("An Android Developer");
        list.add("Work Hard");
        return list;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setRefreshing()传入的参数为false，刷新结束后，刷新图标会消失，如果为true则一直显示
                mSwipeLayout.setRefreshing(false);
            }
        }, 5000);
    }
}
