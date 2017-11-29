zuul 路由配置

如果没有Eureka，zuul路由配置

单实例配置
zuul.routes.hello.path=/hello/**
zuul.routes.hello.url=http://localhost:8111  对于比如http://localhost:8222/hello/haha的请求全部转发至http://localhost:8111/hello/haha



多实例配置
zuul.routes.hello.path=/hello/**
zuul.routes.hello.serviceId=SPRING-CLOUD-SERVICE-PROVIDER
ribbon.eureka.enable=false
hello.ribbo.listOfServers=http://localhost:8111,http://localhost:8112,http://localhost:8113


Eureka介入之后************************************
zuul.routes.hello.path=/hello/**
zuul.routes.hello.serviceId=SPRING-CLOUD-SERVICE-PROVIDER
上面的配置方式等价于zuul.routes.<serviceId>=<path>
zuul.routes.SPRING-CLOUD-SERVICE-PROVIDER=/hello/**

路由规则
#------------------默认创建,以serverid作为默认路由规则-------------
#zuul.routes.api-a.path=/SPRING-CLOUD-SERVICE-PROVIDER/**
#zuul.routes.SPRING-CLOUD-SERVICE-PROVIDER.serviceId=SPRING-CLOUD-SERVICE-PROVIDER
##zuul.ignored-services=*  如果想忽略服务

路径匹配
?任意单个字符
*任意多个字符
**任意多个字符，支持多层级

/hello/? 匹配的是/hello/a ,/hello/b, /hello/c
/hello/* 匹配的是/hello/aa ,/hello/bb, /hello/cc
/hello/** 匹配的是/hello/*之外,/hello/aa/vv,/hello/bb/vv, /hello/cc/vv。。。

还有个问题，就是比如我们的配置为：
zuul.routes.api-a.path=/api-a/**
zuul.routes.api-a.serviceId=SPRING-CLOUD-SERVICE-PROVIDER

有一天我们拆分了SPRING-CLOUD-SERVICE-PROVIDER这个服务，增加了ext
配置就变成了，
zuul.routes.api-a.path=/api-a/**
zuul.routes.api-a.serviceId=SPRING-CLOUD-SERVICE-PROVIDER

zuul.routes.api-a.path=/api-a/ext/**
zuul.routes.api-a.serviceId=SPRING-CLOUD-SERVICE-PROVIDER-EXT

请求到ext的服务有可能会分发到SPRING-CLOUD-SERVICE-PROVIDER，由于properties无法保证内容有序，所以我们需要使用yaml配置文件配置，yaml可以保证有序。


##zuul.ignored-patterns=*  如果想忽略URL


#本地跳转
zuul.routes.api-b.path=/api-b/**
zuul.routes.api-b.url=forward:/local


#cookie和敏感信息  zuul.sensitiveHeaders=Set-Cookie,Cookie,Authorization 不会往服务端转发携带信息
#解决这个问题有两种方式
#1、对指定路由开启自定义header
#zuul.routes.<router>.customSensitiveHeaders=true
#2、对指定路由敏感头设置为空
#zuul.routes.<router>.sensitiveHeaders=
