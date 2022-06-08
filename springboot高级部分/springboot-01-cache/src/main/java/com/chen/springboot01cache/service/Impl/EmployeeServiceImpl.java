package com.chen.springboot01cache.service.Impl;

import com.chen.springboot01cache.bean.Employee;
import com.chen.springboot01cache.mapper.EmployeeMapper;
import com.chen.springboot01cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 *总的注解，覆盖下面的
 */
@CacheConfig(cacheNames = "emp",cacheManager = "empRedisCacheManage")
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Qualifier("empRedisCacheManage")
    @Autowired
    RedisCacheManager empRedisCacheManage;
//    使用编码的方式
    //empRedisCacheManage.getCache("emp")
    //dept.put("emp:1",empById);

    /**
     * 将方法的返回结果缓存，再想要相同的数据，直接从缓存中取
     * 属性：
     *  value 缓存组件的名字/cacheNames
     *  key 缓存数据使用的key 默认是方法的参数 1-方法的返回值 key = "#root.methodName+'['+#id+']'"  ,key = "#id",
     *  keyGenerator key的生成器，/key
     *  cacheManager 缓存管理器，指定哪个缓存，/cacheResolver
     *  condition 指定符合条件的情况下缓存 condition = "#id>0",
     *  unless 当unless指定的条件为true，方法的返回值不会被缓存，获取到结果#result进行缓存 unless = "result == null"
     *  sync 是否使用异步模式
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = {"emp"},keyGenerator = "myKeyGenerator",condition = "#id>1")//#root.args[0] #root.caches[0].name
    @Override
    public Employee getEmpById(Integer id) {
        System.out.println("查询"+id+"号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }


    /*
    @CachePut 既调用方法，又更新缓存数据
    修改了数据库的某个数据，同时更新缓存
    运行时机
        先调用方法
        将目标方法的结果缓存
    更改数据后再次查询发现结果没有变化
    key不同的原因！！！
    更新数据库和覆盖缓存
     */
    @CachePut(value = "emp",key = "#employee.id")//使用返回后的#result.id
    @Override
    public int updateEmp(Employee employee) {
        System.out.println("updateEmp"+employee);
        return employeeMapper.updateEmp(employee);
    }

    /*
    @CacheEvict() 缓存清除
    通过key指定要清除的key的缓存
       beforeInvocation 缓存的清除是否在方法执行之前执行，默认在方法执行之后执行（涉及到出现异常问题）
     */
    @CacheEvict(value = "emp",key = "#id")//allEntries = true 删除这个缓存中所有的数据
    @Override
    public int deleteEmpById(Integer id) {
        return employeeMapper.deleteEmpById(id);
    }
    /*
        Caching 定义复杂的缓存规则
         */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
    }
    )
    @Override
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }



}
