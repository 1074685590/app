package com.example.liumeng.quanminfu2.activity12;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.bean.User;
import com.example.liumeng.quanminfu2.db.MySQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ActivityDatabases extends AppCompatActivity {
    private static final String DB_NAME = "user.db";
    private static final int VERSION = 1;
    private MySQLiteOpenHelper openHelper;
    private SQLiteDatabase     database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_databases);

        openHelper = new MySQLiteOpenHelper(this, DB_NAME, null, VERSION);
        database = openHelper.getReadableDatabase();
    }

    public void insert(View view){
        SQLiteDatabase database = openHelper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("c_name", "隔壁老马");
        values.put("c_age", 23+new Random().nextInt(100));

        long insert = database.insert("t_user", null, values);
        Toast.makeText(this, "insert成功："+insert, Toast.LENGTH_SHORT).show();

    }

    public void query(View view){
        List<User> list = new ArrayList<User>();

        SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();

        //		select * from t_user where c_age>=? order by c_age desc;

        Cursor cursor = sqLiteDatabase.query("t_user", new String[]{"c_age","c_name"}, " c_age>=?", new String[]{"25"}, null, null, "c_age desc");

        while(cursor.moveToNext()){
            User user = new User();
            user.name = cursor.getString(1);
            user.age = cursor.getInt(0);

            list.add(user);
        }
        cursor.close();

        Log.d("tag", list.toString());
        //关闭数据库
        sqLiteDatabase.close();

    }
}
