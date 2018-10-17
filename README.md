# MasterYI UI Test Framework
易大师UI自动化测试框架

 **_当前版本：0.0.1beta_** 

## 项目介绍
 >  **目前文档不够齐全，作者也是刚刚使用，希望对此框架后续开发有兴趣的朋友大牛们进QQ群 468324085 一起交流学习，加群验证： 易大师。**   


基于PageMode模型进行测试代码编程的UI自动化测试框架    
底层由selenium-java框架支持，使用yaml文件定义元素定位和用例执行规则  

在开发过程中参考了以下优秀的自动化测试框架的某些思路或思想，在此感谢：  
- [Sweetest-小而美的自动化测试框架](https://github.com/tonglei100/sweetest)
- [Bee-有赞测试团队开发的自动化测试框架](https://segmentfault.com/a/1190000015057723) 
- 同时感谢功能强大的java工具包  [Hutool](https://gitee.com/xuwangcheng/hutool)

框架中使用的大都数工具方法都由Hutool工具包封装，建议有兴趣的小伙伴自行阅读Wiki，在框架扩展和脚本编写中可以省去很多精力和时间：
http://hutool.mydoc.io/


## 开发环境

 _jdk >= 1.7  
Eclipse 2018_ 

你需要了解以下知识：
-  [java编程基础](https://www.java.com/zh_CN/)   
- [自动化测试框架selenium](http://www.51testing.com/zhuanti/selenium.html) 
- [yaml](https://www.jianshu.com/p/97222440cd08)

## 快速开始
 **通过以下简单的百度搜索示例来了解该框架如何使用：** 
1.  Clone框架代码到本地  

2.  导入到eclipse中为Maven项目 

3. 在项目根目录下的config/element目录下新建baidu.yaml，在此文件中定义相关页面元素的定位规则： 

![baidu.yaml](https://images.gitee.com/uploads/images/2018/1015/180007_24b29a9a_431003.png "屏幕截图.png") 

4. 在com.dcits.test包下新建包baidu.data、baidu.page、baidu.usecase，分别表示测试数据、测试页面、测试用例

![1](https://images.gitee.com/uploads/images/2018/1015/180218_95d5645e_431003.png "屏幕截图.png")

5. 在page包下新建两个PageModel类，类名需要同baidu.yaml中定义的页面名称相同，同时需要继承BasePage类，如下：

![2](https://images.gitee.com/uploads/images/2018/1015/180431_2ba9cc4c_431003.png "屏幕截图.png")

6. 分别在两个PageModel类中定义相关的PageElement对象，对象名称也需要同baidu.yaml定义的元素名称相同：

![3](https://images.gitee.com/uploads/images/2018/1015/180623_faf66970_431003.png "屏幕截图.png")
![4](https://images.gitee.com/uploads/images/2018/1015/180638_790245b7_431003.png "屏幕截图.png")

7. 在PageModel类中定义相关业务方法，如上图

8. 在usecase包下新建Baidu的测试类，新建baidu搜索的测试方法，同时在方法上加上UseCase注解

![5](https://images.gitee.com/uploads/images/2018/1015/180917_84cb0c5e_431003.png "屏幕截图.png")

9. 如图所示，右键Run运行Baidu测试用例

![6](https://images.gitee.com/uploads/images/2018/1015/183344_c4b9926d_431003.png "屏幕截图.png")

10. 下图为测试日志，在根目录下的report目录下会生成一个html报告

![7](https://images.gitee.com/uploads/images/2018/1015/183457_58884c3c_431003.png "屏幕截图.png")
![8](https://images.gitee.com/uploads/images/2018/1015/183522_f7f12f20_431003.png "屏幕截图.png")

## 框架使用详解

### 元素yaml文件

在项目根目录下config/elemment目录下保存元素定义的yaml文件，一般相同或者相似模块的页面中元素定义在同一个文件中，在该目录下，你也可以新建不同的文件夹以对不同系统、模块、功能的页面进行合理分类，如图：  

![9](https://images.gitee.com/uploads/images/2018/1016/110952_fc4f236a_431003.png "屏幕截图.png")

#### 元素定位
```
# 网易邮箱登录页面
LoginPage: 
  url: https://mail.163.com/
  
  loginframe: id x-URS-iframe
  
  用户名输入框: xpath //*[@name="email"] loginframe     
  密码输入框: xpath //*[@name="password"] loginframe
  登录按钮: id dologin loginframe
  
# 网易邮箱页面
MailPage: 
  writeframe: xpath //div[@class="APP-editor-edtr"]/iframe

  用户标签: id spnUid
  写信按钮: xpath //div[@id="dvNavTop"]/ul/li[2]/span[2]  
  收信人: className bz0
  收信人地址: className nui-editableAddr-ipt
  发信按钮: xpath //span[text()="发送"][1]
  
  发送成功确认按钮: xpath //*[@class="nui-msgbox-ft-btns"]/div/span
  收信按钮: xpath //div[@id="dvNavTop"]/ul/li[1]
  邮件搜索框: classname nui-ipt-input[1]
  收信列表链接: xpath //span[contains(@id,"SubjectSpan")][1]
  回复按钮: xpath '//span[text()="回 复"][1]'

  邮件内容输入框: xpath //html/body writeframe
  
  登出按钮: xpath /html/body/header/div/ul/li[last()]/a
  
# 网易邮箱成功退出的页面
MailLogoutPage: 
  重新登录按钮: id js-relogin
```

如上所示，其中 LoginPage、MailPage等以Page结尾表示单个页面，再其下定义包括url、frame/iframe/frameset元素、普通元素的定位规则信息，定位规则的书写格式为：

> 元素名称: 定位器类型 参数[序号] frame名称1|frame名称2|frame名称2
 
**元素名称：** 可以使用中文名称，最好能够清晰明了的表明该元素的功能和类型，如loginNameInput、loginBtn等；  

**定位器类型：**  包括常用的id、name、tagname、xpath、classname、linktext，还包括partiallinktext(链接文本局部匹配)、cssselector(CSS选择器)，这些都是对Selenium的By对象的关键字封装；
  
**参数：** 需要注意的时，如果参数中包含空格，请务必使用单引号将该部分包裹起来；  

**序号：**  对于非xpath类型的定位规则，你可以在参数后添加  _[序号值]_  来指定元素在最终获取的列表的下标，起始为1，可省略，默认为0；  

**frame名称：** 在最上面定义该页面上的frame元素定位规则，在普通元素定义规则最后通过引入定义的frame元素名称来表明该元素位于哪个frame下，涉及到多层frame嵌套的请按顺序使用竖线"|"来分隔。

### PageModel类
一个PageModel类对应一个页面，PageModel类的成员变量对应页面中的成员变量，而类中方法则对应页面中的一些业务操作，比如登录、菜单搜索、公用的表单填写等：

```
public class LoginPage extends BasePage {
	
	public PageElement 用户名输入框;	
	public PageElement 密码输入框;	
	public PageElement 登录按钮;	
	
	
	public void login(String username, String passwd) {
		用户名输入框.sendKeys(username, true);
		密码输入框.sendKeys(passwd, true);
		//等待验证码，手工输入
		//sleep(20);
		登录按钮.click();
		sleep(1);
		screenshot();
	}
}
```

- PageModel需要继承BasePage类，在BasePage类中封装了一些常用的页面操作方法，例如 打开浏览器、截图、断言、刷新、前进等；  
- PageModel类名称和成员变量名称需要同元素yaml文件中定义的一致；  
- PageModel中的业务操作方法建议使用外部传入的数据，最大限度保证Page和Data的分离；  
- PageElement为各种不同元素的模型对象，后期你也可以自行扩展不同的页面元素对象类；  
- 不建议PageModel类以中文名称命名，至于元素名称和方法名称请自行斟酌。  

#### 数据模型（测试数据生成）
为了保证测试数据同业务脚本代码的分离，你可以自行定义不同的数据模型类：

```
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
```

- 自定义的数据模型类需要继承BaseDataModel类，同时实现其中initData方法；  
- 在initData方法中定义数据的生成方式，在实际使用中，可自行调用initData方法来生成新的一组数据；
- 测试数据相关的配置文件请放置于项目根目录下的config/data目录下；  
- 在BaseDataModel类中提供了以下众多的数据生成方法或者工具方法，你也可以执行拓展更多的数据生成方法，比如 **从外部接口获取数据、调用本地程序或者脚本生成** 等：
```
       /**
	 * 执行sql并获取,返回多条信息只会取第一条
	 * @param dbName seleniumConfig.properties配置文件中定义的数据库名称
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public String generateBySql(String dbName, String sql)

       /**
	 * 根据正则生成指定的值
	 * @param regexStr
	 * @return
	 */
	public String generateByRegex(String regexStr)

       /**
	 * 获取随机数
	 * @param max 最大值         
	 * @param min 最小值
	 * @return
	 */
	public int generateRandomNum(int max, int min)
       /**
	 * 生成随机字符串
	 * 
	 * @param mode 模式 0-只包含大写字母   1-只包含小写字母   2-包含大小写字母   3-同时包含大小写字母和数字
	 * @param length 字符串长度
	 * @return
	 */
	public String generateRandomString(int mode, int length) 

       /**
	 * 从txt配置文件中读取数据，需要自行处理
	 * @param path
	 * @return
	 */
	public String readFromTxt(String path)

       /**
	 * 从csv中读取内容
	 * @param path
	 * @return CsvRow对象表示一行数据，通过getRawList()方法获取行数据
	 */
	public List<CsvRow> readFromCsv(String path)

       /**
	 * 从excel读取数据
	 * @param path
	 * @param sheetNum sheet序号，从0开始
	 * @return 行的集合，一行使用List表示
	 */
	public List<List<Object>> readFromExcel(String path, int sheetNum)
       /**
	 * 从excel读取数据,默认第一个sheet页
	 * @param path
	 * @return 行的集合，一行使用List表示
	 */
	public List<List<Object>> readFromExcel(String path)    
```

### 常用元素、页面操作

- 框架中封装了一些常用的元素、页面操作，如果没有你想要用到的，你可以在IBasePage和IBaseElement中自行添加或者在PageModel类中通过 _ getDriver()_  方法获取当前的WebDriver对象再进行操作;  

- PageElement中实现了iframe/frame/frameset的自动切换，在操作不同的元素对象时，不需要手动切换frame层（同样适用于多层嵌套的frame，需要在元素定义的yaml文件中定义好规则）;  

- PageModel类中封装了弹出框操作、窗口切换、浏览器常用操作等方法。

#### 元素操作
```
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
	 * 根据下拉框选项的value值来选择
	 * @param value
	 */
	void selectByValue(String value);
	/**
	 * 根据下拉框选项的文本text来选择
	 * @param option
	 */
	void selectByOption(String option);
	/**
	 * 获取下拉框当前选中的值
	 * @return
	 */
	String getSelectedValue();
	/**
	 * 获取当前下拉框所有选项
	 * @return Map<K, V> K为value V为text
	 */
	Map<String, String> getAllOptions();
```

#### 页面操作
```
       /**
	 * 打开当前页面的url地址
	 */
	void open();
	/**
	 * 打开指定的url地址
	 * @param url
	 */
	void open(String url);
	/**
	 * 获取当前窗口标题
	 * @return
	 */
	String getTitle();
	/**
	 * 获取当前浏览器地址栏地址
	 * @return
	 */
	String getCurrentUrl();
	/**
	 * 关闭该窗口
	 */
	void close();
	/**
	 * 刷新当前页面
	 */
	void refresh();
	/**
	 * 前进
	 */
	void forward();
	/**
	 * 后退
	 */
	void back();
	/**
	 * 跳转到指定的url地址
	 * @param url
	 */
	void to(String url);
	/**
	 * 	X掉关闭或者取消掉对话框
	 */
	void dialogDismiss();
	/**
	 * 	点击对话框的确认按钮
	 */
	void dialogAccept();	
	/**
	 * 	获取对话框中文本并点击确认
	 * @return
	 */
	String getDialogText();
	/**
	 * 	发送内容给prompt(文本对话框)
	 * @param keys
	 */
	void sendKeyDialog(String keys);
	/**
	   *   切换窗口
	 * @param index 窗口下标，从左到右，起始为0
	 */
	void switchWindow(int index);
```

### 自动生成PageModel类

### 测试报告处理器

### 测试配置文件seleniumConfig.properties

### 测试执行

### 使用yaml定义测试用例

### jar包运行

### 扩展改进
