package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.view.CustomViewPager;

public class ActivityCustomViewPager extends AppCompatActivity {
    private int[] imageResIds = {R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5,
            R.mipmap.a6};
    private CustomViewPager mCustomViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_pager);
        initView();
    }

    private void initView() {
        mCustomViewPager = (CustomViewPager) findViewById(R.id.customViewPager);
        //往viewpager中添加视图
        for (int i = 0; i < imageResIds.length; i++) {
            ImageView iv = new ImageView(ActivityCustomViewPager.this);
            iv.setBackgroundResource(imageResIds[i]);
            mCustomViewPager.addView(iv);
        }
        //往viewpager中添加布局文件视图
        View view = View.inflate(ActivityCustomViewPager.this, R.layout.item_viewpager, null);
        //将布局添加到第二个页面
        mCustomViewPager.addView(view,1);
    }
}
