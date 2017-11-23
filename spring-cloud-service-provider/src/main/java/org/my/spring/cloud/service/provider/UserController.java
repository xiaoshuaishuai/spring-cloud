package org.my.spring.cloud.service.provider;

import java.net.URI;
import java.net.URISyntaxException;

import org.my.spring.cloud.commons.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	// 返回对象 ，测试resttemplaate getforobject
	@RequestMapping(value = "/getstruser", method = RequestMethod.GET)
	public User getUser() {
		User u = new User();
		u.setId(1L);
		u.setUsername("shuaixiao");
		u.setPwd("Aa111111");
		return u;
	}

	// 测试post
	@RequestMapping(value = "/postUser", method = RequestMethod.POST)
	public String postUser(@RequestBody User u) {
		if (null == u.getId()) {
			return "失败";
		}
		System.out.println("提供者接收数据postUser():" + u.toString());
		return "成功";
	}
	// 测试put
	@RequestMapping(value = "/putUser", method = RequestMethod.PUT)
	public String putUser(@RequestParam String id , @RequestBody User u) {
		System.out.println(id+",对象信息："+u);
		return id+",对象信息："+u;
	}

//	@RequestMapping(value = "/postUserURI", method = RequestMethod.POST)
//	public URI postUserURI(@RequestBody User u) throws URISyntaxException {
//		return new URI("https://github.com/xiaoshuaishuai/spring-cloud");
//
//	}
}
