package com.gao.spring;

import com.gao.spring.controller.UserController;
import com.gao.spring.service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * �����userController  ע�� userService ʵ��@Autowire����
 * �����д����   spring�����һ����̬�� ģ��ʽ��  ��� ��������
 */
public class RefelctTest {
    // ������Ե�ʱ����Ҫ��userController ����� set,get ����
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        Class<? extends UserController> userControllerClazz = userController.getClass();
        UserService userService = new UserService();
        //������˽�е�  �����Ҫ��set,get��������ȡ,����ֵ  �����userController�����set,get����
        Field userServiceField = userControllerClazz.getDeclaredField("userService");//��ȡprivate ����ֵ
        //ͨ���������ƻ�ȡ���� ��������ֵ
        String methodName = userServiceField.getName();
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        String setMethodName = "set" + methodName;
        Method method = userControllerClazz.getMethod(setMethodName, UserService.class);
        method.invoke(userController, userService); //��һ�������Ƿ������ڵ���  �ڶ��������Ƿ����Ĳ���ֵ
    }
}
