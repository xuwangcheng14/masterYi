package com.dcits.yi.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 框架常量
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class TestConst {
	public static final String BROWSER_CHROME = "chrome";
	public static final String BROWSER_IE = "ie";
	public static final String BROWSER_FIREFOX = "firefox";
	public static final String BROWSER_OPERA = "opera";
	public static final String BROWSER_HTMLUNIT = "htmlunit";
		
	/**
	 * 对应操作的中文释义，主要在日志记录和报告中使用，只有在此定义的方法名在执行过程中才会当成单个测试步骤进行报告记录
	 */
	public static final Map<String, String> action_keyword = new HashMap<String, String>();

	public static final String DEFAULT_FRAME_NAME = "masterYiFrame";
	
	static {
		action_keyword.put("getText", "获取元素文本");
		action_keyword.put("getAttributeValue", "获取元素属性值");
		action_keyword.put("getTagName", "获取元素标签名");
		action_keyword.put("mouseHover", "鼠标悬停");
		action_keyword.put("mouseRightClick", "鼠标右击");
		action_keyword.put("mouseDoubleClick", "鼠标双击");
		action_keyword.put("mouseDragAndDrop", "元素拖拽");
		action_keyword.put("swipe", "滑动");
		action_keyword.put("upload", "上传文件");
		action_keyword.put("mouseClick", "鼠标左击");
		action_keyword.put("switchWindow", "切换到指定窗口");

		action_keyword.put("click", "点击");
		
		action_keyword.put("sendKeys", "输入");
		action_keyword.put("clear", "清除文本");
			
		action_keyword.put("getTitle", "获取当前窗口标题");
		action_keyword.put("getCurrentUrl", "获取当前浏览器地址栏地址");
		action_keyword.put("open", "打开Url地址");
		action_keyword.put("close", "关闭当前窗口");
		action_keyword.put("refresh", "刷新页面");		
		action_keyword.put("forward", "由当前页面前进");
		action_keyword.put("back", "由当前页面后退");
		action_keyword.put("to", "跳转至");
		
		action_keyword.put("dialogDismiss", "关闭弹出框");
		action_keyword.put("dialogAccept", "确认弹出框");
		action_keyword.put("getDialogText", "获取弹出框文本并确认");
		action_keyword.put("sendKeyDialog", "发送内容到文本对话框并确认");
		
		action_keyword.put("selectByValue", "根据value值选择下拉");
		action_keyword.put("selectByOption", "根据文本值选择下拉");
		action_keyword.put("getSelectedValue", "获取当前选中内容");
		action_keyword.put("getAllOptions", "获取全部下拉内容");
	}
}
