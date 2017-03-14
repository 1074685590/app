package com.example.liumeng.quanminfu2.activity12.activity1703;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.bean.UserManager;

public class Activity_a extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        setContentView(R.layout.activity_a);
        changeAValue();
    }

    public void click(View view) {
        startActivity(new Intent(this,Activity_b.class));
    }

    private void changeAValue() {
        UserManager.a = 2;
    }

}
