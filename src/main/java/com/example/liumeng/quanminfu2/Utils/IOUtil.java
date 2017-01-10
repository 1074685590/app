package com.example.liumeng.quanminfu2.Utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class IOUtil {
	
	public static void print(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(bytes[i]).append(" ");
		}
		System.out.println(sb);
	}
	
	public static String file2String(String path) throws IOException {
		FileReader reader = new FileReader(new File(path));
		char[] buffer = new char[1024];
		int len = -1;
		StringBuffer sb = new StringBuffer();
		while ((len = reader.read(buffer)) != -1) {
			sb.append(buffer, 0, len);
		}
		return sb.toString();
	}
	
	public static void string2File(String data, String path){
		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(path));
			writer.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public static String inputStream2String(InputStream in) throws IOException {
		int len = -1;
		byte[] buffer = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while((len = in.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		baos.close();
		
		return baos.toString("UTF-8");
	}

	/**
	 * 写入文件
	 * @param inputStream
	 * @param downFile
	 * @throws IOException
	 */
	public static void writeFile(InputStream inputStream, File downFile)
			throws IOException {
		//通过输出流去写
		FileOutputStream fileOutputStream = new FileOutputStream(downFile);

		//定义缓存
		byte[] buffer = new byte[1024];

		//定义读取的长度
		int len = -1;

		//循环的去读写
		while ((len = inputStream.read(buffer)) != -1) {
			//写入文件
			fileOutputStream.write(buffer, 0, len);

		}

		//关流
        /*           if (inputStream != null) {
                       inputStream.close();
                       inputStream = null;
                   }

                   if (fileOutputStream != null) {
                       fileOutputStream.close();
                       fileOutputStream = null;
                   }*/

		//关闭流的方法
		closeStream(inputStream);
		closeStream(fileOutputStream);
	}

	/**
	 * 关闭流的方法
	 */
	public static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			}
			catch (IOException e) {
 				e.printStackTrace();
			}
		}

	}
	
	
}
