package com.example.liumeng.quanminfu2.activity12.activity1703;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;
import com.example.liumeng.quanminfu2.bean.UserManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Activity_a extends AppCompatActivity {

    private SharedPreferences mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window win = getWindow();
//            WindowManager.LayoutParams winParams = win.getAttributes();
//            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//            winParams.flags |= bits;
//            win.setAttributes(winParams);
//        }
        setContentView(R.layout.activity_a);
        mPreference = getSharedPreferences("liumengapp", Context.MODE_PRIVATE);
        LogUtils.d("onCreate");
        changeAValue();
        saveLoginInfo();
        bianmapmap();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void saveLoginInfo() {
        Map<String, String> info = new HashMap<String, String>();
        info.put("age", "25");
        info.put("name", "liumeng");
        info.put("sex", "男");
        saveStrings(info);
    }

    public void click(View view) {
        //取数据的时候没必要都取出来
        ToastUtil.showToast(this,"年纪"+mPreference.getString("age",""));
//        startActivity(new Intent(this,Activity_b.class));
    }

    private void changeAValue() {
        UserManager.a = 2;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("ondestoroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtils.d("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LogUtils.d("横屏");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LogUtils.d("竖屏");
        }
    }

    /**
     * @Description 批量保存String
     * @param datas
     * 03-20 15:18:05.830 10041-10041/? D/liumeng: 男
    03-20 15:18:05.830 10041-10041/? D/liumeng: liumeng
    03-20 15:18:05.830 10041-10041/? D/liumeng: 25
     */
    private void saveStrings(Map<String, String> datas) {
        SharedPreferences.Editor editor = mPreference.edit();
        Set<String> strings = datas.keySet();
        for (String iterable_element : datas.keySet()) {
            LogUtils.d( datas.get(iterable_element));
            LogUtils.d("iterable_element"+iterable_element);
            editor.putString(iterable_element, datas.get(iterable_element));
        }
        editor.commit();
    }

    //遍历双列集合
    private void bianmapmap() {
        // 创建外层HashMap来存储班级信息和学生，理科班用A作为键，文科班用B作为键，类型用Character。
        HashMap<Character, HashMap<String, Integer>> outHashMap = new HashMap<>();
        // 创建两个班级的学生信息，姓名用String，年龄用Integer类型存储。
        // 注意：集合中不能存储基本数据类型，这是集合区别于数组的特点之一。
        HashMap<String, Integer> inHashMap1 = new HashMap<>();
        HashMap<String, Integer> inHashMap2 = new HashMap<>();
        inHashMap1.put("赵飞燕", 17);
        inHashMap1.put("钱多多", 20);
        inHashMap1.put("孙小可", 19);
        inHashMap2.put("张可辛", 21);
        inHashMap2.put("胡一刀", 21);
        inHashMap2.put("王八蛋", 18);
        // 把学生信息加到外层集合中。
        outHashMap.put('A', inHashMap1);
        outHashMap.put('B', inHashMap2);
        // 开始遍历，先完成外层遍历。
        //方式一
        // 获取外层集合键集合set1。
        Set<Character> set1 = outHashMap.keySet();
        for (Character ch : set1) {
            System.out.println(ch + "班");
            // 通过多层集合的键获取内层存储学生信息的集合对象。
            HashMap<String, Integer> inHashMap = outHashMap.get(ch);
            // 获取内层存储学生信息集合的键的集合
            Set<String> set2 = inHashMap.keySet();
            for (String key : set2) {
                System.out.println("\t" + key + "：" + inHashMap.get(key));
            }
        }
        //方式二
        //获取外层键值对对象的集合
        Set<Map.Entry<Character, HashMap<String, Integer>>> outSet = outHashMap.entrySet();
        for (Map.Entry<Character, HashMap<String, Integer>> keyEntry : outSet) {
            System.out.println(keyEntry.getKey() + "班");
            //获取内层键值对对象的集合
            Set<Map.Entry<String, Integer>> inSet = keyEntry.getValue().entrySet();
            for (Map.Entry<String, Integer> key : inSet) {
                System.out.println("\t" + key.getKey() + ":" + key.getValue());
            }
        }
    }
}
