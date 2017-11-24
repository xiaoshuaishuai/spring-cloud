package org.my.spring.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service("helloServiceImpl")
public class HelloServiceImpl implements IHelloService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "fail")
	public String hello(String msg) {
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}", String.class, msg)
				.getBody();
	}

	// 容错方法 注意这个方法的参数，返回值必须和需要容错的方法一zhi
	public String fail(String msg) {
		return msg + ",调用提供者失败喽！！！";
	}

	@Override
	public String update(String msg) {
		// TODO Auto-generated method stub
		// HelloServiceImpl2实现 通过注解清缓存
		return null;
	}
}
