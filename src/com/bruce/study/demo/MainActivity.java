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

package com.bruce.study.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.log.Logs;
import com.bruce.study.demo.parallax_listview_demo.ParallaxActivity;
import com.bruce.study.demo.studydata.baseadapter_project.BaseAdapterActivity;
import com.bruce.study.demo.studydata.bezier_project.BezierActivity;
import com.bruce.study.demo.studydata.dialog_project.DialogActivity;
import com.bruce.study.demo.studydata.game_view_framework_project.GameViewActivity;
import com.bruce.study.demo.studydata.surfaceview_project.SurfaceViewActivity;
import com.bruce.study.demo.studydata.switch_screen_project.SwitchScreenActivity;
import com.bruce.study.demo.util_demo.PhoneInfoActivity;
import com.bruce.study.demo.shape_loading_demo.ShapeLoadingActivity;
import com.bruce.study.demo.studydata.activity_life_style.LifeStyleActivity;
import com.bruce.study.demo.studydata.button_project.ButtonProject;
import com.bruce.study.demo.studydata.checkbox_project.CheckBoxActivity;
import com.bruce.study.demo.studydata.edittext_project.EditTextActivity;
import com.bruce.study.demo.studydata.imagebutton_project.ImageButtonActivity;
import com.bruce.study.demo.studydata.itheima_lihuoming.popup_window.PopupWindowActivity;
import com.bruce.study.demo.studydata.progressbar_project.ProgressBarActivity;
import com.bruce.study.demo.studydata.radiobutton_project.RadioButtonActivity;
import com.bruce.study.demo.studydata.seekbar_project.SeekBarActivity;
import com.bruce.study.demo.studydata.tab_project.TabActivity;
import com.bruce.study.demo.swipe_refresh_layout_demo.SwipeRefreshLayoutActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 主Activity
 * Created by BruceHurrican on 2015/5/24.
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, Serializable {
    private static final long serialVersionUID = -3277762441808693645L;
    private ArrayList<Class<? extends Activity>> demos;
    private ArrayList<String> demoNamesList;
    private ListView lv_demo_list;
    private Intent it;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initContainer();
    }

    @Override
    public String getTAG() {
        return "MainActivity -- >";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 初始化demo容器
     */
    private void initContainer() {
        demos = new ArrayList<>(5);
        demoNamesList = new ArrayList<>(5);
        lv_demo_list = (ListView) findViewById(R.id.lv__demo_list);
        lv_demo_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoNamesList));
        it = new Intent();

        addDemoContainer(PhoneInfoActivity.class, "工具类demo");
        addDemoContainer(SwipeRefreshLayoutActivity.class, "谷歌自带下拉刷新组件");
        addDemoContainer(ShapeLoadingActivity.class, "58同城加载等待组件");
        addDemoContainer(ParallaxActivity.class, "下拉刷新头图片放大");
        addDemoContainer(PopupWindowActivity.class, "练习PopupWindow");
        //---------------Android开发经典案例60个源码demo------------------------------
        addDemoContainer(LifeStyleActivity.class, "Activity生命周期");
        addDemoContainer(ButtonProject.class, "Button 监听练习");
        addDemoContainer(ImageButtonActivity.class, "ImageButton 练习");
        addDemoContainer(EditTextActivity.class, "EditText 练习");
        addDemoContainer(CheckBoxActivity.class, "CheckBox 练习");
        addDemoContainer(RadioButtonActivity.class, "RadioButton 练习");
        addDemoContainer(ProgressBarActivity.class, "ProgressBar 练习");
        addDemoContainer(SeekBarActivity.class, "SeekBar 练习");
        addDemoContainer(TabActivity.class, "TabActivity 练习");
        addDemoContainer(BaseAdapterActivity.class, "BaseAdapter 练习");
        addDemoContainer(DialogActivity.class, "Dialog 练习");
        addDemoContainer(SwitchScreenActivity.class, "横竖屏切换 练习");
        addDemoContainer(GameViewActivity.class, "游戏框架View 练习");
        addDemoContainer(SurfaceViewActivity.class, "SurfaceView 练习");
        addDemoContainer(BezierActivity.class, "贝塞尔曲线 练习");

        lv_demo_list.setOnItemClickListener(this);
        Logs.i(TAG, "加载列表完成");
    }

    /**
     * 增加demo
     *
     * @param cls  demo class
     * @param name demo 名称
     */
    private void addDemoContainer(Class<? extends Activity> cls, String name) {
        demos.add(cls);
        demoNamesList.add(name);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(MainActivity.this, "你点击了第" + (position + 1) + "条Demo", Toast.LENGTH_SHORT).show();
//        showToastShort("你点击了第" + (position + 1) + "条Demo--" + demoNamesList.get(position));
        showToastShort(String.format("你点击了第 %s 条Demo %s", position + 1, demoNamesList.get(position)));
        it.setClass(MainActivity.this, demos.get(position));
//        Logs.i(TAG, "你点击了第" + (position + 1) + "条Demo--"+demoNamesList.get(position));
        Logs.i(TAG, String.format("你点击了第 %s 条Demo %s",position + 1,demoNamesList.get(position)));
        startActivity(it);
    }
}
