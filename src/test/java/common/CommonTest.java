package common;

import com.dcits.test.baidu.usecase.Baidu;
import com.dcits.yi.WebTest;
import com.dcits.yi.ui.report.manage.CucumberReportManager;
import com.dcits.yi.ui.report.manage.DefaultExeclReportManager;

public class CommonTest {
	
	public static void main(String[] args) throws Exception {
		//WebTest test = new WebTest("testsuite");
		WebTest test = new WebTest(Baidu.class);
		
		test.setReportManagers(new CucumberReportManager(), new DefaultExeclReportManager());		
		test.start();
		//System.out.println(JSONUtil.parse(GlobalTestConfig.report).toStringPretty());;
	}
}
