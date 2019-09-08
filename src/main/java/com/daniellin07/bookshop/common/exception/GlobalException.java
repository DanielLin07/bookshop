package com.daniellin07.bookshop.common.exception;

import com.daniellin07.bookshop.common.result.CodeMsg;
import lombok.Getter;

/**
 * 统一异常处理
 *
 * @author DanielLin07
 * @date 2018/11/11 11:29
 */
@Getter
public class GlobalException extends RuntimeException {

    private Integer code;

    private String msg;

    public GlobalException(CodeMsg codeMsg) {
        this.msg = codeMsg.msg;
        this.code = codeMsg.code;
    }

    public GlobalException(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
