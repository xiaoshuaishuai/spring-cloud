package org.my.spring.cloud.service.customer.feign.service;

import org.my.spring.cloud.refactor.service.IHelloService;
import org.springframework.cloud.netflix.feign.FeignClient;


/**
 * 
 * 
 * 消费者注入该接口
 * 
 * @author ShuaishuaiXiao
 *
 */
@FeignClient("spring-cloud-service-provider-refactor") // 大小写不敏感
public interface IRefactorHelloService extends IHelloService {

}
