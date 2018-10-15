package com.dcits.yi.ui.element;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.dcits.yi.ui.GlobalTestConfig;
import com.dcits.yi.ui.report.CaseReport;
import com.dcits.yi.ui.report.StepReport;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 
 * @author xuwangcheng
 * @version 20181012
 *
 */
public abstract class BaseObject {
	
	private static final Log logger = LogFactory.get();
	public static final Map<String, String> location_Types = new HashMap<String, String>();
	
	static {
		location_Types.put("id", "Id");
		location_Types.put("linktext", "LinkText");
		location_Types.put("name", "Name");
		location_Types.put("tagname", "TagName");
		location_Types.put("xpath", "XPath");
		location_Types.put("classname", "ClassName");
		location_Types.put("partiallinktext", "PartialLinkText");
		location_Types.put("cssselector", "CssSelector");
	}
	
	/**
	 * 获取当前的WebDriver对象
	 * @return
	 */
	public WebDriver getDriver() {
		return GlobalTestConfig.getTestRunningObject().getDriver();
	}
	
	/**
	 * 获取当前的步骤报告对象
	 * @return
	 */
	public StepReport getStepReport() {
		return GlobalTestConfig.getTestRunningObject().getStepReport();
	}
	
	protected void setStepReport() {
		GlobalTestConfig.getTestRunningObject().setStepReport(new StepReport());
	}
	/**
	 * 获取当前的测试用例报告
	 * @return
	 */
	public CaseReport getCaseReport() {
		return GlobalTestConfig.getTestRunningObject().getCaseReport();
	}
	
	/**
	 * 等待时间,秒
	 * @param seconds 秒，可小数
	 */
	public void sleep(double seconds) {
		try {
			Thread.sleep((int)(seconds * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.warn(e, "InterruptedException！");
		}
	}
	
}
