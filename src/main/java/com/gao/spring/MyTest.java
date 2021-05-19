package com.gao.spring;

import com.gao.annotationAop.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

public class MyTest {
    public static void main(String[] args) throws FileNotFoundException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person.getName());

    }
}
