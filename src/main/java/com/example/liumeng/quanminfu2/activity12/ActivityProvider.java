package com.example.liumeng.quanminfu2.activity12;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.liumeng.quanminfu2.R;
import com.example.liumeng.quanminfu2.Utils.LogUtils;
import com.example.liumeng.quanminfu2.Utils.ToastUtil;
import com.example.liumeng.quanminfu2.bean.Contact;

import java.util.ArrayList;
import java.util.List;

public class ActivityProvider extends AppCompatActivity {

    private ContentObserver mObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        if (savedInstanceState != null) {
            String liumeng = savedInstanceState.getString("liumeng", "");
            ToastUtil.showToast(this,liumeng);
        }
        registerObsever();
    }

    //注册内容观测者
    private void registerObsever() {
        mObserver = new MyObserver(new Handler());
        getContentResolver().registerContentObserver(Uri.parse("content://com.liumeng.provider"),true, mObserver);
    }

    class MyObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            LogUtils.d("数据库发生了变化 一个参数  selfChange" +selfChange);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            LogUtils.d("数据库发生了变化 两个参数 selfChange"+selfChange+"   uri = "+uri);
        }
    }


    public void query(View view) {
        List<Contact> contacts = new ArrayList<>();
		/*
		 * 1. 查询raw_contacts表，获取所有的联系人的id（contact_id）
		 */
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"contact_id"}, null, null, null);

        while(cursor.moveToNext()){
            //每while一次就得new一个对象，因为一个contact_id就是一个联系人
            Contact contact = new Contact();
            int contact_id = cursor.getInt(0);
			/*
			 * 2. 根据contact_id，去查询data表中的raw_contact_id=contact_id的所有记录
			 */
            //注意：data表中虽然是mimetype_id，但是操作的时候，只能用mimetype作为其字段名称。
            Cursor dataCursor = getContentResolver().query(Uri.parse("content://com.android.contacts/data"), new String[]{"data1","mimetype"}, "raw_contact_id=?", new String[]{contact_id+""}, null);

            while(dataCursor.moveToNext()){
                String data = dataCursor.getString(0);
                String mimeType = dataCursor.getString(1);
                //根据mimeType确定当前的数据是当前联系人的哪个字段
                if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) {
                    contact.phone = data;
                }else if ("vnd.android.cursor.item/postal-address_v2".equals(mimeType)) {
                    contact.address = data;
                }else if ("vnd.android.cursor.item/name".equals(mimeType)) {
                    contact.name = data;
                }else if ("vnd.android.cursor.item/email_v2".equals(mimeType)) {
                    contact.email = data;
                }
            }
            dataCursor.close();
            contacts.add(contact);
        }
        cursor.close();
        Log.d("tag", contacts.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mObserver != null) {
            getContentResolver().unregisterContentObserver(mObserver);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("liumeng","我是刘蒙");
    }
}
