package com.bruce.study.demo.studydata.edittext_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.bruce.study.demo.R;

/**
 * EditText 练习
 * Created by BruceHurrican on 2015/6/28.
 */
public class EditTextActivity extends Activity{
    private TextView tv_edittextproject;
    private EditText et_edittextproject;
    private Button btn_edittextproject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studydata_activity_edittextproject);
        tv_edittextproject = (TextView) findViewById(R.id.tv_edittextproject);
        et_edittextproject = (EditText) findViewById(R.id.et_edittextproject);
        btn_edittextproject = (Button) findViewById(R.id.btn_edittextproject);
        btn_edittextproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = et_edittextproject.getText().toString().trim();
                tv_edittextproject.setText(str);
            }
        });
    }
}
