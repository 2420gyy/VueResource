package com.wcsy.dao;

import com.wcsy.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
