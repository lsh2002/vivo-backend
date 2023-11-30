package com.lsh.vivo.exception;

import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 业务异常基类
 *
 * @author lsh
 * @version 1.0.0
 * @since 2023-11-22 18:45
 */
@Getter
@Slf4j
public class BaseRequestErrorException extends RuntimeException {

    private String code;

    public BaseRequestErrorException() {
        this(BaseResultCodeEnum.ERROR);
    }

    public BaseRequestErrorException(BaseResultCodeEnum resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }


    public BaseRequestErrorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseRequestErrorException(String code, String message, Throwable cause) {
        super(message, cause);
    }

    public BaseRequestErrorException(Throwable cause) {
        super(cause);
    }

    public BaseRequestErrorException(String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
