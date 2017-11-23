package org.my.spring.cloud.config.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * http://localhost:8110/ribbon
 */
@RestController
public class CustomerController {
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/ribbon",method=RequestMethod.GET)
	public String customer() {
		return restTemplate.getForEntity("http://SPRING-CLOUD-SERVICE-PROVIDER/ribbon", String.class).getBody();
	} 

}
