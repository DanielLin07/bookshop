package com.daniellin07.bookshop.entity;

import lombok.Data;

/**
 * 书籍实体
 *
 * @author DanielLin07
 * @date 2019/5/18 23:44
 */
@Data
public class Book {

    private int id;

    private String name;

    private Category category;

    private int bookType;

    private double price;

    private double originalPrice;

    private User user;

    private String author;

    private String press;

    private String version;

    private double degree;

    private String publishDate;

    private String description;

    private String date;

    private BookImage bookImage;
}
