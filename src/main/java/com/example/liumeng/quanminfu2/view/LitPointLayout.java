package com.example.liumeng.quanminfu2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;

/**
 * Created by liumeng on 2017/1/6 on 10:23
 * 增加健壮性  非空  postion超出范围
 * 需求:可以设置某个点被选中
 */

public class LitPointLayout extends LinearLayout {
    private Context mContext;
    private int mPosition;
    private int mCount;

    public LitPointLayout(Context context) {
        super(context);
    }

    public LitPointLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }


    public LitPointLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs) {
        String namespace = "http://schemas.android.com/apk/res-auto";
        mPosition = attrs.getAttributeIntValue(namespace, "position", -1);
        mCount = attrs.getAttributeIntValue(namespace, "count", -1);
        if (mPosition>-1){
            initPoint(mCount, mPosition);
        }
    }

    private void initPoint(int count,int position) {
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(R.drawable.bg_lit_pot_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            imageView.setLayoutParams(params);
            this.addView(imageView);
            if (position == i) {
                imageView.setSelected(true);
            }
        }
    }

    //设置某个点被选中
    public void setPoint(int position){
        //获取所有子控件遍历将position位置的点选中
        LogUtils.d(position+"");
        int childCount = getChildCount();
        LogUtils.d(childCount+"");
        for (int i = 0; i < childCount; i++) {
            if (position == i) {
                getChildAt(i).setSelected(true);
            }else {
                //其他店设置为未选中
                getChildAt(i).setSelected(false);
            }
        }
    }

    //选中下一个点  将壮性 超出范围处理
    public void setNextPoint(){
        if (mPosition >= mCount-1){
            --mPosition;
        }else {
            setPoint(++mPosition);
        }
    }

    //选中上一个点
    public void setLastPoint(){
        if (mPosition <= 0){
            ++mPosition;
        }else {
            setPoint(--mPosition);
        }
    }


}
