package com.example.liumeng.quanminfu2.activity12.litpoint;

import android.content.Intent;
import android.os.Bundle;

import com.example.liumeng.quanminfu2.R;

public class FiveActivity extends BaseLitPointActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
    }

    @Override
    protected void preStepSub() {
        startActivity(new Intent(this,FourActivity.class));
    }

    @Override
    public void nextStepSub() {

    }
}
