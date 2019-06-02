package com.daniellin07.bookshop.module.system.service;

import com.daniellin07.bookshop.module.system.domain.User;

/**
 * UserService
 *
 * @author DanielLin07
 * @date 2019/6/1 11:37
 */
public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobile(String mobile);

    void update(User user);

    int checkPassword(User user);

    User getByStudentid(String studentid);

}
