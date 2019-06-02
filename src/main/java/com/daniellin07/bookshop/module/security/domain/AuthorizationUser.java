package com.daniellin07.bookshop.module.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 权限验证用户登录信息
 *
 * @author DanielLin07
 * @date 2019/5/26 14:48
 */
@Getter
@Setter
public class AuthorizationUser {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Override
    public String toString() {
        return "{username=" + username + ", password= ******}";
    }
}
