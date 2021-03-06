package org.my.spring.cloud.service.provider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello(@RequestParam String message) {
		System.out.println("HelloController.hello()-node-01" + message);
		return "hello:-node-01" + message;
	}

	@RequestMapping("/ribbon")
	public String ribbon() {
		System.out.println("HelloController.ribbon()-node-01");
		return "hello:-node-01 ribbon";
	}
}
