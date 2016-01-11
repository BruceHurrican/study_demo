/*
 * BruceHurrican
 * Copyright (c) 2016.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *    This document is Bruce's individual learning the android demo, wherein the use of the code from the Internet, only to use as a learning exchanges.
 *   And where any person can download and use, but not for commercial purposes.
 *   Author does not assume the resulting corresponding disputes.
 *   If you have good suggestions for the code, you can contact BurrceHurrican@foxmail.com
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
