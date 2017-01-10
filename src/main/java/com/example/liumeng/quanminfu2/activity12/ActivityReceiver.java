package com.example.liumeng.quanminfu2.activity12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;

/**
 * 广播
 * 动态注册广播
 * 终止广播
 * 发送有序广播如何改变传递的数据
 */
public class ActivityReceiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
    }

    public void sendBroad(View view) {
        //方法一: 显示意图,直接指定广播的类名
        //        Intent intent = new Intent(this, MyReceiver.class);
        //方法二: 隐式意图,通过广播对应的action来发送广播
        Intent intent = new Intent("com.liumeng.receiver");
        sendBroadcast(intent);
    }

    /**
     * 动态注册广播
     */
    public void dynamic(View view) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String money = intent.getStringExtra("money");
//                String resultData = getResultData();
                Toast.makeText(ActivityReceiver.this, "我收到了发送的动态注册的广播+"+money, Toast.LENGTH_SHORT).show();
                setResultData("5000块钱");
            }
        };
        IntentFilter filter = new IntentFilter("com.gaoyan.receiver");
        filter.setPriority(1000);
        registerReceiver(broadcastReceiver, filter);
    }
    /**
     * 动态注册广播
     */
    public void dynamic2(View view) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String money = intent.getStringExtra("money");
                String resultData = getResultData();
                Toast.makeText(ActivityReceiver.this, "我收到了发送的动态注册的广播2"+money+resultData, Toast.LENGTH_SHORT).show();
                setResultData("1000块钱");
                abortBroadcast();  //终止广播
            }
        };
        IntentFilter filter = new IntentFilter("com.gaoyan.receiver");
        filter.setPriority(100);
        registerReceiver(broadcastReceiver, filter);
    }
    /**
     * 动态注册广播
     */
    public void dynamic3(View view) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String money = intent.getStringExtra("money");
                String resultData = getResultData();
                Toast.makeText(ActivityReceiver.this, "我收到了发送的动态注册的广播3+"+money+resultData, Toast.LENGTH_SHORT).show();
                setResultData("10块钱");
            }
        };
        IntentFilter filter = new IntentFilter("com.gaoyan.receiver");
        filter.setPriority(10);
        registerReceiver(broadcastReceiver, filter);
    }

    /**
     * 最终广播者
     */
    public void sendDynamic(View view) {
        Intent intent = new Intent("com.gaoyan.receiver");
        intent.putExtra("money", "发了10000块钱");
        BroadcastReceiver resultReceiver = new FinalReceiver();
        sendOrderedBroadcast(intent, null, resultReceiver, null, 1, null, null);
    }

    class FinalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Bundle extras = intent.getExtras();
            String money = intent.getStringExtra("money");
            String resultData = getResultData();
            Toast.makeText(context, "最终接受者受到了"+money+resultData, Toast.LENGTH_SHORT).show();
        }
    }

    /** 一个广播能同时监听屏幕的开启和关闭吗
     * 一个意图过滤器可以监听多个广播,意思就是可以同时监听屏幕的开启和关闭
     */
    public void screen(View view) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "监听到了屏幕的开启和关闭", Toast.LENGTH_SHORT).show();
            }
        };
        //监听屏幕的开启和关闭
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        //监听开机广播
//        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(broadcastReceiver,filter);
    }
}
