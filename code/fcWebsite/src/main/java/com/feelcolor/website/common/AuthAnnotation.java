package com.feelcolor.website.common;

import java.lang.annotation.*;

/**
 * 自定义注解 判断是否具有权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAnnotation {
    boolean isLogged() default false;
}
