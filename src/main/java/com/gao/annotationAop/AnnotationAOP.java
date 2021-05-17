package com.gao.annotationAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAOP {
    //Controller层切点
    @Pointcut("execution (* com.gao.annotationAop.*(..))")
    public void controllerAspect() {
    }

    @Pointcut
    public void pointcut() {

    }

    @After("pointcut()")
    public void afterPoint() {

    }

    @Around("@annotation(Name)")
    public void annotationAspect(ProceedingJoinPoint pjp) throws Throwable {
        Class<? extends ProceedingJoinPoint> aClass = pjp.getClass();
        System.out.println(aClass);
        pjp.proceed();
    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========执行controller前置通知===============");

    }


}
