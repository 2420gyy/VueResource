package com.chen.blog.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenChen
 * @since 2021-10-04
 */
@ApiModel(value = "Ms_admin对象", description = "")
public class SysUser implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Ms_admin{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        "}";
    }
}
