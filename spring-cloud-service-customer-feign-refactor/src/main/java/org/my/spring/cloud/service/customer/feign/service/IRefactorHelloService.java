package org.my.spring.cloud.service.customer.feign.service;

import org.my.spring.cloud.refactor.service.IHelloService;
import org.my.spring.cloud.service.customer.feign.service.hystrix.HelloServiceFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;


/**
 * 
 * 
 * 消费者注入该接口
 * 
 * @author ShuaishuaiXiao
 *
 */
@FeignClient(value = "spring-cloud-service-provider-refactor", fallback = HelloServiceFallBack.class) // 大小写不敏感
public interface IRefactorHelloService extends IHelloService {

}
