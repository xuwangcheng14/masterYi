package com.dcits.yi;

import com.dcits.yi.ui.EnvSettingInfo;

/**
 * 执行入口：已jar包方式运行
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class StartTest {
	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("参数不正确!");
			System.exit(1);
		}
		EnvSettingInfo.DEV_MODE = false;
		WebTest test = new WebTest(args[0]);		
		test.start();
	}
}
