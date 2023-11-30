package com.lsh.vivo.handler;

import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常拦截
     */
    @ExceptionHandler
    public Result<Void> handlerException(Exception e) {
        log.error("", e);
        return Result.error();
    }

    /**
     * 自定义异常照常抛出
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        //收集所有错误信息
        List<String> errorMsg = bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        log.error("【请求参数异常】:{}", errorMsg, ex);
        return Result.error(BaseResultCodeEnum.ERROR_ARGUMENT.getCode(), errorMsg.toString());
    }


    /**
     * 暂未授权
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result<Void> handlerAccessDeniedException(AccessDeniedException ex) {
        log.error("【暂未授权】:{}", ex.getMessage(), ex);
        return Result.error(BaseResultCodeEnum.ERROR_NOR_PERMISSION);
    }

    /***
     * 参数异常 -- ConstraintViolationException()
     * @param e 异常
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> urlParametersExceptionHandle(ConstraintViolationException e) {
        //收集所有错误信息
        List<String> errorMsg = e.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).toList();
        log.error("【请求参数异常】:{}", errorMsg, e);

        return Result.error(BaseResultCodeEnum.ERROR_ARGUMENT.getCode(), errorMsg.toString());
    }

    /***
     * @param ex 异常信息
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> bindExceptionHandle(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();;
        //收集所有错误信息
        List<String> errorMsg = bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        log.error("【请求参数异常】:{}", errorMsg, ex);
        return Result.error(BaseResultCodeEnum.ERROR_ARGUMENT.getCode(), errorMsg.toString());
    }



    /**
     * 自定义异常照常抛出
     */
    @ExceptionHandler(BaseRequestErrorException.class)
    public Result<Void> handlerCustomException(BaseRequestErrorException ex) {
        return Result.error(ex);
    }

}
