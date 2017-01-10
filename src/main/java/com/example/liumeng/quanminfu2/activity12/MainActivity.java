package com.example.liumeng.quanminfu2.activity12;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.widget.TabHost.TabSpec;

import com.example.liumeng.quanminfu2.Fragment.BlankFragment;
import com.example.liumeng.quanminfu2.R;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//1.初始化TabHost
		FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.tabhost);
		//2.使tabHost和FrameLayout关联
		tabHost.setup(this, getSupportFragmentManager(),R.id.tabcontent);
		//3.添加tab和其对应的fragment
		TabSpec tabSpec = tabHost.newTabSpec("all");
		tabSpec.setIndicator("综合");
		Bundle bundle = new Bundle();
		bundle.putString("text","综合界面");
		tabHost.addTab(tabSpec, BlankFragment.class,bundle);
		
		TabSpec tabSpec2 = tabHost.newTabSpec("tweet");
		tabSpec2.setIndicator("动弹");
		Bundle bundle2 = new Bundle();
		bundle2.putString("text","动弹界面");
		tabHost.addTab(tabSpec2, BlankFragment.class,bundle2);
		
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
		.replace(R.id.tabhost,new BlankFragment())
		.commitAllowingStateLoss();//允许状态丢失，防止崩溃
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
