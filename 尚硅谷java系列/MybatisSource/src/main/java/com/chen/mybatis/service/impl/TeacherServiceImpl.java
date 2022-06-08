package com.chen.mybatis.service.impl;

import com.chen.mybatis.entity.Teacher;
import com.chen.mybatis.dao.TeacherMapper;
import com.chen.mybatis.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenChen
 * @since 2022-05-30
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
