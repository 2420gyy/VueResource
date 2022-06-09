package com.chen.mybatis.luBan;

import lombok.SneakyThrows;
import org.apache.ibatis.executor.ReuseExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author chenchen
 * @date 2022-05-30 22:55
 */
@SpringBootTest
public class ExecutorTest {


    private Configuration configuration;
    private Connection connection;
    private JdbcTransaction jdbcTransaction;

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @BeforeEach
    private void init() throws SQLException {
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        // 构建一个会话工厂
        SqlSessionFactory build = factoryBuilder.build(ExecutorTest.class.getResourceAsStream("/mybatis-config.xml"));
        configuration = build.getConfiguration();
        connection = DriverManager.getConnection(url,userName,password);
        jdbcTransaction  = new JdbcTransaction(connection);
    }

    // 简单执行器测试
    @SneakyThrows
    @Test
    public void simpleTest(){
        SimpleExecutor simpleExecutor = new SimpleExecutor(configuration,jdbcTransaction);
        // 5个参数 sql声明映射 参数 行范围 结果处理器 动态sql语句
        System.out.println(configuration);
        MappedStatement ms = configuration.getMappedStatement("com.chen.mybatis.dao.TeacherMapper.findOneById");
        List<Object> list = simpleExecutor.doQuery(ms, 1, RowBounds.DEFAULT, SimpleExecutor.NO_RESULT_HANDLER,
                ms.getBoundSql(10));
        System.out.println(list.get(0));
    }

    // 可重用执行器
    @Test
    public void ReuseTest(){
        ReuseExecutor reuseExecutor = new ReuseExecutor(configuration, jdbcTransaction);
        // reuseExecutor.doQuery();
    }


    @Test
    public void test(){
        System.out.println(url);
        System.out.println("======");
    }

}
