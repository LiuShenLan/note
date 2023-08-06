
# 1. 概述

Spring Cloud提供了快速构建分布式系统的一些工具，包括配置管理、服务发现、断路器、路由、微代理、事件总线、全局锁、决策竞选、分布式会话等等

# 2. 服务注册中心(Eureka)

[Eureka](https://blog.csdn.net/forezp/article/details/69696915)

## 2.1 概述

Eureka是一个服务注册和发现模块

## 2.2 Eureka Server

* 启动Eureka Server需要在SpringBoot工程的启动`application`类上添加注释`@EnableEurekaServer`

* eureka是一个高可用的组件，它没有后端缓存，每一个实例注册之后需要向注册中心发送心跳（因此可以在内存中完成），在默认情况下eureka server也是一个eureka client ,必须要指定一个 server

* Eureka Server的配置文件为`application.yml`，如下所示↓：

```yaml
server:
	port: 8761

eureka:
	instance:
		hostname: localhost
	client:
		registerWithEureka: false	# 表明该类是Eureka Server
		fetchRegistry: false
		serviceUrl:
			defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

## 2.3 Eureka Client

* 当client向server注册时，它会提供一些元数据，例如主机和端口，URL，主页等。Eureka server 从每个client实例接收心跳消息。 如果心跳超时，则通常将该实例从注册server中删除

* 启动Eureka Client需要在SpringBoot工程的启动`application`类上添加注释`@EnableEurekaClient`

* Eureka Client需要在配置文件`application.yml`中注明自己的服务注册中心地址，如下所示↓：

```yaml
eureka:
	client:
		serviceUrl:
			defaultZone: http://localhost:8761/eureka/
server:
	port: 8762
spring:
	application:
		name: service-hi	# 以后的服务与服务之间相互调用一般都是根据 spring.application.name来调用
```

# 3. 服务消费者

在微服务架构中，服务与服务的通讯是基于http restful的。Spring Cloud有两种服务调用方式，分别是ribbon+restTemplate和feign，本节介绍rest+ribbon

## 3.1 rest+ribbon

[rest+ribbon](https://blog.csdn.net/forezp/article/details/69788938)

ribbon是一个负载均衡客户端，可以很好的控制http和tcp的一些行为。Feign默认继承了ribbon

在工程的启动类中，通过`@EnableDiscoveryClient`向服务中心注册，并且想程序的ioc注入一个bean：`restTemplate`，并通过`@LoadBalanced`注解表明这个restTemplate开启负载均衡的功能

## 3.2 Feign

[Feign](https://blog.csdn.net/forezp/article/details/69808079)

Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果

Feign采用的是基于接口的注解，并且整合了ribbon

# 4. 断路器(Hystrix)

在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC），在Spring Cloud可以用RestTemplate+Ribbon和Feign来调用。为了保证其高可用，单个服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应。为了解决这个问题，业界提出了断路器模型

在微服务架构中，一个请求需要调用多个服务是非常常见的，较底层的服务如果出现故障，会导致连锁故障。当对特定的服务的调用的不可用达到一个阀值（Hystric 是5秒20次） 断路器将会被打开。断路打开后，可用避免连锁故障，fallback方法可以直接返回一个固定值。

# 5. 路由网关(zuul)

在微服务架构中，需要几个基础的服务治理组件，包括服务注册与发现、服务消费、负载均衡、断路器、智能路由、配置管理等，由这几个基础组件相互协作，共同组建了一个简单的微服务系统

在Spring Cloud微服务系统中，一种常见的负载均衡方式是，客户端的请求首先经过负载均衡（zuul、Ngnix），再到达服务网关（zuul集群），然后再到具体的服务。服务统一注册到高可用的服务注册中心集群，服务的所有的配置文件由配置服务管理（下一篇文章讲述），配置服务的配置文件放在git仓库，方便开发人员随时改配置

Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如／api/user转发到到user服务，/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能

# 6. 分布式配置中心(Spring Cloud Config)

在分布式系统中，由于服务数量巨多，为了方便服务配置文件统一管理，实时更新，所以需要分布式配置中心组件。在Spring Cloud中，有分布式配置中心组件spring cloud config ，它支持配置服务放在配置服务的内存中（即本地），也支持放在远程Git仓库中。在spring cloud config 组件中，分两个角色，一是config server，二是config client

config client从config server读取属性，config server可以从被本地或远程仓库读取属性

当服务实例较多时，可以将配置中心做成一个微服务，将其集群化，从而达到高可用

# 7. 消息总线(Spring Cloud Bus)

Spring Cloud Bus 将分布式的节点用轻量的消息代理连接起来。它可以用于广播配置文件的更改或者服务之间的通讯，也可以用于监控

# 8. 服务链路追踪(Spring Cloud Sleuth)

Spring Cloud Sleuth 主要功能就是在分布式系统中提供追踪解决方案，并且兼容支持了 zipkin，只需要在pom文件中引入相应的依赖即可

* 背景：微服务架构上通过业务来划分服务的，通过REST调用，对外暴露的一个接口，可能需要很多个服务协同才能完成这个接口功能，如果链路上任何一个服务出现问题或者网络超时，都会形成导致接口调用失败。随着业务的不断扩张，服务之间互相调用会越来越复杂

* 术语：
	* `Span`：基本工作单元。Span在不断的启动和停止，同时记录了时间信息，当创建了一个span，必须在未来的某个时刻停止它
	* `Trace`：一系列Spans组成的一个树状结构
	* `Annotation`：用来及时记录一个事件的存在，一些核心annotation描述了这个span的开始
		* `cs`：Client Sent，客户端发起一个请求，这个annotation描述了这个span的开始
		* `sr`：Server Received，服务端获得请求并准备开始处理它，如果将其sr减去cs时间戳便可得到网络延迟
		* `ss`：Server Sent，注解表明请求处理的完成(当请求返回客户端)，如果ss减去sr时间戳便可得到服务端需要的处理请求时间
		* `cr`：Client Received，表明span的结束，客户端成功接收到服务端的回复，如果cr减去cs时间戳便可得到客户端从服务端获取回复的所有所需时间
		* ``：
		* ``：

# 待替换

（	(
）	)
eureka	Eureka
“	"
”	"