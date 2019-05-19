package com.daniellin07.bookshop.entity;

import lombok.Data;

/**
 * 用户实体
 *
 * @author DanielLin07
 * @date 2019/5/18 23:44
 */
@Data
public class User {

    private int id;

    private String studentid;

    private String name;

    private String password;

    private char sex;

    private String tel;

    private String address;

    private String major;

}
