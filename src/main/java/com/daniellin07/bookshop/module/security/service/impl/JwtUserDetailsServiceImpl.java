package com.daniellin07.bookshop.module.security.service.impl;

import com.daniellin07.bookshop.common.exception.GlobalException;
import com.daniellin07.bookshop.common.result.CodeMsg;
import com.daniellin07.bookshop.module.security.domain.JwtUser;
import com.daniellin07.bookshop.module.system.domain.User;
import com.daniellin07.bookshop.module.system.service.UserService;
import com.daniellin07.bookshop.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * JWT用户结合Spring Security
 *
 * @author DanielLin07
 * @date 2019/5/26 14:57
 */
@Service("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user;
        if (ValidateUtil.validateEmail(username)) {
            user = userService.findByEmail(username);
        } else if (ValidateUtil.validateMobile(username)) {
            user = userService.findByMobile(username);
        } else {
            user = userService.findByUsername(username);
        }

        if (user == null) {
            throw new GlobalException(CodeMsg.USERNAME_NOT_EXIST);
        } else {
            return create(user);
        }
    }

    private UserDetails create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getSalt(),
                user.getSex(),
                user.getMobile(),
                user.getEmail(),
                user.getAvatar(),
                user.getStatus(),
                user.getCreateTime(),
                user.getLastLoginTime(),
                null
        );
    }

}
