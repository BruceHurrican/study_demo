package com.bruce.study.demo.studydata.button_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bruce.study.demo.R;

/**
 * Button 监听练习
 * Created by BruceHurrican on 2015/6/28.
 */
public class ButtonProject extends Activity{
    TextView tv_btnproject_hello;
    Button btn_btnproject_ok,btn_btnproject_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studydata_activity_btnproject);
        tv_btnproject_hello = (TextView) findViewById(R.id.tv_btnproject_hello);
        btn_btnproject_ok = (Button) findViewById(R.id.btn_btnproject_ok);
        btn_btnproject_cancel = (Button) findViewById(R.id.btn_btnproject_cancel);
        btn_btnproject_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_btnproject_hello.setText("OK btn is clicked!");
            }
        });
        btn_btnproject_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_btnproject_hello.setText("Cancel btn is clicked!");
            }
        });
    }
}
