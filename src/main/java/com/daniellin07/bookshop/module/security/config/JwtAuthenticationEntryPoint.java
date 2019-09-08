package com.daniellin07.bookshop.module.security.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 无验证拦截
 *
 * @author DanielLin07
 * @date 2019/5/26 14:51
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401响应
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                authException == null ? "Unauthorized" : authException.getMessage());
    }
}
