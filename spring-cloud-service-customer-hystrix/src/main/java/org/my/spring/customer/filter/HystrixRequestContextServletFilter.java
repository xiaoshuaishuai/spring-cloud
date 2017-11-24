package org.my.spring.customer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@WebFilter(filterName = "hystrixRequestContextServletFilter", urlPatterns = "/*")
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class HystrixRequestContextServletFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HystrixRequestContext.initializeContext();
		System.err.println("HystrixRequestContext.initializeContext()初始化开启hystrix缓存上下文");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
