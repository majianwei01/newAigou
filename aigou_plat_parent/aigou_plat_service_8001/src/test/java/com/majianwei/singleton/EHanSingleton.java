package com.majianwei.singleton;

//饿汉模式 线程安全
//缺点：这种写法会在内存中创建多个对象（不管对象有没有用到），占资源
public class EHanSingleton {
    private  static final EHanSingleton instance = new EHanSingleton();
    private  EHanSingleton(){

    }
    private static EHanSingleton getInstance(){
        return instance;
    }
}
