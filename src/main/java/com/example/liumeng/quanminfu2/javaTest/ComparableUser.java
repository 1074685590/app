package com.example.liumeng.quanminfu2.javaTest;

import java.util.Arrays;

/**
 * java.lang.comparable 此接口强行对实现它的每个类的对象进行整体排序。 
 * 这种排序被称为类的自然排序，类的 compareTo 方法被称为它的自然比较方法。 
 * Arrays.sort(Object[])根据元素的自然顺序对指定对象数组按升序进行排序。 
 * 数组中的所有元素都必须实现 Comparable 接口。 
 * @author edwin 
 * 
 */  
public class ComparableUser implements Comparable {  
    private String id;  
    private int age;  
      
    public ComparableUser(String id, int age){  
        this.id = id;  
        this.age = age;  
    }  
      
    public String getId() {  
        return id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }  
  
    @Override  
    public int compareTo(Object o) {  
        return this.age - ((ComparableUser)o).getAge();  
    }  
      
    public static void main(String[] args){  
        ComparableUser[] users = new ComparableUser[]{  
                new ComparableUser("id_1",23),  
                new ComparableUser("id_2",20),  
                new ComparableUser("id_3",25),  
                new ComparableUser("id_4",19)  
        };  
        Arrays.sort(users);
        for(ComparableUser user : users){  
            System.out.println("id="+user.getId()+" age="+user.getAge());  
        }  
    }  
}  