package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.OnClick;
import com.example.liumeng.quanminfu2.Utils.ViewInject;
import com.example.liumeng.quanminfu2.Utils.ViewUtils;

public class ActivityViewInject extends AppCompatActivity {
    @ViewInject(R.id.tv_xxxx)
    private TextView tv;

    @ViewInject(R.id.tv_yyy)
    private TextView tvy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inject);
        ViewUtils.inject(this);

        Log.d("tag", "tv="+tv);
        Log.d("tag", "tvy="+tvy);


        Toast.makeText(this, "tv="+tv, Toast.LENGTH_LONG).show();
    }

   @OnClick(R.id.btn)
    private void method1(View view){
        Toast.makeText(this, "我被点击了", Toast.LENGTH_SHORT).show();
    }
}
