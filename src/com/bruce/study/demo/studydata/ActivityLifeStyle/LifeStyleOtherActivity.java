package com.bruce.study.demo.studydata.ActivityLifeStyle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.bruce.study.demo.R;

/**
 * Created by BruceHurrican on 2015/6/22.
 */
public class LifeStyleOtherActivity extends Activity {
    private final String TAG = "LifeStyleOtherActivity";
    private Button btn_life_style_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_style_other);
        Log.v(TAG, "onCreate");
        btn_life_style_other = (Button) findViewById(R.id.btn_life_style_other);
        btn_life_style_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_life_style_other) {
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }

}
