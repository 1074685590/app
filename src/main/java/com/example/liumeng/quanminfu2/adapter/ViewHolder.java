package com.example.liumeng.quanminfu2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 
 * @ClassName ViewHolder
 * @Description View寄存器 (CommonAdapter中用到)
 * @version 1.0.0
 */

public class ViewHolder
{
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;
	private Context mContext;
	private int mLayoutId;

	/**
	 * @param context	
	 * @param parent    父类
	 * @param layoutId	布局id
	 * @param position	当前item索引值
	 */
	public ViewHolder(Context context, ViewGroup parent, int layoutId,
					  int position)
	{
		mContext = context;
		mLayoutId = layoutId;
		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}
	
	/**
	 * @param context	
	 * @param convertView     重用的view
	 * @param parent    	     父类
	 * @param layoutId		     布局id
	 * @param position	               当前item索引值
	 * @return ViewHolder 
	 */
	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position)
	{
		if (convertView == null)
		{
			return new ViewHolder(context, parent, layoutId, position);
		} else
		{
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}
	
	/**
	 * 
	 * @Description 获取索引值
	 * @return  
	 */
	public int getPosition()
	{
		return mPosition;
	}
	
	/**
	 * 
	 * @Description 获取布局id
	 * @return
	 */
	public int getLayoutId(){
		return mLayoutId;
	}

	/**
	 * 通过Id获取view
	 * 
	 * @param viewId
	 * @return
	 */
	public <T extends View> T getView(int viewId)
	{
		View view = mViews.get(viewId);
		if (view == null)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}
	
	/**
	 * 
	 * @Description 返回convertView
	 * @return
	 */
	public View getConvertView()
	{
		return mConvertView;
	}

	/**
	 * 设置TextView的值
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text)
	{
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}
	
	/**
	 * 
	 * @Description 绑定图片资源
	 * @param viewId
	 * @param resId
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int resId)
	{
		ImageView view = getView(viewId);
		view.setImageResource(resId);
		return this;
	}

	/**
	 * 
	 * @Description 绑定图片
	 * @param viewId
	 * @param bitmap
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap)
	{
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * 
	 * @Description 绑定图片
	 * @param viewId
	 * @param drawable
	 * @return
	 */
	public ViewHolder setImageDrawable(int viewId, Drawable drawable)
	{
		ImageView view = getView(viewId);
		view.setImageDrawable(drawable);
		return this;
	}

	/**
	 * 
	 * @Description 设置背景
	 * @param viewId
	 * @param color
	 * @return
	 */
	public ViewHolder setBackgroundColor(int viewId, int color)
	{
		View view = getView(viewId);
		view.setBackgroundColor(color);
		return this;
	}

	/**
	 * 
	 * @Description 设置背景
	 * @param viewId
	 * @param backgroundRes
	 * @return
	 */
	public ViewHolder setBackgroundRes(int viewId, int backgroundRes)
	{
		View view = getView(viewId);
		view.setBackgroundResource(backgroundRes);
		return this;
	}

	/**
	 * @Description 设置文字颜色
	 * @param viewId
	 * @param textColor
	 * @return
	 */
	public ViewHolder setTextColor(int viewId, int textColor)
	{
		TextView view = getView(viewId);
		view.setTextColor(textColor);
		return this;
	}

	/**
	 * @Description 设置文字颜色
	 * @param viewId
	 * @param textColorRes
	 * @return
	 */
	public ViewHolder setTextColorRes(int viewId, int textColorRes)
	{
		TextView view = getView(viewId);
		view.setTextColor(mContext.getResources().getColor(textColorRes));
		return this;
	}
	
	/**
	 * @Description 设置透明度
	 * @param viewId
	 * @param value
	 * @return
	 */
	@SuppressLint("NewApi")
	public ViewHolder setAlpha(int viewId, float value)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			getView(viewId).setAlpha(value);
		} else
		{
			// Pre-honeycomb hack to set Alpha value
			AlphaAnimation alpha = new AlphaAnimation(value, value);
			alpha.setDuration(0);
			alpha.setFillAfter(true);
			getView(viewId).startAnimation(alpha);
		}
		return this;
	}
	
	/**
	 * 
	 * @Description 设置可见状态
	 * @param viewId
	 * @param visible
	 * @return
	 */
	public ViewHolder setVisible(int viewId, boolean visible)
	{
		View view = getView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}
	
	/**
	 * 
	 * @Description 设置超链接
	 * @param viewId
	 * @return
	 */
	public ViewHolder linkify(int viewId)
	{
		TextView view = getView(viewId);
		Linkify.addLinks(view, Linkify.ALL);
		return this;
	}
	
	/**
	 * 
	 * @Description 设置文字字体
	 * @param typeface
	 * @param viewIds
	 * @return
	 */
	public ViewHolder setTypeface(Typeface typeface, int... viewIds)
	{
		for (int viewId : viewIds)
		{
			TextView view = getView(viewId);
			view.setTypeface(typeface);
			view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
		}
		return this;
	}
	
	/**
	 * 
	 * @Description 设置ProgressBar当前进度
	 * @param viewId
	 * @param progress
	 * @return
	 */
	public ViewHolder setProgress(int viewId, int progress)
	{
		ProgressBar view = getView(viewId);
		view.setProgress(progress);
		return this;
	}
	
	/**
	 * 
	 * @Description 设置ProgressBar最大值与当前进度
	 * @param viewId
	 * @param progress
	 * @param max
	 * @return
	 */
	public ViewHolder setProgress(int viewId, int progress, int max)
	{
		ProgressBar view = getView(viewId);
		view.setMax(max);
		view.setProgress(progress);
		return this;
	}
	
	/**
	 * 
	 * @Description 设置ProgressBar最大值
	 * @param viewId
	 * @param max
	 * @return
	 */
	public ViewHolder setMax(int viewId, int max)
	{
		ProgressBar view = getView(viewId);
		view.setMax(max);
		return this;
	}
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param viewId
	 * @param rating
	 * @return
	 */
	public ViewHolder setRating(int viewId, float rating)
	{
		RatingBar view = getView(viewId);
		view.setRating(rating);
		return this;
	}
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param viewId
	 * @param rating
	 * @param max
	 * @return
	 */
	public ViewHolder setRating(int viewId, float rating, int max)
	{
		RatingBar view = getView(viewId);
		view.setMax(max);
		view.setRating(rating);
		return this;
	}
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param viewId
	 * @param tag
	 * @return
	 */
	public ViewHolder setTag(int viewId, Object tag)
	{
		View view = getView(viewId);
		view.setTag(tag);
		return this;
	}
	
	/**
	 * 
	 * @Description (设置tag)
	 * @param viewId
	 * @param key
	 * @param tag
	 * @return
	 */
	public ViewHolder setTag(int viewId, int key, Object tag)
	{
		View view = getView(viewId);
		view.setTag(key, tag);
		return this;
	}
	
	/**
	 * 
	 * @Description (设置Checkable选中状态)
	 * @param viewId
	 * @param checked
	 * @return
	 */
	public ViewHolder setChecked(int viewId, boolean checked)
	{
		Checkable view = (Checkable) getView(viewId);
		view.setChecked(checked);
		return this;
	}

	/**
	 * @Description 设置点击事件
	 * @param viewId
	 * @param listener
	 * @return
	 */
	public ViewHolder setOnClickListener(int viewId,
			View.OnClickListener listener)
	{
		View view = getView(viewId);
		view.setOnClickListener(listener);
		return this;
	}
	
	/**
	 * 
	 * @Description 设置Touch事件监听
	 * @param viewId
	 * @param listener
	 * @return
	 */
	public ViewHolder setOnTouchListener(int viewId,
			View.OnTouchListener listener)
	{
		View view = getView(viewId);
		view.setOnTouchListener(listener);
		return this;
	}
	
	/**
	 * 
	 * @Description 长按监听
	 * @param viewId
	 * @param listener
	 * @return
	 */
	public ViewHolder setOnLongClickListener(int viewId,
			View.OnLongClickListener listener)
	{
		View view = getView(viewId);
		view.setOnLongClickListener(listener);
		return this;
	}

}
