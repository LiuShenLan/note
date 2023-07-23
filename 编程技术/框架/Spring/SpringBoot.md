[TOC]

[CSDN教程](https://blog.csdn.net/friggly/article/details/123888590)

[CSDN教程](https://blog.csdn.net/cuiqwei/article/details/118188540)

# 1.简介

* 特性
	* 能够快速创建基于spring的程序
	* 能够直接使用Java main方法启动内嵌的Tomcat服务器运行springboot程序，不需要部署war包
	* 提供约定的starter POM来简化Maven配置，让Maven的配置变得简单
	* 自动化配置，根据项目的Maven依赖配置，springboot自动配置spring、springmvc等
	* 提供了程序的健康检查功能
	* 基本可以完全不使用xml配合文件，采用注解配置

* 四大核心：自动配置、起步依赖、Actuator、命令行界面

## 1.1 项目结构

* `projectName/`
	* `.mvn/`
	* `src/`
		* `main/`
			* `java/`
				* `com/.../Application.java`：SpringBoot程序执行的入口，执行该程序中的main方法，启动当前SpringBoot项目
			* `resources/`
				* `static/`：存放静态资源，如图片、CSS、JavaScript等
				* `templates/`：存放Web页面的模板文件
				* `application.properties`：存放程序的各种依赖模块的配置信息，如服务端口、数据路链接配置等
		* `test/java/...`
	* `pom.xml`

* `pom.xml`内容如下所示↓：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<!-- 继承SpringBoot框架的一个父项目，所有自己开发的SpringBoot都必须的继承 -->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.1.1</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<!-- Maven坐标 -->
	<groupId>com.learn</groupId>
	<artifactId>SpringBoot</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<!-- Maven项目名称，可删除 -->
	<name>SpringBoot</name>
	<!-- Maven项目描述，可删除 -->
	<description>SpringBoot</description>

	<!-- Maven属性配置，可以在其他地方通过 ${} 方式进行引用 -->
	<properties>
		<java.version>17</java.version>
	</properties>

	<!-- 依赖 -->
	<dependencies>
		<!-- SpringBoot框架web项目起步依赖，通过该以来自动关联其它依赖 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- SpringBoot卡滚家测试起步依赖，不需要可删除 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<!-- SpringBoot提供的打包编译等插件 -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

# 2.注解

* `@SpringBootApplication`：SpringBoot项目核心注解，作用为开启Spring自动配置

* `@Value`：逐个读取`application.properties`中的配置

* `@ConfigurationProperties`：将整个文件映射成一个对象，用于自动以配置项比较多的情况

* `@Mapper`：MyB阿提升自动扫描数据持久层的映射文件及DAO接口的关系

# 3.SpringBoot + MyBatis

## 3.1 创建流程

1. 创建新的数据库
2. 创建一个新的SpringBoot的Module
3. 在`pom.xml`中添加相关jar依赖，内容如下所示↓
	```xml
	<!-- MyBatis 整合 SpringBoot 的起步依赖 -->
	<dependency>
		<groupId>org.mybatis.spring.boot</groupId>
		<artifactId>mybatis-spring-boot-starter</artifactId>
		<version>2.0.0</version>
	</dependency>

	<!-- MySQL 的驱动依赖 -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency>
	```
4. 在SpringBoot的核心配置文件`application.properties`中配置数据源
	```
	# 配置内嵌 Tomcat 端口号
	server.port = 9090

	# 配置项目上下文根
	server.servlet.context-path = /010-springboot-web-mybatis

	# 配置数据库的连接信息
	# 注意这里的驱动类有变化
	spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver
	spring.datasource.url = jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8

	spring.datasource.username = root
	spring.datasource.password = root
	```
5. 开发代码

