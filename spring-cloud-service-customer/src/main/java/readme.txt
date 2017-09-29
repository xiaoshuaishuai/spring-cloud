服务消费者，需要配合spring-cloud-eureka 注册中心使用


Feign是一个声明式Web Service客户端。
使用Feign能让编写Web Service客户端更加简单, 
它的使用方法是定义一个接口，
___________________________
然后在上面添加注解，
同时也支持JAX-RS标准的注解。
Feign也支持可拔插式的编码器和解码器。
Spring Cloud对Feign进行了封装，
使其支持了Spring MVC标准注解和HttpMessageConverters。
Feign可以与Eureka和Ribbon组合使用以支持负载均衡。
Feign介绍：

http://xujin.org/sc/sc-fegin01/

http://localhost:8000/

Application	AMIs	Availability Zones	Status
SPRING-CLOUD-SERVICE-CUSTOMER	n/a (1)	(1)	UP (1) - qf-shuaixiao-01.quark.com:spring-cloud-service-customer:8110
SPRING-CLOUD-SERVICE-PROVIDER	n/a (1)	(1)	UP (1) - qf-shuaixiao-01.quark.com:spring-cloud-service-provider:8111

先访问下服务提供者
http://localhost:8111/hello?message=shuai
输出
hello:shuai

说明服务提供者正常服务.................
访问 消费者 http://localhost:8110/hello/shuaishuai
输出正常 说明已经正常消费.

