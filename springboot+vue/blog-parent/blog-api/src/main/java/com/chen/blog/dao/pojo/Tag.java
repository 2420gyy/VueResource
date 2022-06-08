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
@ApiModel(value = "Ms_tag对象", description = "")
public class Tag implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String avatar;

    private String tag_name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    @Override
    public String toString() {
        return "Ms_tag{" +
        "id=" + id +
        ", avatar=" + avatar +
        ", tag_name=" + tag_name +
        "}";
    }
}
