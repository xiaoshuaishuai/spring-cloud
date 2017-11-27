package org.my.spring.cloud.service.customer.feign;

import org.my.spring.cloud.service.customer.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ShuaishuaiXiao
 *
 */
@RestController
public class HelloController {
	@Autowired
	HelloService helloService;
	/**
	 * http://localhost:8110/hello?message=hahaha
	 * 
	 * 我们可以发现消费者的controller 和 服务提供者controller基本一致
	 * 
	 * 我们下面通过基础解决这个问题
	 * 
	 * 需要依赖工程 spring-cloud-service-api
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(@RequestParam(value = "message") String message) {
		return helloService.hello(message);
	}
}
