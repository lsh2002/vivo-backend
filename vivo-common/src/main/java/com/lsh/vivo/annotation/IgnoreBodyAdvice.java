package com.lsh.vivo.annotation;

import java.lang.annotation.*;

/**
 * 忽略不包装
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 23:05
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface IgnoreBodyAdvice {
}
