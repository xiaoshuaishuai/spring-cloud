api网关：需要配合注册中心、提供者、消费者使用
增加请求过滤

使用步骤：
1、启动spring-cloud-eureka
2、启动spring-cloud-service-provider
4、启动spring-cloud-zuul-gateway
5、启动spring-cloud-service-customer
查看路由规则访问：
http://localhost:8200/routes

验证zuul filter 访问消费者服务：


Request URL:http://localhost:8200/api-a/hello?message=shuaishuai&token=1
Request Method:GET
Status Code:200 
Remote Address:[::1]:8200
Referrer Policy:no-referrer-when-downgrade循环输出：

验证zuul filter 访问消费者服务：


Request URL:http://localhost:8200/api-a/hello?message=shuaishuai
Request Method:GET
Status Code:401 
Remote Address:[::1]:8200
Referrer Policy:no-referrer-when-downgrade




*************
默认的zuulfilter有问题
如果在post 阶段发生异常，页面将不会得到响应。

