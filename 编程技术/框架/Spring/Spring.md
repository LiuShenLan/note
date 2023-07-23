[TOC]

[Spring全面详解](https://blog.csdn.net/weixin_44207403/article/details/106736102)

# 1.简介

## 1.1 构成

* 框架：Spring框架是一个分层架构，由7个定义良好的模块组成。Spring模块构建在核心容器之上，核心容器定义了创建、配置和管理bean的方式
	* Spring Core：核心容器提供Spring框架的基本功能。核心容器的主要组件是BeanFactory，它是工厂模式的实现。BeanFactory使用控制反转(IOC)模式将应用程序的配置和依赖性规范与实际的应用程序代码分开
	* Spring上下文：Spring上下文是一个配置文件，向Spring框架提供上下文信息。Spring上下文包括企业服务，例如JNDI、EJB、电子邮件、国际化、校验和调度功能
	* Spring AOP：通过配置管理特性，Spring AOP模块直接将面向切面的编程功能,集成到了Spring框架中。所以，可以很容易地使Spring框架管理任何支持AOP的对象。Spring AOP模块为基于Spring的应用程序中的对象提供了事务管理服务。通过使用Spring AOP，不用依赖组件，就可以将声明性事务管理集成到应用程序中
	* Spring DAO：JDBC DAO抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量(例如打开和关闭连接)。Spring DAO的面向JDBC的异常遵从通用的DAO异常层次结构
	* Spring ORM：Spring框架插入了若干个ORM框架，从而提供了ORM的对象关系工具，其中包括JDO、Hibernate和iBatis SQLMap。所有这些都遵从Spring的通用事务和DAO异常层次结构
	* Spring Web：Web上下文模块建立在应用程序上下文模块之上，为基于Web的应用程序提供了上下文。所以，Spring框架支持与Jakarta Struts的集成。Web模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作
	* Spring MVC：MVC框架是一个全功能的构建Web应用程序的MVC实现。通过策略接口，MVC框架变成为高度可配置的，MVC容纳了大量视图技术，其中包括JSP、Velocity、Tiles、iText和POI

## 1.2 SpringBoot与SpringCloud

* SpringBoot： Spring 的一套快速配置脚手架，可以基于Spring Boot 快速开发单个微服务
* SpringCloud：基于Spring Boot实现的

* 区别与联系：
	* Spring Boot专注于快速、方便集成的单个微服务个体；Spring Cloud关注全局的服务治理框架
	* Spring Boot使用了约束优于配置的理念，很多集成方案已经帮你选择好了，能不配置就不配置 ；Spring Cloud很大的一部分是基于Spring Boot来实现；Spring Boot可以离开Spring Cloud独立使用开发项目，但是Spring Cloud离不开Spring Boot，属于依赖的关系
	* SpringBoot在SpringCloud中起到了承上启下的作用，如果你要学习SpringCloud必须要学习SpringBoot

# 2.IOC与AOP

## 2.1 IOC

[深度理解依赖注入](https://www.cnblogs.com/xingyukun/archive/2007/10/20/931331.html)
[Object Builder Application Block](https://www.cnblogs.com/zhenyulu/articles/641728.html)

* IOC：Inversion of Control，控制反转

* 概述：借助于"第三方"实现具有依赖关系的对象之间的解耦

* 原理：
	* 引入前：对象A依赖于对象B，那么对象A需要主动去创建对象B或者使用已经创建的对象B，控制权在对象A
	* 引入后：对象A依赖于对象B，当对象A需要对象B时，IOC容器会主动创建一个对象B注入到对象A需要的地方
	* 对象A获得依赖对象B的过程由主动行为变为了被动行为，控制权颠倒

* DI：Dependency Injection，依赖注入
	* 来源：IOC即为获得依赖对象的过程被反转了，控制被反转之后，获得依赖对象的过程由自身管理变为了由IOC容器注入，因此IOC又名DI
	* 定义：由IOC容器在运行期间，动态地将某种依赖关系注入到对象之中

* DI种类：
	* Interface Injection：接口注入。将原本构建于对象间的依赖关系，转移到一个接口上。执行接口注入动作的仍然是主程序，这意味着与主程序与该对象间的依赖关系仍然存在，要将Interface Injection的概念发挥到极致的方式有两个，一是使用组态文件，让主程序由组态文件中读入要构建的对象，这样一来，主程序便可以在不重新编译的情况下，改变InputAccept对象的行为。二是使用Container(容器)，Avalon是一个标准的范例
	```Java
	using System;
	using System.Collections.Generic;
	using System.Text;

	namespace ConsoleApplication2 {
		class Program {
			static void Main(string[] args) {
				InputAccept accept = new InputAccept();
				accept.Inject(new DummyDataProcessor());
				accept.Execute();
				Console.Read();
			}
		}

		public class InputAccept {
			private IDataProcessor _dataProcessor;

			public void Inject(IDataProcessor dataProcessor) {
				_dataProcessor = dataProcessor;
			}

			public void Execute() {
				Console.Write("Please Input some words:");
				string input = Console.ReadLine();
				input = _dataProcessor.ProcessData(input);
				Console.WriteLine(input);
			}
		}

		public interface IDataProcessor {
			string ProcessData(string input);
		}

		public class DummyDataProcessor : IDataProcessor {

			#region IDataProcessor Members

			public string ProcessData(string input) {
				return input;
			}

			#endregion
		}

		public class PromptDataProcessor : IDataProcessor {
			#region IDataProcessor Members

			public string ProcessData(string input) {
				return "your input is: " + input;
			}

			#endregion
		}
	}

	// 在正确的Interface Injection定义中，组装InputAccept与IDataProcessor的是容器
	// 在本例中，我并未使用容器，而是提取其行为
	```
	* Constructor Injection：构造函数注入。利用构造函数参数来注入依赖关系，构造函数注入通常是与容器紧密相关的，容器允许设计者透过特定方法，将欲注入的对象事先放入容器中，当使用端要求一个支持构造函数注入的对象时，容器中会依据目标对象的构造函数参数，一一将已放入容器中的对象注入
	```Java
	// 实现构造函数注入
	public static class Container {
		private static Dictionary<Type, object> _stores = null;

		// 存储了类型及对应的构建好的对象
		// 若对象为null，则使用GetInstance创建对象
		private static Dictionary<Type, object> Stores {
			get
			{
				if (_stores == null)
					_stores = new Dictionary<Type, object>();
				return _stores;
			}
		}

		private static Dictionary<string, object> CreateConstructorParameter(Type targetType) {
			Dictionary<string, object> paramArray = new Dictionary<string, object>();

			ConstructorInfo[] cis = targetType.GetConstructors();
			if (cis.Length > 1)
				throw new Exception("target object has more then one constructor,container can't peek one for you.");

			foreach (ParameterInfo pi in cis[0].GetParameters()) {
				if (Stores.ContainsKey(pi.ParameterType))
					paramArray.Add(pi.Name, GetInstance(pi.ParameterType));
			}
			return paramArray;
		}

		// 获取指定类型对应的对象
		public static object GetInstance(Type t) {
			if (Stores.ContainsKey(t)) {
				ConstructorInfo[] cis = t.GetConstructors();
				if (cis.Length != 0) {
					Dictionary<string, object> paramArray = CreateConstructorParameter(t);
					List<object> cArray = new List<object>();
					foreach (ParameterInfo pi in cis[0].GetParameters()) {
						if (paramArray.ContainsKey(pi.Name))
							cArray.Add(paramArray[pi.Name]);
						else
							cArray.Add(null);
					}
					return cis[0].Invoke(cArray.ToArray());
				}
				else if (Stores[t] != null)
					return Stores[t];
				else
					return Activator.CreateInstance(t, false);
			}
			return Activator.CreateInstance(t, false);
		}

		// 存储对应的类型与构建好的对象
		public static void RegisterImplement(Type t, object impl) {
			if (Stores.ContainsKey(t))
				Stores[t] = impl;
			else
				Stores.Add(t, impl);
		}

		// 存储类型
		public static void RegisterImplement(Type t) {
			if (!Stores.ContainsKey(t))
				Stores.Add(t, null);
		}
	}
	```
	```Java
	// 使用构造函数注入
	class Program {
		static void Main(string[] args)
		{
			Container.RegisterImplement(typeof(InputAccept));
			Container.RegisterImplement(typeof(IDataProcessor), new PromptDataProcessor());
			InputAccept accept = (InputAccept)Container.GetInstance(typeof(InputAccept));
			accept.Execute();
			Console.Read();
		}
	}

	public class InputAccept {
		private IDataProcessor _dataProcessor;

		public void Execute() {
			Console.Write("Please Input some words:");
			string input = Console.ReadLine();
			input = _dataProcessor.ProcessData(input);
			Console.WriteLine(input);
		}

		public InputAccept(IDataProcessor dataProcessor) {
			_dataProcessor = dataProcessor;
		}
	}

	public interface IDataProcessor {
		string ProcessData(string input);
	}

	public class DummyDataProcessor : IDataProcessor {
		#region IDataProcessor Members

		public string ProcessData(string input) {
			return input;
		}

		#endregion
	}

	public class PromptDataProcessor : IDataProcessor {
		#region IDataProcessor Members

		public string ProcessData(string input) {
			return "your input is: " + input;
		}

		#endregion
	}
	```
	* Setter Injection：设值注入。透过属性的途径，将依赖对象注入目标对象中，与构造函数注入模式一样，这个模式同样需要容器的支持。对象是提前构造好的，而构造函数注入模式中是现场构造的
	```Java
	// 实现设值注入
	public static class Container {
		private static Dictionary<Type, object> _stores = null;

		private static Dictionary<Type, object> Stores {
			get {
				if (_stores == null)
					_stores = new Dictionary<Type, object>();
				return _stores;
			}
		}

		public static object GetInstance(Type t) {
			if (Stores.ContainsKey(t)) {
				if (Stores[t] == null) {
					object target = Activator.CreateInstance(t, false);
					foreach (PropertyDescriptor pd in TypeDescriptor.GetProperties(target)) {
						if (Stores.ContainsKey(pd.PropertyType))
							pd.SetValue(target, GetInstance(pd.PropertyType));
					}
					return target;
				}
				else
					return Stores[t];
			}
			return Activator.CreateInstance(t, false);
		}

		public static void RegisterImplement(Type t, object impl) {
			if (Stores.ContainsKey(t))
				Stores[t] = impl;
			else
				Stores.Add(t, impl);
		}

		public static void RegisterImplement(Type t) {
			if (!Stores.ContainsKey(t))
				Stores.Add(t, null);
		}
	}
	```
	```Java
	// 使用设值注入
	class Program {
		static void Main(string[] args) {
			Container.RegisterImplement(typeof(InputAccept));
			Container.RegisterImplement(typeof(IDataProcessor), new PromptDataProcessor());
			InputAccept accept = (InputAccept)Container.GetInstance(typeof(InputAccept));
			accept.Execute();
			Console.Read();
		}
	}

	public class InputAccept {
		private IDataProcessor _dataProcessor;

		public IDataProcessor DataProcessor {
			get {
				return _dataProcessor;
			}
			set {
				_dataProcessor = value;
			}
		}

		public void Execute() {
			Console.Write("Please Input some words:");
			string input = Console.ReadLine();
			input = _dataProcessor.ProcessData(input);
			Console.WriteLine(input);
		}
	}
	```

## 2.2 AOP

* AOP：Aspect Oriented Programming，面向切面编程

* 概述：通过预编译方式和运行期动态代理实现程序功能的统一维护

* 定义：
	* 切面：Aspect。生命类似Java中的类声明，在切面中会包含着一些切点以及相应的建议
	* 连接点：Joint point。表示在程序中明确定义的点，典型的包括方法调用，对类成员的访问以及异常处理程序块的执行等等，它自身还可以嵌套其它连接点
	* 切点：Point cut。表示一组连接点，这些连接点或是通过逻辑关系组合起来，或是通过通配、正则表达式等方式集中起来，它定义了相应的建议将要发生的地方
	* 建议：Advice。定义了在切点里面定义的程序点具体要做的操作，它通过 before、after 和 around 来区别是在每个连接点之前、之后还是代替执行的代码
	* 目标对象：Target。织入建议的目标对象
	* 织入：Weaving。将切面和其它对象连接起来，并创建建议的过程
	* Spring中：连接点指代的是所有方法的执行点，而切点是一个描述信息，它修饰的是连接点，通过切点就可以确定哪些连接点可以被织入建议

* 连接点类型：
	* AOP中的连接点可以有多种类型：构造方法调用，字段的设置和获取，方法的调用，方法的执行，异常的处理执行，类的初始化。也就是说在AOP的概念中我们可以在上面的这些连接点上织入我们自定义的建议
	* 但是在Spring中却没有实现上面所有的连接点，确切的说，Spring只支持方法执行类型的连接点

* Advice类型：
	* before advice：在连接点前被执行的 advice. 虽然before advice是在连接点前被执行，但是它并不能够阻止连接点的执行，除非发生了异常(即我们在before advice代码中，不能人为地决定是否继续执行连接点中的代码)
	* after return advice：在一个连接点正常返回后执行的advice
	* after throwing advice：当一个连接点抛出异常后执行的advice
	* after(final) advice：无论一个连接点是正常退出还是发生了异常，都会被执行的 advice
	* around advice：在连接点前和连接点退出后都执行的advice. 这个是最常用的advice
	* introduction：introduction可以为原有的对象增加新的属性和方法
