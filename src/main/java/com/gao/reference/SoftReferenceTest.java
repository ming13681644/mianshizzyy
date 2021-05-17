package com.gao.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.lang.ref.SoftReference;
import java.util.concurrent.locks.ReentrantLock;

public class SoftReferenceTest extends ReentrantLock{
    public synchronized void method01(){
        method02();
    }
    public synchronized void method02(){

    }


    public static void main(String[] args) {

        Browser prev = new Browser("aaa");               // 获取页面进行浏览
        SoftReference sr = new SoftReference(prev); // 浏览完毕后置为软引用
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.gc();
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        if(sr.get()!=null){
            prev = (Browser) sr.get();           // 还没有被回收器回收，直接获取
            System.out.println(prev);
        }else{
            prev = new Browser("bbb");               // 由于内存吃紧，所以对软引用的对象回收了
            System.out.println(prev);
            sr = new SoftReference(prev);       // 重新构建
        }
    }
}
@Data
@AllArgsConstructor
@ToString
class Browser{
    private String name;
}
