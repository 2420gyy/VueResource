package com.chen.springboot01cache.mapper;

import com.chen.springboot01cache.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id=#{id}")
    int updateEmp(Employee employee);

    @Delete("delete from employee where id=#{id}")
    int deleteEmpById(Integer id);

    @Insert("insert into employee(lastName,email,gender,d_id) values(#{lastname},#{email},#{gender},#{dId})")
    void insertEmp(Employee employee);

    @Select("select * from employee")
    List<Employee> selectEmpList();

    @Select("select * from employee where lastName = #{lastName}")
    Employee getEmpByLastName(String lastName);
}
