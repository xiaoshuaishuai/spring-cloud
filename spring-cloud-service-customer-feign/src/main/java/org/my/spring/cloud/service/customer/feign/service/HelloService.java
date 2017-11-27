package org.my.spring.cloud.service.customer.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SPRING-CLOUD-SERVICE-PROVIDER") // 服务的name
public interface HelloService {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	String hello(@RequestParam(value = "message") String message);
}
