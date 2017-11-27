package org.my.spring.cloud.service.customer.feign.service.hystrix;

import org.my.spring.cloud.refactor.dto.User;
import org.my.spring.cloud.service.customer.feign.service.IRefactorHelloService;
import org.springframework.stereotype.Component;

/**
 * 失败回调
 * 
 * @author ShuaishuaiXiao
 *
 */
@Component
public class HelloServiceFallBack implements IRefactorHelloService {

	@Override
	public String hello(String message) {
		return "hystrix error";
	}

	@Override
	public String hello(User user) {
		return "hystrix error";
	}

	@Override
	public String hello2(String message) {
		return "hystrix error";
	}

}
