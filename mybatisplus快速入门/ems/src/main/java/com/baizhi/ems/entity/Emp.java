package com.baizhi.ems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
//链式调用
@Accessors(chain = true)
public class Emp {
    private String id;
    private String name;
    private Double salary;
    private Integer age;
}
