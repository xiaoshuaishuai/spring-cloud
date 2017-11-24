package org.my.spring.customer.filter.demo2;

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

/**
 * spring boot使用filter 并且制定执行顺序1,2,3
 * 使用方式：
 * 1.@webfilter 2.@ServletComponentScan 启动类加上
 * 
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "demoTwo1Filter", urlPatterns = "/*", asyncSupported = true)
public class DemoTwo1Filter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.err.println("DemoTwo1Filter");
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
