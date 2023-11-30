package com.lsh.vivo.annotation;

import java.lang.annotation.*;

/**
 * 实体主键前缀
 *
 * @author lsh
 * @version 1.0.0
 * 2023/9/21 11:24
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface TableIdPrefix {

    /**
     * 前缀值
     *
     * @return 前缀值
     */
    String value() default "";
}
