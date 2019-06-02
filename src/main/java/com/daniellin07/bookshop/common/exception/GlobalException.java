package com.daniellin07.bookshop.common.exception;

import lombok.Getter;

/**
 * 统一异常处理
 *
 * @author DanielLin07
 * @date 2018/11/11 11:29
 */
@Getter
public class GlobalException extends RuntimeException {

    private String msg;

    public GlobalException(String msg){
        super(msg);
        this.msg = msg;
    }
}
