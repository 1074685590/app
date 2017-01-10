package com.example.liumeng.quanminfu2.activity12.litpoint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;

/**
 * Created by liumeng on 2017/1/6 on 16:20
 * 需求需要为五个页面定制统一的切换动画,所以要实现一个方法调用统一的动画,然后再调用上一步或者下一步
 * 待完善:边界处理
 * 疑问: 为什么finish在动画前,而不是在动画后,可以放在后面吗
 */

public abstract class BaseLitPointActivity extends AppCompatActivity {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                float x1 = e1.getX();
                float x2 = e2.getX();
                float v = x1 - x2;
                //下一步
                if (v > 0)
                    nextStep(null);
                else {
                    //上一步
                    preStep(null);
                }
                LogUtils.d(v + "");
                return true;
            }
        });
    }

    public void preStep(View view) {
        //activity换动画
        preStepSub();
        overridePendingTransition(R.anim.activity_enter_anim,R.anim.activity_exit_anim);
        finish();
    }


    public void nextStep(View view) {
        //activity切换动画
        nextStepSub();
        finish();
        overridePendingTransition(R.anim.activity_enter_anim2,R.anim.activity_exit_anim2);
    }

    //上一步
    protected abstract void preStepSub();
    //下一步
    public abstract void nextStepSub();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
