# MasterYI UI Test Framework
易大师UI自动化测试框架

#### 项目介绍
基于PageMode模型进行测试代码编程的UI自动化测试框架    
底层由selenium-java框架支持，使用yaml文件定义元素定位和用例执行规则  

在开发过程中参考了以下优秀的自动化测试框架的某些思路或思想，在此感谢：  
- [Sweetest-小而美的自动化测试框架](https://github.com/tonglei100/sweetest)
- [Bee-有赞测试团队开发的自动化测试框架](https://segmentfault.com/a/1190000015057723)

### 开发环境
jdk >= 1.7  
Eclipse 2018

### 快速开始
 **通过以下简单的百度搜索示例来了解该框架如何使用：** 
- Clone框架代码到本地  
- 将Maven项目导入到eclipse中  
- 在根目录下的config/element目录下新建baidu.yaml，在此文件中定义相关页面元素的定位规则：
![baidu.yaml](https://images.gitee.com/uploads/images/2018/1015/180007_24b29a9a_431003.png "屏幕截图.png") 

- 在com.dcits.test包下新建包baidu.data、baidu.page、baidu.usecase，分别表示测试数据、测试页面、测试用例
![1](https://images.gitee.com/uploads/images/2018/1015/180218_95d5645e_431003.png "屏幕截图.png")

- 在page包下新建两个PageModel类，类名需要同baidu.yaml中定义的页面名称相同，同时需要继承BasePage类，如下：
![2](https://images.gitee.com/uploads/images/2018/1015/180431_2ba9cc4c_431003.png "屏幕截图.png")

- 分别在两个PageModel类中定义相关的PageElement对象，对象名称也需要同baidu.yaml定义的元素名称相同：
![3](https://images.gitee.com/uploads/images/2018/1015/180623_faf66970_431003.png "屏幕截图.png")
![4](https://images.gitee.com/uploads/images/2018/1015/180638_790245b7_431003.png "屏幕截图.png")

- 在PageModel类中定义相关业务方法，如上图
- 在usecase包下新建Baidu的测试类，新建baidu搜索的测试方法，同时在方法上加上UseCase注解
![5](https://images.gitee.com/uploads/images/2018/1015/180917_84cb0c5e_431003.png "屏幕截图.png")
- 如图所示，右键Run运行Baidu测试用例
![6](https://images.gitee.com/uploads/images/2018/1015/183344_c4b9926d_431003.png "屏幕截图.png")
- 下图为测试日志，在根目录下的report目录下会生成一个html报告
![7](https://images.gitee.com/uploads/images/2018/1015/183457_58884c3c_431003.png "屏幕截图.png")
![8](https://images.gitee.com/uploads/images/2018/1015/183522_f7f12f20_431003.png "屏幕截图.png")

### 框架使用详解

#### 页面元素定义yaml

#### PageModel模型类

#### 测试用例类

#### 测试数据生成器

#### 常用页面操作

#### 测试报告处理器

#### 使用yaml编辑测试用例

#### jar包运行

#### 测试配置文件seleniumConfig.properties

#### 自动生成PageModel类

#### 扩展改进
