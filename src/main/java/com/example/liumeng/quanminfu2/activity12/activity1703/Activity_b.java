package com.example.liumeng.quanminfu2.activity12.activity1703;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.bean.UserManager;

public class Activity_b extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        LogUtils.d(UserManager.a+"");
    }
}
