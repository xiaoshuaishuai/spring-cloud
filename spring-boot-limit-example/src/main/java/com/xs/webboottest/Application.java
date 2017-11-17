package com.xs.webboottest;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class Application{
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		ApplicationContext ctx = new SpringApplicationBuilder()
                .sources(Application.class)
                .web(true) 
                .run(args);
        logger.info("-------应用启动-----");
	}
}