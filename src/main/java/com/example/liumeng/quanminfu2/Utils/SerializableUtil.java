package com.example.liumeng.quanminfu2.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableUtil {
     /**
      * 保存密钥到磁盘
      * @param object
      * @param path
      * @throws Exception
      */
	public static void saveSercetKey(Object object,String path) throws  Exception{
		FileOutputStream fos=new FileOutputStream(new File(path));
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(object);
	}
	
	 /**
     * 读取密钥
     * @param path
     * @throws Exception
     */
	public static Object readSercetKey(String path) throws  Exception{
		FileInputStream fis=new FileInputStream(new File(path));
		ObjectInputStream oos=new ObjectInputStream(fis);
		return oos.readObject();
	}
	
}
