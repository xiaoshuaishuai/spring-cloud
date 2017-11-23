增加eureka注册中心的配置

server端配置
https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-client/src/main/java/org/springframework/cloud/netflix/eureka/EurekaInstanceConfigBean.java
client端配置
https://github.com/spring-cloud/spring-cloud-netflix/blob/master/spring-cloud-netflix-eureka-client/src/main/java/org/springframework/cloud/netflix/eureka/EurekaClientConfigBean.java

#Eureka的客户端标识
eureka.client.enabled=true
#从Eureka刷新注册信息时间，默认30s
eureka.client.registryFetchIntervalSeconds=30  
#eureka更改备份的时间，默认30s
eureka.client.instanceInfoReplicationIntervalSeconds=30
#初始将实例信息复制到Eureka的时间,默认40s
eureka.client.initialInstanceInfoReplicationIntervalSeconds=40
#指定多久去轮训Eureka servers信息，server 添加或删除配置，client知道的时间，默认5分钟
eureka.client.eurekaServiceUrlPollIntervalSeconds=5*60
#指定等待读取server的超时时间，默认8s
eureka.client.eurekaServerReadTimeoutSeconds=8
#指定连接server超时等待时间，在客户端汇集 org.apache.http.client.HttpClient，这个设置会影响实际的连接，创建以及从池中获取连接的等待时间。默认5s
eureka.client.eurekaServerConnectTimeoutSeconds=5
#client连接server的总数，默认200
eureka.client.eurekaServerTotalConnections=200
#同一个server主机支持的连接数
eureka.client.eurekaServerTotalConnectionsPerHost=50
eureka.client.
eureka.client.
eureka.client.