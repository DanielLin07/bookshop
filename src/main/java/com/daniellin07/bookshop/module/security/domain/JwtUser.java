package com.daniellin07.bookshop.module.security.domain;

import com.daniellin07.bookshop.common.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 权限验证用户
 *
 * @author DanielLin07
 * @date 2019/5/26 14:49
 */
@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {

    @JsonIgnore
    private final Long id;

    private final String username;

    @JsonIgnore
    private final String password;

    @JsonIgnore
    private final String salt;

    private final Integer sex;

    private final String mobile;

    private final String email;

    private final String avatar;

    private final Integer status;

    private final Date createTime;

    @JsonIgnore
    private final Date lastLoginTime;

    @JsonIgnore
    private final Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(UserStatusEnum.LOCKED.getCode());
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return status.equals(UserStatusEnum.NORMAL.getCode());
    }

}
