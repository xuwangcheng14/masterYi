package com.dcits.test.mail.usecase;

import com.dcits.test.mail.data.MailTestData;
import com.dcits.test.mail.page.LoginPage;
import com.dcits.test.mail.page.MailLogoutPage;
import com.dcits.test.mail.page.MailPage;
import com.dcits.yi.ui.usecase.UseCase;

public class MailTest {
	
	public LoginPage loginPage;
	public MailPage mailPage;
	public MailLogoutPage logoutPage;
	
	public MailTestData data;
	
	@UseCase(name="163邮箱测试")
	public void mailTest() throws Exception {
		loginPage.open();
		loginPage.login(data.send_email, data.send_password);
		mailPage.verifyLogin(data.send_email);
		mailPage.sendEmail(data.receive_email, "测试易大师框架", data.send_content);		
		mailPage.logout();
		logoutPage.verifyLogout();
		logoutPage.reLogin();
		loginPage.login(data.receive_email, data.receive_password);
		mailPage.verifyLogin(data.receive_email);
		mailPage.reveiceEmail(data.send_email);
		mailPage.replyEmail(data.send_content);
		mailPage.logout();
		logoutPage.verifyLogout();
	}
}
