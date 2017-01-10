package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.view.LitPointLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FivePointActivity extends AppCompatActivity {

    @Bind(R.id.five_point_last_step)
    Button         mFivePointLastStep;
    @Bind(R.id.five_point_next_step)
    Button         mFivePointNextStep;
    @Bind(R.id.five_point_litl)
    LitPointLayout mFivePointLitl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_point);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.five_point_last_step, R.id.five_point_next_step})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.five_point_last_step:
                mFivePointLitl.setLastPoint();
                break;
            case R.id.five_point_next_step:
                mFivePointLitl.setNextPoint();
                break;
        }
    }
}
