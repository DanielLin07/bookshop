package com.daniellin07.bookshop.common.result;

import org.apache.commons.lang3.StringUtils;

/**
 * 请求结果生成工具
 *
 * @author DanielLin07
 * @date 2018/11/10 14:00
 */
public class ResultBuilder {

    private ResultBuilder() {
    }

    /**
     * 生成带有data数据的响应Result
     *
     * @param code 状态码
     * @param msg  响应信息
     * @param data 响应数据
     * @return 带有data数据的响应Result
     */
    public static <T> Result<T> build(int code, String msg, T data) {
        Result<T> result = build(code, msg);
        result.setData(data);
        return result;
    }

    /**
     * 生成响应Result
     *
     * @param code 状态码
     * @param msg  响应信息
     * @return 响应Result
     */
    public static <T> Result<T> build(int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        if (StringUtils.isEmpty(msg)) {
            msg = CodeMsg.SERVER_ERROR.msg;
        }
        result.setMsg(msg);
        return result;
    }

    /**
     * 根据已定义的CodeMsg生成响应Result
     *
     * @param codeMsg 请求结果枚举类
     * @return 响应Result
     */
    public static <T> Result<T> build(CodeMsg codeMsg) {
        return build(codeMsg.code, codeMsg.msg);
    }

    /**
     * 根据已定义的CodeMsg生成响应Result
     *
     * @param codeMsg 请求结果枚举类
     * @param data    响应数据
     * @return 响应Result
     */
    public static <T> Result<T> build(CodeMsg codeMsg, T data) {
        return build(codeMsg.code, codeMsg.msg, data);
    }

    /**
     * 生成带有数据的响应成功Result
     *
     * @param data 响应数据
     * @return 响应Result
     */
    public static <T> Result<T> success(T data) {
        return build(CodeMsg.SUCCESS, data);
    }

    /**
     * 生成无数据的响应成功Result
     *
     * @return 响应Result
     */
    public static <T> Result<T> success() {
        return build(CodeMsg.SUCCESS);
    }

    /**
     * 根据已定义的CodeMsg生成错误响应Result
     *
     * @param codeMsg 请求结果枚举类
     * @return 响应Result
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return build(codeMsg);
    }

    /**
     * 生成错误响应Result
     *
     * @param code 状态码
     * @param msg  响应信息
     * @return 响应Result
     */
    public static <T> Result<T> error(Integer code, String msg) {
        return build(code, msg);
    }

}
