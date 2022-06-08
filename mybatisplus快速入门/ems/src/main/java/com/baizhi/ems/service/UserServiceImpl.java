package com.baizhi.ems.service;

import com.baizhi.ems.dao.UserDao;
import com.baizhi.ems.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PrivateKey;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;
//    public void setUserDao(UserDao userDao) {
//        this.userDao = userDao;
//    }



    @Override
    public void save(User user) {
        user.setId(UUID.randomUUID().toString());
        userDao.save(user);
    }

    @Override
    public User login(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }

}
