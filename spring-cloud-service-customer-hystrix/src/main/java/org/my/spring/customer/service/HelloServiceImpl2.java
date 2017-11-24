package org.my.spring.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;

/**
 * 
 * @cache hystrix缓存通过注解使用
 * 
 */
@Service("helloServiceImpl2")
public class HelloServiceImpl2 implements IHelloService {
	@Autowired
	private RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "fail", commandKey = "hello", groupKey = "HelloService", threadPoolKey = "HelloServicePool") // 1
	@CacheResult(cacheKeyMethod = "getCacheKey") // 2
	public String hello(@CacheKey("msg") String msg) {// @CacheKey 4
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}", String.class, msg)
				.getBody();
	}

	
	public String getCacheKey(String msg) {// 3
		System.out.println("HelloServiceImpl2.getCacheKey():" + msg);
		return msg;
	}


	// 容错方法 注意这个方法的参数，返回值必须和需要容错的方法一zhi
	public String fail(String msg) {
		return msg + ",调用提供者失败喽！！！";
	}
	@Override
	@CacheRemove(commandKey = "hello",cacheKeyMethod = "getCacheKey") // 5
	@HystrixCommand(commandKey = "clear", groupKey = "HelloService", threadPoolKey = "HelloServicePool")
	public String update(@CacheKey("msg") String msg) {//6
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}", String.class, msg)
				.getBody();
	}
}
