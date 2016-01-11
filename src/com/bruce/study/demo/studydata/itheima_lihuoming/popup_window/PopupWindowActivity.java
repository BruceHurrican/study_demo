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

package com.bruce.study.demo.studydata.itheima_lihuoming.popup_window;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bruce.study.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 练习PopupWindow
 * Created by BruceHurrican on 2015/6/14.
 */
public class PopupWindowActivity extends Activity {
    private PopupWindow popupWindow;
    private View parent;
    private int[] images = {R.drawable.itheima_popwindow_i1, R.drawable.itheima_popwindow_i2, R.drawable.itheima_popwindow_i3, R.drawable.itheima_popwindow_i4,
            R.drawable.itheima_popwindow_i5, R.drawable.itheima_popwindow_i6, R.drawable.itheima_popwindow_i7, R.drawable.itheima_popwindow_i8};
    private String[] names = {"搜索", "文件管理", "下载管理", "全屏", "网址", "书签", "加入书签", "分享页面"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itheima_activity_popup_window);

        View contentView = getLayoutInflater().inflate(R.layout.itheima_view_popup_window, null);
        GridView gridView = (GridView) contentView.findViewById(R.id.gridview);
        gridView.setAdapter(getAdapter());
        gridView.setOnItemClickListener(new ItemClickListener());

        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true); // 取得焦点
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.itheimaPopupWindowAnimation);
        parent = this.findViewById(R.id.ll_main);
    }


    private ListAdapter getAdapter() {
        List<HashMap<String, Object>> data = new ArrayList<>(5);
        for (int i = 0; i < images.length; i++) {
            HashMap<String, Object> item = new HashMap<>(2);
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }
        return new SimpleAdapter(this, data, R.layout.itheima_item_grid, new String[]{"image", "name"}, new int[]{R.id.imageview, R.id.textview});
    }

    public void openPopWindow(View v) {
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    private final class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }
    }
}
