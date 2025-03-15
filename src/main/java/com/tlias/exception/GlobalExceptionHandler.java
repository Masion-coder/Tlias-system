package com.tlias.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tlias.model.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("程序出错啦", e);
        return Result.error("出错啦，请联系管理员~");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错啦", e);
        String message = e.getMessage();
        String errMsg = message.substring(message.indexOf("Duplicate entry"));
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }
}
