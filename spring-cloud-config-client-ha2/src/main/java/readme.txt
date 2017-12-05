spring cloud bus支持，需要启动rabbitmq服务器
同spring-cloud-config-client-ha1，修改了server.port
步骤：
启动spring-cloud-eureka
启动spring-cloud-config-server
启动spring-cloud-config-client-ha
启动spring-cloud-config-client-ha2

访问
http://localhost:8110/personAge
http://localhost:8111/personAge

返回age:25

修改仓库的配置文件修改为24

访问任何一个config-client，比如curl -X POST localhost:8110/bus/refresh

访问
http://localhost:8110/personAge
http://localhost:8111/personAge

返回age:24

curl 可以替换成webhook功能，就可以实现了全自动化配置更新
{
Webhooks
必须在同一个网络，访问不到会连接不到
http://localhost:8110/bus/refresh  (push)【防止单点问题，生产环境可以配置成zuul代理】

}

试用场景：服务较多，不可能每个服务去通知重新加载配置的情况下采用

https://cloud.spring.io/spring-cloud-static/Camden.SR7/#_spring_cloud_bus
局部刷新:
The HTTP endpoints accept a "destination" parameter, e.g. "/bus/refresh?destination=customers:9000", where the destination is an ApplicationContext ID. If the ID is owned by an instance on the Bus then it will process the message and all other instances will ignore it. Spring Boot sets the ID for you in the ContextIdApplicationContextInitializer to a combination of the spring.application.name, active profiles and server.port by default.


架构优化 将config server也引入bus，所有的/bus/refresh请求到配置中心，而不在请求具体实例
参考：spring-cloud-config-server-bus
