package com.example.liumeng.quanminfu2.activity12;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.view.RefreshListView;

import java.util.ArrayList;
import java.util.List;

public class ActivityRefreshListView extends AppCompatActivity {


    private RefreshListView mRefreshListView;
    private List<String>    mDatas;
    private Handler mHandler = new Handler();
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_list_view);
        initView();
        initData();
    }
    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDatas.add("这是listview的数据" + i);
        }
        mMyAdapter = new MyAdapter();
        mRefreshListView.setAdapter(mMyAdapter);
    }

    private void initView() {
        mRefreshListView = (RefreshListView) findViewById(R.id.refreshListView);
        mRefreshListView.setOnFreshListener(new RefreshListView.OnFreshListener() {
            @Override
            public void onDownPull() {
                //获取新的数据并展示
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.add(0,"下拉刷新的新数据");
                        mMyAdapter.notifyDataSetChanged();
                        //通知控件我们已经刷新完成
                        mRefreshListView.onFinish();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                //获取新的数据并展示
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.add("这是加载更多的数据1");
                        mDatas.add("这是加载更多的数据2");
                        mDatas.add("这是加载更多的数据3");
                        mMyAdapter.notifyDataSetChanged();
                        //通知控件我们已经刷新完成
                        mRefreshListView.onFinish();
                    }
                }, 2000);
            }
        });
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String text = mDatas.get(position);
            TextView tv = new TextView(ActivityRefreshListView.this);
            tv.setText(text);
            tv.setPadding(5, 5, 5, 5);
            tv.setTextColor(Color.BLACK);
            return tv;
        }
    }
}
