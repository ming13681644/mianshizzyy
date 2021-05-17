package com.gao.sofoom;

import java.nio.ByteBuffer;

/**
 * ��NIO����ؾ���ʹ��ByteBuffer����ȡ����д������һ�ֻ���Channel ����buffer ��I/O��ʽ,������ʹ��Native����ֱ�ӷ�������ڴ�Ȼ��ͨ��һ���洢��java���е�
 * 		DirectByteBuffer������Ϊ����ڴ�����ý��в���,������һЩ���������������,��Ϊ�����˶���Native�������ظ�������
 * 	���ǲ��Ϸ��䱾���ڴ�����ڴ����ʹ����ôjvm����GC DirectBuffer���󲻻ᱻ����,��ʱ���ڴ�ḻ�������ڴ�Խ�,�ٴγ��Է��䱾���ڴ��ʱ��ͻ����OOM
 * -Xms2m -Xmx2m  -XX:MaxDirectMemorySize=5m
 */
public class DirectBufferDemo {
    public static void main(String[] args) {
        System.out.println("���õ�maxDirectMemory��С:" + sun.misc.VM.maxDirectMemory() / (double) (1024 * 1024) + "MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
