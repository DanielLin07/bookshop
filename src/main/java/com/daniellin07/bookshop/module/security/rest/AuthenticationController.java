package com.daniellin07.bookshop.module.security.rest;

import com.daniellin07.bookshop.common.result.CodeMsg;
import com.daniellin07.bookshop.common.result.Result;
import com.daniellin07.bookshop.common.result.ResultBuilder;
import com.daniellin07.bookshop.module.security.domain.AuthenticationInfo;
import com.daniellin07.bookshop.module.security.domain.AuthorizationUser;
import com.daniellin07.bookshop.module.security.domain.JwtUser;
import com.daniellin07.bookshop.module.security.util.JwtTokenUtil;
import com.daniellin07.bookshop.module.security.util.SecurityContextUtil;
import com.daniellin07.bookshop.module.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 权限验证用户登录控制器
 *
 * @author DanielLin07
 * @date 2019/5/26 15:18
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/login")
    public Result login(@Validated @RequestBody AuthorizationUser authorizationUser) {
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());

        if (!jwtUser.getPassword().equals(SecurityUtil.encryptPassword(authorizationUser.getPassword()))) {
            return ResultBuilder.build(CodeMsg.PASSWORD_ERROR);
        }

        if (!jwtUser.isEnabled()) {
            return ResultBuilder.build(CodeMsg.ACCOUNT_IS_DISABLED);
        }

        // 生成Token令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);

        // 返回Token
        return ResultBuilder.build(new AuthenticationInfo(token, jwtUser));
    }

    @GetMapping(value = "/info")
    public Result getUserInfo() {
        UserDetails userDetails = SecurityContextUtil.getUserDetails();
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(userDetails.getUsername());
        return ResultBuilder.build(jwtUser);
    }
}
