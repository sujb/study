package fly.weight;

/**
 * @Author: sujinbo
 * @Date: 2018/4/19 23:50
 * @Description:
 */
public class FinancialReportManager implements IReportManager{

    protected String tenantId = null;

    public FinancialReportManager(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String createReport() {
        return "this is a financial report";
    }
}
