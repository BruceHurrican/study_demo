package com.bruce.study.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.bruce.study.demo.parallax_listview_demo.ParallaxActivity;
import com.bruce.study.demo.shape_loading_demo.ShapeLoadingActivity;
import com.bruce.study.demo.swipe_refresh_layout_demo.SwipeRefreshLayoutActivity;

import java.util.ArrayList;

/**
 * Created by BruceHurrican on 2015/5/24.
 */
public class MyActivity extends Activity implements AdapterView.OnItemClickListener{
    private ArrayList<Class<? extends Activity>> demos;
    private ArrayList<String> demoNamesList;
    private  ListView lv_demo_list;
    private Intent it;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initContainer();
    }

    /**
     * 初始化demo容器
     */
    private void initContainer(){
        demos = new ArrayList<>(5);
        demoNamesList = new ArrayList<>(5);
        lv_demo_list = (ListView) findViewById(R.id.lv__demo_list);
        lv_demo_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoNamesList));
        it = new Intent();

        addDemoContainer(SwipeRefreshLayoutActivity.class, "谷歌自带下拉刷新组件");
        addDemoContainer(ShapeLoadingActivity.class, "58同城加载等待组件");
        addDemoContainer(ParallaxActivity.class, "下拉刷新头图片放大");

        lv_demo_list.setOnItemClickListener(this);
    }

    /**
     * 增加demo
     * @param cls demo class
     * @param name demo 名称
     */
    private void addDemoContainer(Class<? extends Activity> cls, String name){
        demos.add(cls);
        demoNamesList.add(name);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MyActivity.this, "你点击了第" + (position + 1) + "条Demo", Toast.LENGTH_SHORT).show();
        it.setClass(MyActivity.this, demos.get(position));
        startActivity(it);
    }
}
