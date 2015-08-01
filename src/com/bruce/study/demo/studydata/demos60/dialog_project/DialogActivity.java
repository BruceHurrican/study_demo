/*
 * Copyright (c) 2015.
 *   This document is just for Bruce's personal study.
 *   Some lines from Internet. Everyone can download and use for study, but can
 *   not be used for commercial purpose. The author does not bear the
 *   corresponding disputes arising therefrom.
 *   Please delete within 24 hours after download.
 *   If you have good suggestions for this code, you can contact BurrceHurrican@foxmail.com.
 *   本文件为Bruce's个人学习android的demo, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *   任和何人可以下载并使用, 但是不能用于商业用途。
 *   作者不承担由此带来的相应纠纷。
 *   如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package com.bruce.study.demo.studydata.demos60.dialog_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.bruce.study.demo.R;
import com.bruce.study.demo.base.BaseActivity;

/**
 * Dialog 练习
 * Created by BruceHurrican on 2015/7/18.
 */
public class DialogActivity extends BaseActivity {

    @Override
    public String getTAG() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demos60_activity_dialogproject);
//        LinearLayout ll_dialog = (LinearLayout) findViewById(R.id.ll_dialog);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("对话框");
        builder.setMessage("Dialog Message");
        View view = LayoutInflater.from(this).inflate(R.layout.demos60_view_dialogproject, (ViewGroup) findViewById
                (R.id.ll_container_dialogproject));
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "yes button", Toast.LENGTH_SHORT).show();
                ((ViewGroup) view.getParent()).removeView(view);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogActivity.this, "no button", Toast.LENGTH_SHORT).show();
                ((ViewGroup) view.getParent()).removeView(view);
            }
        });

        builder.setView(view);
        Button btn_dialogproject = (Button) findViewById(R.id.btn_dialogproject);
        btn_dialogproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });
    }
}
