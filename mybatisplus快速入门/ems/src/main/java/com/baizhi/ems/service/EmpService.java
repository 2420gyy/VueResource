package com.baizhi.ems.service;

import com.baizhi.ems.entity.Emp;

import java.util.List;

public interface EmpService {
    //查询所有员工
    List<Emp> findAll();

    void saveEmp(Emp emp);

    void toDelete(String id);

    //根据id查询
    Emp findById(String id);

    //更新
    void update(Emp emp);
}
