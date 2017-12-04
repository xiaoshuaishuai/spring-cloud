spring cloud config 安全

用户密码、
server端增加:spring-security
security.user.name=user
security.user.password=111111111111111111111111
client端增加：
spring.cloud.config.username=user
spring.cloud.config.password=111111111111111111111111

****************************************
配置文件中的敏感信息

譬如：

spring.data.username=shuaishuaixiao
spring.data.pwd=Aa123456

如上密码明文放置到配置文件中是一件很敏感的事情

*****************************************



步骤：
部署cloud config的服务的jdk需要做的事情：
1.下载jce 完整包（java安全扩展）：
http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
下载原因：http://blog.csdn.net/fishmai/article/details/52398532
2.从下载的jce_policy-8.zip中的jar包，jdk1.8.0_45\jre\lib\security\覆盖掉原生2个jar包
local_policy.jar
US_export_policy.jar

准备工作完毕之后启动服务。
增加了以下几个URL：
2017-12-01 17:41:31.730  INFO 6452 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/encrypt],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.encrypt(java.lang.String,org.springframework.http.MediaType)
2017-12-01 17:41:31.730  INFO 6452 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/encrypt/{name}/{profiles}],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.encrypt(java.lang.String,java.lang.String,java.lang.String,org.springframework.http.MediaType)
2017-12-01 17:41:31.731  INFO 6452 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/decrypt/{name}/{profiles}],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.decrypt(java.lang.String,java.lang.String,java.lang.String,org.springframework.http.MediaType)
2017-12-01 17:41:31.731  INFO 6452 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/decrypt],methods=[POST]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.decrypt(java.lang.String,org.springframework.http.MediaType)
2017-12-01 17:41:31.731  INFO 6452 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/encrypt/status],methods=[GET]}" onto public java.util.Map<java.lang.String, java.lang.Object> org.springframework.cloud.config.server.encryption.EncryptionController.status()
2017-12-01 17:41:31.731  INFO 6452 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/key],methods=[GET]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.getPublicKey()
2017-12-01 17:41:31.731  INFO 6452 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/key/{name}/{profiles}],methods=[GET]}" onto public java.lang.String org.springframework.cloud.config.server.encryption.EncryptionController.getPublicKey(java.lang.String,java.lang.String)

访问：
http://localhost:8888/key 公钥

{"description":"No public key available","status":"NOT_FOUND"}
没有公钥可用

http://localhost:8888/encrypt/status

{"description":"No key was installed for encryption service","status":"NO_KEY"}
没有安装秘钥
******************************对称性加密
使用方式：增加
#密码 对称性加密
encrypt.key=password

加密：
curl http://localhost:8888/encrypt -d sss
控制台：4e478cc9f13b041cf17416d7b411b37a65cd70af9099c1e5d68e7e2bb258e89e
解密：
curl http://localhost:8888/decrypt -d 4e478cc9f13b041cf17416d7b411b37a65cd70af9099c1e5d68e7e2bb258e89e
控制台：sss

配置中心仓库配置：
spring.data.username=shuaishuaixiao
spring.data.pwd=Aa123456
改为
spring.data.username=shuaishuaixiao
spring.data.pwd={cipher}4e478cc9f13b041cf17416d7b411b37a65cd70af9099c1e5d68e7e2bb258e89e

还是需要非对称性加密
******************************非对称性加密
使用jdk keytool生成证书

keytool -genkeypair -alias config-server -keyalg RSA -dname "CN=shuaixiao, OU=qf, O=man, L=hp, ST=sh, C=china" -keypass 222222  -keystore D:\config-server.keystore -storepass 111111 -validity 36500

copy 放到任意位置config-server.keystore
#RSA非对称性加密
encrypt.key-store.location=classpath:/config-server.keystore
encrypt.key-store.alias=config-server
#-storepass 111111
encrypt.key-store.password=111111
#-keypass 222222
encrypt.key-store.secret=222222

加密
curl http://localhost:8888/encrypt -d  xxx
加密之后的密文放入配置仓库
#非对称性加密
#uname
spring.data.username.rsa={cipher}AQB4DPIagw8E2Gojg2E9SXVH2DvkjLnbtjo3BgqKp9krsS1I0srUTerheK56mnMAD4fSz9UUY4umJ1AFGnfyhWArp5WHswtDW3tj8IXp/72/ussx+VVQAPX22Qjl0ZtK8GG0y53GBn98lzvClyhxKmlLOrPL4MZkKIrm6qwYnSZiBr+pORTzQpe3qH6yrYdLgqtIV0R/L1RtcihduNV5Wbde9jvOx8So2L6v+DFsjHxp0v/tuyw9+FwVky8PZv3iJ4ojAJ6X1M27gV7YHnxyRcDgup6v/YMIFFw4l4xgfxz8g69HmAiOG+OlD0PplIOcTQGyPuRM9mEQPvHXkWPHW/MITReVOawHiQ2tp5uC/ge4ALaKWynhXwHkD4QuMuNEi5w=
#Aa123456
spring.data.pwd.rsa={cipher}AQBOioqipKYBXnOe42aMtoUK8O4tAO7ujPnbqKWFIHOXY/bNDeEhqzKRMp96EjFRaIX+2K7KlZ1OXAR52OzpeNLgDMG555ZFnTo4THLIxEB6A20JjX2dGfqrrpUk6X7ZMKTSHDcvWFMoS7QahQWVqnbjSahPATTYBBV8h61er4EwgQTYH289Qp5fj7IxIxrMi96cNMedc7owEkVSH4Q8doYJhFeAkgJ4kjVaf3AiTAxOEzIX9/80+5aVAIsmXRwMApMvA8NF7qERcbj2e/ng1g6UB/PkAFMO9Fx4fVyjZkDCKrf54tCuKEoIBit7sb56fonp8+hCSw95MHCK4/TNK/IKawHw7jKivfhi0Y1TxDNfZLuQy6fQmihdTIhajjtDIXs=
启动应用访问获取参数值：
spring.data.pwd.rsa:Aa123456
spring.data.username.rsa:uname