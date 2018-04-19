package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author: sujinbo
 * @Date: 2018/4/20 00:33
 * @Description:
 */
public class MyObserver implements Observer {

    public MyObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg);
    }
}
