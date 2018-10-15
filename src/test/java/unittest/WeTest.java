package unittest;

import org.junit.Test;

import cn.hutool.core.util.ReUtil;

public class WeTest {
	
	@Test
	public void test() {
		String content = "xpath //div[@id=\"dvNavTop\"]/ul/li[1]";
		System.out.println(ReUtil.contains("'(.*?)'", content));
	/*	String s = ReUtil.get("'(.*?)'", "xpath '//span[text()=\"回 复\"][1]'", 1);
		System.out.println(s);
		content = content.replace(s, "default");
		System.out.println(content);*/
	}
}
