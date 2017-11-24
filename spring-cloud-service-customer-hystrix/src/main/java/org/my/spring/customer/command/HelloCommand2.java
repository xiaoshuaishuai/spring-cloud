package org.my.spring.customer.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * 带clear缓存的熔断
 */
public class HelloCommand2 extends HystrixCommand<String> {
	private String msg;
	private RestTemplate restTemplate;

	public HelloCommand2(Setter setter, RestTemplate restTemplate, String msg) {
		super(setter);
		this.restTemplate = restTemplate;
		this.msg = msg;
	}

	@Override
	protected String run() throws Exception {
		String res = restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}", String.class, msg)
				.getBody();
		//commandName对应setter设置的key
		HystrixRequestCache.getInstance(HystrixCommandKey.Factory.asKey("commandName"), HystrixConcurrencyStrategyDefault.getInstance()).clear(msg);;
		//--------------------------------------------------------
		return res;
	}

	// 容错回调
	@Override
	protected String getFallback() {
		return "熔断信息";
	}

	// 开启缓存,需要首先初始化HystrixRequestContext
	@Override
	protected String getCacheKey() {
		System.out.println("HelloCommand.getCacheKey():" + msg + "|"
				+ new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));

		return msg;
	}
}
