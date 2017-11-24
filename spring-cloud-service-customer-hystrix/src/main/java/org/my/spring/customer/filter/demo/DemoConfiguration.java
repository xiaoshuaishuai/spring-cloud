package org.my.spring.customer.filter.demo;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
/**
 * filter 注入spring boot方式1
 */
@Configuration
public class DemoConfiguration {
	/**
	 * 配置过滤器1
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean demo1FilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(demo1Filter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("demo1Filter");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);//值越小优先级越高
		return registration;
	}

	/**
	 * 配置过滤器2
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean demo2FilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(demo2Filter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("demo2Filter");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE+1);//值越小优先级越高

		return registration;
	}

	/**
	 * 配置过滤器3
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean demo3FilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(demo3Filter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("demo3Filter");
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE+2);//值越小优先级越高
		return registration;
	}

	/**
	 * 创建一个bean
	 */
	@Bean(name = "demo1Filter")
	public Filter demo1Filter() {
		return new Demo1Filter();
	}

	/**
	 * 创建一个bean
	 */
	@Bean(name = "demo2Filter")
	public Filter demo2Filter() {
		return new Demo2Filter();
	}

	/**
	 * 创建一个bean
	 */
	@Bean(name = "demo3Filter")
	public Filter demo3Filter() {
		return new Demo3Filter();
	}
}
