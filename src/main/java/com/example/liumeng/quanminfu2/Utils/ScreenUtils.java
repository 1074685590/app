package com.example.liumeng.quanminfu2.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.liumeng.quanminfu2.global.LiumengApp;

/**
 * 类描述: 获取与屏幕相关的一些参数
 *
 */
public class ScreenUtils {
	
	private ScreenUtils(){}
	
	/**
	 * 获取屏幕宽高
	 * 
	 * @param context
	 * 
	 * @return width and height 
	 */
	@SuppressWarnings("deprecation")
	public static int[] getScreenWidthHeight(Context context) {
		WindowManager wm = (WindowManager) LiumengApp.getAppContext().getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth(); 
	    int height = wm.getDefaultDisplay().getHeight();
	    
	    return new int[]{width, height};
	}
	
	/**
	 * 获取屏幕像素密度
	 * 
	 * @param context
	 * @return density
	 */
	public static float getDensity(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metric);
        
		return metric.density;
	}
	

	/**
	 * 获取屏幕像素
	 * @param context
	 * @return
	 */
	public static int[] getPixels(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metric);
		return new int[]{metric.widthPixels, metric.heightPixels};
	}
	
	/**
	 * 获得状态栏的高度
	 * 
	 * @param context
	 * @return the Statu's Height
	 */
	public static int getStatusHeight(Context context) {

		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

}
