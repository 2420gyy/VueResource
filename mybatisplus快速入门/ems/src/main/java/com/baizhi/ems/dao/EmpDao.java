package com.baizhi.ems.dao;

import com.baizhi.ems.entity.Emp;

import java.util.List;

public interface EmpDao {
    //查询所有
    List<Emp> findAll();

    void saveEmp(Emp emp);

    void toDelete(String id);


    Emp findById(String id);

    void update(Emp emp);
}
