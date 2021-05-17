package com.gao.spring.circularDependence;

/**
 * ���췽���޷����ѭ����������
 */
public class CircularDependence {
    public static void main(String[] args) {

    }
}

class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}

class ServiceB {
    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
