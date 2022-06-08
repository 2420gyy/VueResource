package com.chen.mybatis.dao;

import com.chen.mybatis.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChenChen
 * @since 2022-05-30
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

    Teacher findOneById(@Param("id") Integer id);


}
