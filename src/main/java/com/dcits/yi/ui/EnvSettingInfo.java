package com.dcits.yi.ui;

import com.dcits.yi.tool.TestKit;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 测试环境信息
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class EnvSettingInfo {
	
	public static boolean DEV_MODE = true;
	
	private boolean remoteMode;
	
	private String hubRemoteUrl;
	
	private String reportFolder = TestKit.getProjectRootPath() + "/report";
	private String screenshotFolder = "/screenshot";;
	
	private String elementFolder = TestKit.getProjectRootPath() + "/config/element/";
	private String suiteFolder = TestKit.getProjectRootPath() + "/config/suite/";
	
	private String chromeDriverPath = TestKit.getProjectRootPath() + "/src/main/resources/chromedriver.exe";
	private String ieDriverPath = TestKit.getProjectRootPath() + "/src/main/resources/IEDriverServer.exe";
	private String operaDriverPath = TestKit.getProjectRootPath() + "/src/main/resources/operadriver.exe";
	private String firefoxDriverPath = TestKit.getProjectRootPath() + "/src/main/resources/geckodriver.exe";
	
	private Double defaultSleepSeconds;
	
	private Integer elementLocationRetryCount;
	private Double elementLocationTimeouts;

	public EnvSettingInfo(Props props) {
		super();
		remoteMode = props.getBool("remote_mode", false);
		
		hubRemoteUrl = TestKit.getStrIsNotEmpty(props, "hub.remote.url", "http://127.0.0.1:4444/wd/hub");
		
		defaultSleepSeconds = props.getDouble("sleep_seconds", 0.5);
		
		elementLocationRetryCount = props.getInt("element_location_retry_count", 2);
		elementLocationTimeouts = props.getDouble("element_location_timeouts", 6.00);
		
		if (!EnvSettingInfo.DEV_MODE) {
			elementFolder =  TestKit.getProjectRootPath() + "/config/element/";
			suiteFolder =  TestKit.getProjectRootPath() + "/config/suite/";
			chromeDriverPath = TestKit.getProjectRootPath() + "/drivers/chromedriver.exe";
			ieDriverPath = TestKit.getProjectRootPath() + "/drivers/IEDriverServer.exe";
			operaDriverPath = TestKit.getProjectRootPath() + "/drivers/operadriver.exe";
			firefoxDriverPath = TestKit.getProjectRootPath() + "/drivers/geckodriver.exe";
		}
		
		if (StrUtil.isNotEmpty(chromeDriverPath)) System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		if (StrUtil.isNotEmpty(ieDriverPath)) System.setProperty("webdriver.ie.driver", ieDriverPath);
		if (StrUtil.isNotEmpty(firefoxDriverPath)) System.setProperty("webdriver.firefox.bin", firefoxDriverPath);
		if (StrUtil.isNotEmpty(operaDriverPath)) System.setProperty("webdriver.opera.driver", operaDriverPath);	
	}

	public Integer getElementLocationRetryCount() {
		return elementLocationRetryCount;
	}

	public void setElementLocationRetryCount(Integer elementLocationRetryCount) {
		this.elementLocationRetryCount = elementLocationRetryCount;
	}

	public Double getElementLocationTimeouts() {
		return elementLocationTimeouts;
	}

	public void setElementLocationTimeouts(Double elementLocationTimeouts) {
		this.elementLocationTimeouts = elementLocationTimeouts;
	}

	public String getElementFolder() {
		return elementFolder;
	}

	public void setElementFolder(String elementFolder) {
		this.elementFolder = elementFolder;
	}

	public String getSuiteFolder() {
		return suiteFolder;
	}

	public void setSuiteFolder(String suiteFolder) {
		this.suiteFolder = suiteFolder;
	}

	public boolean isRemoteMode() {
		return remoteMode;
	}

	public void setRemoteMode(boolean remoteMode) {
		this.remoteMode = remoteMode;
	}

	public String getHubRemoteUrl() {
		return hubRemoteUrl;
	}

	public void setHubRemoteUrl(String hubRemoteUrl) {
		this.hubRemoteUrl = hubRemoteUrl;
	}

	public String getReportFolder() {
		return reportFolder;
	}

	public void setReportFolder(String reportFolder) {
		this.reportFolder = reportFolder;
	}

	public String getScreenshotFolder() {
		return screenshotFolder;
	}

	public void setScreenshotFolder(String screenshotFolder) {
		this.screenshotFolder = screenshotFolder;
	}

	public String getChromeDriverPath() {
		return chromeDriverPath;
	}

	public void setChromeDriverPath(String chromeDriverPath) {
		this.chromeDriverPath = chromeDriverPath;
	}

	public String getIeDriverPath() {
		return ieDriverPath;
	}

	public void setIeDriverPath(String ieDriverPath) {
		this.ieDriverPath = ieDriverPath;
	}

	public String getOperaDriverPath() {
		return operaDriverPath;
	}

	public void setOperaDriverPath(String operaDriverPath) {
		this.operaDriverPath = operaDriverPath;
	}

	public String getFirefoxDriverPath() {
		return firefoxDriverPath;
	}

	public void setFirefoxDriverPath(String firefoxDriverPath) {
		this.firefoxDriverPath = firefoxDriverPath;
	}

	public Double getDefaultSleepSeconds() {
		return defaultSleepSeconds;
	}

	public void setDefaultSleepSeconds(Double defaultSleepSeconds) {
		this.defaultSleepSeconds = defaultSleepSeconds;
	}

	@Override
	public String toString() {
		return "EnvSettingInfo [remoteMode=" + remoteMode + ", hubRemoteUrl=" + hubRemoteUrl
				+ ", reportFolder=" + reportFolder + ", screenshotFolder=" + screenshotFolder + ", elementFolder="
				+ elementFolder + ", suiteFolder=" + suiteFolder + ", chromeDriverPath=" + chromeDriverPath
				+ ", ieDriverPath=" + ieDriverPath + ", operaDriverPath=" + operaDriverPath + ", firefoxDriverPath="
				+ firefoxDriverPath + ", defaultSleepSeconds=" + defaultSleepSeconds + ", elementLocationRetryCount="
				+ elementLocationRetryCount + ", elementLocationTimeouts=" + elementLocationTimeouts + "]";
	}
}
