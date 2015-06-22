package com.bruce.study.demo.studydata.ActivityLifeStyle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.bruce.study.demo.R;

/**
 * Activity生命周期
 * Created by BruceHurrican on 2015/6/22.
 */
public class LifeStyleActivity extends Activity implements View.OnClickListener {
    private final String TAG = "LifeStyleActivity -->";
    private Button btn_life_style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_style);
        Log.v(TAG, "onCreate");
        btn_life_style = (Button) findViewById(R.id.btn_life_style);
        btn_life_style.setOnClickListener(this);
        // this.finish(); 结束当前activity
    }

    @Override
    public void onClick(View v) {
        if (v == btn_life_style) {
            Intent it = new Intent();
            it.setClass(this, LifeStyleOtherActivity.class);
            this.startActivity(it);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }
}
