package com.example.liumeng.quanminfu2.Utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
 
    private static Toast mToast;
 
    public static void showToast(Context context, String str) {
        if (mToast == null) {
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(str);
        mToast.show();
    }
}