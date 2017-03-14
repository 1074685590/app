package com.example.liumeng.quanminfu2.activity12;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.liumeng.quanminfu2.R.id.animation_ll_open;

public class AnimationActivity extends AppCompatActivity {

    private ImageView mAnimation_iv;
    private Button mAnimation_btn_start;
    float translationX = 0;
    private TextView mAnimation_tv_desc;
    private int mHeight;
    private boolean isOpen = false;
    private ImageView mAnimation_iv_arrow;
    private LinearLayout mAnimation_ll_open;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        init();
        showTextView();
        //验证api的区别
        test();
    }

    private void test() {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = Environment.getExternalStorageDirectory().getPath();
        LogUtils.d("absolutePath = "+absolutePath); //absolutePath = /storage/emulated/0
        LogUtils.d("path = "+path);  // path = /storage/emulated/0
    }

    private void showTextView() {
        new ObjectAnimator();
        mAnimation_iv_arrow = (ImageView) findViewById(R.id.animation_iv_arrow);
        mAnimation_tv_desc = (TextView) findViewById(R.id.animation_tv_desc);
        mAnimation_tv_desc.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //移除监听
                mAnimation_tv_desc.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //得到当前控件的高度,控件有多高获得到的就是多高
                mHeight = mAnimation_tv_desc.getHeight();
                LogUtils.d("mHeight = "+mHeight);
                //影藏描述文字
                mAnimation_tv_desc.setPadding(0,-mHeight,0,0);
            }
        });
        mAnimation_ll_open = (LinearLayout) findViewById(animation_ll_open);
        findViewById(animation_ll_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对箭头进行180度动画
                ViewCompat.animate(mAnimation_iv_arrow).rotationBy(isOpen?-90:90).setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        //如果动画开启那么久不能点击mAnimation_ll_open
                        mAnimation_ll_open.setClickable(false);
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        mAnimation_ll_open.setClickable(true);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                }).setDuration(200).start();
                //创建属性动画设置动画的起始值,并开启动画
                ValueAnimator valueAnimator = new ValueAnimator();

                if (!isOpen) {
                    valueAnimator.setIntValues(-mHeight,0);
                }else {
                    valueAnimator.setIntValues(0,-mHeight);
                }
                isOpen = !isOpen;
                //设置监听将不断变化的动画值设置到textview上面
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedValue();
                        mAnimation_tv_desc.setPadding(0,animatedValue,0,0);
                    }
                });
                valueAnimator.setDuration(200);
                valueAnimator.start();
            }
        });
    }

    private void init() {
        mAnimation_iv = (ImageView) findViewById(R.id.animation_iv);
        int measuredWidth = mAnimation_iv.getWidth();
        int measuredHeight = mAnimation_iv.getHeight();
        Log.i("====1","width="+measuredWidth+",height="+measuredHeight);
        mAnimation_iv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int measuredWidth = mAnimation_iv.getMeasuredWidth();
                int measuredHeight = mAnimation_iv.getMeasuredHeight();

                Log.i("====2","width="+measuredWidth+",height="+measuredHeight);
            }
        });
        mAnimation_btn_start = (Button) findViewById(R.id.animation_btn_start);
        mAnimation_btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translationX = translationX + 20;
                mAnimation_iv.setTranslationX(translationX);
                //改变颜色

            }
        });
        changeBgColor();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void changeBgColor() {
        View line = findViewById(R.id.line);
        line.setBackground(getGradientDrawable("#000000"));

    }

    private GradientDrawable getGradientDrawable(String color) {
        float r = 130;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[] { 0xFFFF0000, 0xFF00FF00,
                        0xFF0000FF });
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(Color.parseColor(color));
        setCornerRadii(gradientDrawable, r, 0, 0, r);
        return gradientDrawable;
    }

    static void setCornerRadii(GradientDrawable drawable, float r0,
                               float r1, float r2, float r3) {
        drawable.setCornerRadii(new float[] { r0, r0, r1, r1,
                r2, r2, r3, r3 });
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
