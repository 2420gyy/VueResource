package com.baizhi.ems.dao;

import com.baizhi.ems.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

//@Repository
public interface UserDao {
    void save(User user);

    //登录
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    //全部用户
    List<User> allUser();
}
