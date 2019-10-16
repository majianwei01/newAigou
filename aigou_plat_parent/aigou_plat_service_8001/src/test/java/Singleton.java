/*
* 懒汉单例模式 - - - 线程不安全
* */
public class Singleton {
    Object o;
    private static  Singleton instance = null;
    private static Singleton instance2 = new Singleton();
    private static volatile Singleton instance3;//volatile同步锁修饰----解决多线程访问创建多个对象的问题

    private  Singleton() {
    }

    //懒汉模式，线程不安全
    public static Singleton getInstance(){
        if (instance == null){
            instance = new Singleton();
        }
        return  instance;
    }

    //恶汉模式，instance2变量在类加载的时候就已经创建了一个实例；天生是线程安全的
    public static Singleton getInstance2(){
        return instance2;
    }

    //静态内部类的方式实现单例，保证多线程对象的唯一性，不同加同步锁
    private static class SingletonSon{
        public static Singleton instance3 = new Singleton();
    }

    public Singleton getInstance3(){
        return SingletonSon.instance3;
    }
}
