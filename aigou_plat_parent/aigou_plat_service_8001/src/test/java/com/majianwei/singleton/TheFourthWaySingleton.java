package com.majianwei.singleton;

//懒汉模式优化版
public class TheFourthWaySingleton {
    private static volatile TheFourthWaySingleton instance = null;//volatile关键字防止多线程之间的变量覆盖，保证一致性
    private TheFourthWaySingleton(){}
    private static TheFourthWaySingleton getInstance(){
        if (instance == null){
            synchronized (TheFourthWaySingleton.class){//这里synchronized是锁方法级别的
                if (instance ==null){
                    instance = new TheFourthWaySingleton();
                }
            }
        }
        return instance;
    }
}
