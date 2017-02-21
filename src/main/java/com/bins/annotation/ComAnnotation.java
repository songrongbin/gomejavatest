package com.bins.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @date 2011-6-12
 * @see  
 * @author andybin
 * @version 1.0
 *
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface ComAnnotation {
    /**
     * 显示字符输出名
     * @return String
     */
	String name() default "";
	/**
	 * 排序如果按order 值排序
	 * @return
	 */
	int order() default 1;
}
