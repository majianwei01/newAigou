package com.majianwei.singleton;
//静态内部类的写法
//比较灵活巧妙，但是如果通过反射来获取对象时对象又时多个不同的对象了
public class NeiBuNeiSingleton {
    private NeiBuNeiSingleton(){}
    private NeiBuNeiSingleton getInstance(){
        return StaticNeiBuNei.instance;
    }
    public static class StaticNeiBuNei{
        private static NeiBuNeiSingleton instance = new NeiBuNeiSingleton();
    }
}
