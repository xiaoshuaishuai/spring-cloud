配置中心服务端高可用


1.引入Eureka 
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-eureka</artifactId>
	</dependency>
	
2.properties增加了eureka注册中心的配置
eureka.client.serviceUrl.defaultZone=http://localhost:8000/eureka/,http://localhost:8001/eureka/,http://localhost:8002/eureka/

3.@EnableDiscoveryClient激活对配置中心的支持

______________________________________________________________________________________
Instances currently registered with Eureka

Application	AMIs	Availability Zones	Status
CONFIG-SERVER	n/a (1)	(1)	UP (1) - qf-shuaixiao-01.quark.com:config-server:8888


提供多个配置中心服务端
8888
spring-cloud-config-server-ha1
8889
spring-cloud-config-server-ha2


访问：localhost:eureka

Instances currently registered with Eureka

Application	AMIs	Availability Zones	Status
CONFIG-SERVER	n/a (2)	(2)	UP (2) - qf-shuaixiao-01.quark.com:config-server:8888 , qf-shuaixiao-01.quark.com:config-server:8889

发现两个服务都在运行中了。



