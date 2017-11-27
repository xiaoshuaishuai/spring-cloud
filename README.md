![Image text](https://raw.githubusercontent.com/xiaoshuaishuai/spring-cloud/master/static-img/SpringCloudTechs.png)
<h1>Spring Cloud Main Project  主要工程组件</h1>

Spring Cloud Config ：配置中心

Spring Cloud Netflix ：spring集成的NetFlix，NetFlix包含很多核心个组件

Eureka：云端服务发现，一个基于 REST 的服务，用于定位服务，以实现云端中间层服务发现和故障转移。注册中心的担当

Hystrix：熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。可以理解为生活中的"触电保险器"

Zuul：在云平台上提供动态路由,监控,弹性,安全等边缘服务的框架。Zuul 相当于是设备和 Netflix 流应用的 Web 网站后端所有请求的前门。api网关的存在，外部或者内部的非Spring Cloud项目都统一通过API网关（Zuul）来访问内部服务.
	     
Archaius：配置管理API，包含一系列配置管理API，提供动态类型化属性、线程安全配置操作、轮询框架、回调机制等功能。
	
Ribbon：提供云端负载均衡，有多种负载均衡策略可供选择，可配合服务发现和断路器使用。

Turbine：Turbine是聚合服务器发送事件流数据的一个工具，用来监控集群下hystrix的metrics情况。监控多个hystri服务的组件。

Spring Cloud Bus：将分布式系统的节点与轻量级消息代理连接起来。
		  然后可以将其用于广播状态改变（例如，配置更改）或其他管理指令。
		  目前唯一的实现是使用AMQP代理作为传输，但是相同的基本功能集（还有一些取决于传输）在其他传输的路线图上。

Spring Cloud Cloud Foundry：通过Oauth2协议绑定服务到Cloud Foundry，Cloud Foundry是VMware推出的开源PaaS云平台。

Spring Cloud Cloud Foundry Service Broker：提供构建管理Cloud Foundry管理服务的服务代理的起点。

Spring Cloud Cluster ：Zookeeper，Redis，Hazelcast，Consul的抽象和实施的领导选举和共同的有状态。

Spring Cloud Consul：服务发现和配置工具，与Docker容器可以无缝集成。

Spring Cloud Security：基于spring security的安全工具包，为你的应用程序添加安全控制。

Spring Cloud Sleuth：Spring Cloud Sleuth实现了Spring Cloud的分布式跟踪解决方案，大量借用了Dapper，Zipkin和HTrace。
		     对于大多数用户，Sleuth应该是不可见的，并且所有与外部系统的交互都应该自动进行检测。
	             您可以在日志中简单捕获数据，也可以将其发送到远程收集器服务。

Spring Cloud Data Flow：大数据操作工具，作为Spring XD的替代产品，它是一个混合计算模型，结合了流数据与批量数据的处理方式。

Spring Cloud Stream：Spring Cloud Stream是构建消息驱动的微服务的框架。 
		     Spring Cloud Stream建立在Spring Boot之上，
		     以创建DevOps友好的微服务应用程序和Spring Integration来提供与消息代理的连接。 
		     Spring Cloud Stream提供了一个有意见的消息代理配置，在多个中间件供应商中引入了持久的pub / sub语义，
		     消费者组和分区的概念。这种认真的配置为创建流处理应用程序提供了基础。

Spring Cloud Zookeeper：操作Zookeeper的工具包，用于使用zookeeper方式的服务发现和配置管理。

Spring Cloud CLI：基于 Spring Boot CLI，可以让你以命令行方式快速建立云组件。

Feign：Feign是一种声明式、模板化的HTTP客户端。Urlconnection httpcliet，用再服务消费端。(合并了ribbon&hystrix)

Spring Cloud Task：提供云端计划任务管理、任务调度。

Spring Cloud Connectors：便于云端应用程序在各种PaaS平台连接到后端，如：数据库和消息代理服务。

Spring Cloud Starters：Spring Boot式的启动项目，为Spring Cloud提供开箱即用的依赖管理。
