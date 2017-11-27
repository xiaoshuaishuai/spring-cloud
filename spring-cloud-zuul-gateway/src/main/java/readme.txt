api网关：需要配合注册中心、提供者、消费者使用

使用步骤：
1、启动spring-cloud-eureka
2、启动spring-cloud-service-provider
3、启动spring-cloud-service-provider-node-01
4、启动spring-cloud-zuul-gateway
5、启动spring-cloud-service-customer
查看路由规则访问：
http://localhost:8200/routes

访问消费者服务：
http://localhost:8200/api-a/hello?message=shuaishuai
循环输出：
hello:shuaishuai
hello:-node-01shuaishuai