package com.daniellin07.bookshop.common;

/**
 * 响应结果封装
 *
 * @author DanielLin07
 * @date 2018/11/10 13:59
 */
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
