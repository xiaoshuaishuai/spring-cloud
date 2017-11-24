package org.my.spring.customer.service;

import org.my.spring.cloud.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "fail")
	public User getUser() {
		return restTemplate.getForObject("http://SPRING-CLOUD-SERVICE-PROVIDER/getstruser", User.class);
	}

	public User fail() {
		User u = new User();
		u.setId(999999L);
		u.setUsername("熔断用户");
		u.setPwd("熔断密码");
		return u;
	}
}
