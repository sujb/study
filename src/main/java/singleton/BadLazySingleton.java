package singleton;

import java.util.concurrent.TimeUnit;

/**
 * @Author: sujinbo
 * @Date: 2018/4/18 00:20
 * @Description:
 */
public class BadLazySingleton {

    private BadLazySingleton(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("badLazySingleton created");
    }

    private static BadLazySingleton instance;

    public static BadLazySingleton getInstance(){
        if(instance == null){
            instance = new BadLazySingleton();
        }
        return instance;
    }

}
