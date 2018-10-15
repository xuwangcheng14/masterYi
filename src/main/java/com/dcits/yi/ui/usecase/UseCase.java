package com.dcits.yi.ui.usecase;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
	String name();
	//为true时执行该用例方法，否则不执行
	boolean enabled() default true; 
	//执行失败时是否中断整个测试流程
	boolean failInterrupt() default false;
	//标签，在分布式运行时根据标签分组
	String tag() default "default";
	int retryCount() default 0;
}
