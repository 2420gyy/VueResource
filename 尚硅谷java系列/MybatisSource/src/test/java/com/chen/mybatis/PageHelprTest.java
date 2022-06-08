package com.chen.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.mybatis.dao.TeacherMapper;
import com.chen.mybatis.entity.Teacher;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenchen
 * @date 2022-06-01 17:43
 */
@SpringBootTest
public class PageHelprTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TeacherMapper teacherMapper;

    @Test
    public void test(){

        /*
        PageHelper 方法使用了静态的 ThreadLocal 参数，分页参数和线程是绑定的。
        只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的。
        因为 PageHelper 在 finally 代码段中自动清除了 ThreadLocal 存储的对象。
        如果代码在进入 Executor 前发生异常，就会导致线程不可用，这属于人为的 Bug（例如接口方法和 XML 中的不匹配，
        导致找不到 MappedStatement 时）， 这种情况由于线程不可用，也不会导致 ThreadLocal 参数被错误的使用。
         */

            PageHelper.startPage(1,1);
            if(false){ //
                Teacher res = teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>().like(Teacher::getName, "老师"));
            }else{

                while(true){

                    try {
                        Teacher teacher = teacherMapper.findOneById(1);
                        logger.info(teacher.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("+++++++++++");
                    }

                }
        }
    }

}
