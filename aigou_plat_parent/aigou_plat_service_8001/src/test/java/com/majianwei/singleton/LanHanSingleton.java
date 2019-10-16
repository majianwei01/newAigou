package com.majianwei.singleton;

//懒汉模式 线程不安全
//多个线程进入getIstance方法时，可能会同时进入产生多个实例
public class LanHanSingleton {
    private static  LanHanSingleton instance = null;
    private LanHanSingleton(){}
    private static LanHanSingleton getInstance(){
        if (instance == null){
            instance = new LanHanSingleton();
        }
        return  instance;
    }
}
