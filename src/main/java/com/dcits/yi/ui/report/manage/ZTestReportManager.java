package com.dcits.yi.ui.report.manage;

import java.util.Date;

import com.dcits.yi.tool.TestKit;
import com.dcits.yi.ui.GlobalTestConfig;
import com.dcits.yi.ui.report.CaseReport;
import com.dcits.yi.ui.report.StepReport;
import com.dcits.yi.ui.report.SuiteReport;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

/**
 * ztest测试报告实现，样式参考<a href="https://github.com/zhangfei19841004/ztest">zhangfei-ztest</a>
 * @author xuwangcheng
 * @version 2018.10.22
 *
 */
public class ZTestReportManager implements IReportManager {

	@Override
	public void create(SuiteReport reportData) {
		JSONObject report = new JSONObject();
		report.put("testPass", reportData.getSuccessCount());
		report.put("testName", reportData.getTitle());
		report.put("testAll", reportData.getTotalCount());
		report.put("testFail", reportData.getFailCount());
		report.put("beginTime", reportData.getTestTime());
		report.put("totalTime", String.valueOf(DateUtil.between(DateUtil.parseDateTime(reportData.getEndTime())
				, DateUtil.parseDateTime(reportData.getTestTime()), DateUnit.MS)) + "ms");
		report.put("testSkip", 0);
		
		JSONArray results = new JSONArray();
		report.put("testResult", results);
		
		for (CaseReport caseReport:reportData.getCaseReports()) {
			JSONObject result = new JSONObject();
			String classMethod = caseReport.getCaseMethodPath();
			result.put("className", classMethod.substring(0, classMethod.lastIndexOf(".")));
			result.put("methodName", classMethod.substring(classMethod.lastIndexOf(".") + 1));
			result.put("description", caseReport.getCaseName());
			result.put("spendTime", caseReport.getUseTime() + "ms");
			result.put("status", "success".equals(caseReport.getStatus()) ? "成功" : "失败");
			
			JSONArray log = new JSONArray();
			for (StepReport step:caseReport.getStepReports()) {
				log.add("<strong>" + step.getStepName() + "</strong>");
				if (StrUtil.isNotEmpty(step.getLocation())) log.add("元素:&nbsp;" + step.getLocation());
				if (StrUtil.isNotEmpty(step.getParams())) log.add("参数:&nbsp;" + step.getParams());
				if (StrUtil.isNotEmpty(step.getResult())) log.add("结果:&nbsp;" + step.getResult());
				if (StrUtil.isNotEmpty(step.getMark())) log.add("<span style=\"color:red;\">" + step.getMark() + "</span><br>"); 
			}
			result.put("log", log);
			results.add(result);
		}
		
		String json = report.toString();
		String reportHtml = FileUtil.readString(TestKit.getProjectRootPath() + "/template/ztestTemplate", "utf-8");
		
		reportHtml = reportHtml.replace("${resultData}", json);
		FileUtil.writeString(reportHtml, GlobalTestConfig.ENV_INFO.getReportFolder() + "/" 
				+ reportData.getTitle() + "_" + DateUtil.format(new Date(), "yyyyMMddHHmmss") + "_ztest" + ".html", "utf-8");
		
	}

}
