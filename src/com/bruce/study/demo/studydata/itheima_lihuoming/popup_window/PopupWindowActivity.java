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

    private final class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }
    }

    private ListAdapter getAdapter() {
        List<HashMap<String, Object>> data = new ArrayList<>(5);
        for (int i = 0; i < images.length; i++) {
            HashMap<String, Object> item = new HashMap<>(2);
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.itheima_item_grid, new String[]{"image", "name"}, new int[]{R.id.imageview, R.id.textview});
        return simpleAdapter;
    }

    public void openPopWindow(View v) {
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }
}
