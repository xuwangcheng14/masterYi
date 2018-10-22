package com.dcits.yi.ui.report;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试用例报告
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class CaseReport {
	/**
	 * 完成时间
	 */
	private String finishTime;
	/**
	 * 测试用例名称
	 */
	private String caseName;
	/**
	 * 测试结果：success-成功， fail/error-失败
	 */
	private String status = "success";
	/**
	 * 失败，一般只在失败时才有
	 */
	private String mark;
	/**
	 * 执行消耗时间
	 */
	private String useTime;
	/**
	 * 执行次数
	 */
	private int runCount;
	/**
	 * 测试用例方法路径
	 */
	private String caseMethodPath;
	
	/**
	 * 包含的测试步骤报告
	 */
	private List<StepReport> stepReports = new ArrayList<StepReport>();
	
		
	public void setCaseMethodPath(String caseMethodPath) {
		this.caseMethodPath = caseMethodPath;
	}
	
	public String getCaseMethodPath() {
		return caseMethodPath;
	}
	
	public int getRunCount() {
		return runCount;
	}

	public void setRunCount(int runCount) {
		this.runCount = runCount;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getMark() {
		return mark;
	}
	
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	
	public String getUseTime() {
		return useTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<StepReport> getStepReports() {
		return stepReports;
	}

	public void setStepReports(List<StepReport> stepReports) {
		this.stepReports = stepReports;
	}

	@Override
	public String toString() {
		return "CaseReport [finishTime=" + finishTime + ", caseName=" + caseName + ", status=" + status
				+ ", stepReports=" + stepReports + "]";
	}
}
