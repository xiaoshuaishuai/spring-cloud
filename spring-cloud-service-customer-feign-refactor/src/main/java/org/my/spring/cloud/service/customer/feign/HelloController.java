package org.my.spring.cloud.service.customer.feign;

import org.my.spring.cloud.refactor.dto.User;
import org.my.spring.cloud.service.customer.feign.service.IRefactorHelloService;
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
	IRefactorHelloService refactorHelloService;
	/**
	 * http://localhost:8110/hello1?message=hahaha
	 * 
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	String hello(@RequestParam String message) {
		return refactorHelloService.hello(message);
	}
	/**
	 * http://localhost:8110/postuser
	 * 
	 * @return
	 */
	@RequestMapping(value = "/postuser", method = RequestMethod.POST)
	String hello() {
		User u = new User();
		u.setId(2L);
		u.setUsername("2222");
		u.setPwd("Aa222222222");
		return refactorHelloService.hello(u);
	}
}
