package fly.weight;

/**
 * @Author: sujinbo
 * @Date: 2018/4/19 23:58
 * @Description：享元模式用于提升系统性能，主要作用就是复用大对象（重量级对象），节省内存空间和对象创建时间
 */
public class FlyWeightTest {

    public static void main(String[] args){
        ReportManagerFactory factory = new ReportManagerFactory();
        IReportManager reportManager = factory.getEmpolyeeReportManager("1");
        System.out.println(reportManager.createReport());
    }

}
