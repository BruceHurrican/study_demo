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

package com.bruce.study.demo.studydata.google_api_demos.snake;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;
import com.bruce.study.demo.studydata.google_api_demos.snake.widgte.SnakeView;

/**
 * 贪吃蛇游戏
 * Created by BruceHurrican on 2015/10/10.
 * todo 界面显示不全
 */
public class SnakeActivity extends BaseActivity {
    public static final String SNAKE_VIEW = "snake-view";
    private SnakeView snakeView;

    @Override
    public String getTAG() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gd_activity_snake);
        snakeView = (SnakeView) findViewById(R.id.snake);
        TextView snake_tv = (TextView) findViewById(R.id.snake_tv);
        snakeView.setTextView(snake_tv);
        if (null == savedInstanceState) {
            snakeView.setMode(SnakeView.READY);
        } else {
            Bundle map = savedInstanceState.getBundle(SNAKE_VIEW);
            if (null != map) {
                snakeView.restoreState(map);
            } else {
                snakeView.setMode(SnakeView.PAUSE);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        snakeView.setMode(SnakeView.PAUSE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBundle(SNAKE_VIEW, snakeView.saveState());
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_snake_up:
                snakeView.moveUp();
                break;
            case R.id.btn_snake_down:
                snakeView.moveDown();
                break;
            case R.id.btn_snake_left:
                snakeView.moveLeft();
                break;
            case R.id.btn_snake_right:
                snakeView.moveRight();
                break;
        }
    }
}
