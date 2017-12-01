******【动态路由】

动态路由配置，需要配合 cloud config server、Eureka
首先访问http://localhost:8200/routes 查看路由
修改仓库配置,然后post请求http://localhost:8200/refresh
curl -X POST http://localhost:8200/refresh
访问http://localhost:8200/routes 查看路由已经变化。
spring cloud config威力的展现...

******【动态过滤器】
