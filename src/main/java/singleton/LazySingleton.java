package singleton;

/**
 * 懒汉式
 * 私有构造方法
 * 静态私有成员变量，不初始化
 * 静态共有同步实例获取方法
 * @author sujinbo
 */
public class LazySingleton {

    private LazySingleton(){
        System.out.println("lazySingleton is created");
    }

    private static LazySingleton instance;

    public synchronized static LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

}
