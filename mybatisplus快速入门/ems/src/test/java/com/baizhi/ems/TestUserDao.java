package com.baizhi.ems;

import com.baizhi.ems.dao.UserDao;
import com.baizhi.ems.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest(classes = EmsApplication.class)
@RunWith(SpringRunner.class)
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSave(){
        userDao.save(new User(UUID.randomUUID().toString(),"小陈","陈男","1234","男"));
    }

}
