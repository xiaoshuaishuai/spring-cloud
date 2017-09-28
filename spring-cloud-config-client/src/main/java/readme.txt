如何在修改了仓库配置信息之后，同步刷新到使用客户端？？？？

1.@RefreshScope
 
2.引入<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		
3.post 请求 localhost:8002/refresh 刷新url

 cmd 执行： curl -X POST localhost:8002/refresh

==========================================================================
C:\Users\shuaishuaixiao>curl -X POST http://localhost:8002/refresh
["config.client.version","spring.datasource.password"]
C:\Users\shuaishuaixiao>
==========================================================================


 ps：https://curl.haxx.se/download.html
 http://www.cnblogs.com/xing901022/p/4652624.html
 curl 需要安装。