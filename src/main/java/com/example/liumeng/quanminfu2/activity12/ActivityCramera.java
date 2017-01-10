package com.example.liumeng.quanminfu2.activity12;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;

import java.io.File;
import java.util.Date;

public class ActivityCramera extends AppCompatActivity {
    private static final int REQUEST_PIC = 1;
    private static final int REQUEST_VIDEO = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cramera);
        LayoutInflater inflater = LayoutInflater.from(this);
        // 引入窗口配置文件
        View view = inflater.inflate(R.layout.activity_cramera, null);
        // 创建PopupWindow对象
        final PopupWindow pop = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        Button btn = (Button) findViewById(R.id.btn);
        // 需要设置一下此参数，点击外边可消失
        pop.setBackgroundDrawable(new BitmapDrawable());//??
        //设置点击窗口外边窗口消失
        pop.setOutsideTouchable(false);
        // 设置此参数获得焦点，否则无法点击
        pop.setFocusable(false);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pop.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    pop.dismiss();
                } else {
                    // 显示窗口
                    pop.showAsDropDown(v);
                }

            }
        });
    }

    public void camera(View view) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //设置照相之后输出路径
        File file = new File(Environment.getExternalStorageDirectory(),new Date().getTime()+".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent,REQUEST_PIC);
    }

     public void video(View view) {
         Intent intent = new Intent();
         intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
         //设置照相之后输出路径
         File file = new File(Environment.getExternalStorageDirectory(),new Date().getTime()+".3gp");
         intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
         startActivityForResult(intent,REQUEST_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PIC) {
            if (resultCode == RESULT_OK) {
                ToastUtil.showToast(this, "保存拍照成功");
            } else {
                ToastUtil.showToast(this, "保存拍照失败");
            }
        } else if (requestCode == REQUEST_VIDEO) {
            if (resultCode == RESULT_OK) {
                ToastUtil.showToast(this, "摄像成功");
            } else {
                ToastUtil.showToast(this,"保存摄像失败");
            }
        }
    }
}
