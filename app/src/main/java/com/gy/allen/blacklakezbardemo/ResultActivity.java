package com.gy.allen.blacklakezbardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by allen on 17/12/12.
 */

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        initView();
    }

    private void initView() {
        TextView textView = findViewById(R.id.tv_result);
        String code = getIntent().getStringExtra("CODE");
        textView.setText("二维码：" + code);
    }
}
