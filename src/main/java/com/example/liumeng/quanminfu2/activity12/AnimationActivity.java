package com.example.liumeng.quanminfu2.activity12;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.example.liumeng.quanminfu2.R;

public class AnimationActivity extends AppCompatActivity {

    private ImageView mAnimation_iv;
    private Button mAnimation_btn_start;
    float translationX = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        init();

    }

    private void init() {
        mAnimation_iv = (ImageView) findViewById(R.id.animation_iv);
        int measuredWidth = mAnimation_iv.getWidth();
        int measuredHeight = mAnimation_iv.getHeight();
        Log.i("====1","width="+measuredWidth+",height="+measuredHeight);
        mAnimation_iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int measuredWidth = mAnimation_iv.getMeasuredWidth();
                int measuredHeight = mAnimation_iv.getMeasuredHeight();

                Log.i("====2","width="+measuredWidth+",height="+measuredHeight);
            }
        });
        mAnimation_btn_start = (Button) findViewById(R.id.animation_btn_start);
        mAnimation_btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translationX = translationX + 20;
                mAnimation_iv.setTranslationX(translationX);
                //改变颜色

            }
        });
        changeBgColor();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeBgColor() {
        View line = findViewById(R.id.line);
        line.setBackground(getGradientDrawable("#000000"));

    }

    private GradientDrawable getGradientDrawable(String color) {
        float r = 130;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[] { 0xFFFF0000, 0xFF00FF00,
                        0xFF0000FF });
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(Color.parseColor(color));
        setCornerRadii(gradientDrawable, r, 0, 0, r);
        return gradientDrawable;
    }

    static void setCornerRadii(GradientDrawable drawable, float r0,
                               float r1, float r2, float r3) {
        drawable.setCornerRadii(new float[] { r0, r0, r1, r1,
                r2, r2, r3, r3 });
    }
}
