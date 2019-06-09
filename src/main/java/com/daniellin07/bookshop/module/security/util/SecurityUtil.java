package com.daniellin07.bookshop.module.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * 与安全相关的工具类
 *
 * @author DanielLin07
 * @date 2018/11/18 20:06
 */
public class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * 生成加密后的密码
     *
     * @param password 加密前的密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 验证密码
     *
     * @param rawPassword     用户密码
     * @param encodedPassword 加密后的密码
     * @return 是否一致
     */
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    /**
     * 生成随机UUID字符串
     *
     * @return UUID字符串
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
