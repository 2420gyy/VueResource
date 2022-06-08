package com.chen.mybatis.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author ChenChen
 * @since 2022-05-30
 */
@Data
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    public Teacher() {
    }

    public Teacher(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
