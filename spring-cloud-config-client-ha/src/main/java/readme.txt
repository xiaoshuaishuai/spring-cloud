1.配置中心化
由原来的客户端直连配置中心服务端 改为直连Eureka注册中心

第一步：
<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
		
第二步：
#去掉这句spring.cloud.config.uri=http://localhost:8888/，不在直连配置中心服务端，改连注册中心
#
#
#
#
#
#
#
#服务发现
spring.cloud.config.discovery.enabled=true
#这个name指向 配置中心服务端的spring.application.name=config-server
spring.cloud.config.discovery.service-id=config-server
eureka.client.serviceUrl.defaultZone=http://localhost:8000/eureka/,http://localhost:8001/eureka/,http://localhost:8002/eureka/

第三步：

引导类增加服务发现支持@EnableDiscoveryClient



*********************************************************************************

观察Eureka http://ha1:8000/eureka
发现客户端已经注册上了
Instances currently registered with Eureka

Application	AMIs	Availability Zones	Status
CONFIG-SERVER	n/a (1)	(1)	UP (1) - qf-shuaixiao-01.quark.com:config-server:8888
SPRING-CLOUD-CONFIG-CLIENT	n/a (1)	(1)	UP (1) - qf-shuaixiao-01.quark.com:spring-cloud-config-client:8110
SPRING-CLOUD-EUREKA	n/a (3)	(3)	UP (3) - qf-shuaixiao-01.quark.com:spring-cloud-eureka:8002 , qf-shuaixiao-01.quark.com:spring-cloud-eureka:8001 , qf-shuaixiao-01.quark.com:spring-cloud-eureka:8000

客户端地址：
http://localhost:8110/db
http://localhost:8110/person

刷新配置：
C:\Users\shuaishuaixiao>curl -X POST http://localhost:8110/refresh