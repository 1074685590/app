package com.example.liumeng.quanminfu2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	

	/**
	 * 
	 * @param context
	 * @param name 数据库文件的名称
	 * @param factory 游标工厂 null
	 * @param version 数据库文件的版本号必须大于等于1
	 */
	public MySQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public MySQLiteOpenHelper(Context context){
		super(context,"my.db",null,1);

	}

	//初始化表结构
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table t_user(_id integer primary key,c_name varchar(20),c_age integer)");
		Log.d("tag", "onCreate了");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
