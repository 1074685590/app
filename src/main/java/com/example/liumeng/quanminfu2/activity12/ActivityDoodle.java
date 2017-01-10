package com.example.liumeng.quanminfu2.activity12;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ActivityDoodle extends AppCompatActivity {

    private ImageView mDoodle_iv;
    private int mStartX;
    private int mStartY;
    private Bitmap mBitmap;
    private Paint mPaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle);
        init();
    }
    //ontouch的返回值是怎么确定的
    private void init() {
        mDoodle_iv = (ImageView) findViewById(R.id.doodle_iv);
        mDoodle_iv.setOnTouchListener(new View.OnTouchListener() {

            private Canvas mCanvas;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.d("手指按下了");
                        mStartX = (int)event.getX();
                        mStartY = (int)event.getY();
                        //初始化画图所需
                        if (mBitmap == null) {
                            mBitmap = Bitmap.createBitmap(mDoodle_iv.getMeasuredWidth(), mDoodle_iv.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
                            //画笔 画板
                            mCanvas = new Canvas(mBitmap);
                            mCanvas.drawColor(Color.WHITE); //给画板设置底色
                            mPaint = new Paint();
                            mPaint.setStrokeWidth(5);
                            mPaint.setColor(Color.RED);
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.d("手机开始移动了");
                        int currentX = (int)event.getX();
                        int currentY = (int)event.getY();
                        //将移动产生的先画到imageview上
                        mCanvas.drawLine(mStartX,mStartY,currentX,currentY,mPaint);
                        //获取到产生的bitmap设置到屏幕上 ,这个地方已经和bitmap关联了,只需要将bitmap设置给imageview就可以了
                        mDoodle_iv.setImageBitmap(mBitmap);
                        mStartX = currentX;
                        mStartY = currentY;
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.d("手指抬起了");
                        break;
                }
                return true; //代表将当前事物消费掉
            }
        });
    }

    public void sava(View view) throws IOException {
        if (mBitmap == null) {
            ToastUtil.showToast(this,"没有可以保存的信息");
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), SystemClock.currentThreadTimeMillis() + ".jpeg");
        OutputStream fos = new FileOutputStream(file);
        /*
		 * 参数1：压缩的格式（JPG WEBP，PNG）
		 * 参数2：压缩的质量，【0.100】,只对有损压缩有用，对PNG格式无用
		 * 参数3：对外的输出流
		 */
        boolean compress = mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

        if (compress) {
            Toast.makeText(this, "压缩成功", Toast.LENGTH_SHORT).show();

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
                Intent intent = new Intent();
				/*
				 * 所有的系统都兼容
				 */
                //让系统图库去扫描文件广播
                intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                //设置文件的路径
                intent.setData(Uri.fromFile(file));

                sendBroadcast(intent);

            }else {
				/*
				 * 只用能在Android4.4以下版本
				 */
                //模拟系统发送sdcard挂载好广播，让系统图库自动去扫描我们的sdcard。
                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
                //设置文件路径
                intent.setData(Uri.fromFile(file));//file:/mnt/sdcartd/140000.jpg

                sendBroadcast(intent);
            }
        }
        //记得关闭流
        fos.close();

    }

    public void clear(View view) {
        mBitmap = null;
        mDoodle_iv.setImageBitmap(mBitmap);
    }
}
