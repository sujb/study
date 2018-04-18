package proxy;

/**
 * @Author: sujinbo
 * @Date: 2018/4/18 23:43
 * @Description:
 */
public class DBQueryProxy implements IDBQuery{

    private IDBQuery dbQuery;

    @Override
    public String request() {
        if(dbQuery==null){
            dbQuery = new DBQuery();
        }
        return dbQuery.request();
    }
}
