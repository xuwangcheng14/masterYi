package common;

import com.dcits.yi.WebTest;

public class CommonTest {
	
	public static void main(String[] args) throws Exception {
		//WebTest test = new WebTest("testsuite");
		WebTest test = new WebTest("testsuite");
		
		//test.setReportManagers(new ZTestReportManager());		
		test.start();
		//System.out.println(JSONUtil.parse(GlobalTestConfig.report).toStringPretty());;
	}
}
