package com.asiainfo.latte_annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 生成器注解
 */
@Target(ElementType.TYPE) // 声明注解作用范围是作用在类，接口，注解，枚举上
@Retention(RetentionPolicy.SOURCE) // 声明注解的有效期为源码期
public @interface PayEntryGenerator {
    String packageName(); // 声明该注解所要生成的包名规则

    Class<?> payEntryTemplate(); // 声明该注解所生成java类需要继承哪个父类
}
