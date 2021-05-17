package com.gao.spring.circularDependence;

/**
 * ʹ��setter���Խ��ѭ����������
 */
public class CircularDependence2 {
    public static void main(String[] args) {
        ServiceAA serviceAA = new ServiceAA();
        ServiceBB serviceBB = new ServiceBB();
        serviceAA.setServiceBB(serviceBB);
        serviceBB.setServiceAA(serviceAA);
    }
}

class ServiceAA {
    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB) {
        this.serviceBB = serviceBB;
        System.out.println("A�����и�B");
    }
}

class ServiceBB {
    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
        System.out.println("B�����и�A");
    }
}
