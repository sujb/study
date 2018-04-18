package proxy;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Author: sujinbo
 * @Date: 2018/4/18 23:44
 * @Description:代理测试
 */
public class DBQueryTest {

    /**
     * 静态代理，延迟加载
     */
    @Test
    public void test() {
        IDBQuery dbQuery = new DBQueryProxy();
        System.out.println("代理对象创建完成 -> " + System.currentTimeMillis());
        System.out.println(dbQuery.request());
        System.out.println("执行方法的时候创建正式对象 -> " + System.currentTimeMillis());
    }

    /**
     * 动态代理性能测试
     * 1、jdk动态代理
     * 2、cglib
     * 3、javassist
     * createJdkProxy spend 3
     * jdkProxy class : com.sun.proxy.$Proxy4
     * call jdkProxy : 20
     * createCglibProxy spend 160
     * cglibProxy class : proxy.IDBQuery$$EnhancerByCGLIB$$eb523eb0
     * call cglibProxy : 10
     * createJavassistProxy spend 128
     * javassistProxy class : proxy.IDBQuery_$$_jvstab9_0
     * call javassistProxy : 11
     *
     */
    @Test
    public void test1() throws InstantiationException, IllegalAccessException {

        int circle = 30000000;

        IDBQuery d = null;

        long begin = System.currentTimeMillis();
        d = createJdkProxy();
        System.out.println("createJdkProxy spend " + (System.currentTimeMillis()-begin));
        System.out.println("jdkProxy class : " + d.getClass().getName());
        begin = System.currentTimeMillis();
        for(int i=0;i<circle;i++){
            d.request();
        }
        System.out.println("call jdkProxy : " + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        d = createCglibProxy();
        System.out.println("createCglibProxy spend " + (System.currentTimeMillis()-begin));
        System.out.println("cglibProxy class : " + d.getClass().getName());
        begin = System.currentTimeMillis();
        for(int i=0;i<circle;i++){
            d.request();
        }
        System.out.println("call cglibProxy : " + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        d = createJavassistProxy();
        System.out.println("createJavassistProxy spend " + (System.currentTimeMillis()-begin));
        System.out.println("javassistProxy class : " + d.getClass().getName());
        begin = System.currentTimeMillis();
        for(int i=0;i<circle;i++){
            d.request();
        }
        System.out.println("call javassistProxy : " + (System.currentTimeMillis() - begin));
    }


    /**
     * jdk内置动态代理生成对象
     *
     * @return
     */
    public static IDBQuery createJdkProxy() {
        IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IDBQuery.class}, new JDKDBQueryHandler());
        return jdkProxy;
    }

    /**
     * cglib生成代理对象
     *
     * @return
     */
    public static IDBQuery createCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new CglibDbQueryInterceptor());
        enhancer.setInterfaces(new Class[]{IDBQuery.class});
        IDBQuery cglibProxy = (IDBQuery) enhancer.create();
        return cglibProxy;
    }

    /**
     * javassist生成代理对象
     * @return
     */
    public static IDBQuery createJavassistProxy() throws IllegalAccessException, InstantiationException {

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(new Class[]{IDBQuery.class});
        Class proxyClass = proxyFactory.createClass();
        IDBQuery javassistProxy = (IDBQuery) proxyClass.newInstance();
        ((ProxyObject)javassistProxy).setHandler(new JavassistDbQueryHandler());
        return javassistProxy;

    }

}
