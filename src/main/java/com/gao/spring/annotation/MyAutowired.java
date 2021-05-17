package com.gao.spring.annotation;

import java.lang.annotation.*;

/**
 * 1. Retention 加载时候
 * 2. Target 注解所在位置  这个为属性
 * 3. 当@InheritedAnno注解加在某个类A上时，假如类B继承了A，则B也会带上该注解。
 * 4. @Documented注解只是用来做标识，没什么实际作用，了解就好。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface MyAutowired {
}
