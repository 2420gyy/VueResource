package com.chen.mybatis.ThreadLocalTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chenchen
 * @date 2022-06-02 0:05
 */
@SpringBootTest
public class MyDemo01 {

    /*
        线程隔离
            在多线程隔离的情况下，每个线程中的变量都是相互独立的
        Threadlocal
            set(): 将变量绑定到当前线程中
            get(): 获取当前绑定的变量
     */

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String content;

    public String getContent() {
        // return content;
        return threadLocal.get();
    }

    public void setContent(String content) {
        // this.content = content;
        threadLocal.set(content);
    }

    public static void main(String[] args) {
        MyDemo01 demo01 = new MyDemo01();


        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    /**
                     * 每个线程 ： 存一个数据，过一会，取出这个数据
                     * 问题：content被线程共享了
                     */
                    demo01.setContent(Thread.currentThread().getName()+"的数据");
                    System.out.println("--------------------");
                    System.out.println(Thread.currentThread().getName()+"------>"+demo01.getContent());
                }
            });

            thread.setName("线程" + i);
            thread.start();

        }

    }




}
