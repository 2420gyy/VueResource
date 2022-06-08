package com.baizhi.ems.service;

import com.baizhi.ems.dao.EmpDao;
import com.baizhi.ems.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmpServiceImpl implements EmpService{

    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void saveEmp(Emp emp) {
        emp.setId(UUID.randomUUID().toString());
        empDao.saveEmp(emp);
    }

    @Override
    public void toDelete(String id) {
        empDao.toDelete(id);
    }

    @Override
    public Emp findById(String id) {
        return empDao.findById(id);
    }

    @Override
    public void update(Emp emp) {
        empDao.update(emp);
    }

}
