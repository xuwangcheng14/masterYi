package com.dcits.yi.ui.element.basics;

import java.util.Map;

import org.openqa.selenium.support.ui.Select;

import com.dcits.yi.ui.element.PageElement;

/**
 * 元素对象模型基类，包含一些基础方法
 * @author xuwangcheng
 * @version 20181011
 *
 */
public interface IBaseElement {
	/**
	 * 获取文本内容
	 * @return
	 */
	String getText();
	/**
	 * 获取元素属性值
	 * @param attributeName
	 * @return
	 */
	String getAttributeValue(String attributeName);
	
	/**
	 * 获取元素的标签名称
	 * @return
	 */
	String getTagName();
	
	/**
	 * 鼠标悬停
	 */
	void mouseHover();
	/**
	 * 鼠标右击
	 */
	void mouseRightClick();
	/**
	 * 鼠标双击
	 */
	void mouseDoubleClick();
	/**
	 * 鼠标点击
	 */
	void mouseClick();
	/**
	 * 点击
	 */
	void click();
	/**
	 * 元素是否存在
	 * @return
	 */
	boolean isExist();
	/**
	 * 拖拽一个元素
	 * @param begin
	 * @param end
	 */
	void mouseDragAndDrop(PageElement end);
	
	/**
	 * 滑动
	 * @param x x轴距离
	 * @param y y轴距离
	 */
	void swipe(int x, int y);
	
	/**
	 * 
	 * 	上传文件<br>
	 * 	1、查找对应的插件位置，在input的标签对中输入文件所在位置使用sendkeys<br>
	 * 	2、使用autoit等工具来实现
	 * @param filePath
	 */
	void upload(String filePath);
	
	/**
	   *    发送内容给输入框
	 * @param str  字符串、按键、文件路径等
	 */
	void sendKeys(String str);
	/**
	 * 发送内容给输入框
	 * @param str
	 * @param clearFlag 传入true则再发送之前清除内容
	 */
	void sendKeys(String str, boolean clearFlag);
	/**
	 * 清除文本框内容
	 */
	void clear();
	
	/**
	 * 获取Select对象
	 * @return
	 */
	Select getSelect();
	/**
	 * 根据下拉选项的value值来选择
	 * @param value
	 */
	void selectByValue(String value);
	/**
	 * 根据下拉选项的文本text来选择
	 * @param option
	 */
	void selectByOption(String option);
	/**
	 * 获取当前选中的值
	 * @return
	 */
	String getSelectedValue();
	/**
	 * 获取当前下拉框所有选项
	 * @return Map&lt;K, V&gt; K为value V为text
	 */
	Map<String, String> getAllOptions();
}
