package com.dcits.yi.ui.report;

import java.util.ArrayList;
import java.util.List;

import com.dcits.yi.ui.EnvSettingInfo;

/**
 * 测试套件报告
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class SuiteReport {
	
	/**
	 * 测试标题
	 */
	private String title;
	/**
	 * 浏览器名称
	 */
	private String browserName;
	/**
	 * 开始时间
	 */
	private String testTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 用例总数
	 */
	private int totalCount = 0;
	/**
	 * 成功数
	 */
	private int successCount = 0;
	/**
	 * 失败数
	 */
	private int failCount = 0;
	
	/**
	 * 环境信息
	 */
	private EnvSettingInfo env;
	
	/**
	 * 包含的用例测试报告
	 */
	private List<CaseReport> caseReports = new ArrayList<CaseReport>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public synchronized void setSuccessCount() {
		this.successCount += 1;
	}

	public int getFailCount() {
		return failCount;
	}

	public synchronized void setFailCount() {
		this.failCount += 1;
	}

	public EnvSettingInfo getEnv() {
		return env;
	}

	public void setEnv(EnvSettingInfo env) {
		this.env = env;
	}

	public List<CaseReport> getCaseReports() {
		return caseReports;
	}

	public void setCaseReports(List<CaseReport> caseReports) {
		this.caseReports = caseReports;
	}

	@Override
	public String toString() {
		return "SuiteReport [title=" + title + ", browserName=" + browserName + ", testTime=" + testTime + ", endTime="
				+ endTime + ", totalCount=" + totalCount + ", successCount=" + successCount + ", failCount=" + failCount
				+ ", env=" + env + ", caseReports=" + caseReports + "]";
	}
}
