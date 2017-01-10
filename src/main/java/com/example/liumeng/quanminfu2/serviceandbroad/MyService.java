package com.example.liumeng.quanminfu2.serviceandbroad;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.Utils.LogUtils;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtils.d("解绑服务");
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        User user = new User();
        LogUtils.d("绑定服务");
        return user;
    }

    public class User extends Binder {
        public void eat() {
            Toast.makeText(MyService.this, "吃了一碗牛肉面", Toast.LENGTH_SHORT).show();
        }
    }
}
