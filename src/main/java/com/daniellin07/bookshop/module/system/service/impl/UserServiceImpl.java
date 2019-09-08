package com.daniellin07.bookshop.module.system.service.impl;

import com.daniellin07.bookshop.common.constant.LogConstants;
import com.daniellin07.bookshop.common.exception.GlobalException;
import com.daniellin07.bookshop.common.result.CodeMsg;
import com.daniellin07.bookshop.module.system.dao.UserDAO;
import com.daniellin07.bookshop.module.system.domain.User;
import com.daniellin07.bookshop.module.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            log.error(LogConstants.LACK_OF_PARAM, "id");
            throw new GlobalException(CodeMsg.LACK_OF_PARAM_ERROR);
        }
        return userDAO.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            log.error(LogConstants.LACK_OF_PARAM, "username");
            throw new GlobalException(CodeMsg.LACK_OF_PARAM_ERROR);
        }
        return userDAO.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            log.error(LogConstants.LACK_OF_PARAM, "email");
            throw new GlobalException(CodeMsg.LACK_OF_PARAM_ERROR);
        }
        return userDAO.findByEmail(email);
    }

    @Override
    public User findByMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            log.error(LogConstants.LACK_OF_PARAM, "mobile");
            throw new GlobalException(CodeMsg.LACK_OF_PARAM_ERROR);
        }
        return userDAO.findByMobile(mobile);
    }

    @Override
    public boolean insert(User user) {
        return userDAO.insert(user) > 1;
    }

}
