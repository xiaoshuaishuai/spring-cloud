package org.my.spring.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="hello")
public class Receiver {
	@RabbitHandler
	public void process(String msg)  {
		System.out.println("消费消息:"+msg);
	}
}
