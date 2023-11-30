package com.lsh.vivo.bean.response.system;

import com.lsh.vivo.enumerate.BaseResultCodeEnum;
import com.lsh.vivo.exception.BaseRequestErrorException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回对象
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/6/25 23:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Result<T> extends BaseResult {

    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return success(BaseResultCodeEnum.SUCCESS, data);
    }

    public static <T> Result<T> success(BaseResultCodeEnum resultCode, T data) {
        return success(resultCode.getCode(), resultCode.getMessage(), data);
    }

    public static <T> Result<T> success(String code, String message, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result<Void> error() {
        return error(BaseResultCodeEnum.ERROR);
    }

    public static <T> Result<T> error(T data) {
        return error(BaseResultCodeEnum.ERROR, data);
    }

    public static Result<Void> error(BaseResultCodeEnum errorCode) {
        return error(errorCode.getCode(), errorCode.getMessage());
    }

    public static <T> Result<T> error(BaseResultCodeEnum errorCode, T data) {
        return error(errorCode.getCode(), errorCode.getMessage(), data);
    }

    public static Result<Void> error(BaseRequestErrorException ex) {
        return error(ex.getCode(), ex.getMessage());
    }

    public static Result<Void> error(String code, String message) {
        return error(code, message, null);
    }

    public static <T> Result<T> error(String code, String message, T data) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }
}
