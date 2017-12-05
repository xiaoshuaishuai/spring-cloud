package org.my.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RestController
public class PersonController {
	@Value("${person.age}")
	private String personAge;

	@RequestMapping("/personAge")
	public String personAge() {
		return "age:"+personAge;
	}
}
