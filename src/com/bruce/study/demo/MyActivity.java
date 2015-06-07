package com.bruce.study.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.bruce.study.demo.swipe_refresh_layout_demo.SwipeRefreshLayoutActivity;

import java.util.ArrayList;

/**
 * Created by BruceHurrican on 2015/5/24.
 */
public class MyActivity extends Activity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        HashMap<String, Class<? extends Activity>> demos = new HashMap<>(5);
        ArrayList<Class<? extends Activity>> demos = new ArrayList<>(5);
        ArrayList<String> demoNamesList = new ArrayList<>(5);
        demos.add(SwipeRefreshLayoutActivity.class);
        demoNamesList.add("下拉刷新效果1");
//        String[] demos = {"MatchTextView","MatchTextView","MatchTextView"};
        ListView lv__demo_list = (ListView) findViewById(R.id.lv__demo_list);
        lv__demo_list.setDivider(getResources().getDrawable(android.R.drawable.divider_horizontal_bright));
        Intent it = new Intent();
        lv__demo_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoNamesList));
        lv__demo_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyActivity.this, "你点击了第" + (position + 1) + "条Demo", Toast.LENGTH_SHORT).show();
                it.setClass(MyActivity.this, demos.get(position));
                startActivity(it);
            }
        });

    }

}
