package com.daniellin07.bookshop.module.security.util;

import com.daniellin07.bookshop.common.exception.GlobalException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前登录用户
 *
 * @author DanielLin07
 * @date 2019/6/2 14:18
 */
public class SecurityContextUtil {

    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new GlobalException("登录状态过期");
        }
        return userDetails;
    }
}
