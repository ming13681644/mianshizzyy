package com.gao.sofoom;

import lombok.ToString;

/**
 * 循环调用toString方法
 * 当两个对象相互引用，在调用toString方法时会产生这个异常，因为它们会循环调用toString方法。
 *     main申请的线程由于存在循环引用导致栈深度不足,导致StackOverflowError
 */

public class StackOverFlowDemo2 {
    @ToString
    static class Student {
        String name;
        Book book;

        public Student(String name) {
            this.name = name;
        }
    }

    @ToString
    static class Book {
        String isbn;
        Student student;

        public Book(String isbn, Student student) {
            this.isbn = isbn;
            this.student = student;
        }
    }

    public static void main(String[] args) {
        Student student=new Student("zhang3");
        Book book=new Book("1111",student);
        student.book=book;
        System.out.println(book);
    }
}
