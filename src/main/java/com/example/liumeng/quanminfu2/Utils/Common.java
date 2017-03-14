package com.example.liumeng.quanminfu2.Utils;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;

import com.example.liumeng.quanminfu2.global.LiumengApp;

import java.io.File;

/**
 * Created by liumeng on 2017/1/9 on 14:38
 */

public class Common {
    /**
     * 安装apk
     */
    /**
     * @param file 安装 文件
     * @param INSTALLREQUEST 请求码
     */
    public static void installAPK(Activity context, File file, int INSTALLREQUEST) {
        /*     <intent-filter>
             <action android:name="android.intent.action.VIEW" />
             <category android:name="android.intent.category.DEFAULT" />
             <data android:scheme="content" />
             <data android:scheme="file" />
             <data android:mimeType="application/vnd.android.package-archive" />
         </intent-filter>*/

        //创建一个意图
        Intent intent_install = new Intent();
        intent_install.setAction("android.intent.action.VIEW");
        intent_install.addCategory("android.intent.category.DEFAULT");
        Uri data = Uri.fromFile(file);
        intent_install.setDataAndType(data, "application/vnd.android.package-archive");

        //开启intent
        // startActivity(intent_install);
        context.startActivityForResult(intent_install, INSTALLREQUEST);
    }

    //得到资源管理的类
    public static Resources getResource(){
        return LiumengApp.context.getResources();
    }

    //获取到字符串数组
    public static String[] getStringArrray(int resId) {
        return getResource().getStringArray(resId);
    }
}
