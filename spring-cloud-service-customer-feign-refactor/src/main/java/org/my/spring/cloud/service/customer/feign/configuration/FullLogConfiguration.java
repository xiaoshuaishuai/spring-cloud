package org.my.spring.cloud.service.customer.feign.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;


@Configuration
public class FullLogConfiguration {

	@Bean
	Logger.Level feignLogLevel(){
		return Logger.Level.FULL;
	}
}
/**
 * 
 * Logger.Level.NONE:不记录任何信息
 * Logger.Level.BASIC:仅记录请求方法,url,响应状态码,执行时间
 * Logger.Level.HEADERS:除了记录BASIC,还记录请求、响应头
 * Logger.Level.FULL:记录所有
 * 
 * 
2017-11-27 15:07:46.540 DEBUG 30880 --- [ider-refactor-1] o.m.s.c.s.c.f.s.IRefactorHelloService    : [IRefactorHelloService#hello] <--- HTTP/1.1 200 (446ms)
2017-11-27 15:07:46.540 DEBUG 30880 --- [ider-refactor-1] o.m.s.c.s.c.f.s.IRefactorHelloService    : [IRefactorHelloService#hello] content-length: 11
2017-11-27 15:07:46.540 DEBUG 30880 --- [ider-refactor-1] o.m.s.c.s.c.f.s.IRefactorHelloService    : [IRefactorHelloService#hello] content-type: text/plain;charset=UTF-8
2017-11-27 15:07:46.540 DEBUG 30880 --- [ider-refactor-1] o.m.s.c.s.c.f.s.IRefactorHelloService    : [IRefactorHelloService#hello] date: Mon, 27 Nov 2017 07:07:46 GMT
2017-11-27 15:07:46.541 DEBUG 30880 --- [ider-refactor-1] o.m.s.c.s.c.f.s.IRefactorHelloService    : [IRefactorHelloService#hello] 
2017-11-27 15:07:46.542 DEBUG 30880 --- [ider-refactor-1] o.m.s.c.s.c.f.s.IRefactorHelloService    : [IRefactorHelloService#hello] hello:shuai
2017-11-27 15:07:46.542 DEBUG 30880 --- [ider-refactor-1] o.m.s.c.s.c.f.s.IRefactorHelloService    : [IRefactorHelloService#hello] <--- END HTTP (11-byte body) 
 * 
 */
