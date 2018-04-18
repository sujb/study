package singleton;

/**
 * 饿汉式
 * 私有构造方法
 * 私有静态成员变量instance
 * 共有静态单例获取方法getInstance
 * @author sujinbo
 */
public class Singleton {

    private Singleton(){
        System.out.println("singletonInstance create");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }

}
