package com.daniellin07.bookshop.module.system.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * UserService单元测试
 *
 * @author DanielLin07
 * @date 2019/9/1 23:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findById() {
        System.out.println(userService.findById(1L));
    }

    @Test
    public void findByUsername() {
    }

    @Test
    public void findByEmail() {
    }

    @Test
    public void findByMobile() {
    }
}