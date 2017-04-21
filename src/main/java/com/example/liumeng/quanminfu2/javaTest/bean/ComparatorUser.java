package com.example.liumeng.quanminfu2.javaTest.bean;
import java.util.Arrays;
import java.util.Comparator;

/**
 * java.util.Comparator强行对某个对象 collection 进行整体排序 的比较函数。
 * Arrays.sort(Object[]a,Comparator c)根据指定比较器产生的顺序
 * 对指定对象数组进行排序。 数组中的所有元素都必须是通过指定比较器可相互比较的
 * @author edwin
 *
 */
public class ComparatorUser {



    public static void main(String[] args){
        User[] users = new User[]{
                new User("id_1",38),
                new User("id_2",29),
                new User("id_3",39),
                new User("id_4",20)
        };
        Arrays.sort(users, new Comparator(){
            @Override
            public int compare(Object lhs, Object rhs) {
                return ((User)lhs).getAge()-((User)rhs).getAge();
            }
        });
        for(User user : users){
            System.out.println("id="+user.getId()+" age="+user.getAge());
        }
    }
}  