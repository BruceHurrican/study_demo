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

package com.bruce.study.demo.studydata.fragment.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseFragment;

/**
 * 使用 android 系统自带动画
 * Created by BruceHurrican on 2015/11/22.
 */
public class AnimationFragment extends BaseFragment implements View.OnClickListener {
    private ImageView iv_show_animation; // 显示动画的图片
    private Button btn_fade_in, btn_fade_out, btn_slide_in, btn_slide_out;

    @Override
    public String getTAG() {
        return "AnimationFragment->";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_show_animation = (ImageView) view.findViewById(R.id.iv_show_animation);
        btn_fade_in = (Button) view.findViewById(R.id.btn_fade_in);
        btn_fade_out = (Button) view.findViewById(R.id.btn_fade_out);
        btn_slide_in = (Button) view.findViewById(R.id.btn_slide_in);
        btn_slide_out = (Button) view.findViewById(R.id.btn_slide_out);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_fade_in.setOnClickListener(this);
        btn_fade_out.setOnClickListener(this);
        btn_slide_in.setOnClickListener(this);
        btn_slide_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Animation animation = null;
        switch (v.getId()) {
            case R.id.btn_fade_in:
                animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
                break;
            case R.id.btn_fade_out:
                animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
                break;
            case R.id.btn_slide_in:
                animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
                break;
            case R.id.btn_slide_out:
                animation = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
                break;
        }
        if (null != animation) {
            animation.setDuration(2000);
            iv_show_animation.startAnimation(animation);
        } else {
            logE("当前无动画显示");
        }
    }
}
