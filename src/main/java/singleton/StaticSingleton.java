package singleton;

/**
 * 静态内部类，懒汉式
 * 私有构造方法
 * 私有静态内部类，包含静态私有成员变量承载单例
 * 公有静态方法获取实例
 * @author sujinbo
 */
public class StaticSingleton {

    private StaticSingleton(){
        System.out.println("static singleton is created");
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }

}
