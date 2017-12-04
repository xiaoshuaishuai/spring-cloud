package org.my.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application implements ApplicationRunner{
	@Autowired
	Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		System.err.println(environment.getProperty("spring.data.pwd","没有取到"));
		System.err.println("spring.data.pwd.rsa:"+environment.getProperty("spring.data.pwd.rsa","没有取到"));
		System.err.println("spring.data.username.rsa:"+environment.getProperty("spring.data.username.rsa","没有取到"));
	}
	
}
