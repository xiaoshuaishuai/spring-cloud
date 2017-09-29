package org.my.spring.cloud.config.service.customer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="spring-cloud-service-provider") // 服务提供者name
public interface HelloRemote {
	@RequestMapping(value = "/hello")
	public String hello(@RequestParam(value = "message") String message);
}
