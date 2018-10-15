package com.dcits.yi.ui;

import org.openqa.selenium.WebDriver;

import com.dcits.yi.constant.TestConst;
import com.dcits.yi.ui.element.Locator;
import com.dcits.yi.ui.report.CaseReport;
import com.dcits.yi.ui.report.StepReport;

import cn.hutool.core.util.ReUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 属于每个测试线程的运行对象
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class TestRunningObject {
	private static final Log logger = LogFactory.get();
	
	private WebDriver driver;
	private CaseReport caseReport;
	private StepReport stepReport;
	/**
	 * 当前所在frame名称
	 */
	private String currentFrameName = TestConst.DEFAULT_FRAME_NAME;
	private String currentWindowHandle = null;
	
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setCurrentFrameName(String currentFrameName) {
		this.currentFrameName = currentFrameName;
	}
	
	public String getCurrentFrameName() {
		return currentFrameName;
	}

	/**
	 * 在每步操作之后都会检查窗口是否变动
	 */
	public void checkWindow(String methodName) {
		if (!this.driver.getWindowHandle().equals(currentWindowHandle)) {
			currentWindowHandle = this.driver.getWindowHandle();
			currentFrameName = TestConst.DEFAULT_FRAME_NAME;
		} else if (ReUtil.isMatch("open|close|refresh|forward|back|to", methodName)) {
			currentFrameName = TestConst.DEFAULT_FRAME_NAME;
		}
	}
	
	/**
	 * 切换到指定的frame层
	 * @param locator frame元素定位器
	 * @throws Exception
	 */
	public void switchFrame(Locator locator) throws Exception {
		if (locator == null) {
			logger.info("Switch To DefaultContent...");
			driver.switchTo().defaultContent();
			currentFrameName = TestConst.DEFAULT_FRAME_NAME;
			return;
		}
		logger.info("Switch Frame to {}:{} => {}[{}]", locator.getName(), locator.getLocationType(), locator.getLocationValue(), locator.getLocationSeq());
		try {
			driver.switchTo().frame(locator.getElement(driver));
			currentFrameName = locator.getName();
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Switch To Frame {} Fail!", locator.getName());
			throw e;
		}
	}
	
	public CaseReport getCaseReport() {
		return caseReport;
	}
	public void setCaseReport(CaseReport caseReport) {
		if (this.caseReport != null) GlobalTestConfig.report.getCaseReports().add(this.caseReport);
		this.caseReport = caseReport;
	}
	public StepReport getStepReport() {
		return stepReport;
	}
	public void setStepReport(StepReport stepReport) {
		if (this.caseReport != null && this.stepReport != null) this.caseReport.getStepReports().add(this.stepReport);
		this.stepReport = stepReport;
	}
	
}
