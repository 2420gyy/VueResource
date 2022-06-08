package com.chen.springsource.MyTest;

import com.chen.springsource.MyTest.cicle.A;
import com.chen.springsource.MyTest.cicle.B;
import com.chen.springsource.SpringSourceApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenchen
 * @date 2022-05-21 21:54
 */
public class Test {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        A bean = context.getBean(A.class);
        B bean1 = context.getBean(B.class);
        System.out.println(bean);
        System.out.println(bean1);

        // Teacher bean = context.getBean(Teacher.class);
        // // aware 存在的意义是方便通过spring中的bean对象来获取对应容器中的相关属性值
        // System.out.println(bean.getBeanName());
        // System.out.println(bean.getEnvironment());

    }
}
