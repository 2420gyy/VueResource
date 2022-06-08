package com.chen.springboot02amqp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenchen
 * @date 2021-11-24 23:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String bookName;
    private String author;
}
