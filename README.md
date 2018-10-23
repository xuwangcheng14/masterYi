# MasterYI UI Test Framework
易大师UI自动化测试框架

 **_当前版本：0.1.0beta_** 

码云地址：https://gitee.com/xuwangcheng/MasterYI-UI-Test-Framework  
更新日志: [易大师UI自动化测试框架-Wiki](https://gitee.com/xuwangcheng/MasterYI-UI-Test-Framework/wikis/%E6%9B%B4%E6%96%B0%E6%97%A5%E5%BF%97?sort_id=879222)  

框架详细使用说明请参考：https://gitee.com/xuwangcheng/MasterYI-UI-Test-Framework/wikis/pages  

## 项目介绍
 >  **希望对此框架后续开发有兴趣的朋友大牛们进QQ群 468324085 一起交流学习，加群验证： 易大师。**   


基于PageMode模型进行测试代码编程的UI自动化测试框架，元素定位、业务逻辑、测试数据分离。    
底层由selenium-java框架支持，使用yaml文件定义元素定位和用例执行规则。 

在开发过程中参考了以下优秀的自动化测试框架的某些思路或思想，在此感谢：  
- [Sweetest-小而美的自动化测试框架](https://github.com/tonglei100/sweetest)
- [Bee-有赞测试团队开发的自动化测试框架](https://segmentfault.com/a/1190000015057723)  

框架使用或者借鉴了以下开源工具：
- [功能强大的java工具包Hutool](https://gitee.com/xuwangcheng/hutool)
- [自动化测试报告ZTest](https://github.com/zhangfei19841004/ztest)



## 环境要求
系统: windows  
jdk >= 1.7    
浏览器： chrome >=68  
ide: Eclipse 

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
![8](https://images.gitee.com/uploads/images/2018/1022/101939_b5be5809_431003.png "屏幕截图.png")


