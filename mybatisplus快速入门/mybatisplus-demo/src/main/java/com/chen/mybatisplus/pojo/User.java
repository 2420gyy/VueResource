package com.chen.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //    推荐使用Long 类型/将主键类型从int修改为integer
    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;
    @TableLogic //逻辑删除
    private Integer deleted;
    @Version
    private Integer version;
    //字段添加填充内容
    //填充原理是直接给entity的属性设置值!!!
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
