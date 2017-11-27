package org.my.spring.cloud.service.provider;

import org.my.spring.cloud.refactor.dto.User;
import org.my.spring.cloud.refactor.service.IHelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 服务提供者 重构
 * @author ShuaishuaiXiao
 *
 */
@RestController
public class HelloController implements IHelloService {

	@Override
	public String hello(@RequestParam String message) {
		// TODO Auto-generated method stub
		return "hello:" + message;
	}

	@Override
	public String hello(@RequestBody User user) {
		System.out.println("HelloController.hello()"+ user.toString());
		return user.toString();
	}

}
