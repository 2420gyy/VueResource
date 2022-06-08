package com.chen.springboot01cache.controller;


import com.chen.springboot01cache.bean.Employee;
import com.chen.springboot01cache.service.EmployeeService;
import com.chen.springboot01cache.service.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Qualifier("empRedisCacheManage")
    @Autowired
    RedisCacheManager empRedisCacheManage;


//    /1
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee empById = employeeService.getEmpById(id);
        Cache emp = empRedisCacheManage.getCache("emp");//这个不好测试呀。。。 P12
        emp.put("emp:"+"id",empById);
        return empById;
    }
    @GetMapping("/empupdate")//empupdate?id=2&lastName=李四改&gender=0
    public int updateEmp(Employee employee) {
        return employeeService.updateEmp(employee);
    }
//    ？id=1
    @GetMapping("/empdelete")//empupdate?id=2&lastName=李四改&gender=0
    public int deleteEmpById(Integer id) {
        System.out.println("开始删除");
        return employeeService.deleteEmpById(id);
    }
    @GetMapping("/empupdatebyname/{lastName}")//empupdate?id=2&lastName=李四改&gender=0
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        System.out.println("查询员工");
        return employeeService.getEmpByLastName(lastName);
    }
}
