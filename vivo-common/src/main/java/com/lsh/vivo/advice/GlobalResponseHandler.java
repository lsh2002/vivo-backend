package com.lsh.vivo.advice;

import com.lsh.vivo.annotation.IgnoreBodyAdvice;
import com.lsh.vivo.bean.response.system.BaseResult;
import com.lsh.vivo.bean.response.system.Result;
import com.lsh.vivo.enumerate.CommonStatusEnum;
import com.lsh.vivo.enumerate.GenericEnumPropertySupport;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对返回值在输出之前进行统一包装
 *
 * @author AdolphLv
 */
@RestControllerAdvice(basePackages = "com.lsh.vivo.controller")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 这里可以做成scan扫描包的方式，扫描所有枚举类，然后分批注入，或者其他的方式也行，看自己项目的规则
        binder.registerCustomEditor(CommonStatusEnum.class, new GenericEnumPropertySupport<>(CommonStatusEnum.class));

    }

    /**
     * 判断支持的类型
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> clazz) {
        // 检查注解是否存在，存在则忽略拦截
        return !methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreBodyAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> clazz, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 判断为null构建ResponseData对象进行返回
        if (o == null) {
            return Result.success();
        }
        // 判断是ResponseData子类或其本身就返回Object o本身，因为有可能是接口返回时创建了ResponseData,这里避免再次封装
        if (o instanceof BaseResult) {
            return o;
        }
        return Result.success(o);
    }

}