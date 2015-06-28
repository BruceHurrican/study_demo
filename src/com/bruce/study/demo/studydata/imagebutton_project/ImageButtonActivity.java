package com.bruce.study.demo.studydata.imagebutton_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import com.bruce.study.demo.R;

/**
 * ImageButton 练习
 * Created by BruceHurrican on 2015/6/28.
 */
public class ImageButtonActivity extends Activity{
        ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studydata_activity_imgbtnproject);
        imgbtn = (ImageButton) findViewById(R.id.imgbtn);
        imgbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    imgbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.studydata_imgbtn_press));
                } else if (event.getAction() == MotionEvent.ACTION_UP){
                    imgbtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.studydata_imgbtn_nopress));
                }
                return false;
            }
        });
    }
}
