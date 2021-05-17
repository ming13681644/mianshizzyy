package com.gao.spring.circularDependence;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIOCTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        ServiceAA aa = ioc.getBean("aa", ServiceAA.class);
        ServiceBB bb = ioc.getBean("bb", ServiceBB.class);

    }
}

