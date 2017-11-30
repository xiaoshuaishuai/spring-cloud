package org.my.spring.filter.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
/**
 * post异常默认spring cloud zuul 不会处理  所以如果用户自定义filter类型为post的过滤器 出现没有处理的异常 页面将不会得到响应
 */
@Component
public class PostExampleZuulFilter extends ZuulFilter{
	private static final Logger logger = LoggerFactory.getLogger(PostExampleZuulFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		logger.info("post filter=================");
		
		doSomething();
	
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	private void doSomething() {
		throw new RuntimeException("filter dosomething error...");
	}

}