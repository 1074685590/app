package com.example.liumeng.quanminfu2.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.liumeng.quanminfu2.R;

/**
 * Created by liumeng on 2016/12/19 on 10:44
 */
public class SwichButton extends View {
    private boolean isOpen = false;//用来记录开关的状态，默认false关闭
    private  Paint  mPaint;
    private Bitmap mSwitchBitmap;//滑动开关的背景图片对象
    private  Bitmap mSlidBitmap;//滑动开关的滑块图片对象
    private int slidLeft = 0;//用来记录滑块的left值
    private int mMax_left;//滑块的left的最大值
    private onCheckChangeListener mListener;
    private int mStartX;
    private int mMoveX;//用来记录手指在控件上移动的间距
    private boolean isClick = false;//用来记录当前是点击事件还是触摸事件，true为点击事件，

    public SwichButton(Context context) {
        super(context);
        init();
    }

    public SwichButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        //获取xml中设置的属性
        String namespace = "http://schemas.android.com/apk/res-auto";
        isOpen = attrs.getAttributeBooleanValue(namespace, "isOpen", false);
        if (isOpen) {
            slidLeft = mMax_left;
        } else {
            slidLeft = 0;
        }

        int slidBitmapId = attrs.getAttributeResourceValue(namespace, "slidBitmap", -1);
        if (slidBitmapId > -1) {
            mSlidBitmap = BitmapFactory.decodeResource(getResources(), slidBitmapId);
        }
    }
    //带有样式的时候，使用这个构造方法
    public SwichButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        //在初始化方法中，创建出对应的bitmap对象
        mSwitchBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_background);
        mSlidBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.slide_button);
        //计算出left的最大值
        mMax_left = mSwitchBitmap.getWidth() - mSlidBitmap.getWidth();
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick) {
                    if (isOpen) {
                        slidLeft = 0;
                    } else {
                        slidLeft = mMax_left;
                    }
                    isOpen = !isOpen;
                    if (mListener != null) {
                        mListener.onCheckChange(isOpen);
                    }
                    invalidate();//刷新方法，强制view进行重新绘制，重新调用ondraw方法
                }
            }
        });
    }

    //测量方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //有什么用  设置控件的宽和高
        setMeasuredDimension(mSwitchBitmap.getWidth(),mSwitchBitmap.getHeight());
    }


    /**
     * @param canvas 画布，将控件绘制到画布上后，才能显示到屏幕上
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //        canvas.drawRect(0, 0, 200, 200, mPaint);
        //绘制控件的背景
        canvas.drawBitmap(mSwitchBitmap,0,0,null);
        //绘制控件的滑块图片
        canvas.drawBitmap(mSlidBitmap,slidLeft,0,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //1、记录起始点：按下的点startX
                mStartX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //2、记录移动后的结束点：moveX
                int moveX = (int) event.getX();
                //3、计算出间距
                int diffX = moveX - mStartX;
                //记录手指移动的间距(注意间距是有正负)
                mMoveX = mMoveX + Math.abs(diffX);
                //4、更新slidleft
                slidLeft = slidLeft + diffX;
                if (slidLeft < 0) {//设置左边界
                    slidLeft = 0;
                }
                if (slidLeft > mMax_left) {//设置右边界
                    slidLeft = mMax_left;
                }
                //5、刷新界面
                invalidate();
                //6、更新起始点
                mStartX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                if (mMoveX > 5) {//移动的总间距大于5个像素，触摸事件
                    System.out.println("触摸事件");
                    isClick = false;
                } else {//移动的总间距小于等于5个像素，点击事件
                    System.out.println("点击事件");
                    isClick = true;
                }
                //手指抬起后，总间距归0
                mMoveX = 0;
                if (!isClick) {//如果是触摸事件才走下面的代码
                    //计算出中心线
                    int center = mMax_left/2;
                    if (slidLeft > center) {
                        System.out.println("开关打开");
                        isOpen = true;
                        slidLeft = mMax_left;
                    } else {
                        System.out.println("开关关闭");
                        isOpen = false;
                        slidLeft = 0;
                    }
                    invalidate();
                    if (mListener != null) {
                        mListener.onCheckChange(isOpen);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);  //自己来处理触摸事件
    }

    public interface onCheckChangeListener{
        void onCheckChange(boolean isOpen);
    }

    public void setOnCheckChangeListener(onCheckChangeListener listener){
        this.mListener = listener;
    }
}
