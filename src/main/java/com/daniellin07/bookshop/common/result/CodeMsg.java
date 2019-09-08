package com.daniellin07.bookshop.common.result;

/**
 * 请求结果枚举类
 *
 * @author DanielLin07
 * @date 2018/11/10 13:58
 */
public enum CodeMsg {

    /**
     * 成功处理请求
     */
    SUCCESS(200, "SUCCESS"),

    /**
     * 请求无效
     */
    BAD_REQUEST(412, "请求无效"),

    /**
     * 服务端异常
     */
    SERVER_ERROR(500, "服务端异常"),

    /**
     * 通用模块 5001XX
     */
    LACK_OF_PARAM_ERROR(500101, "缺少必要参数"),

    /**
     * 用户模块 5002XX
     */
    SESSION_ERROR(500201, "Session不存在或者已经失效"),
    PASSWORD_EMPTY(500202, "登录密码不能为空"),
    USERNAME_EMPTY(500203, "用户名不能为空"),
    USERNAME_ERROR(500204, "用户名格式错误"),
    USERNAME_NOT_EXIST(500205, "用户名不存在"),
    PASSWORD_ERROR(500206, "用户密码错误"),
    ACCOUNT_IS_DISABLED(500207, "账号已停用，请联系管理员"),
    ACCOUNT_IS_EXPIRED(500207, "账号已停用，请联系管理员"),

    ;

    /**
     * 状态码
     */
    public int code;

    /**
     * 信息
     */
    public String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
