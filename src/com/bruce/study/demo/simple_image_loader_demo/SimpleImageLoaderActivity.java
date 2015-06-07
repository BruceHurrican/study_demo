//package com.bruce.study.demo.simple_image_loader_demo;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.widget.ListView;
//import com.bruce.study.demo.R;
//
///**
// * 简单图片加载demo
// * Created by BruceHurrican on 2015/6/7.
// */
//public class SimpleImageLoaderActivity extends FragmentActivity{
//    private ListView mListView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_simple_image_loader_layout);
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//        if (null == fragment){
//            fragment = new ImagesFragment();
//            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
//        }
//
//    }
//
//    private void initImageLoader(){
//        ImageLoad
//    }
//}
