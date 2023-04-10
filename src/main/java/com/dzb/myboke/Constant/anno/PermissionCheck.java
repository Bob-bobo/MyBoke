package com.dzb.myboke.Constant.anno;

import java.lang.annotation.*;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/1 0:11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionCheck {
    String value();
}
