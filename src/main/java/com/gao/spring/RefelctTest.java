package com.gao.spring;

import com.gao.spring.controller.UserController;
import com.gao.spring.service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射给userController  注入 userService 实现@Autowire功能
 * 这个是写死的   spring因该是一个动态的 模板式的  因此 功能升级
 */
public class RefelctTest {
    // 这个测试的时候需要在userController 中添加 set,get 方法
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        Class<? extends UserController> userControllerClazz = userController.getClass();
        UserService userService = new UserService();
        //由于是私有的  因此需要有set,get方法来获取,设置值  因此在userController中添加set,get方法
        Field userServiceField = userControllerClazz.getDeclaredField("userService");//获取private 属性值
        //通过方法名称获取方法 设置属性值
        String methodName = userServiceField.getName();
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        String setMethodName = "set" + methodName;
        Method method = userControllerClazz.getMethod(setMethodName, UserService.class);
        method.invoke(userController, userService); //第一个参数是方法所在的类  第二个参数是方法的参数值
    }
}
