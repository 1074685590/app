package com.example.liumeng.quanminfu2.activity12;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.Common;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;
import com.example.liumeng.quanminfu2.serviceandbroad.MyIntentService;
import com.example.liumeng.quanminfu2.serviceandbroad.MyService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 服务生命周期:
 * 绑定: oncreate--onStartCommand--ondestroy  创建--开启--销毁
 * 非绑定: oncreate--onbind--onunbind--ondestroy 创建--绑定--解绑销毁
 */
public class ActivityService extends AppCompatActivity {

    private static final int INSTALLREQUESTCODE = 101;
    private MyService.User mUser;//http://192.168.1.13:8080/Mobsafe.apk
    private String url= "http://10.0.2.2:8080/Mobsafe.apk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        EventBus.getDefault().register(this);
    }

    public void bindService(View view) {
        Intent service = new Intent(this, MyService.class);
        ServiceConnection conn = new MyServiceConnection();
        bindService(service,conn, Service.BIND_AUTO_CREATE);
    }

    public void download(View view) {
        LogUtils.d("进来了");
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("URL",url);
        startService(intent);
    }

    public void savapicture(View view) {
       //获取到bitmap对象
        BitmapDrawable drawable = (BitmapDrawable)getResources().getDrawable(R.drawable.account_transfer);
        Bitmap bitmap = drawable.getBitmap();
        File file =null ;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            file = new File(Environment.getExternalStorageDirectory(),"my.png");
        }
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        if (compress) {
            Intent intent = new Intent();
                /*
                 * 所有的系统都兼容
                 */
            //让系统图库去扫描文件广播
            intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            //设置文件的路径
            intent.setData(Uri.fromFile(file));

            sendBroadcast(intent);
        }
    }

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mUser = (MyService.User) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void helloEventBus(String message){
        File storageDirectory = Environment.getExternalStorageDirectory();
        //创建一个文件
        File mDownFile = new File(storageDirectory, "mobsafe.apk");
        ToastUtil.showToast(this,"安装应用");
        Common.installAPK(this,mDownFile,INSTALLREQUESTCODE);
    }

    public void eat(View view) {
        mUser.eat();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
