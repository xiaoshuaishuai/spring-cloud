package org.my.spring;

import org.my.spring.filter.cust.CustFilterProcessor;
import org.my.spring.filter.pre.ExamplePreZuulFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.netflix.zuul.FilterProcessor;

@SpringBootApplication
@EnableZuulProxy
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean//这里让filter生效
	public ExamplePreZuulFilter filter() {
		return new ExamplePreZuulFilter();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		FilterProcessor.setProcessor(new CustFilterProcessor());
		System.err.println("设置自定义filterProcessor完成");
	}
}
