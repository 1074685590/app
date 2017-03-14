package com.example.liumeng.quanminfu2.datamanger;

/**
 * Created by liumeng on 2016/7/23.
 */

import android.text.TextUtils;

import com.example.liumeng.quanminfu2.Utils.GsonUtil;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 主要功能是实现返回缓存数据
 */
public class DataLoader {

    private  DataLoader() {

    }

    private  static  DataLoader sDataLoader = new DataLoader();

    public static  DataLoader getInstance() {
        return sDataLoader;
    }


    //暴露一个方法出去,获取网络的数据
    //当前的数据分成两种,一种是bean,一种集合,json数据
    public <T> T getDataBean(String url,Class<T> clazz) {
        //1. 优先从网络获取数据

        String content = HttpManager.getInstance().dataGet(url);

        System.out.println("当前网络获取的数据:"+content);


        if (TextUtils.isEmpty(content)) {
            //2. 如果网络数据为空
            //去缓存 获取数据
            content = CacheManger.getInstance().getData(url);
            System.out.println("得到缓存数据");

        } else {
            //3. 保存数据
            CacheManger.getInstance().saveData(url,content);

        }

        //解析json数据

        Object obj = parseJson(content, clazz);
/*
        return obj;*/

        return parseJson(content, clazz);
       // return  content;

    }

    private <T> T parseJson(String content,Class<T> clazz) {
        //在这里进行解析,
       /* Object obj = GsonUtil.parseJsonToBean(content, clazz);
        return obj;*/
        return GsonUtil.parseJsonToBean(content, clazz);
    }

    //创建一个list集合
    public List<?> getDataList(String url, Type type) {
        //1. 优先从网络获取数据

        String content = HttpManager.getInstance().dataGet(url);


        if (TextUtils.isEmpty(content)) {
            //2. 如果网络数据为空
            //去缓存 获取数据
            content = CacheManger.getInstance().getData(url);
            System.out.println("content = "+content);
            System.out.println("得到缓存数据");

        } else {
            //3. 保存数据
            CacheManger.getInstance().saveData(url,content);

        }

        //解析json数据

/*
        return obj;*/

        return parseJsonList(content, type);
        // return  content;

    }

    private List<?> parseJsonList(String content,Type type) {
        //在这里进行解析,
        return GsonUtil.parseJsonToList(content,type);
    }


}
