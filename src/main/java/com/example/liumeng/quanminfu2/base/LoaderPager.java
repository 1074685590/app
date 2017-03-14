package com.example.liumeng.quanminfu2.base;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.liumeng.quanminfu2.global.LiumengApp;

/**
 * Created by liumeng on 2017/1/13 on 17:49
 */

public abstract class LoaderPager {
    private Context mContext;
    public LoaderPager(){
        this.mContext = LiumengApp.context;
    }
    //成功
    public abstract View success();
    //加载中
    public View loading(){
        TextView textView = new TextView(mContext);
        textView.setText("加载中");
        return textView;
    }
    //失败
    public View fail(){
        TextView textView = new TextView(mContext);
        textView.setText("失败");
        return textView;
    }
}
