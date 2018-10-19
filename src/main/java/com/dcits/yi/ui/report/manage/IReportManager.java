package com.dcits.yi.ui.report.manage;

import com.dcits.yi.ui.report.SuiteReport;

/**
 * 测试报告处理器接口
 * @author xuwangcheng
 * @version 20181012
 *
 */
public interface IReportManager {
	/**
	 * 处理测试报告数据
	 * @param reportData
	 */
	void create(SuiteReport reportData);
}
