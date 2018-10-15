package com.dcits.yi.ui.element;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.dcits.yi.ui.GlobalTestConfig;
import com.dcits.yi.ui.element.basics.IBaseElement;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 元素对象基类
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class PageElement extends BaseObject implements IBaseElement {
	private static final Log logger = LogFactory.get();
	
	protected WebElement ele;	
	protected Locator locator = new Locator();

	protected String name;
	
	public void setLocator(Locator locator) {
		this.locator = locator;
	}
	
	public Locator getLocator() {
		return locator;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 获取WebElement对象
	 * @return
	 */
	protected WebElement getEle() {
		ele = null;	
		getStepReport().setElementName(getName());
		getStepReport().setLocation(StrFormatter.format("{} => {}{}", locator.getLocationType(), locator.getLocationValue()
				, (locator.getLocationSeq() > 0 ? "[" + locator.getLocationSeq() + "]" : "")));
		
		try {	
			ele = locator.getElement(getDriver());			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("无法定位到元素 [{}][{} => {}],  当前Frame：{}", getName(), locator.getLocationType(), locator.getLocationValue()
					, GlobalTestConfig.getTestRunningObject().getCurrentFrameName());
			getStepReport().setMark(StrFormatter.format("元素定位失败:{} => {}{}\n", locator.getLocationType(), locator.getLocationValue()
					, (locator.getLocationSeq() > 0 ? "[" + locator.getLocationSeq() + "]" : "")));
		}
		
		sleep(GlobalTestConfig.ENV_INFO.getDefaultSleepSeconds());
		return ele;
	}


	@Override
	public String getText() {
		// TODO Auto-generated method stub
		getEle();
		String result = ele.getText();
		getStepReport().setResult(result);		
		return result;
	}

	@Override
	public String getAttributeValue(String attributeName) {
		// TODO Auto-generated method stub
		getEle();	
		String result = ele.getAttribute(attributeName);
		getStepReport().setResult(result);
		return result;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		getEle();
		String result = ele.getTagName();
		getStepReport().setResult(result);
		return result;
	}

	@Override
	public void mouseHover() {
		getEle();
		new Actions(getDriver()).moveToElement(ele).perform();
	}

	@Override
	public void mouseRightClick() {
		// TODO Auto-generated method stub
		getEle();
		new Actions(getDriver()).contextClick(ele).perform();
		
	}

	@Override
	public void mouseDoubleClick() {
		// TODO Auto-generated method stub
		getEle();
		new Actions(getDriver()).doubleClick(ele).perform();
	}

	@Override
	public boolean isExist() {
		// TODO Auto-generated method stub
		getEle();
		if (ele == null) {
			return false;
		}
		return true;
	}

	@Override
	public void mouseDragAndDrop(PageElement end) {
		getEle();
		new Actions(getDriver()).dragAndDrop(ele, end.getEle()).perform();
	}

	@Override
	public void swipe(int x, int y) {
		getEle();
		new Actions(getDriver()).dragAndDropBy(ele, x, y);
	}

	@Override
	public void upload(String filePath) {
		getEle();
		ele.sendKeys(filePath);
	}

	@Override
	public void mouseClick() {
		getEle();
		new Actions(getDriver()).click(ele).perform();	
	}

	@Override
	public void click() {
		getEle();
		ele.click();
	}
	
	@Override
	public void sendKeys(String str) {
		getEle();
		ele.sendKeys(str);
		
	}

	@Override
	public void clear() {
		getEle();
		ele.clear();
	}

	@Override
	public void sendKeys(String str, boolean clearFlag) {
		getEle();
		if (clearFlag) ele.clear();
		ele.sendKeys(str);		
	}
	
	@Override
	public Select getSelect() {
		getEle();
		return new Select(ele);
	}

	@Override
	public void selectByValue(String value) {
		getSelect().selectByValue(value);
	}

	@Override
	public void selectByOption(String option) {
		getSelect().selectByVisibleText(option);
	}

	@Override
	public String getSelectedValue() {
		getEle();
		String str = ele.getAttribute("value");
		getStepReport().setResult(str);
		return str;
	}

	@Override
	public Map<String, String> getAllOptions() {
		Map<String, String> map = new HashMap<String, String>();
		for (WebElement e:getSelect().getAllSelectedOptions()) {
			map.put(e.getAttribute("value"), e.getText());
		}
		getStepReport().setResult(map.toString());
		return map;
	}
}
