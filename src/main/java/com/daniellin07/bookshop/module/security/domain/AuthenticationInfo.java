package com.daniellin07.bookshop.module.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * 权限验证用户信息
 *
 * @author DanielLin07
 * @date 2019/5/26 14:47
 */
@Getter
@AllArgsConstructor
public class AuthenticationInfo implements Serializable {

    private final String token;

    private final JwtUser user;
}
