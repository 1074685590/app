package com.example.liumeng.quanminfu2.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;
import com.example.liumeng.quanminfu2.android.BaseAdapterHolder;
import com.example.liumeng.quanminfu2.android.QuickAdapter;
import com.example.liumeng.quanminfu2.bean.QuickAdapterBean;

import java.util.List;

/**
 * Created by liumeng on 2017/1/9 on 9:52
 */

public class MyQuickAdapter extends QuickAdapter<QuickAdapterBean> {
    private Context mContext;

    public MyQuickAdapter(Context context, int layoutResId, List<QuickAdapterBean> data) {
        super(context, R.layout.item_quick_adapter, data);
    }

    public MyQuickAdapter(Context context, List<QuickAdapterBean> data) {
        this(context, R.layout.item_quick_adapter, data);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseAdapterHolder helper, QuickAdapterBean item) {
//        ((ImageView)helper.getView(R.id.quick_adapter_iv)).setImageResource(R.drawable.account_transfer);
//        ((TextView)helper.getView(R.id.quick_adapter_tv)).setText(item.title);
//        ((Button)helper.getView(R.id.quick_adapter_btn)).setText(item.btnText);
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.account_transfer);
        helper.setImageDrawable(R.id.quick_adapter_iv, drawable);
        helper.setText(R.id.quick_adapter_tv,item.title);
        helper.setText(R.id.quick_adapter_btn,item.btnText);
        helper.setOnClickListener(R.id.quick_adapter_btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(mContext,"我被点击了");
            }
        });
    }
}
