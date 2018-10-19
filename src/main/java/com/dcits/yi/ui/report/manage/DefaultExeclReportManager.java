package com.dcits.yi.ui.report.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dcits.yi.ui.GlobalTestConfig;
import com.dcits.yi.ui.report.CaseReport;
import com.dcits.yi.ui.report.SuiteReport;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

/**
 * 默认excel报告处理器
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class DefaultExeclReportManager implements IReportManager {

	@Override
	public void create(SuiteReport reportData) {
		List<List<String>> rows = new ArrayList<>();
		for (CaseReport r:reportData.getCaseReports()) {
			rows.add(CollUtil.newArrayList(r.getCaseName(), r.getFinishTime(), r.getStatus(), r.getUseTime(), String.valueOf(r.getRunCount()), r.getMark()));
		}		
		ExcelWriter writer = ExcelUtil.getWriter(GlobalTestConfig.ENV_INFO.getReportFolder() + "/" + reportData.getTitle() + "_" 
				+ DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".xlsx");	
		writer.writeHeadRow(CollUtil.newArrayList("用例名称", "执行时间", "状态", "耗时(ms)", "运行次数", "备注"));
		writer.write(rows);
		writer.close();
	}

}
