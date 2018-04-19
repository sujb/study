package fly.weight;

/**
 * @Author: sujinbo
 * @Date: 2018/4/19 23:51
 * @Description:
 */
public class EmployeeReportManager implements IReportManager{

    protected String tenantId = null;

    public EmployeeReportManager(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String createReport() {
        return "this is a employee report";
    }
}
