package org.my.spring.customer.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;

/**
 * 通过实现HystrixCommand 来使用熔断
 */
public class HelloCommand extends HystrixCommand<String> {
	private String msg;
	private RestTemplate restTemplate;

	public HelloCommand(Setter setter, RestTemplate restTemplate, String msg) {
		super(setter);
		this.restTemplate = restTemplate;
		this.msg = msg;
	}

	@Override
	protected String run() throws Exception {
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}", String.class, msg)
				.getBody();
	}

	// 容错回调
	@Override
	protected String getFallback() {
		return "熔断信息";
	}

	// 开启缓存,需要首先初始化HystrixRequestContext
	@Override
	protected String getCacheKey() {
		System.out.println(
				"HelloCommand.getCacheKey():"+msg+"|" + new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));

		return msg;
	}
}
