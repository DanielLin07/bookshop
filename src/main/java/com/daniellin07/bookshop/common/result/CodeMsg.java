package com.daniellin07.bookshop.common.result;

/**
 * 请求结果枚举类
 *
 * @author DanielLin07
 * @date 2018/11/10 13:58
 */
public enum CodeMsg {

    /**
     * 用户模块 5002XX
     */
    SESSION_ERROR(500210, "Session不存在或者已经失效"),
    PASSWORD_EMPTY(500211, "登录密码不能为空"),
    USERNAME_EMPTY(500212, "用户名不能为空"),
    USERNAME_ERROR(500213, "用户名格式错误"),
    USERNAME_NOT_EXIST(500214, "用户名不存在"),
    PASSWORD_ERROR(500215, "密码错误"),
    ACCOUNT_IS_DISABLED(500216, "账号已停用，请联系管理员"),

    /**
     * 秒杀模块 5005XX
     */
    SECKILL_OVER(500500, "商品秒杀已经结束"),
    SECKILL_REPEAT(500501, "不能重复秒杀"),

    /**
     * 订单模块 5004XX
     */
    ORDER_NOT_EXIST(500400, "订单不存在"),

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
    SERVER_ERROR(500, "服务端异常");

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
