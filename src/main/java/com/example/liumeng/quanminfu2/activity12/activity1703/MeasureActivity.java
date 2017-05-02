package com.example.liumeng.quanminfu2.activity12.activity1703;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.example.liumeng.quanminfu2.R;

public class MeasureActivity extends AppCompatActivity {

    private Button mMeasure_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);

        mMeasure_btn = (Button) findViewById(R.id.measure_btn);

//        System.out.println("liumeng onCreate() getMeasuredHeight()"+mMeasure_btn.getMeasuredHeight());
//        System.out.println("liumeng onCreate() getHeight()"+mMeasure_btn.getHeight());

        mMeasure_btn.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("liumeng onCreate() getMeasuredHeight()"+mMeasure_btn.getMeasuredHeight());
                System.out.println("liumeng onCreate() getHeight()"+mMeasure_btn.getHeight());
            }
        });

        ViewTreeObserver viewTreeObserver = mMeasure_btn.getViewTreeObserver();
        viewTreeObserver.addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                mMeasure_btn.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
                //如果没有这一句,当焦点发生改变的时候会反复测量
                System.out.println("liumeng onGlobalFocusChanged() getMeasuredHeight()"+mMeasure_btn.getMeasuredHeight());
                System.out.println("liumeng onGlobalFocusChanged() getHeight()"+mMeasure_btn.getHeight());
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        System.out.println("liumeng onWindowFocusChanged() getMeasuredHeight()"+mMeasure_btn.getMeasuredHeight());
        System.out.println("liumeng onWindowFocusChanged() getHeight()"+mMeasure_btn.getHeight());
    }

    @Override
    protected void onStart() {
        super.onStart();
//        System.out.println("liumeng onStart() getMeasuredHeight()"+mMeasure_btn.getMeasuredHeight());
//        System.out.println("liumeng onStart() getHeight()"+mMeasure_btn.getHeight());

    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("liumeng onResume() getMeasuredHeight()"+mMeasure_btn.getMeasuredHeight());
        System.out.println("liumeng onResume() getHeight()"+mMeasure_btn.getHeight());
    }
}
