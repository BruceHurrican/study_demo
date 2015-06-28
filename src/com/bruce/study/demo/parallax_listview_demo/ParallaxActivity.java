package com.bruce.study.demo.parallax_listview_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.bruce.study.demo.R;
import com.bruce.study.demo.parallax_listview_demo.widget.ParallaxScrollListView;

/**
 * Created by BruceHurrican on 2015/6/13.
 */
public class ParallaxActivity extends Activity{
    private ParallaxScrollListView mListView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parallax_activity);
        mListView = (ParallaxScrollListView) findViewById(R.id.pslv);
        View header = LayoutInflater.from(this).inflate(R.layout.parallax_view_listview_header,null);
        mImageView = (ImageView)header.findViewById(R.id.iv_header_image);

        mListView.setParallaxImageView(mImageView);
        mListView.addHeaderView(header);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, new String[]{"First Item","Second Item","Third Item","Forth Item","Fifth Item","Sixth Item","First Item","Second Item","Third Item","Forth Item","Fifth Item","Sixth Item","First Item","Second Item","Third Item","Forth Item","Fifth Item","Sixth Item"});
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.parallax_menu,menu);
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            mListView.setViewsBounds(ParallaxScrollListView.ZOOM_X2);
        }
    }
}
