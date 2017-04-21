package com.example.liumeng.quanminfu2.activity12.homediandian;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.liumeng.quanminfu2.Fragment.didian.CarFragment;
import com.example.liumeng.quanminfu2.Fragment.didian.HomePageFragment;
import com.example.liumeng.quanminfu2.Fragment.didian.MarketFragment;
import com.example.liumeng.quanminfu2.Fragment.didian.MineFragment;
import com.example.liumeng.quanminfu2.R;

public class HomeDianDianActivity extends AppCompatActivity {
    //    首页
    private RadioGroup       mBottom_bar;
    private Drawable[]       drawables;
    private HomePageFragment mHomePageFragment;
    private MarketFragment   mMarketFragment;
    private CarFragment      mCarFragment;
    private MineFragment     mMineFragment;
    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();

            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                    /*
                     * 当点击的时候，点击谁就把谁显示出来，把其他的全部隐藏
                     */
            transaction
                    .hide(mHomePageFragment)//隐藏Fragment
                    .hide(mMarketFragment)//隐藏Fragment
                    .hide(mCarFragment)//隐藏
                    .hide(mMineFragment);//隐藏

            switch (checkedId) {
                case R.id.rb_bottom_home:
                    transaction.show(mHomePageFragment);
                    break;
                case R.id.rb_bottom_market:
                    transaction.show(mMarketFragment);
                    break;
                case R.id.rb_bottom_car:
                    transaction.show(mCarFragment);
                    break;
                case R.id.rb_bottom_mine:
                    transaction.show(mMineFragment);
                    break;
            }
            transaction.commit();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dian_dian);
        initView();
        initButton();
        initFragment();
    }

    private void initView() {
        mBottom_bar = (RadioGroup) findViewById(R.id.bottom_bar);
        mBottom_bar.setOnCheckedChangeListener(listener);
    }

    /**
     * 设置底部按钮
     */
    public void initButton() {
        int childCount = mBottom_bar.getChildCount();
        for (int i = 0; i < childCount; i++) {//循环
            RadioButton childAt = (RadioButton) mBottom_bar.getChildAt(i);
            drawables = childAt.getCompoundDrawables();//通过RadioButton的getCompoundDrawables()方法，拿到图片的drawables,分别是左上右下的图片

            switch (i) {//为每一个drawableTop设置属性setBounds(left,top,right,bottom)
                case 0:
                    drawables[1].setBounds(getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x25),
                            getResources().getDimensionPixelSize(R.dimen.x25));
                    break;

                case 1:
                    drawables[1].setBounds(getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x25),
                            getResources().getDimensionPixelSize(R.dimen.x25));
                    break;
                case 2:
                    drawables[1].setBounds(getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x25),
                            getResources().getDimensionPixelSize(R.dimen.x25));
                    break;
                case 3:
                    drawables[1].setBounds(getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x10), getResources().getDimensionPixelSize(R.dimen.x25),
                            getResources().getDimensionPixelSize(R.dimen.x25));
                    break;
                default:
                    break;
            }

            childAt.setCompoundDrawables(drawables[0], drawables[1], drawables[2],
                    drawables[3]);//将改变了属性的drawable再重新设置回去
        }
        ((RadioButton) mBottom_bar.getChildAt(0)).setChecked(true);
    }

    private void initFragment() {
        mHomePageFragment = new HomePageFragment();
        mMarketFragment = new MarketFragment();
        mCarFragment = new CarFragment();
        mMineFragment = new MineFragment();
         /*
         * 当初始化的时候把所有的Fragment全部添加到FrameLayout中
         */
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, mHomePageFragment, "HomePageFragment")
                .add(R.id.fl_content, mMarketFragment, "MarketFragment")
                .add(R.id.fl_content, mCarFragment, "CarFragment")
                .add(R.id.fl_content, mMineFragment, "MineFragment")
                .hide(mMarketFragment)//隐藏Fragment
                .hide(mCarFragment)//隐藏
                .hide(mMineFragment)//隐藏
                .commit();
    }
}
