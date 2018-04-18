package singleton;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 单例测试
 *
 * @author sujinbo
 */
public class SingletonTest {

    /**
     * 功能测试
     */
    @Test
    public void test1() throws InterruptedException {
        new Thread(() -> System.out.println(Singleton.getInstance())).start();
        new Thread(() -> System.out.println(Singleton.getInstance())).start();

        new Thread(() -> System.out.println(LazySingleton.getInstance())).start();
        new Thread(() -> System.out.println(LazySingleton.getInstance())).start();

        new Thread(() -> System.out.println(StaticSingleton.getInstance())).start();
        new Thread(() -> System.out.println(StaticSingleton.getInstance())).start();

        new Thread(() -> System.out.println(BadLazySingleton.getInstance())).start();
        new Thread(() -> System.out.println(BadLazySingleton.getInstance())).start();

        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 性能测试
     * 饿汉式性能和静态内部类的性能差不多
     * 静态内部类的方式性能较好
     * 懒加载方式性能最差
     */
    @Test
    public void test2() throws InterruptedException {

        int count = 1000000;
        Singleton.getInstance();
        LazySingleton.getInstance();
        StaticSingleton.getInstance();

        new Thread(() -> {
            Long beginTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                Singleton.getInstance();
            }
            System.out.println("singleton spend : " + (System.currentTimeMillis() - beginTime));
        }).start();

        new Thread(() -> {
            Long beginTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                LazySingleton.getInstance();
            }
            System.out.println("lazySingleton spend : " + (System.currentTimeMillis() - beginTime));

        }).start();

        new Thread(() -> {
            Long beginTime = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                StaticSingleton.getInstance();
            }
            System.out.println("staticSingleton spend : " + (System.currentTimeMillis() - beginTime));
        }).start();

        TimeUnit.SECONDS.sleep(2);

    }

}
