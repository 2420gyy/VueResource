package com.chen.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chen.mybatis.dao.TeacherMapper;
import com.chen.mybatis.entity.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author chenchen
 * @date 2022-05-31 20:02
 */
@SpringBootTest
public class OriginalConnectionWay {

    private static SqlSessionFactory sqlSessionFactory;//提升作用域


    // 这里其实是一个工具类，注意性能问题
    @Test
    public void test(){
        //使用mybatis第一步，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            // 得到SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//去掉类型声明 ??
            // 得到sqlSession对象
            SqlSession sqlSession = sqlSessionFactory.openSession();//加true，实现自动提交
            Connection connection = sqlSession.getConnection();
            Statement statement = connection.createStatement();
            Connection connection1 = statement.getConnection();
            // statement.executeQuery()
            // connection.setAutoCommit(false);
            // 清楚sqlSession可以做什么
            TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
            LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
            // String[] strings;
            wrapper.eq(Teacher::getId, Arrays.asList("1","2"));
            // TODO: 搞清为什么没有。。。
            List<Teacher> teacher = mapper.selectList(wrapper);
            // Teacher teacher = mapper.findOneById(1);
            System.out.println(teacher);
        } catch (IOException e) {
            // 关闭？？？

            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Autowired
    private TeacherMapper teacherMapper;

    // TODO 这两个注解还不一样！！！！
    @Test
    // @org.junit.Test
    public void test11(){
        Teacher oneById = teacherMapper.findOneById(1);
        System.out.println(oneById);
        System.out.println("======");
    }

}
