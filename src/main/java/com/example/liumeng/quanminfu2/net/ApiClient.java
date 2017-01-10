package com.example.liumeng.quanminfu2.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by TangYang on 2016/8/2.
 * 封装了三方请求框架
 */
public class ApiClient {

    // 初始化请求队列
    public static RequestQueue requestQueue;

    public interface OnResultListener {
        public void onSuccess(String s);

        public void onError(Throwable t);
    }

    // 请求框架的初始化
    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static void get(String url, final OnResultListener mListener) {
        request(url, Request.Method.GET, null, null, mListener);
    }

    public static void get(String url, Map<String, String> params, final OnResultListener mListener) {
        request(url, Request.Method.GET, null, params, mListener);
    }

    public static void post(String url, Map<String, String> params, final OnResultListener mListener) {
        request(url, Request.Method.POST, null, params, mListener);
    }

    public static void request(String url, int method, final Map<String, String> headers, final Map<String, String> params, final OnResultListener mListener) {
        if (requestQueue.getCache().get(url) != null) {
            // 有缓存, 读缓存
            byte[] data = requestQueue.getCache().get(url).data;
            mListener.onSuccess(new String(data));
        } else {
            // 创建请求对象,
            MyStringRequest stringRequest = new MyStringRequest(method, // 请求方式GET
                    url, // 请求路径
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            // 可能 7s 以后才执行
                            //                        tv_text.setText(s);
                            mListener.onSuccess(s);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    //                        tv_text.setText("请求失败: " + volleyError.getMessage());
                    mListener.onError(volleyError);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    if (headers != null)
                        return headers;

                    return super.getHeaders();
                }

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    if (params != null)
                        return params;

                    return super.getParams();
                }
            };

            // 把请求加入到请求队列, 执行请求, 异步
            requestQueue.add(stringRequest);
        }
    }

}
