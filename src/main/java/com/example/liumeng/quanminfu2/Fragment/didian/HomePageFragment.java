package com.example.liumeng.quanminfu2.Fragment.didian;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {


    private ConvenientBanner mConvenientBanner;
    private TextSwitcher     mHome_page_ts;
    private int              curStr;
    private Timer            timer;
    // 要显示的文本
    String[] strs = new String[]
            {
                    "静夜思",
                    "床前明月光",
                    "疑是地上霜",
                    "举头望明月",
                    "低头思故乡"
            };
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    next(null); //取得下标值
                    break;
                case 2:
                    sendEmptyMessageDelayed(2, 5000);
                    next(null);
                    break;
                default:
                    break;
            }
        }

        ;
    };
    private RecyclerView mHome_page_rv;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtils.d("HomePageFragment");
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initTextSwicher();
    }

    private void initTextSwicher() {
        mHome_page_ts.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                TextView tv = new TextView(getActivity());
                tv.setTextSize(20);
                // 字体颜色品红
                tv.setTextColor(Color.MAGENTA);
                tv.setEllipsize(TextUtils.TruncateAt.END);
                return tv;
            }
        });
        //调用next方法显示下一个字符串
        //方法1 使用TimerTask
//        setTextStillTime(5000);
//        方法2 使用handler发空消息形成循环
        handlerMethod();
    }

    private void handlerMethod() {
        mHandler.sendEmptyMessageDelayed(2,500);
    }

    // 事件处理函数，控制显示下一个字符串
    public void next(View source) {
        mHome_page_ts.setText(strs[curStr++ % strs.length]);
    }

    private void initView() {
        mConvenientBanner = (ConvenientBanner) getActivity().findViewById(R.id.convenientBanner);
        mHome_page_ts = (TextSwitcher) getActivity().findViewById(R.id.home_page_ts);
        mHome_page_rv = (RecyclerView)getActivity().findViewById(R.id.home_page_rv);
        initConvenientBanner();
    }

    private void initConvenientBanner() {
        mConvenientBanner.startTurning(2000000);
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        List<Integer> localImages = new ArrayList<>();
        localImages.add(R.mipmap.a1);
        localImages.add(R.mipmap.a2);
        localImages.add(R.mipmap.a3);
        localImages.add(R.mipmap.a4);
        localImages.add(R.mipmap.a5);
        localImages.add(R.mipmap.a6);
        mConvenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.point, R.drawable.red_point})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        mConvenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtil.showToast(getActivity(), "position的值为" + position);
            }
        });

    }

    public void setTextStillTime(long time) {
        if (timer == null) {
            timer = new Timer();
        }
        timer.scheduleAtFixedRate(new MyTask(), 1, time);//每5秒更新
    }

    class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            imageView.setImageResource(data);
        }
    }

    class MyTask extends TimerTask {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
        }
    }
}
