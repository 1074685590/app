package com.example.liumeng.quanminfu2.activity12.litpoint;

import android.content.Intent;
import android.os.Bundle;

import com.example.liumeng.quanminfu2.R;

public class FourActivity extends BaseLitPointActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
    }

    @Override
    protected void preStepSub() {
        startActivity(new Intent(this,Third.class));
    }

    @Override
    public void nextStepSub() {
        startActivity(new Intent(this,FiveActivity.class));
    }
}
