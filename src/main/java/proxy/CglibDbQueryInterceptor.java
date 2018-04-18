package proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: sujinbo
 * @Date: 2018/4/19 00:00
 * @Description:
 */
public class CglibDbQueryInterceptor implements MethodInterceptor{

    IDBQuery real = null;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(real == null){
            real = new DBQuery();
        }
        return real.request();
    }
}
