package com.example.liumeng.quanminfu2.serviceandbroad;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.liumeng.quanminfu2.db.MySQLiteOpenHelper;

public class MyContentProvider extends ContentProvider {

    private MySQLiteOpenHelper mHelper;

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mHelper = new MySQLiteOpenHelper(getContext());
        return true;  //建议返回true，代表初始化成功
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        long id = readableDatabase.insert("t_user", null, values);
        Uri appendedId = ContentUris.withAppendedId(uri, id);
        //发出通知
        getContext().getContentResolver().notifyChange(appendedId, null);
        return appendedId;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }




    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
