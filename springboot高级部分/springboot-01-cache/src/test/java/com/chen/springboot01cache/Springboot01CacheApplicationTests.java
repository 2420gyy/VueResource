package com.chen.springboot01cache;

import com.chen.springboot01cache.bean.Employee;
import com.chen.springboot01cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot01CacheApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作字符串的

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象

    @Autowired
    RedisTemplate<Object,Employee> empRedisTemplate;




    /*
    常见五大数据类型
    String list set hash zset（有序集合）
    stringRedisTemplate.opsForValue() 操作字符串
    ...
     */

    @Test
    public void test01(){
//        stringRedisTemplate.opsForValue().append("msg","hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
    }
    @Test
    public void test02(){
        Employee empById = employeeMapper.getEmpById(1);
        //默认如果保存对象，使用jdk序列化机制，序列化后保存到redis
//        redisTemplate.opsForValue().set("emp-01",empById);
        //1.数据以json形式保存
        //（1）自己转换
        //（2）编写自己的规则
        empRedisTemplate.opsForValue().set("emp-01",empById);

    }

    @Test
    void contextLoads() {
        Employee empById = employeeMapper.getEmpById(2);
        System.out.println(empById);
    }

}
