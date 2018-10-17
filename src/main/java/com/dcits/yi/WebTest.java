package com.dcits.yi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.dcits.yi.constant.TestConst;
import com.dcits.yi.tool.TestKit;
import com.dcits.yi.ui.GlobalTestConfig;
import com.dcits.yi.ui.aop.CreateStepReportAspect;
import com.dcits.yi.ui.data.BaseDataModel;
import com.dcits.yi.ui.data.DataModelFactory;
import com.dcits.yi.ui.data.db.TestDB;
import com.dcits.yi.ui.driver.SeleniumDriver;
import com.dcits.yi.ui.element.BasePage;
import com.dcits.yi.ui.report.manage.IReportManager;
import com.dcits.yi.ui.usecase.ExecuteCaseModel;
import com.dcits.yi.ui.usecase.UseCase;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * 单次执行
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class WebTest {	
	private static final Log logger = LogFactory.get();
	
	private String testTitle = "Web自动化";
	
	private String broswerType = TestConst.BROWSER_CHROME;	
	private boolean failInterrupt = false;	
	private String tag = "default";	
	private int retryCount = 2;
	
	private String suiteYamlFileName;	
	private Class[] caseClasses;
	
	private List<ExecuteCaseModel> cases = new ArrayList<ExecuteCaseModel>();
	
	private List<IReportManager> reportManagers = new ArrayList<IReportManager>();
	
	private boolean quitFlag = true;
	
	/**
	 * 实例化测试对象
	 * @param suiteYamlFileName 设定测试套件的yaml文件名称，不带.yaml后缀，在文件中定义执行规则
	 */
	public WebTest(String suiteYamlFileName) {
		super();
		this.suiteYamlFileName = suiteYamlFileName;
	}
	
	/**
	  * 实例化测试对象
	 * @param caseClasses 指定多个需要执行的Case类，根据类中用例方法上的UseCase注解规则来执行
	 */
	public WebTest(Class ... caseClasses) {
		super();
		this.caseClasses = caseClasses;
	}

	/**
	 * 开始执行,有两种方法<br>
	 * 1、设置testsuite的yaml文件，在文件中定义执行规则<br>
	 * 2、指定执行的Case类，自动化根据类中方法上的注解规则来执行<br>
	 * 两种都配置了优先使用配置文件
	 * @throws Exception 
	 */
	public void start() throws Exception {
		if (StrUtil.isNotBlank(suiteYamlFileName)) {			
			parseSuiteYaml();
		} else {
			parseCaseClasses();
		}

		if (cases.size() == 0) {
			logger.info("可执行用例个数为0,测试退出！");
			clean();
			System.exit(0);
		}
		
		GlobalTestConfig.getTestRunningObject().setDriver(SeleniumDriver.initWebDriver(broswerType));
		initPageObject();
		
		logger.info("开始执行测试，测试用例数为{}个", cases.size());
		
		GlobalTestConfig.report.setTitle(testTitle);
		GlobalTestConfig.report.setEnv(GlobalTestConfig.ENV_INFO);
		GlobalTestConfig.report.setTestTime(DateUtil.now());
		GlobalTestConfig.report.setBrowserName(broswerType);
		GlobalTestConfig.report.setTotalCount(cases.size());
		
		//执行用例	
		for (ExecuteCaseModel ecm:cases) {
			ecm.execute();			
			if (!ecm.isSuccessFlag() && ecm.isFailInterrupt()) {
				break;
			}
		}
		
		GlobalTestConfig.report.setEndTime(DateUtil.now());
		clean();
		manageReport();
		logger.info("测试完成");
	}
	
	/**
	 * 处理测试报告
	 */
	public void manageReport() {		
		for (IReportManager r:reportManagers) {
			logger.info("执行报告处理器 [{}]", r.getClass().getName());
			try {
				r.create(GlobalTestConfig.report);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e, "[{}] 报告处理器执行出错！", r.getClass().getName());
			}
			
		}
	}
	
	/**
	 * 测试结束清理环境
	 */
	public void clean() {		
		if (GlobalTestConfig.getTestRunningObject().getDriver() != null && quitFlag) {
			//关闭webdriver
			logger.info("关闭webdriver");
			GlobalTestConfig.getTestRunningObject().getDriver().quit();		
		}
		//断开数据库连接
		if (GlobalTestConfig.dbConnections.size() > 0) {
			logger.info("断开测试数据库连接");
			for (TestDB db:GlobalTestConfig.dbConnections.values()) {
				db.closeConnection();
			}
		}
	}
	
	/**
	 * 初始化所有PageModel类
	 * @throws Exception
	 */
	private void initPageObject() throws Exception {
		logger.info("正在初始化所有PageModel...");
		for (ExecuteCaseModel model:cases) {
			for (Object caseObj:model.getTargets()) {
				for (Field f:caseObj.getClass().getFields()) {
					if (BasePage.class.isAssignableFrom(f.getType())) {
						f.set(caseObj, ProxyUtil.proxy(ReflectUtil.newInstance(f.getType()), CreateStepReportAspect.class));
						((BasePage) f.get(caseObj)).initPageObject();
					} else if(BaseDataModel.class.isAssignableFrom(f.getType())) {
						f.set(caseObj, DataModelFactory.getDataModelInstance(f.getType()));
						continue;
					} else {
						continue;
					}
				}
			}
		}
		logger.info("PageModel初始化完成");
	}
	
	/**
	 * 根据yaml文件解析执行用例信息
	 * @throws Exception
	 */
	private void parseSuiteYaml() throws Exception {
		if (StrUtil.isEmpty(suiteYamlFileName)) {
			return;
		}
		Map map = null;
		try {
			map = TestKit.parseYaml(GlobalTestConfig.ENV_INFO.getSuiteFolder() + "/" + suiteYamlFileName + ".yaml");
			
			broswerType = Convert.toStr(map.get("broswerType"), TestConst.BROWSER_CHROME);
			failInterrupt = Convert.toBool(map.get("failInterrupt"), false);
			tag = Convert.toStr(map.get("tag"), "default");
			retryCount = Convert.toInt(map.get("retryCount"), 2);
			testTitle = Convert.toStr(map.get("title"), "Web自动化测试");
			
			//报告处理器
			List<String> reportManagerClass = (List<String>) map.get("reportManager");
			if (reportManagerClass != null && reportManagerClass.size() > 0) {
				for (String s:reportManagerClass) {
					try {
						if (IReportManager.class.isAssignableFrom(Class.forName(s)) ) {
							reportManagers.add(ReflectUtil.newInstance(s));
						}						
					} catch (Exception e) {
						// TODO: handle exception
						logger.info("测试报告处理器[{}]实例化失败,请检查！", s);
					}
				}
			}
			
			List<Map> cases = (List<Map>) map.get("cases");	
			for (Map m:cases) {
				//如果设置了enabled=false则忽略
				if (!Convert.toBool(m.get("enabled"), true)) {
					continue;
				}		
				ExecuteCaseModel caseModel = new ExecuteCaseModel();
				//获取测试用例类和需要执行的方法
				Object cs = m.get("method");
				List<String> css = ConverterRegistry.getInstance().convert(List.class, cs, Arrays.asList(new String[]{cs.toString()}));
				for (String s:css) {
					String methodName = ArrayUtil.get(s.split("\\."), -1);
					String className = s.substring(0, s.lastIndexOf("."));
					
					//实例化用例类
					Object caseObj = ReflectUtil.newInstance(className);						
					caseModel.getTargets().add(caseObj);					
					//获取用例方法
					caseModel.getMethods().add(ReflectUtil.getMethod(caseObj.getClass(), methodName));
				}				
				//获取其他属性
				caseModel.setName(MapUtil.getStr(m, "name"));
				caseModel.setFailInterrupt(Convert.toBool(m.get("failInterrupt"), failInterrupt));
				caseModel.setRetryCount(Convert.toInt(m.get("retryCount"), retryCount));
				caseModel.setTag(Convert.toStr(m.get("tag"), tag));
				this.cases.add(caseModel);
			}
			logger.info("测试用例配置文件{}.yaml解析完成", this.suiteYamlFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e, "测试用例配置文件{}.yaml解析过程中出错", this.suiteYamlFileName);
			throw e;
		}
		
	}

	/**
	 * 根据用例类中的方法注解UseCase来解析执行用例情况
	 * @throws Exception
	 */
	private void parseCaseClasses() throws Exception {
		if (caseClasses.length == 0) return;
		for (Class clz:caseClasses) {
			logger.info("解析测试用例执行类：{}", clz.getName());
			Object o = ReflectUtil.newInstance(clz);
			for (Method m:clz.getMethods()) {
				//必须有UseCase注解
				if (!m.isAnnotationPresent(UseCase.class)) {
					continue;
				}	
				//必须设定了enabled=true
				UseCase uc = m.getAnnotation(UseCase.class);
				if (!uc.enabled()) {
					continue;
				}
				
				ExecuteCaseModel caseModel = new ExecuteCaseModel();
				caseModel.setName(StrUtil.isBlank(uc.name()) ? m.getName() : uc.name());
				caseModel.setFailInterrupt(uc.failInterrupt());
				caseModel.setRetryCount(uc.retryCount());
				caseModel.setTag(uc.tag());
				
				caseModel.getTargets().add(o);
				caseModel.getMethods().add(m);
				
				this.cases.add(caseModel);
			}
		}
	}
	
	public void setQuitFlag(boolean quitFlag) {
		this.quitFlag = quitFlag;
	}
	
	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}
	
	public String getTestTitle() {
		return testTitle;
	}
	
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}
	
	public int getRetryCount() {
		return retryCount;
	}

	public boolean isFailInterrupt() {
		return failInterrupt;
	}

	public void setFailInterrupt(boolean failInterrupt) {
		this.failInterrupt = failInterrupt;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setBroswerType(String broswerType) {
		this.broswerType = broswerType;
	}
	
	public String getBroswerType() {
		return broswerType;
	}

	public List<IReportManager> getReportManagers() {
		return reportManagers;
	}
	
	/**
	 * 设置报告数据处理器，传入多个将会安装顺序执行
	 * @param reportManagers
	 */
	public void setReportManagers(IReportManager... reportManagers) {
		if (reportManagers != null && reportManagers.length > 0) {
			this.reportManagers.addAll(Arrays.asList(reportManagers));
		}
	}
	
	
}
