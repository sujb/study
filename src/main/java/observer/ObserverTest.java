package observer;

import org.junit.Test;

/**
 * @Author: sujinbo
 * @Date: 2018/4/20 00:34
 * @Description:观察者模式用于事件监听，通知发布等场合，可以确保观察者在不使用轮询的情况下，及时收到相关消息和事件
 */
public class ObserverTest {

    @Test
    public void test() throws InterruptedException {
        MyObservable observable = new MyObservable();
        new MyObserver(observable);
        new MyObserver(observable);
        new MyObserver(observable);
        observable.doSomeThing();
    }

}
