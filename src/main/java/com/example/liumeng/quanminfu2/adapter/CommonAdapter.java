package com.example.liumeng.quanminfu2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @ClassName CommonAdapter
 * @Description  通用适配器
 * @author
 * @version 1.0.0
 * @param <T> 数据类型
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	private int layoutId;
	
	/**
	 * @param context 			上下文
	 * @param datas				数据源
	 * @param layoutId			item布局Id
	 */
	public CommonAdapter(Context context, List<T> datas, int layoutId)
	{
		this.mContext = context;
		mInflater = LayoutInflater.from(context);
		this.mDatas = datas;
		this.layoutId = layoutId;
	}

	@Override
	public int getCount()
	{
		return mDatas == null ? 0 : mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
				layoutId, position);
		convert(holder, getItem(position), position);
		return holder.getConvertView();
	}
	
	/**
	 * @Description 	将数据与条目上的控件绑定
	 * @param holder
	 * @param t			带绑定的数据
	 * @param position	索引值
	 */
	public abstract void convert(ViewHolder holder, T t, int position);
	
	/**
	 * 
	 * @Description 设置数据
	 * @param data
	 */
	public void setData(List<T> data) {
		mDatas = data;
		notifyDataSetChanged();
	}
	
	/**
	 * @Title: addData 
	 * @Description: 添加数据
	 * @param data
	 * @return: void
	 */
	public void addData(List<T> data) {
		mDatas.addAll(data);
		notifyDataSetChanged();
	}
	
	/**
	 * @Title: getDatas 
	 * @Description: TODO
	 * @return
	 * @return: List<T>
	 */
	public List<T> getDatas() {
		return mDatas;
	}
}
