集群配置
相互配置Eureka的连接地址即可
#hostname
eureka.instance.hostname=ha1
eureka.client.serviceUrl.defaultZone=http://ha1:8000/eureka/,http://ha2:8001/eureka/

访问：http://ha1:8000/


registered-replicas	http://ha2:8001/eureka/, http://ha3:8002/eureka/
unavailable-replicas	
available-replicas	http://ha2:8001/eureka/,http://ha3:8002/eureka/,