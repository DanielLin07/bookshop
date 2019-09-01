package com.daniellin07.bookshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用入口
 *
 * @author DanielLin07
 * @date 2019/5/18 21:42
 */
@SpringBootApplication
@MapperScan("com.daniellin07.bookshop.module.system.dao")
public class BookshopApp {

    public static void main(String[] args) {
        SpringApplication.run(BookshopApp.class, args);
    }
}
