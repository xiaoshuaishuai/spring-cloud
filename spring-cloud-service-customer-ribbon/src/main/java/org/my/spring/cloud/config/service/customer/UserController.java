package org.my.spring.cloud.config.service.customer;

import java.net.URI;

import org.my.spring.cloud.commons.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	/**
	 * http://localhost:8110/getstruser
	 * User(id=1, username=shuaixiao, pwd=Aa111111)
	 */
	@RequestMapping(value = "/getstruser", method = RequestMethod.GET)
	public String getUser() {
		User u = restTemplate.getForObject("http://SPRING-CLOUD-SERVICE-PROVIDER/getstruser", User.class);
		System.out.println("UserController.getUser():" + u.toString());
		return u.toString();
	}
	/*
	 * 
	 * 	public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType, Object... uriVariables)
	 * 
	 * Object request如果是httpentity ，不仅包含body还包含head
	 * 
	 * 
	 */
	//postForEntity
	@RequestMapping(value = "/postUser", method = RequestMethod.POST)
	public String postUser() {
		User u = new User();
		u.setId(2L);
		u.setUsername("2222");
		u.setPwd("Aa222222222");
		return restTemplate.postForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/postUser",u,String.class).getBody();
	}
	//postForObject简化postForEntity,返回结果为对象
	@RequestMapping(value = "/postUser2", method = RequestMethod.POST)
	public String postUser2() {
		User u = new User();
		u.setId(2L);
		u.setUsername("2222");
		u.setPwd("Aa222222222");
		return restTemplate.postForObject("http://SPRING-CLOUD-SERVICE-PROVIDER/postUser",u,String.class);
	}
	
	// 测试put http://localhost:8110/putUser?id=6060
	@RequestMapping(value = "/putUser", method = RequestMethod.PUT)
	public void putUser(@RequestParam String id ) {
		User u = new User();
		u.setId(3L);
		u.setUsername("333");
		u.setPwd("Aa333");
		restTemplate.put("http://SPRING-CLOUD-SERVICE-PROVIDER/putUser?id={1}",u,id);
	}
	//postForLocation返回URI
//	@RequestMapping(value = "/postUser3", method = RequestMethod.POST)
//	public String postUser3() {
//		User u = new User();
//		u.setId(2L);
//		u.setUsername("33");
//		u.setPwd("Aa3333");
//		URI uri =  restTemplate.postForLocation("http://SPRING-CLOUD-SERVICE-PROVIDER/postUserURI",u);
//		return 	uri.toString();
//	}
}
