package com.example.liumeng.quanminfu2.datamanger;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liumeng on 2016/7/23.
 */
//统一网络管理类
public class HttpManager {

    private  HttpManager() {

    }

    private static  HttpManager sHttpManager = new HttpManager();

    public static  HttpManager getInstance() {
        return sHttpManager;
    }


    //从网络获取数据
    //get方式
    public String dataGet(String url) {

        try {
            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                        .url(url)
                        .build();
            Response response = okHttpClient.newCall(request).execute();
            return  response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }
}
