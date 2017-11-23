package org.my.spring.cloud.config.service.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * http://localhost:8110/ribbon
 * 
 * RestTemplate使用
 */
@RestController
public class CustomerController {
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/ribbon", method = RequestMethod.GET)
	public String customer() {
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/ribbon", String.class).getBody();
	}
	/**
	 * 单个参数
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		/**
		 * http://localhost:8111/hello?message=shuai服务提供者URL
		 * 
		 * 
		 * http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}
		 * 
		 * 访问：http://localhost:8110/hello
		 * 
		 * 
		 * out：hello:lala
		 */
		// 占位符{1}
		return restTemplate
				.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}", String.class, "lala")
				.getBody();
	}
	/**
	 * uri
	 */
	@RequestMapping(value = "/hellouri", method = RequestMethod.GET)
	public String hellourl() {
		UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://SPRING-CLOUD-SERVICE-PROVIDER/hello/?message={1}")
		.build()
		.expand("哈哈哈哈哈哈哈哈")
		.encode();
		return restTemplate.getForEntity(uriComponents.toUri(), String.class).getBody();
		
	}

	/**
	 * 多个参数
	 */
	@RequestMapping(value = "/hellodesc", method = RequestMethod.GET)
	public String hellodesc() {
		// http://SPRING-CLOUD-SERVICE-PROVIDER/hellodesc/?message={1}&desc={2}
		// hello:lala,desc:meme
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hellodesc/?message={1}&desc={2}",
				String.class, "lala", "meme").getBody();
	}

	/**
	 * 多个参数map
	 */
	@RequestMapping(value = "/hellodescmap", method = RequestMethod.GET)
	public String hellodescmap() {
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("message", "lala");
		uriVariables.put("desc", "map");
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/hellodesc/?message={message}&desc={desc}",
				String.class, uriVariables).getBody();
	}

}
