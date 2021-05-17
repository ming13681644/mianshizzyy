package com.gao;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 要了解在jvm中的位置,
 * 基本类型在栈中分配 , 栈管运行(方法引用,对象引用,基本类型) ,堆管存储
 */
public class TransferValueTest {
    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("name");
    }

    public void changeValue3(String string) {
        string = "***";
    }

    public static void main(String[] args) {
        TransferValueTest test = new TransferValueTest();
        //main方法的局部变量 存放在方法栈中
        int age = 20;
        /**
         * 栈(基本类型,方法的声明)
         *  TransferValue.changeValue1(20)
         *  age=30(弹栈)
         * ----------
         * main age=20(打印)
         *
         */
        test.changeValue1(age); //age创建在栈中TransferValue.changeValue1()方法中的age修改为30 之后方法执行结束弹栈  而下一步要打印的是main线程中的值
        System.out.println("age " + age);//打印的是main线程中的值
        /**
         * 栈                                      堆
         *  所以进行修改就会变值
         * TransferValue.changeValue2(person)
         * person指向堆中的person------------------\
         *                                         \
         *  main                                    \
         * person -------------------------------->person("aaa")
         */
        Person aaa = new Person("aaa");
        test.changeValue2(aaa);
        System.out.println(aaa.getName());
        /**
         *   Java中的常量池，实际上分为两种形态：静态常量池和运行时常量池。
         *      所谓静态常量池，即*.class文件中的常量池，class文件中的常量池不仅仅包含字符串(数字)字面量，还包含类、方法的信息
         *      ，占用class文件绝大部分空间。这种常量池主要用于存放两大类常量：字面量(Literal)和符号引用量(Symbolic References)
         *      ，字面量相当于Java语言层面常量的概念，如文本字符串，声明为final的常量值等，符号引用则属于编译原理方面的概念，包括了如下三种类型的常量：
         *          类和接口的全限定名
         *          字段名称和描述符
         *          方法名称和描述符
         *      而运行时常量池，则是jvm虚拟机在完成类装载操作后，将class文件中的常量池载入到内存中，并保存在方法区中，我们常说的常量池，就是指方法区中的运行时常量池。
         */
        // 常量池中 如果没有就会新建一个ss
        /**
         *                                   常量池
         *                                "aaaa"     "***"
         *                                 /          /
         *   main.ss----------------------/          /
         *   TransferValueTest.changeValue3(ss)-----/
         *   没有堆main.ss产生影响
         */
        //注意方法作用域 还有jvm
        String ss = "aaaa";
        test.changeValue3(ss); //在常量池中找*** test.age的值指向这个***
        System.out.println(ss);

        String s1 = "a";
        String s2 = "b";
        String s3 = s1 + s2; //堆对象 不在常量池中
        String s4 = "ab";
        System.out.println(s3 == s4);
    }

}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;

    public Person(String name) {
        this.name = name;
    }
}
