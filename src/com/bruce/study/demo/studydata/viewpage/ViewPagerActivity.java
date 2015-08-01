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

package com.bruce.study.demo.studydata.viewpage;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager练习
 * Created by BruceHurrican on 2015/8/1.
 */
public class ViewPagerActivity extends BaseActivity {
    private List<View> pageViews;
    private ImageView[] imageViews;

    @Override
    public String getTAG() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getLayoutInflater();
        pageViews = new ArrayList<>(5);
        pageViews.add(inflater.inflate(R.layout.viewpager_item_01, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_item_02, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_item_03, null));
        pageViews.add(inflater.inflate(R.layout.viewpager_item_04, null));

        imageViews = new ImageView[pageViews.size()];
        ViewGroup main = (ViewGroup) inflater.inflate(R.layout.viewpager_activity, null);
        ViewGroup group = (ViewGroup) main.findViewById(R.id.ll_viewgroup);
        ViewPager viewPager = (ViewPager) main.findViewById(R.id.vp_guide);
        for (int i = 0; i < pageViews.size(); i++) {
            ImageView imageView = new ImageView(ViewPagerActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(20, 0, 20, 0);
            imageViews[i] = imageView;
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.viewpager_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.viewpage_indicator);
            }
            group.addView(imageViews[i]);
        }
        setContentView(main);
        viewPager.setAdapter(new MyViewPagerAdapter());
        viewPager.setOnPageChangeListener(new PageChangeListener());
    }

    /**
     * 指引页面的Adapter
     */
    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view.equals(o);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(pageViews.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager) container).addView(pageViews.get(position));
            return pageViews.get(position);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View container) {
        }

        @Override
        public void finishUpdate(View container) {
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            for (int j = 0; j < imageViews.length; j++) {
                imageViews[i].setBackgroundResource(R.drawable.viewpager_indicator_focused);
                if (i != j) {
                    imageViews[j].setBackgroundResource(R.drawable.viewpage_indicator);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }
}
