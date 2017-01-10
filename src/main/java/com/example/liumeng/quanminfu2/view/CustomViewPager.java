package com.example.liumeng.quanminfu2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;

/**
 * Created by liumeng on 2016/12/19 on 17:05
 * 1.为什么当ontouchevent不是返回true的时候走不到手势识别事件   返回false的时候只走down, 返回true的时候都走
 * 2.如何判断是水平事件还是垂直事件  事件拦截在move中判断是x大还是y大,y大就不拦截.
 */
public class CustomViewPager extends ViewGroup {

    private Scroller        mScroller; //只用来做数据的模拟，而不做任何移动操作
    private GestureDetector mDetector;
    private int             mStartX;
    private int             mStartY;

    public CustomViewPager(Context context) {
        super(context);
        init();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //当手指滑动的时候实时调用
        /**
         *
         * @param e1  这个滑动事件的，按下事件
         * @param e2  当前的移动事件
         * @param distanceX  前一个移动事件与当前的移动事件的间距 = 前一个移动事件的x的值 - 当前移动事件的x值；
         * @param distanceY  前一个移动事件与当前的移动事件的间距 = 前一个移动事件的y的值 - 当前移动事件的y值；
         * @return
         */ //scrollto、srollby(viewgroup，要进行界面的移动，只能通过这2个方法进行移动)
        //scrollto:将屏幕的左上角的点移动指定的位置（绝对位置）
        //scrollby:将当前屏幕的左上角的点加上指定的值，移动到计算过后的位置（相对位置）
        //注意x的平移，只能从0到图片的张数-1 * 屏幕的宽(left)
        mDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            //当手指滑动的时候实时调用

            /**
             *
             * @param e1  这个滑动事件的，按下事件
             * @param e2  当前的移动事件
             * @param distanceX  前一个移动事件与当前的移动事件的间距 = 前一个移动事件的x的值 - 当前移动事件的x值；
             * @param distanceY  前一个移动事件与当前的移动事件的间距 = 前一个移动事件的y的值 - 当前移动事件的y值；
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //scrollto、srollby(viewgroup，要进行界面的移动，只能通过这2个方法进行移动)
                //scrollto:将屏幕的左上角的点移动指定的位置（绝对位置）
                //scrollby:将当前屏幕的左上角的点加上指定的值，移动到计算过后的位置（相对位置）
                ToastUtil.showToast(getContext(), distanceX + "");
                scrollBy((int) distanceX, 0);
                //注意x的平移，只能从0到图片的张数-1 * 屏幕的宽(left)
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        mScroller = new Scroller(getContext());
    }

    //viewgroup必须要实现排版布局方法

    /**
     * 根据当前的布局文件，将对应的左上右下传递进来
     *
     * @param changed
     * @param l       0
     * @param t       0
     * @param r       屏幕的宽
     * @param b       屏幕的高
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历所有子控件，将子控件显示到屏幕的正中间
        for (int i = 0; i < this.getChildCount(); i++) {
            View childAt = this.getChildAt(i);
            childAt.layout(0 + i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }

    //测量，它只对自己第一层子控件进行测量，如果子控件内部还有控件，需要自己手动测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //遍历所有子控件，手动调用测量方法
        for (int i = 0; i < this.getChildCount(); i++) {
            View childAt = this.getChildAt(i);
            childAt.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件交由手势识别器，进行处理
        mDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.d("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.d("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:                //获取当前屏幕左上角的位置
                LogUtils.d("ACTION_UP");
                int scrollX = getScrollX();
                System.out.println("scrollX:" + scrollX);                //计算得出将要移动到的位置
                int position = (scrollX + getWidth() / 2) / getWidth();
                System.out.println("position:" + position);                //移动界面[0,子控件个数-1]
                // 设置右边界
                if (position > getChildCount() - 1) {
                    position = getChildCount() - 1;
                }
                //                scrollTo(position * getWidth(), 0);
                setCurrentItem(position);
                break;
        }
        return true;
    }

    //让viewpager切换到对应的界面
    public void setCurrentItem(int position) {

        //起始点就是手指松开时，屏幕的左上角的x的值
        int startX = getScrollX();
        //结束点就是将要移动到的对应索引界面的角标*屏幕的宽
        int endX = position * getWidth();
        int dx = endX - startX;
        //偏移值越大，时间就越长
        int duration = Math.abs(dx) * 2;
        mScroller.startScroll(startX, 0, dx, 0, duration);//根据起始点与偏移值进行模拟数据
        invalidate();//界面重绘->computeScroll只执行一次
    }

    @Override
    public void computeScroll() {
        //如果要获取最新的模拟数据的值，必须要先调用一次computeScrollOffset方法
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();//获取当前正在模拟得到x轴的模拟值
            System.out.println("currX:" + currX);
//            mScroller.getCurrY()
            scrollTo(currX, 0);
            invalidate();
        }
    }

    //事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //进行判断水平移动还是垂直移动
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //将手指按下事件，还给手势识别进行，起始点的设置，这样界面滑动才会正常
                mDetector.onTouchEvent(ev);
                mStartX = (int) ev.getX();
                mStartY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();
                //计算间距并比较
                int diffX = endX - mStartX;
                int diffY = endY - mStartY;
                Log.d("liumeng", "diffX = " + diffX + ";  diffY" + diffY);
                if (Math.abs(diffX) > Math.abs(diffY)) {//水平移动，给viewpager
                    return true;
                } else {//垂直移动，给scrollview
                    return false;
                }

            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }


}
