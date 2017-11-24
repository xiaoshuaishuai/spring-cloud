package org.my.spring.customer;

import javax.annotation.Resource;

import org.my.spring.customer.command.HelloCommand;
import org.my.spring.customer.command.HelloCommand2;
import org.my.spring.customer.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * 
 * http://localhost:9001/hello/shuaishuaiXiao
 */
@RestController
public class HelloController {
	@Autowired
	private RestTemplate restTemplate;
	@Resource
	@Qualifier(value = "helloServiceImpl")
	private IHelloService helloServiceImpl;
	@Resource
	@Qualifier(value = "helloServiceImpl2")
	private IHelloService helloServiceImpl2;

	/**
	 * 
	 * 方法描述 通过注解实现熔断
	 */
	@RequestMapping(value = "/hello/{message}", method = RequestMethod.GET)
	public String index(@PathVariable("message") String message) {
		return helloServiceImpl.hello(message);
	}

	/**
	 * 
	 * 方法描述 通过extends HystrixCommand
	 */
	@RequestMapping(value = "/hello2/{message}", method = RequestMethod.GET)
	public String hello2(@PathVariable("message") String message) {

		Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName"));// 线程名
		setter.andCommandKey(HystrixCommandKey.Factory.asKey("commandName"));// 命令名
		return new HelloCommand(setter, restTemplate, message).execute();
	}

	/**
	 * 
	 * 方法描述 通过extends HystrixCommand,开启缓存
	 * 
	 * hystrix缓存的作用是 - 1.减少重复的请求数，降低依赖服务的返回数据始终保持一致。 -
	 * 2.==在同一个用户请求的上下文中，相同依赖服务的返回数据始终保持一致==。 -
	 * 3.请求缓存在run()和construct()执行之前生效，所以可以有效减少不必要的线程开销。
	 */
	@RequestMapping(value = "/hello3/{message}", method = RequestMethod.GET)
	public String hello3(@PathVariable("message") String message) {

		Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName"));// 线程名
		setter.andCommandKey(HystrixCommandKey.Factory.asKey("commandName"));// 命令名
		return new HelloCommand(setter, restTemplate, message).execute();
	}

	/**
	 * 
	 * 方法描述
	 * 在同一用户请求的上下文中，相同依赖服务的返回数据始终保持一致。在当次请求内对同一个依赖进行重复调用，只会真实调用一次。在当次请求内数据可以保证一致性。
	 * 
	 * 可以观察提供者：只会调用一次 消费者通过缓存获取
	 * 
	 * Demo1Filter Demo2Filter Demo3Filter
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36 DemoTwo1Filter
	 * DemoTwo2Filter DemoTwo3Filter
	 * HystrixRequestContext.initializeContext()初始化开启hystrix缓存上下文
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * HelloCommand.getCacheKey():shuaishuai|2017-13-24 05:13:36
	 * 
	 */
	@RequestMapping(value = "/hello4/{message}", method = RequestMethod.GET)
	public String hello4(@PathVariable("message") String message) {
		Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName"));// 线程名
		setter.andCommandKey(HystrixCommandKey.Factory.asKey("commandName"));// 命令名

		for (int i = 0; i < 10; i++) {
			new HelloCommand(setter, restTemplate, message).execute();
		}

		return new HelloCommand(setter, restTemplate, message).execute();
	}

	/**
	 * 
	 * 方法描述 每次请求后都会清除缓存,都会请求到提供者
	 */
	@RequestMapping(value = "/hello5/{message}", method = RequestMethod.GET)
	public String hello5(@PathVariable("message") String message) {
		Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupName"));// 线程名
		setter.andCommandKey(HystrixCommandKey.Factory.asKey("commandName"));// 命令名

		for (int i = 0; i < 10; i++) {
			new HelloCommand2(setter, restTemplate, message).execute();
		}

		return new HelloCommand2(setter, restTemplate, message).execute();
	}

	/**
	 * 
	 * 方法描述
	 * 
	 * 验证通过hystrix注解 cacheresult cachekey cacheremove使用缓存
	 */
	@RequestMapping(value = "/hello6/{message}", method = RequestMethod.GET)
	public String hello6(@PathVariable("message") String message) {
		System.out.println("通过缓存====");
		for (int i = 0; i < 5; i++) {
			helloServiceImpl2.hello(message);
		}
		System.out.println("通过缓存end====");
		System.err.println("***********************************************************************");
		System.out.println("直接调用====");
		for (int i = 0; i < 5; i++) {
			helloServiceImpl2.update(message);
		}
		System.out.println("直接调用end====");
		return helloServiceImpl2.hello(message);
	}
}
