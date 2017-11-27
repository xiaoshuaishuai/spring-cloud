package org.my.spring.cloud.service.provider;

import java.util.Random;

import org.my.spring.cloud.refactor.dto.User;
import org.my.spring.cloud.refactor.service.IHelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
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
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Override
	public String hello(@RequestParam String message) {
		logger.info("hello:" + message);

		return "hello:" + message;
	}

	@Override
	public String hello(@RequestBody User user) {
		logger.info("HelloController.hello()"+ user.toString());
		return user.toString();
	}

	@Override
	public String hello2(String message) {
		int time = new Random().nextInt(3000);//随机延迟
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("测试ribbon连接超时，sleep time:"+time);
		return "hello:" + message;
	}

}
