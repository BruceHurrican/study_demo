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

package com.bruce.study.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.github.custom_shape_imageview_demo.CustomShapeImageViewActivity;
import com.bruce.study.demo.github.parallax_listview_demo.ParallaxActivity;
import com.bruce.study.demo.github.shape_loading_demo.ShapeLoadingActivity;
import com.bruce.study.demo.github.swipe_refresh_layout_demo.SwipeRefreshLayoutActivity;
import com.bruce.study.demo.studydata.demos60.activity_life_style.LifeStyleActivity;
import com.bruce.study.demo.studydata.demos60.animation_frame_project.FrameActivity;
import com.bruce.study.demo.studydata.demos60.animation_project.AnimationActivity;
import com.bruce.study.demo.studydata.demos60.baseadapter_project.BaseAdapterActivity;
import com.bruce.study.demo.studydata.demos60.bezier_project.BezierActivity;
import com.bruce.study.demo.studydata.demos60.bitmap_active_project.BitmapActiveActivity;
import com.bruce.study.demo.studydata.demos60.bitmap_project.BitmapActivity;
import com.bruce.study.demo.studydata.demos60.button_project.ButtonProject;
import com.bruce.study.demo.studydata.demos60.canvas_project.CanvasActivity;
import com.bruce.study.demo.studydata.demos60.checkbox_project.CheckBoxActivity;
import com.bruce.study.demo.studydata.demos60.circlecollision_project.CircleCollisionActivity;
import com.bruce.study.demo.studydata.demos60.clip_bitmap_movie_project.ClipBitmapMovieActivity;
import com.bruce.study.demo.studydata.demos60.clip_canvas_project.ClipCanvasActivity;
import com.bruce.study.demo.studydata.demos60.dialog_project.DialogActivity;
import com.bruce.study.demo.studydata.demos60.edittext_project.EditTextActivity;
import com.bruce.study.demo.studydata.demos60.game_view_framework_project.GameViewActivity;
import com.bruce.study.demo.studydata.demos60.httpclient.MyHttpClientActivity;
import com.bruce.study.demo.studydata.demos60.imagebutton_project.ImageButtonActivity;
import com.bruce.study.demo.studydata.demos60.mediaplayer_project.MediaPlayerActivity;
import com.bruce.study.demo.studydata.demos60.paint_project.PaintActivity;
import com.bruce.study.demo.studydata.demos60.player_project.PlayerActivity;
import com.bruce.study.demo.studydata.demos60.progressbar_project.ProgressBarActivity;
import com.bruce.study.demo.studydata.demos60.radiobutton_project.RadioButtonActivity;
import com.bruce.study.demo.studydata.demos60.rectcollision2_project.RectCollisionActivity2;
import com.bruce.study.demo.studydata.demos60.rectcollision_project.RectCollisionActivity;
import com.bruce.study.demo.studydata.demos60.regioncollision_project.RegionCollisonActivity;
import com.bruce.study.demo.studydata.demos60.seekbar_project.SeekBarActivity;
import com.bruce.study.demo.studydata.demos60.soundpool_project.SoundPoolActivity;
import com.bruce.study.demo.studydata.demos60.surfaceview_project.SurfaceViewActivity;
import com.bruce.study.demo.studydata.demos60.switch_screen_project.SwitchScreenActivity;
import com.bruce.study.demo.studydata.demos60.tab_project.MyTabActivity;
import com.bruce.study.demo.studydata.handler_thread.HandlerThreadDemoActivity;
import com.bruce.study.demo.studydata.itheima_lihuoming.popup_window.PopupWindowActivity;
import com.bruce.study.demo.studydata.masking_buttons.MaskingActivity;
import com.bruce.study.demo.studydata.viewpage.ViewPagerActivity;
import com.bruce.study.demo.studydata.volley_demo.VolleyActivity;
import com.bruce.study.demo.util_demo.PhoneInfoActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 主Activity
 * Created by BruceHurrican on 2015/5/24.
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener, Serializable {
    private static final long serialVersionUID = -3277762441808693645L;
    private List<Class<? extends Activity>> demos;
    private List<String> demoNamesList;
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
        ListView lv_demo_list = (ListView) findViewById(R.id.lv__demo_list);
//        lv_demo_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoNamesList));
        lv_demo_list.setAdapter(new ArrayAdapter<>(this, R.layout.main_item, demoNamesList));
        it = new Intent();

        addDemoContainer(PhoneInfoActivity.class, "工具类demo");
        addDemoContainer(VolleyActivity.class, "volley 练习");
        addDemoContainer(CustomShapeImageViewActivity.class, "图片下载框架练习1");
        addDemoContainer(SwipeRefreshLayoutActivity.class, "谷歌自带下拉刷新组件");
        addDemoContainer(ShapeLoadingActivity.class, "58同城加载等待组件");
        addDemoContainer(ParallaxActivity.class, "下拉刷新头图片放大");
        addDemoContainer(PopupWindowActivity.class, "PopupWindow 练习");
        addDemoContainer(ViewPagerActivity.class, "ViewPager 练习");
        addDemoContainer(MaskingActivity.class, "屏蔽手机物理键盘 练习");
        addDemoContainer(HandlerThreadDemoActivity.class, "HandlerThread 练习");
        //---------------Android开发经典案例60个源码demo------------------------------
        addDemoContainer(MyHttpClientActivity.class, "自定义网络请求 练习");
        addDemoContainer(SoundPoolActivity.class, "sound pool 练习");
        addDemoContainer(MediaPlayerActivity.class, "播放器 练习");
        addDemoContainer(RegionCollisonActivity.class, "碰撞检测 练习");
        addDemoContainer(RectCollisionActivity2.class, "多矩形碰撞 练习");
        addDemoContainer(CircleCollisionActivity.class, "圆形碰撞 练习");
        addDemoContainer(RectCollisionActivity.class, "矩形碰撞 练习");
        addDemoContainer(PlayerActivity.class, "操作游戏主角 练习");
        addDemoContainer(ClipBitmapMovieActivity.class, "剪切动画 练习");
        addDemoContainer(FrameActivity.class, "帧动画 练习");
        addDemoContainer(BitmapActiveActivity.class, "动态位图 练习");
        addDemoContainer(AnimationActivity.class, "动画效果 练习");
        addDemoContainer(ClipCanvasActivity.class, "可视化区域练习");
        addDemoContainer(LifeStyleActivity.class, "Activity生命周期");
        addDemoContainer(ButtonProject.class, "Button 监听练习");
        addDemoContainer(ImageButtonActivity.class, "ImageButton 练习");
        addDemoContainer(EditTextActivity.class, "EditText 练习");
        addDemoContainer(CheckBoxActivity.class, "CheckBox 练习");
        addDemoContainer(RadioButtonActivity.class, "RadioButton 练习");
        addDemoContainer(ProgressBarActivity.class, "ProgressBar 练习");
        addDemoContainer(SeekBarActivity.class, "SeekBar 练习");
        addDemoContainer(MyTabActivity.class, "TabActivity 练习");
        addDemoContainer(BaseAdapterActivity.class, "BaseAdapter 练习");
        addDemoContainer(DialogActivity.class, "Dialog 练习");
        addDemoContainer(SwitchScreenActivity.class, "横竖屏切换 练习");
        addDemoContainer(GameViewActivity.class, "游戏框架View 练习");
        addDemoContainer(SurfaceViewActivity.class, "SurfaceView 练习");
        addDemoContainer(BezierActivity.class, "贝塞尔曲线 练习");
        addDemoContainer(CanvasActivity.class, "Canvas 练习");
        addDemoContainer(PaintActivity.class, "Paint 练习");
        addDemoContainer(BitmapActivity.class, "Bitmap 练习");

        lv_demo_list.setOnItemClickListener(this);
        logI("加载列表完成");
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
        logI(String.format("你点击了第 %s 条Demo %s", position + 1, demoNamesList.get(position)));
        logI("当前线程为 -->" + Thread.currentThread());
        startActivity(it);
    }
}
