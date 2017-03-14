package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.liumeng.quanminfu2.Fragment.BlankFragment;
import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.Common;
import com.example.liumeng.quanminfu2.adapter.MainFragmentAdapter;
import com.example.liumeng.quanminfu2.bean.FragmentInfo;
import com.example.liumeng.quanminfu2.view.PagerSlidingTab;

import java.util.ArrayList;

public class DrawerLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    // 定义集合
    private ArrayList<FragmentInfo> mShowItems = new ArrayList<>();
    private ViewPager mVp_main_shows;
    private PagerSlidingTab mPst_main_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        initView();
        setActionBar();
        initData();

    }



    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main_menu);
        mVp_main_shows = (ViewPager) findViewById(R.id.vp_main_shows);
        mPst_main_title = (PagerSlidingTab) findViewById(R.id.pst_main_title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setActionBar() {
        // 得到actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("刘蒙");
        // 设置箭头,需要记住home
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, null, 0, 0);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void initData() {
        // viewpager是不是要设置数据
        // 参数:得到支持的fragment管理器
        MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        //初始化集合
        //得到标题
        String[] titles = Common.getStringArrray(R.array.tab_names);
        mShowItems.add(new FragmentInfo(titles[0], new BlankFragment()));
        mShowItems.add(new FragmentInfo(titles[1], new BlankFragment()));
        mShowItems.add(new FragmentInfo(titles[2], new BlankFragment()));
        mShowItems.add(new FragmentInfo(titles[3], new BlankFragment()));
        mShowItems.add(new FragmentInfo(titles[4], new BlankFragment()));

        // 设置集合
        mainFragmentAdapter.setShowItems(mShowItems);

        mVp_main_shows.setAdapter(mainFragmentAdapter);

        // 指示器跟viewpager关联
        mPst_main_title.setViewPager(mVp_main_shows);
    }

}
