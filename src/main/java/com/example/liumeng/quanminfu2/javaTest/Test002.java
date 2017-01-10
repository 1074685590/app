package com.example.liumeng.quanminfu2.javaTest;

/**
 * Created by liumeng on 2017/1/3 on 17:11
 */
public class Test002 {
    public static void main(String[] args) {
//        String info = "{{{{{{[][]{fasdfasfd}fasfdasf()(sdfasdfsad)()";
//        System.out.println(isMatch(info));

        User user = new User("刘蒙", 25);
        String student = "{'age':24,'name':'刘蒙'}";
//        JSONObject map = JSONObject.fromObject(user);
    }

    public static boolean isMatch(String info) {
        MyStack2 ms = new MyStack2(info.length() / 2);
        for (int i = 0; i < info.length(); i++) {
            char ch = info.charAt(i);
            switch (ch) {
                case '[':
                case '{':
                case '(':
                    ms.push(ch);
                    break;
                case '}':
                    if (!(ms.pop() == '{')){
                        return false;
                    }
                case ')':
                    if (!(ms.pop() == '(')){
                        return false;
                    }
                case ']':
                    if (!(ms.pop() == '[')){
                        return false;
                    }
            }
        }
        return true;
    }

}
class MyStack2{
    private char[] arr;
    private int top;
    public MyStack2(){
        arr = new char[10];
    }
    public MyStack2(int maxSize){
        arr = new char[maxSize];
    }

    /**
     * 把data压倒栈顶
     */
    public void push(char data) {
        if (isFull()){
            throw new ArrayIndexOutOfBoundsException();
        }
        arr[top++] = data;
    }

    /**
     * 这个栈满数据了就返回true,反则返回false
     * @return
     */
    public boolean isFull() {
        return top == arr.length;
    }

    /**
     * 这个栈如果是没有元素了，就返回false，否则返回true
     * @return
     */
    public boolean isEmpty(){
        return top==0;
    }

    /**
     * 查看栈顶元素，但是不移除这个元素
     * @return
     */
    public int peek(){
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[top-1];
    }

    /**
     * 移除栈顶元素彬并且把这个元素返回
     * @return
     */
    public int pop(){
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[--top];
    }

}

class MyQueue{
    private int arr[];
    private int elements;
    private int front; //这个引用,指向队列头
    private int end; //这个引用指向下一次添加数据的索引

    public MyQueue(){
        arr = new int[10];
    }

    public MyQueue(int maxSize){
        arr = new int[maxSize];
    }

    //在队列结尾添加value
    public void push(int value) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (end == arr.length) {
            end = 0;
        }
        arr[end]=value;
        end++;
        elements++;
    }

    public boolean isFull() {
        return elements == arr.length;
    }
}

