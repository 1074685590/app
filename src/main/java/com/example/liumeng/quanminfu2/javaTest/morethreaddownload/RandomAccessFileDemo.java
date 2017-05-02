package com.example.liumeng.quanminfu2.javaTest.morethreaddownload;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo{
    public static void main(String[] args) throws IOException {
//        test();
        write();
        read();
        randomWrite();
    }

    private static void test() {
        File file = new File("aaaaaaa.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean exists = file.exists();
        System.out.println(exists);
    }

    //随机写入数据，可以实现已有数据的修改。
    public static void randomWrite()throws IOException{
        RandomAccessFile raf = new RandomAccessFile("random.txt","rw");
        raf.seek(8*4);
        System.out.println("pos:"+raf.getFilePointer());
        raf.write("王武".getBytes());
        raf.writeInt(102);
        raf.close();
    }
    public static void read()throws IOException{
        RandomAccessFile raf = new RandomAccessFile("random.txt","r");//只读模式。
        //指定指针的位置。
        raf.seek(8*1);//实现随机读取文件中的数据。注意：数据最好有规律。
        System.out.println("pos1 :"+raf.getFilePointer());
        byte[] buf = new byte[4];
        System.out.println("pos2:"+raf.getFilePointer());
        raf.read(buf);
        System.out.println("pos3:"+raf.getFilePointer());
        String name = new String(buf);
        System.out.println("pos4:"+raf.getFilePointer());
        int age = raf.readInt();
        System.out.println("pos5:"+raf.getFilePointer());
        System.out.println(name+"::"+age);
        System.out.println("pos6:"+raf.getFilePointer());
        raf.close();
    }
    public static void write()throws IOException{
        //rw：当这个文件不存在，会创建该文件。当文件已存在，不会创建。所以不会像输出流一样覆盖。
        RandomAccessFile raf = new RandomAccessFile("random.txt","rw");//rw读写模式
        //往文件中写入人的基本信息，姓名，年龄。
        raf.write("张三".getBytes());
        raf.writeInt(97);
        raf.close();
    }
}