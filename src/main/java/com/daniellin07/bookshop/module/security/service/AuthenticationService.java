package com.daniellin07.bookshop.module.security.service;

import com.daniellin07.bookshop.module.security.domain.AuthenticationInfo;
import com.daniellin07.bookshop.module.security.domain.AuthorizationUser;
import com.daniellin07.bookshop.module.security.domain.JwtUser;
import com.daniellin07.bookshop.module.system.domain.User;

import javax.servlet.http.HttpServletResponse;

/**
 * 权限验证服务
 *
 * @author DanielLin07
 * @date 2019/9/8 12:27
 */
public interface AuthenticationService {

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册结果，注册成功为true
     */
    Boolean register(User user);

    /**
     * 用户登录
     *
     * @param authorizationUser 用户信息
     * @param response          HTTP响应
     * @return 登录结果，登录成功为true
     */
    AuthenticationInfo login(AuthorizationUser authorizationUser, HttpServletResponse response);

    /**
     * 获取当前登录的用户
     *
     * @return 用户信息
     */
    JwtUser getUserInfo();
}
