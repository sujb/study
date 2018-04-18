package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: sujinbo
 * @Date: 2018/4/18 23:58
 * @Description:
 */
public class JDKDBQueryHandler implements InvocationHandler{

    IDBQuery real = null;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(real == null){
            real = new DBQuery();
        }
        return real.request();
    }
}
