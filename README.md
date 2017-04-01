# 阅书网系统

后台管理系统(spring + springmvc + mybatis)

> 简单介绍

该项目为 SSM 核心库

已集成组件：

1、mybatis-plus （mybatis 自动 crud 功能）
2、kisso （单点授权、权限管理、验证码、api 服务、oauth2认证）
3、mail（收发邮件）
4、veloctiy （继承模板支持、环境控制）
5、slf4j-api（日志 logback 管理）
7、fastjson （json 处理）
8、quartz (定时任务)
9、cos （优化、支持头文件字节检查、图片剪切、视频处理、文档处理）
10、Sigar （系统信息收集）
11、pingyin4j（中文转拼音库 ）
12、oauth2.0  weibo  github 登录支持
13、表单重复提交aop 辅助工具类
14、日志组件 logback 动态环境配置支持
15、 等..

项目准备工作

JDK 和 maven 
http://blog.csdn.net/z1049186181/article/details/42607945

根据这篇文章搭建环境

安装分布式代码管理工具 git

http://www.cnblogs.com/vitah/p/3612473.html

根据文章配置github秘钥，并添加到github里面

安装web服务nginx

模块划分
  商品管理
  订单管理
  会员管理
  物流管理
  地址管理
