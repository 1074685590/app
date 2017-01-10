package com.example.liumeng.quanminfu2.serviceandbroad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到了发送过来的广播", Toast.LENGTH_SHORT).show();
    }
}
