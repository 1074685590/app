package com.example.liumeng.quanminfu2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.liumeng.quanminfu2.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liumeng on 2016/12/19 on 16:02
 */
public class RefreshListView extends ListView {
    private final int DOWN_PULL = 0;//下拉刷新的状态值
    private final int RELEASE_REFRESH = 1;//松开刷新的状态值
    private final int REFRESHING = 2;//正在刷新的状态值

    private View mHeaderView;//头布局视图
    private ImageView mHeaderIv;//箭头图片
    private ProgressBar mPB;//进度圈
    private TextView mTv_state;//状态文本
    private TextView mTvTime;//时间文本
    private int mHeaderViewHeight;//头布局的高度
    private View mFooterView;
    private int mFooterViewHeight;
    private RotateAnimation mUp;//向上动画
    private RotateAnimation mDown;//向下的动画
    private boolean isLoadMore = false;//用来记录是否处于加载更多的状态
    private OnFreshListener mOnFreshListener;//刷新监听接口对象
    private int currentState = DOWN_PULL;//记录当前的状态，默认是下拉刷新
    private int mDownY;

    public RefreshListView(Context context) {
        super(context);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    //初始化方法
    private void init() {
        //在控件创建时，就要给listview添加一个头布局
        initHeaderView();
        //在控件创建时，就要给listview添加一个脚布局
        initFooterView();
        //初始化动画
        initAnimation();

        //给listview设置滚动监听
        this.setOnScrollListener(new OnScrollListener() {
            /**
             * OnScrollListener.SCROLL_STATE_FLING;//当手指用力滑动listview，手指离开屏幕，lv进入一个惯性滑动状态  2
             OnScrollListener.SCROLL_STATE_TOUCH_SCROLL;//手指触摸着屏幕上下滚动的状态  1
             OnScrollListener.SCROLL_STATE_IDLE;//当手指离开屏幕，listview处于停滞状态   0
             * @param view
             * @param scrollState
             */
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                System.out.println("scrollState:" + scrollState);  //当手指离开屏幕&&滑动到最后一行时，进行加载更多
                int lastVisiblePosition = RefreshListView.this.getLastVisiblePosition();
                if (scrollState != OnScrollListener.SCROLL_STATE_TOUCH_SCROLL &&
                        lastVisiblePosition == (getCount() - 1) && !isLoadMore) {
                    System.out.println("加载更多");
                    isLoadMore = true;
                    //显示脚布局
                    mFooterView.setPadding(0, 0, 0, 0);
                    RefreshListView.this.setSelection(getCount() - 1);//直接让listview显示到特定的条目上
                    if (mOnFreshListener != null) {
                        mOnFreshListener.onLoadMore();
                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.listview_header, null);
        mHeaderIv = (ImageView) mHeaderView.findViewById(R.id.iv_arrow);
        mPB = (ProgressBar) mHeaderView.findViewById(R.id.pb);
        mTv_state = (TextView) mHeaderView.findViewById(R.id.tv_state);
        mTvTime = (TextView) mHeaderView.findViewById(R.id.tv_time);
        //先获取头布局的高度
        mHeaderView.measure(0, 0);//让系统去测量一下控件
        //获取一个测量后的高度
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        //给头布局设置内边paddingtop为高度的负数
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
        this.addHeaderView(mHeaderView);
    }

    private void initFooterView() {
        mFooterView = View.inflate(getContext(), R.layout.listview_footer, null);
        //获取高度
        mFooterView.measure(0, 0);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0, -mFooterViewHeight, 0, 0);
        this.addFooterView(mFooterView);
    }

    private void initAnimation() {
        mUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mUp.setDuration(500);
        mUp.setFillAfter(true);

        mDown = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mDown.setDuration(500);
        mDown.setFillAfter(true);
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(new Date());
    }

    public interface OnFreshListener {
         void onDownPull();

         void onLoadMore();
    }

    public void setOnFreshListener(OnFreshListener listener) {
        this.mOnFreshListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //如果当前处于正在刷新状态，那就不进行上下移动操作
                if (currentState == REFRESHING) {
                    break;
                }
                //1、获取起始点
                mDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //2、获取移动后的结束点
                int moveY = (int) ev.getY();
                //3、计算间距
                int diffY = moveY - mDownY;
                //4、计算出头布局将要显示的高度=当前的头布局的高度+间距
                int paddingtop = -mHeaderViewHeight + diffY;
                //                System.out.println("paddingtop:"+paddingtop);
                //获取当前可见条目的第一个索引
                int firstVisiblePosition = this.getFirstVisiblePosition();
                //                System.out.println("firstVisiblePosition" + firstVisiblePosition);
                //大于头布局 的高度的时候，才需要给头布局设置 paddingtop来让头布局显示&当前可见条目的第一个索引为0 的时候才显示头布局
                if (paddingtop > -mHeaderViewHeight && firstVisiblePosition == 0) {
                    //判断当前是下拉还是松开刷新
                    if (paddingtop > 0 && currentState == DOWN_PULL) {
                        System.out.println("松开刷新");
                        currentState = RELEASE_REFRESH;
                        updateViewByState();
                    } else if (paddingtop <= 0 && currentState == RELEASE_REFRESH) {
                        currentState = DOWN_PULL;
                        System.out.println("下拉刷新");
                        updateViewByState();
                    }
                    //5、将高度设置给头布局
                    mHeaderView.setPadding(0, paddingtop, 0, 0);
                    return true;
                } else {
                    mDownY = moveY;
                }
                break;
            case MotionEvent.ACTION_UP:
                //根据当前的状态，进行手指抬起的操作
                if (currentState == DOWN_PULL) {
                    //让头布局直接隐藏
                    mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {
                    //进入正在刷新状态
                    currentState = REFRESHING;
                    updateViewByState();
                    if (mOnFreshListener != null) {//手指抬起的时候将下拉刷新操作回调给主界面
                        mOnFreshListener.onDownPull();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    //根据当前的状态修改界面
    private void updateViewByState() {
        switch (currentState) {
            case DOWN_PULL://下拉刷新
                //箭头向下的动画
                mHeaderIv.startAnimation(mDown);
                //修改文本
                mTv_state.setText("下拉刷新");
                break;
            case RELEASE_REFRESH://松开刷新
                //箭头向上的动画
                mHeaderIv.startAnimation(mUp);
                //修改文本
                mTv_state.setText("松开刷新");
                break;
            case REFRESHING://正在刷新
                //箭头隐藏，进度圈显示
                mHeaderIv.clearAnimation();
                mHeaderIv.setVisibility(INVISIBLE);
                mPB.setVisibility(VISIBLE);
                //头布局刚好完全显示
                mHeaderView.setPadding(0, 0, 0, 0);
                //修改文本
                mTv_state.setText("正在刷新");
                break;

        }
    }

    //刷新完成后的回调方法
    public void onFinish() {
        if (isLoadMore) {//加载更多
            isLoadMore = false;
            mFooterView.setPadding(0,0,0,-mFooterViewHeight);

        } else {//下拉刷新
            mPB.setVisibility(INVISIBLE);
            mHeaderIv.setVisibility(VISIBLE);
            mTv_state.setText("下拉刷新");
            currentState = DOWN_PULL;
            mTvTime.setText("最新刷新时间：" + getCurrentTime());
            mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
        }

    }
}
