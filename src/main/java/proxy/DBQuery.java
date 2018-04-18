package proxy;

import java.util.concurrent.TimeUnit;

/**
 * @Author: sujinbo
 * @Date: 2018/4/18 23:42
 * @Description:
 */
public class DBQuery implements IDBQuery{

    public DBQuery() {
        //测试性能时注释此行代码
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String request() {
        return "request string";
    }
}
