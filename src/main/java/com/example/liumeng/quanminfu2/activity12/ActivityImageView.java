package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.liumeng.quanminfu2.R;

public class ActivityImageView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ImageView iv_img_1 = (ImageView) findViewById(R.id.iv_img_1);
        iv_img_1.setImageResource(R.drawable.toolbar_bg_bmp);
    }
}
