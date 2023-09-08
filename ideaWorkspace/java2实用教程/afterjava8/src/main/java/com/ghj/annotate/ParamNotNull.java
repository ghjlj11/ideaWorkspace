package com.ghj.annotate;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author guohuanjun1
 * @date 2023/8/4 14:57
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ParamNotNull {


    @AliasFor("message")
    String value() default "";

    @AliasFor("value")
    String message() default "";
}
