package com.example.liumeng.quanminfu2.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


/**
 * Created by liumeng on 2017/1/13 on 9:55
 */

public class LiumengApp extends Application {
    //上下文
    public static Context context;
    //handler
    public static Handler mainHandler;
    @Override
    public void onCreate() {
        context = this;

        mainHandler = new Handler();
        //比如在这里可以初始化imageLoader
//        LogUtils.d("applicion"+"创建了一次");
    }

}
