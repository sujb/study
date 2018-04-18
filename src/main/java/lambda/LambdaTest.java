package lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * lambda表达式基础语法
 * Created by Administrator on 2018/4/17.
 */
public class LambdaTest {

    /**
     * 无参数,无返回值
     */
    @Test
    public void test1() throws InterruptedException {
        Runnable runnable = () -> System.out.println("hello lambda");
        new Thread(runnable).start();
        TimeUnit.MILLISECONDS.sleep(200);
    }

    /**
     * 有一个参数，无返回值
     */
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("hello lambda!");
    }

    /**
     * 有一个参数，小括号可以省略
     * 不建议
     */
    @Test
    public void test3() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("hi lambda");
    }

    /**
     * 有两个以上的参数，有返回值，并且lambda体中有多条语句
     */
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式编程");
            return Integer.compare(x, y);
        };
        System.out.println(comparator.compare(1, 2));
    }

    /**
     * lambda体中只有一条语句，return和大括号都可以不写
     */
    @Test
    public void test5() {
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(2, 1));
    }

    /**
     * Lambda表达式参数列表的数据类型可以省略，JVM会通过上下文推断
     * 建议省略
     */
    @Test
    public void test6() {
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(1, 1));
    }

    /**
     * 函数式接口：@FunctionalInterface,只能有一个抽象方法
     */
    @Test
    public void test7() {
        operation(200,(x) -> x*x);
        operation(200,(x) -> x + x);
    }

    private void operation(Integer x,MyFun myFun){
        System.out.println(myFun.getValue(x));
    }

}

@FunctionalInterface
interface MyFun {
    public Integer getValue(Integer x);
}