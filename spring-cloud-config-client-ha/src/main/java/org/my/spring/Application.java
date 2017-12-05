package org.my.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableDiscoveryClient  //服务发现客户端
public class Application implements CommandLineRunner {
//	@Value("${person.age}")
//	private String personAge;
	@Autowired
	private Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//			System.err.println(personAge);
	}
}