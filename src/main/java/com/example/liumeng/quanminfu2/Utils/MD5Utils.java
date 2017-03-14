package com.example.liumeng.quanminfu2.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liumeng on 2017/1/13 on 9:36
 */

public class MD5Utils {
    /**
     * 生成一个md5文件名
     * @param url
     * @return
     */
    public static String getMd5Name(String url) {
        StringBuffer stringBuffer = new StringBuffer();
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            byte[] digest = messageDigest.digest();
            // 把byte转成16进制
            for (int i = 0; i < digest.length; i++)
            {
//                System.out.println("md5" + Integer.toHexString(digest[i] & 0xff));
                stringBuffer.append(Integer.toHexString(digest[i] & 0xff));
            }

            return stringBuffer.toString();

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
