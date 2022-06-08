package com.chen.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.mybatisplus.mapper.UserMapper;
import com.chen.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;


@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.isNotNull("birthday");
        queryWrapper.ge("id",16);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", "狂神");
        User user =  userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void test3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.likeRight("username", "张");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testFindPage(){
        Page<User> page = new Page<>(1, 2);
        Page<User> userPage = userMapper.selectPage(page, null);
        long total = userPage.getTotal();
        System.out.println("总记录数："+total);
        userPage.getRecords().forEach(user -> System.out.println("user = "+user));

    }
}

