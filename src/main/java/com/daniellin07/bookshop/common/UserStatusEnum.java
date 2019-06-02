package com.daniellin07.bookshop.common;

/**
 * 用户状态枚举类
 *
 * @author DanielLin07
 * @date 2019/6/2 10:14
 */
public enum UserStatusEnum {

    /**
     * 锁定
     */
    LOCKED("lock", 0),

    NORMAL("normal", 1),

    DELETED("deleted", 2);

    private String key;

    private Integer code;

    UserStatusEnum(String key, Integer code) {
        this.key = key;
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
