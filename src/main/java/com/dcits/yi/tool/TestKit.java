package com.dcits.yi.tool;

import java.io.File;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;

/**
 * 工具类
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class TestKit {
	private static final Log logger = LogFactory.get();
	
	/**
	 * 执行类根路径
	 */
	private static String rootClassPath;
	/**
	 * 项目根路径
	 */
	private static String projectRootPath;
	
	/**
	 *  获取项目根路径
	 * @return
	 */
	public static String getProjectRootPath() {
		if (projectRootPath == null) {
			projectRootPath = System.getProperty("user.dir");
		}
		return projectRootPath;
	}
	
	/**
	 * 获取class根路径
	 * @return
	 */
	public static String getRootClassPath() {
		if (rootClassPath == null) {
			try {
				// String path = PathKit.class.getClassLoader().getResource("").toURI().getPath();
				String path = getClassLoader().getResource("").toURI().getPath();
				rootClassPath = new File(path).getAbsolutePath();
			}
			catch (Exception e) {
				// String path = PathKit.class.getClassLoader().getResource("").getPath();
				String path = getClassLoader().getResource("").getPath();
				rootClassPath = new File(path).getAbsolutePath();
			}
		}
		return rootClassPath;
	}
	
	private static ClassLoader getClassLoader() {
		ClassLoader ret = Thread.currentThread().getContextClassLoader();
		return ret != null ? ret : TestKit.class.getClassLoader();
	}
	
	/**
	 * 解析yaml文件
	 * @param filePath
	 * @return Map
	 * @throws Exception 
	 */
	public static Map parseYaml(String filePath) throws Exception {
		try {
			FileReader fileReader = new FileReader(filePath);
			String yamlstr = fileReader.readString();
			Yaml yaml = new Yaml();
			return (Map) yaml.load(yamlstr);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e, "解析yaml文件 {} 失败!" , filePath);
			throw new Exception("解析yaml文件 {} 失败!");
		}	
	}
	
	/**
	 * Props.getStr()方法使用时排除所有null和空字符串情况
	 * @param p Props对象
	 * @param key key值
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getStrIsNotEmpty(Props p, String key, String defaultValue) {
		String v = p.getStr(key);
		return StrUtil.isBlank(v) ? defaultValue : v;
	}
}
