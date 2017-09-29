package org.my.spring.cloud.config.service.customer;

import org.my.spring.cloud.config.service.customer.service.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private HelloRemote HelloRemote;

	@RequestMapping("/hello/{message}")
	public String index(@PathVariable("message") String message) {
		return HelloRemote.hello(message);
	}
}
	