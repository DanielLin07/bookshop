package com.daniellin07.bookshop.module.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.daniellin07.bookshop.module.system.domain.User;

/**
 * UserDAO
 *
 * @author DanielLin07
 * @date 2019/6/1 11:37
 */
public interface UserDAO extends BaseMapper<User> {

    User findById(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByMobile(String mobile);

    void update(User user);
}
