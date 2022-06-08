package com.chen.springboot01cache.service;

import com.chen.springboot01cache.bean.Employee;

public interface EmployeeService {

    Employee getEmpById(Integer id);

    int updateEmp(Employee employee);

    int deleteEmpById(Integer id);

    Employee getEmpByLastName(String lastName);
}
