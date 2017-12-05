package org.my.spring.sender;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	public void send() {
		for (int i = 0; i < 100; i++) {
			amqpTemplate.convertAndSend("hello", "date:"+new Date()+", index :"+i);
		}
	}
}
