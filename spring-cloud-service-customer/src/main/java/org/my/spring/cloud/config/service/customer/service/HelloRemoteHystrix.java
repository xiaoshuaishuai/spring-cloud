package org.my.spring.cloud.config.service.customer.service;

import org.my.spring.cloud.config.service.customer.service.HelloRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
@Component//注入spring容器
public class HelloRemoteHystrix implements HelloRemote {

	@Override
	public String hello(@RequestParam(value = "message") String message) {
		return "hello：这是熔断器hystrix返回的信息"+message;
	}

}
