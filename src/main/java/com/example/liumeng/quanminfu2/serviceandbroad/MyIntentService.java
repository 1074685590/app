package com.example.liumeng.quanminfu2.serviceandbroad;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;

import com.example.liumeng.quanminfu2.Utils.IOUtil;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    private static final int INSTALLREQUESTCODE = 100;
    private File mDownFile;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        this("MYINtentService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("服务自动销毁");
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ToastUtil.showToast(MyIntentService.this,"下载成功");
                    //安装应用
//                    Common.installAPK(MyIntentService.this,mDownFile,INSTALLREQUESTCODE);
                    EventBus.getDefault().post("");
                    break;
                case 2:
                    String s = (String)msg.obj;
                    ToastUtil.showToast(MyIntentService.this,s);
                    break;
                default:
                    break;
            }
        }
    } ;

    @Override
    protected void onHandleIntent(Intent intent) {
        String url1 = intent.getStringExtra("URL");
        LogUtils.d(url1);
        try {
            URL url = new URL(url1);
            //2. 打开一个连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //3. 设置参数
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            //4. 连接网络
            connection.connect();//开启网络，同步方法，会阻塞代码
            //5. 获取状态码
            int responseCode = connection.getResponseCode();
            LogUtils.d(responseCode + "");
            if (200 == responseCode) {

                InputStream is = connection.getInputStream();
                //判断当前的状态是否可写可读SD
                LogUtils.d(Environment.getExternalStorageState()+"");
                if (TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
                    //可读可写
                    File storageDirectory = Environment.getExternalStorageDirectory();
                    //创建一个文件
                    mDownFile = new File(storageDirectory, "mobsafe.apk");
                    IOUtil.writeFile(is, mDownFile);
                }
                //睡十秒
                SystemClock.sleep(10000);
                handler.obtainMessage(1).sendToTarget();

            } else {
                handler.obtainMessage(2, "状态码不正确"+responseCode).sendToTarget();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
