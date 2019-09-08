package com.daniellin07.bookshop.module.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.daniellin07.bookshop.common.exception.GlobalException;
import com.daniellin07.bookshop.common.result.CodeMsg;
import com.daniellin07.bookshop.module.security.domain.AuthenticationInfo;
import com.daniellin07.bookshop.module.security.domain.AuthorizationUser;
import com.daniellin07.bookshop.module.security.domain.JwtUser;
import com.daniellin07.bookshop.module.security.service.AuthenticationService;
import com.daniellin07.bookshop.module.security.util.JwtTokenUtil;
import com.daniellin07.bookshop.module.security.util.SecurityContextUtil;
import com.daniellin07.bookshop.module.security.util.SecurityUtil;
import com.daniellin07.bookshop.module.system.domain.User;
import com.daniellin07.bookshop.module.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限验证服务实现
 *
 * @author DanielLin07
 * @date 2019/9/8 12:29
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @Override
    public Boolean register(User user) {
        if (user == null) {
            throw new GlobalException(CodeMsg.LACK_OF_PARAM_ERROR);
        }
        return userService.insert(user);
    }

    @Override
    public AuthenticationInfo login(AuthorizationUser authorizationUser, HttpServletResponse response) {
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());

        if (!SecurityUtil.matchPassword(authorizationUser.getPassword(), jwtUser.getPassword())) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        if (!jwtUser.isEnabled()) {
            throw new GlobalException(CodeMsg.ACCOUNT_IS_DISABLED);
        }

        // 生成Token令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);
        // 将Token写回请求方的Cookie中
        addCookie(response, token);
        return new AuthenticationInfo(token, jwtUser);
    }

    @Override
    public JwtUser getUserInfo() {
        UserDetails userDetails = SecurityContextUtil.getUserDetails();
        return (JwtUser) userDetailsService.loadUserByUsername(userDetails.getUsername());
    }

    /**
     * 添加用户的token信息到Cookie中
     *
     * @param response response
     * @param token    用户token
     */
    private void addCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
