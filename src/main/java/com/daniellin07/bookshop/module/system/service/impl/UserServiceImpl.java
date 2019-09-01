package com.daniellin07.bookshop.module.system.service.impl;

import com.daniellin07.bookshop.common.constant.LogConstants;
import com.daniellin07.bookshop.module.system.dao.UserDAO;
import com.daniellin07.bookshop.module.system.domain.User;
import com.daniellin07.bookshop.module.system.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * UserService实现类
 *
 * @author DanielLin07
 * @date 2019/6/1 11:37
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findById(Long id) {
        if (id == null) {
            log.error(LogConstants.LACK_OF_PARAM, "用户Id");
            return null;
        }
        return userDAO.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isNotBlank(username)) {
            log.error(LogConstants.LACK_OF_PARAM, "用户名");
            return null;
        }
        return userDAO.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        if (StringUtils.isNotBlank(email)) {
            log.error(LogConstants.LACK_OF_PARAM, "用户邮箱");
            return null;
        }
        return userDAO.findByEmail(email);
    }

    @Override
    public User findByMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            log.error(LogConstants.LACK_OF_PARAM, "用户手机");
            return null;
        }
        return userDAO.findByMobile(mobile);
    }

    @Override
    public Boolean register(User user) {
        if (user == null) {
            return false;
        }
        return userDAO.insert(user) > 1;
    }

}
