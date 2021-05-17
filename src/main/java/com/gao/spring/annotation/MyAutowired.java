package com.gao.spring.annotation;

import java.lang.annotation.*;

/**
 * 1. Retention ����ʱ��
 * 2. Target ע������λ��  ���Ϊ����
 * 3. ��@InheritedAnnoע�����ĳ����A��ʱ��������B�̳���A����BҲ����ϸ�ע�⡣
 * 4. @Documentedע��ֻ����������ʶ��ûʲôʵ�����ã��˽�ͺá�
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
@Documented
public @interface MyAutowired {
}
