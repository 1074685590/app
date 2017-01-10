package com.example.liumeng.quanminfu2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;

public class OpenResultFragment extends LazyFragment{
        // 标志位，标志已经初始化完成。  
        private boolean isPrepared;  
      
        @Override  
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LogUtils.d("onCreateView");
            View view = inflater.inflate(R.layout.fragment_open_result, container, false);
            //XXX初始化view的各控件  
            isPrepared = true;
            lazyLoad();  
            return view;  
        }  
      
        @Override  
        protected void lazyLoad() {  
            if(!isPrepared || !isVisible) {  
                return;  
            }  
            //填充各控件的数据  
        }  
      
    }  