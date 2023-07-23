[TOC]

[CSDN教程](https://blog.csdn.net/weixin_43477531/article/details/125164271)

# 1.项目结构

* projectName
	* .settings/
	* src/：源码目录
		* main/：主程序目录
			* java/：主程序的Java源文件目录
			* resources/：主程序的资源文件目录
		* test/：测试程序目录
			* java/：测试程序的Java源文件目录
			* resources/：测试程序的资源文件目录
	* target/：编译结果
	* .classpath
	* .project
	* pon.xml

# 2.构建过程

1. clean：删除上一次构建的结果，为下一次构建做好准备
2. compile：Java源程序编译成.class字节码文件
3. test：运行提前准备好的测试程序，执行`src/text/java`下的`junit`测试用例
4. site：每次测试后用标准格式记录和显示测试结果
5. package：Java工程-jar包、Web工程-war包
6. install：把一个Maven工程经过打包操作生成的jar包或war包存入Maven仓库
7. deploy：
	* 部署jar包：把一个jar包部署到Nexus私服服务器上，让其他开发人员共享
	* 部署war包：借助相关Maven插件(如cargo)，将war包部署到Tomcat服务器上

# 3.使用

## 3.1 定位jar包

* `groupId`：公司或组织的id，即公司或组织域名的倒序，通常也会加上项目名称
* `artifactId`：一个项目或者是项目中一个模块的id，即模块的名称，将来作为Maven工程的工程名
* `version`：版本号

```xml
<groupId>javax.servlet</groupId>
<artifactId>servlet-api</artifactId>
<version>2.5</version>

上述坐标对应的jar包在Maven本地仓库中的位置为：
Maven本地仓库根目录\javax\servlet\servlet-api\2.5\servlet-api-2.5.jar
```

## 3.2 `pom.xml`

* POM：Project Object Model，项目对象模型，与之类似的是DOM：Document Object Model，文档对象模型

* `pom.xml`配置文件是Maven工程的核心配置文件

```xml
<!-- 当前Maven工程的坐标 -->
<groupId>com.example</groupId>
<artifactId>demo</artifactId>
<version>0.0.1-SNAPSHOT</version>

<name>demo</name>
<description>Demo project for Spring Boot</description>

<!-- 当前Maven工程的打包方式，可选值有下面三种： -->
<!-- jar：表示这个工程是一个Java工程 -->
<!-- war：表示这个工程是一个Web工程 -->
<!-- pom：表示这个工程是“管理其他工程”的工程 -->
<packaging>jar</packaging>

<properties>
	<!-- 工程构建过程中读取源码时使用的字符集 -->
	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

<!-- 当前工程所依赖的jar包 -->
<dependencies>
	<!-- 使用dependency配置一个具体的依赖 -->
	<dependency>
		<!-- 在dependency标签内使用具体的坐标依赖我们需要的一个jar包 -->
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<version>4.12</version>
		<!-- scope标签配置依赖的范围 -->
		<scope>test</scope>
	</dependency>
</dependencies>
```

## 3.3 依赖

* Maven依赖范围如下表所示↓：

|依赖范围|说明|编译|测试|运行时|是否会被打入jar包|
|:-:|:-:|:-:|:-:|:-:|:-:|
|compile|常用，表示编译范围，A在编译时依赖B，是默认依赖范围|√|√|√|√|
|provided|常用，只有当jdk或者一个容器已提供该依赖之后才使用|√|√|×|×|
|runtime||×|√|√|√|
|test|常用|×|√|×|×|
|system|范围依赖于provide类似，但是必须显式的提供一个本地系统中jar文件的路径，一般不使用|√|√|×|√|

* 依赖传递：A依赖B，B依赖C，在A没有配置对C的依赖的情况下，A中是否能够直接使用C。在以上前提下，C是否能够传递到A，取决于B依赖C时使用的依赖范围
	* B依赖C时使用compile范围，可以传递
	* B依赖C时使用test或provided范围，不能传递。此时必须在需要的地方明确配置依赖
	* 取决于是否会被打入jar包？

* 依赖排除：在依赖传递中，为了避免jar包之间的冲突，需要配置依赖排除以阻止某些jar包的传递
	* 一般通过使用`excludes`标签配置依赖的排除
	```xml
	<dependency>
		<groupId>net.javatv.maven</groupId>
		<artifactId>auth</artifactId>
		<version>1.0.0</version>
		<!-- scope标签配置依赖的范围 -->
		<scope>compile</scope>

		<!-- 使用excludes标签配置依赖的排除	-->
		<exclusions>
			<!-- 在exclude标签中配置一个具体的排除 -->
			<exclusion>
				<!-- 指定要排除的依赖的坐标（不需要写version） -->
				<groupId>commons-logging</groupId>
				<artifactId>commons-logging</artifactId>
			</exclusion>
		</exclusions>
	</dependency>
	```

## 3.4 继承

* 概念：A工程继承B工程，即子工程的`pom.xml`中的配置继承了父工程中`pom.xml`的配置

* 作用：在父工程中统一管理项目中的依赖信息的版本

* 父工程的打包方式需要为`pom`，只有打包方式为`pom`的Maven工程能够管理其他Maven工程。其中不写业务代码，是专门管理其他Maven工程的工程，所以可以将生成的`src`目录删除

* 在父工程中配置依赖的统一管理：使用`dependencyManagement`标签配置对依赖的统一管理，如下所示↓：
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>

		<groupId>net.javatv.maven</groupId>
		<artifactId>maven-demo-parent</artifactId>
		<version>1.0-SNAPSHOT</version>
		<packaging>pom</packaging>

		<!-- 子工程 -->
		<modules>
			<module>demo-module</module>
		</modules>

		<dependencyManagement>
			<dependencies>
				<dependency>
					<groupId>org.springframework</groupId>
					<artifactId>spring-beans</artifactId>
					<version>5.3.19</version>
				</dependency>

				<dependency>
					<groupId>org.springframework</groupId>
					<artifactId>spring-core</artifactId>
					<version>5.3.19</version>
				</dependency>

				<dependency>
					<groupId>org.springframework</groupId>
					<artifactId>spring-context</artifactId>
					<version>5.3.19</version>
				</dependency>

				<dependency>
					<groupId>org.springframework</groupId>
					<artifactId>spring-aop</artifactId>
					<version>5.3.19</version>
				</dependency>
			</dependencies>
		</dependencyManagement>

	</project>
	```

* 子工程中引用被父工程管理的依赖：子工程引用父工程中的依赖信息时，可以把版本号去掉，表示子工程中这个依赖的版本由父工程的`dependencyManagement`决定，如下所示↓：
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

		<!-- 使用parent标签指定当前工程的父工程 -->
		<parent>
			<groupId>net.javatv.maven</groupId>
			<artifactId>maven-demo-parent</artifactId>
			<version>1.0-SNAPSHOT</version>
		</parent>
		<modelVersion>4.0.0</modelVersion>

		<!-- 子工程的坐标 -->
		<!-- 如果子工程坐标中的groupId和version与父工程一致，那么可以省略 -->
		<artifactId>demo-module</artifactId>

		<dependencies>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-beans</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-core</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-context</artifactId>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-aop</artifactId>
			</dependency>
		</dependencies>

	</project>
	```

* 父工程中声明自定义属性
	* 为了方便升级框架，可以将jar包的版本信息统一提取出来，统一声明版本号，如下所示↓：
	```xml
	<!-- 通过自定义属性，统一指定Spring的版本 -->
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<!-- 自定义标签，维护Spring版本数据 -->
		<spring.version>5.3.19</spring.version>
	</properties>
	```
	* 在需要的地方使用`${}`的形式来引用自定义的属性名，如下所示↓：
	```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>

		<groupId>net.javatv.maven</groupId>
		<artifactId>maven-demo-parent</artifactId>
		<packaging>pom</packaging>
		<version>1.0-SNAPSHOT</version>

		<modules>
			<module>demo-module</module>
		</modules>


		<!-- 通过自定义属性，统一指定Spring的版本 -->
		<properties>
			<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

			<!-- 自定义标签，维护Spring版本数据 -->
			<spring.version>5.3.19</spring.version>
		</properties>

		<dependencyManagement>
			<dependencies>
				<dependency>
					<groupId>org.springframework</groupId>
					<artifactId>spring-beans</artifactId>
					<version>${spring.version}</version>
				</dependency>
			</dependencies>
		</dependencyManagement>

	</project>
	```

## 3.5 聚合

* 概念：使用一个总工程将各个模块工程汇集起来，作为一个整体对应完整的项目，即`module`标签

* 配置：在总工程中配置`modules`即可，如下所示↓:
	```xml
	<modules>
		<module>demo-module</module>
	</modules>
	```

# 4.`build`

## 4.1 `build`标签组成

* `build`标签的子标签大致包含三个主体部分：定义约定的目录结构、备用插件管理、生命周期插件
	* 定义约定的目录结构：代码如下所示↓：
	```xml
	<sourceDirectory>D:\product\maven-demo-parent\demo-module\src\main\java</sourceDirectory>
	<scriptSourceDirectory>D:\product\maven-demo-parent\demo-module\src\main\scripts</scriptSourceDirectory>
	<testSourceDirectory>D:\product\maven-demo-parent\demo-module\src\test\java</testSourceDirectory>
	<outputDirectory>D:\product\maven-demo-parent\demo-module\target\classes</outputDirectory>
	<testOutputDirectory>D:\product\maven-demo-parent\demo-module\target\test-classes</testOutputDirectory>
	<resources>
		<resource>
			<directory>D:\product\maven-demo-parent\demo-module\src\main\resources</directory>
		</resource>
	</resources>
	<testResources>
		<testResource>
			<directory>D:\product\maven-demo-parent\demo-module\src\test\resources</directory>
		</testResource>
	</testResources>
	<directory>D:\product\maven-demo-parent\demo-module\target</directory>
	<finalName>demo-module-1.0-SNAPSHOT</finalName>
	```

	|目录名|作用|
	|:-:|:-:|
	|sourceDirectory|主体源程序存放目录|
	|scriptSourceDirectory|脚本源程序存放目录|
	|testSourceDirectory|测试源程序存放目录|
	|outputDirectory|主体源程序编译结果输出目录|
	|testOutputDirectory|测试源程序编译结果输出目录|
	|resources|主体资源文件存放目录|
	|testResources|测试资源文件存放目录|
	|directory|构建结果输出目录|

	* 备用插件管理：`pluginManagement`标签存放着几个极少用到的插件：maven-antrun-plugin、maven-assembly-plugin、maven-dependency-plugin、maven-release-plugin。通过`pluginManagement`标签管理起来的插件类似`dependencyManagement`一样，子工程使用时可以省略版本号，起到在父工程中统一管理版本的效果。
	* 声明周期插件：`plugins`标签存放的是默认生命周期中实际会用到的插件，标签结构如下所示↓：
	```xml
	<plugin>
		<!-- 坐标，作为Maven的自带插件省略了groupId -->
		<artifactId>maven-compiler-plugin</artifactId>
		<version>3.1</version>

		<!-- 执行部分 -->
		<executions>
			<execution>
				<!-- 指定唯一标识 -->
				<id>default-compile</id>
				<!-- 关联的生命周期阶段 -->
				<phase>compile</phase>
				<!-- 关联指定生命周期的目标 -->
				<goals>
					<!-- 一个生命周期环节可以对应当前插件的多个目标 -->
					<goal>compile</goal>
				</goals>
			</execution>
			<execution>
				<id>default-testCompile</id>
				<phase>test-compile</phase>
				<goals>
					<goal>testCompile</goal>
				</goals>
			</execution>
		</executions>
	</plugin>
	```

## 4.2 指定JDK版本

通过负责编译操作的maven-compiler-plugin插件指定JDK版本，代码如下所示↓：

```xml
<!-- 定制build行为 -->
<build>
	<!-- 插件 -->
	<plugins>
		<!-- 一个具体的插件 -->
		<plugin>
			<!-- 插件坐标。此处引用的maven-compiler-plugin插件是Maven自带的插件。 -->
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.1</version>

			<!-- 配置maven-compiler-plugin插件 -->
			<configuration>
				<!-- 具体配置信息会因为插件不同、需求不同而有所差异 -->
				<source>1.8</source>
				<target>1.8</target>
				<encoding>UTF-8</encoding>
			</configuration>
		</plugin>
	</plugins>
</build>
```

* 在`settings.xml`中配置：仅在本地生效，如果脱离当前`settings.xml`能够覆盖的范围，则无法生效
* 在当前Maven工程`pom.xml`中配置：无论在哪个环境执行编译等build操作都有效

# 5.依赖配置补充`import`

* Maven是单继承，在不同POM中，可以使用`import`依赖范围

* 引入SpringBoot、SpringCloud依赖如下所示↓：

```xml
<dependencyManagement>
	<dependencies>
		<!-- SpringCloud 微服务 -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-dependencies</artifactId>
			<version>${spring-cloud.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>

		<!-- SpringCloud Alibaba 微服务 -->
		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-alibaba-dependencies</artifactId>
			<version>${spring-cloud-alibaba.version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```

* `import`依赖范围使用要求：打包类型必须是`pom`、必须放在`dependencyManagement`中

# 6.`profile`

* Maven使用`profile`机制来管理不同环境下的配置信息，`project`标签下除了`modelVersion`和坐标标签之外，其他标签都可以配置到`profile`中

## 6.1 `profile`配置

* 配置文件：
	* `setting.xml`：全局生效
	* `pom.xml`：当前POM生效

* 具体标签语法要求
	* `profiles`、`profile`：`profile`标签覆盖了`pom.xml`中的默认配置，所以`profiles`标签通常是`pom.xml`中的最后一个标签
	* `id`：每个`profile`都必须有一个`id`标签，指定该`profile`的唯一标识。这个`id`标签的值会在命令行调用`profile`时被用到。这个命令格式是`-D<profile id>`
	* 其他允许出现的标签：
		* build
			* defaultGoal
			* finalName
			* resources
			* testResources
			* plugins
		* reporting
		* modules
		* dependencies
		* dependencyManagement
		* repositories
		* pluginRepositories
		* properties

## 6.2 激活`profile`

* 默认配置默认被激活：POM中没有在`profile`标签里的就是默认的`profile`，默认被激活
* 基于环境信息激活：环境信息包含JDK版本、操作系统参数、文件、属性等各个方面。一个`profile`一旦被激活，那么它定义的所有配置都会覆盖原来POM中对应层次的元素。标签结构如下所示↓：
	```xml
	<profile>
		<id>dev</id>
		<activation>
			<!-- 配置是否默认激活 -->
			<activeByDefault>false</activeByDefault>
			<jdk>1.5</jdk>
			<os>
				<name>Windows XP</name>
				<family>Windows</family>
				<arch>x86</arch>
				<version>5.1.2600</version>
			</os>
			<property>
				<name>mavenVersion</name>
				<value>2.0.5</value>
			</property>
			<file>
				<exists>file2.properties</exists>
				<missing>file1.properties</missing>
			</file>
		</activation>
	</profile>
	```

## 6.3 多环境配置

配置如下所示↓：

```xml
<build>
	<!-- profile对资源的操作 -->
	<resources>
		<resource>
			<directory>src/main/resources</directory>
			<!-- 先排除所有环境相关的配置文件 -->
			<excludes>
				<exclude>application*.yml</exclude>
			</excludes>
		</resource>
		<resource>
			<directory>src/main/resources</directory>
			<!-- 是否替换 @xx@ 表示的maven properties属性值 -->
			<!--通过开启 filtering，maven 会将文件中的 @xx@ 替换 profile 中定义的 xx 变量/属性-->
			<filtering>true</filtering>
			<includes>
				<include>application.yml</include>
				<include>application-${profileActive}.yml</include>
			</includes>
		</resource>
	</resources>
</build>


<!--多环境文件配置-->
<profiles>
	<!--开发环境-->
	<profile>
		<id>dev</id>
		<activation>
			<!--默认激活-->
			<activeByDefault>true</activeByDefault>
		</activation>
		<properties>
			<profileActive>dev</profileActive>
		</properties>
	</profile>
	<!--测试环境-->
	<profile>
		<id>test</id>
		<properties>
			<profileActive>test</profileActive>
		</properties>
	</profile>
	<!--正式环境-->
	<profile>
		<id>prod</id>
		<properties>
			<profileActive>prod</profileActive>
		</properties>
	</profile>
</profiles>
```
