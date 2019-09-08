package com.daniellin07.bookshop.module.security.rest;

import com.daniellin07.bookshop.common.exception.GlobalException;
import com.daniellin07.bookshop.common.result.CodeMsg;
import com.daniellin07.bookshop.common.result.Result;
import com.daniellin07.bookshop.common.result.ResultBuilder;
import com.daniellin07.bookshop.module.security.domain.AuthorizationUser;
import com.daniellin07.bookshop.module.security.service.AuthenticationService;
import com.daniellin07.bookshop.module.system.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public Result login(@Validated @RequestBody AuthorizationUser authorizationUser, HttpServletResponse response) {
        log.info("执行用户登录，参数={}", authorizationUser);
        return ResultBuilder.success(authenticationService.login(authorizationUser, response));
    }

    @PostMapping(value = "/register")
    public Result register(@Validated @RequestBody User user) {
        if (authenticationService.register(user)) {
            return ResultBuilder.build(CodeMsg.SUCCESS);
        } else {
            throw new GlobalException(CodeMsg.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/info")
    public Result getUserInfo() {
        return ResultBuilder.success(authenticationService.getUserInfo());
    }
}
