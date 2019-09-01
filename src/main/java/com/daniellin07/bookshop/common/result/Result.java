package com.daniellin07.bookshop.common.result;

import lombok.Data;

/**
 * 响应结果封装
 *
 * @author DanielLin07
 * @date 2018/11/10 13:59
 */
@Data
public class Result<T> {

    /**
     * 响应结果码
     */
    private int code;

    /**
     * 响应结果信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
