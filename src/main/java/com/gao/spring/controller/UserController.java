package com.gao.spring.controller;

import com.gao.spring.annotation.MyAutowired;
import com.gao.spring.service.UserService;

/**
 * IOC �ײ�ʵ�� ����
 * �ֶ�ʵ��Autowrieע��   ʹ�÷���ʵ�� ע��
 */
public class UserController {
    //����ֻ��������һ������ ������һ��ʵ�ʵĶ���
    @MyAutowired              //���ֶ�ʵ�ֵ�ע��
    private UserService userService;

    @Override
    public String toString() {
        return "UserController{" +
                "userService=" + userService +
                '}';
    }
}
