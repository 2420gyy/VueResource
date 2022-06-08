package com.chen.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.mybatisplus.pojo.User;
import org.springframework.stereotype.Repository;

//在对应的mapper上面继承基本类
//JPA类似
//代表持久层
@Repository
public interface UserMapper extends BaseMapper<User> {
}
