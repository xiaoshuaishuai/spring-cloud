package org.my.spring.cloud.refactor.service;

import org.my.spring.cloud.refactor.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 重构service接口层
 * 
 * 提供者implements该接口 
 * 消费者extend该接口，增加@ferginClinet注解
 * 
 * @author ShuaishuaiXiao
 *
 */
public interface IHelloService {
	
	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	String hello(@RequestParam(value="message") String message);

	@RequestMapping(value = "/postuser", method = RequestMethod.POST)
	String hello(@RequestBody User user);

}
