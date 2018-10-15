package com.dcits.test.baidu.page;

import com.dcits.yi.ui.element.BasePage;
import com.dcits.yi.ui.element.PageElement;

public class BaiduSearchPage extends BasePage {
	
	public PageElement 搜索框;
	public PageElement 搜索按钮;
	
	public void search(String keyword) {		
		搜索框.sendKeys(keyword);
		搜索按钮.click();
	}
}
