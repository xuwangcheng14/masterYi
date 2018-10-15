package com.dcits.test.mail.data;

import com.dcits.yi.tool.TestKit;
import com.dcits.yi.ui.data.BaseDataModel;

import cn.hutool.setting.dialect.Props;

public class MailTestData extends BaseDataModel {
	
	public String send_email;
	public String send_password;
	
	public String receive_email;
	public String receive_password;
	
	public String send_content;
	
	/**
	 * 测试时，请换成你自己的邮箱账号和密码，同时注意使用的账号不要在登录的时候出现验证码
	 */
	@Override
	public void initData() {
		Props p = new Props(TestKit.getProjectRootPath() + "/config/data/email.data");
		
		
		send_email = p.getStr("send_email");
		send_password = p.getStr("send_password");
		
		receive_email = p.getStr("receive_email");
		receive_password = p.getStr("receive_password");
		
		send_content = "易大师UI自动化测试框架";
	}	
}
