package com.gao.spring;

import com.gao.spring.controller.UserController;

/**
 * ע�� + ���� ʵ��autowired ����
 */
public class AnnotationTest  {
    public static void main(String[] args) {

        UserController userController = new UserController();
        Class<? extends UserController> userControllerClazz = userController.getClass();
        //��ȡ���е�����ֵ
        //Stream.of(userControllerClazz.getDeclaredFields()).forEach((field) -> {
        //    MyAutowired myAutowried = field.getAnnotation(MyAutowired.class);
        //    if (myAutowried != null) {
        //        field.setAccessible(true);
        //        //��ȡ��ǰ���Ե����� class com.gao.spring.service.UserService
        //        Class<?> type = field.getType();
        //        try {
        //            Object o = type.newInstance();   //new ��һ��userService����
        //            System.out.println(o);
        //            field.set(userController, o);  //����������ֵ
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //    }
        //});
        System.out.println(userController);
    }
}
