package com.daniellin07.bookshop.module.system.service;

import com.daniellin07.bookshop.module.system.domain.User;

/**
 * UserService
 *
 * @author DanielLin07
 * @date 2019/6/1 11:37
 */
public interface UserService {

    /**
     * 根据用户ID查找用户
     *
     * @param id 用户ID
     * @return User
     */
    User findById(Long id);

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return User
     */
    User findByUsername(String username);

    /**
     * 根据用户邮箱查找用户
     *
     * @param email 用户邮箱
     * @return User
     */
    User findByEmail(String email);

    /**
     * 根据用户手机查找用户
     *
     * @param mobile 用户手机
     * @return User
     */
    User findByMobile(String mobile);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册结果，注册成功为true
     */
    Boolean register(User user);

}
