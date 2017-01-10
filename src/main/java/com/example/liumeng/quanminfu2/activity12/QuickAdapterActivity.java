package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;
import com.example.liumeng.quanminfu2.adapter.MyQuickAdapter;
import com.example.liumeng.quanminfu2.bean.QuickAdapterBean;

import java.util.ArrayList;

public class QuickAdapterActivity extends AppCompatActivity {

    private ListView mAc_quick_adapter_lv;
    private MyQuickAdapter mMyQuickAdapter;
    private ArrayList<QuickAdapterBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_adapter);
        initData();
        init();
    }

    private void init() {
        mAc_quick_adapter_lv = (ListView) findViewById(R.id.ac_quick_adapter_lv);
        mMyQuickAdapter = new MyQuickAdapter(this,mList);
        mAc_quick_adapter_lv.setAdapter(mMyQuickAdapter);
        mAc_quick_adapter_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showToast(QuickAdapterActivity.this,"条目"+position+"被点击");
            }
        });
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(new QuickAdapterBean("第"+i+"组","按钮"+i));
        }
    }

}
