package com.dcits.test.baidu.usecase;

import com.dcits.test.baidu.page.BaiduSearchPage;
import com.dcits.test.baidu.page.SearchResultPage;
import com.dcits.yi.ui.usecase.UseCase;

public class Baidu {
	
	public BaiduSearchPage page;
	public SearchResultPage result;
	
	@UseCase(name="百度搜索")
	public void search() throws Exception {
		page.open();
		page.search("xuwangcheng.com");
		result.clickFirst();	
		result.sleep(5);
		System.out.println(page.getDriver().getWindowHandle());
		page.switchWindow(1);
		System.out.println(page.getDriver().getWindowHandle());
		page.switchWindow(0);
		System.out.println(page.getDriver().getWindowHandle());
		page.refresh();
		System.out.println(page.getDriver().getWindowHandle());
	}
}
