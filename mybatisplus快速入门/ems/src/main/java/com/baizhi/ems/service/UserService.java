package com.baizhi.ems.service;

import com.baizhi.ems.entity.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User login(String username,String password);


}
