package fly.weight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: sujinbo
 * @Date: 2018/4/19 23:49
 * @Description:享元工厂
 */
public class ReportManagerFactory {

    Map<String,IReportManager> financialReportManager = new HashMap<>();
    Map<String,IReportManager> employeeReportManager = new HashMap<>();


    IReportManager getFinancialReportManager(String tenantId){
        IReportManager reportManager = financialReportManager.get(tenantId);
        if(reportManager == null){
            reportManager = new FinancialReportManager(tenantId);
            financialReportManager.put(tenantId,reportManager);
        }
        return reportManager;
    }

    IReportManager getEmpolyeeReportManager(String tenantId){
        IReportManager reportManager = employeeReportManager.get(tenantId);
        if(reportManager == null){
            reportManager = new EmployeeReportManager(tenantId);
            employeeReportManager.put(tenantId,reportManager);
        }
        return reportManager;
    }


}
