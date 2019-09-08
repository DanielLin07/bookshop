package com.daniellin07.bookshop.common.exception;

import com.daniellin07.bookshop.common.result.CodeMsg;
import com.daniellin07.bookshop.common.result.Result;
import com.daniellin07.bookshop.common.result.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理器
 *
 * @author DanielLin07
 * @date 2019/5/26 18:10
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义全局异常
     *
     * @param exception 异常
     * @return 异常返回
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public Result handlerGlobalException(GlobalException exception) {
        log.error(exception.getMsg());
        return ResultBuilder.error(exception.getCode(), exception.getMsg());
    }

    /**
     * 处理未知异常，一般是服务端的程序异常
     *
     * @param exception 异常
     * @return 异常返回
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResultBuilder.error(CodeMsg.SERVER_ERROR);
    }
}
