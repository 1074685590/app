package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.view.SwichButton;

/**
 * 自定义控件
 */
public class ActivityCustom extends AppCompatActivity {

    private SwichButton mCustom_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        mCustom_btn = (SwichButton) findViewById(R.id.custom_btn);
        mCustom_btn.setOnCheckChangeListener(new SwichButton.onCheckChangeListener() {
            @Override
            public void onCheckChange(boolean isOpen) {
                Toast.makeText(ActivityCustom.this, "当前开关状态是:"+isOpen, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
