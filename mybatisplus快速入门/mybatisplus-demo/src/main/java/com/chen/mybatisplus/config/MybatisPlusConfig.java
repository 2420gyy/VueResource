package com.chen.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//扫描包
@MapperScan("com/chen/mybatisplus/mapper")
//事务管理
@EnableTransactionManagement
@Configuration//配置类
public class MybatisPlusConfig {
    /*
    *   支持的数据类型只有:int,Integer,long,Long,Date,Timestamp,LocalDateTime
        整数类型下 newVersion = oldVersion + 1
        newVersion 会回写到 entity 中
        仅支持 updateById(id) 与 update(entity, wrapper) 方法
        在 update(entity, wrapper) 方法下, wrapper 不能复用!!!
    * */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    // 最新版 分页插件
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
