package proxy;

import javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;

/**
 * @Author: sujinbo
 * @Date: 2018/4/19 00:02
 * @Description:
 */
public class JavassistDbQueryHandler implements MethodHandler{

    IDBQuery real = null;

    @Override
    public Object invoke(Object o, Method method, Method method1, Object[] objects) throws Throwable {
        if(real == null){
            real = new DBQuery();
        }
        return real.request();
    }
}
