package org.my.spring.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}
}
