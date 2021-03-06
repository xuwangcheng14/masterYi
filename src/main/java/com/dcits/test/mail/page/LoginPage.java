package com.dcits.test.mail.page;

import com.dcits.yi.ui.element.BasePage;
import com.dcits.yi.ui.element.PageElement;

/**
 * This PageModel is generated by MasterYIUITest
 * @author xuwangcheng14@163.com
 */

public class LoginPage extends BasePage {
	
	public PageElement 用户名输入框;	
	public PageElement 密码输入框;	
	public PageElement 登录按钮;	
	
	
	public void 登录(String username, String passwd) {
		用户名输入框.sendKeys(username, true);
		密码输入框.sendKeys(passwd, true);
		//等待验证码，手工输入
		//sleep(20);
		登录按钮.click();
		sleep(1);
		screenshot();
	}
}