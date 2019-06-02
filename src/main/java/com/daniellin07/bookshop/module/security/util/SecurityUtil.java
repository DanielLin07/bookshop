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

    /**
     * 加密算法
     */
    private static final String ALGORITHM_NAME = "MD5";

    /**
     * 加密次数
     */
    private static final int HASH_ITERATIONS = 2;

    /**
     * 生成加密后的密码
     *
     * @param password 加密前的密码
     * @param username 用户名，用于与盐一起使用
     * @param salt     盐
     * @return 加密后的密码
     */
    public static String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
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
