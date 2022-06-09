package com.chen.mybatis.ThreadLocalTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author chenchen
 * @date 2022-06-08 23:24
 */
public class ThreadLocal2 {

    // 要有内存分布的概念
    static ThreadLocal<Person> tl = new ThreadLocal<>();
    // 天然的隔离线程的特性，和线程绑定 （应用：就是保证当前使用的变量属于只属于当前线程）

    // static List<Person> tl = new ArrayList<>();



    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person("第一个线程"));
            // System.out.println(tl.get(0).name);
            System.out.println(tl.get());
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // tl.add(new Person());

            tl.set(new Person("第二个线程")); // 拿到了当前线程，自己独有的map<tl,value>;
            System.out.println("==1==");
            System.out.println(tl.get());
            System.out.println("==1==");
        }).start();
        // HashMap<String, String> hashMap = new HashMap<>();
        // hashMap.put("a","b");
        // String a = hashMap.get("a");

    }

    static class Person{
        public Person(String name) {
            this.name = name;
        }

        public Person() {
        }

        String name;

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}


