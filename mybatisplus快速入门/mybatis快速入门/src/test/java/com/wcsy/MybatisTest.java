package com.wcsy;


import com.wcsy.dao.UserDao;
import com.wcsy.pojo.User;
import com.wcsy.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class MybatisTest
{

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> alluser = mapper.findAll();
        for (User user : alluser){
            System.out.println(user);
        }

        sqlSession.close();

    }

    @Test
    public void traditionSqlTest()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //通过驱动管理类获取数据库链接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sell?characterEncoding=utf-8", "root", "root");
            //定义 sql 语句 ?表示占位符
            String sql = "select * from user where id = ?";
            //获取预处理 statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为 sql 语句中参数的序号（从 1 开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "10");
            //向数据库发出 sql 执行查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //遍历查询结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " "+resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
