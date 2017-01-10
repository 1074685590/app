package com.example.liumeng.quanminfu2.activity12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.view.UpMarqueeTextView;

public class ActivityBigPicture extends AppCompatActivity {

    private ImageView mBig_picture_iv;
    private UpMarqueeTextView mMarqueeTextView;

    private String[] names = {"我的地盘.蒲公英的约定.给我一首歌的时间.说好的幸福呢.......", "我的地盘", "蒲公英的约定", "给我一首歌的时间", "说好的幸福呢"};
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_picture);
        mBig_picture_iv = (ImageView) findViewById(R.id.big_picture_iv);
        int measuredWidth = mBig_picture_iv.getMeasuredWidth();
        int measuredHeight = mBig_picture_iv.getMeasuredHeight();
        LogUtils.d("measuredWidth = "+measuredWidth+"  measuredHeight = "+measuredHeight);
        runUpLantern();
    }
    private  Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String text = names[index];
            index ++;
            if (index > 3) {
                index = 0;
            }
            mMarqueeTextView.setText(text);
            sendEmptyMessageDelayed(0,2000);
        }
    };
    private void runUpLantern() {
        mMarqueeTextView = (UpMarqueeTextView) findViewById(R.id.marquee);
        mHandler.sendEmptyMessageDelayed(0,1000);
    }

    public void loadPicture(View view) {
        //将内存卡上的图片转换成bitmap
        //获取路径
        String picturePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/me.jpg";
        //获取图片的宽和高
        BitmapFactory.Options options = new BitmapFactory.Options();
        //修改属性改为true,代码仅获取宽高信息而加载整个图片
        options.inJustDecodeBounds = true;
        //一段吧inJustDEcodeBounds设置为true的话这个方法返回的是null
        BitmapFactory.decodeFile(picturePath,options); //把图片的元信息设置到options对象中
        //获取到图片的宽和高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        LogUtils.d("outWidth = "+outWidth+"  outHeight = "+outHeight);

        //这种方法也可以获取到真实宽高信息,但是如果图片过大就直接内存溢出了,所以在这不适用
//        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//        //获取bitmap的宽和高  可能有问题
//        int bitmapWidth = bitmap.getWidth();
//        int bitmapHeight = bitmap.getHeight();
//        LogUtils.d("bitmapWidth = "+bitmapWidth+"  bitmapHeight"+bitmapHeight);

        //获取ImageView的宽高信息
        int mBig_picture_ivWidth = mBig_picture_iv.getWidth();
        int mBig_picture_ivHeight = mBig_picture_iv.getHeight();
        LogUtils.d("mBig_picture_ivWidth = "+mBig_picture_ivWidth+"   mBig_picture_ivHeight = "+mBig_picture_ivHeight);

        //获取到缩放比例
        int scale = Math.max(outWidth/mBig_picture_ivWidth,outHeight/mBig_picture_ivHeight);
        //对图片进行压缩
        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);
        mBig_picture_iv.setImageBitmap(bitmap);
    }
}
