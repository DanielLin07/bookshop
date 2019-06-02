package com.daniellin07.bookshop.module.system.domain;

import lombok.Data;

import java.util.Date;

/**
 * 书籍实体
 *
 * @author DanielLin07
 * @date 2019/5/18 23:44
 */
@Data
public class Book {

    private Long id;

    private String bookName;

    private Long categoryId;

    private Double price;

    private Double originalPrice;

    private Long publisherId;

    private String publisherName;

    private String author;

    private String press;

    private String version;

    private String degree;

    private String description;

    private Integer status;

    private Date publishTime;

    private Date createTime;

    private Date updateTime;

}
