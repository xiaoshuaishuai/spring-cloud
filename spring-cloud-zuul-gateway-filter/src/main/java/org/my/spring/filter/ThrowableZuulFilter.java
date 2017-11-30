package org.my.spring.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
//@Component
public class ThrowableZuulFilter extends ZuulFilter{
	private static final Logger logger = LoggerFactory.getLogger(ThrowableZuulFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		logger.info("doSomething()=================");
		
		RequestContext context = RequestContext.getCurrentContext();
		doSomething();//两种方式，手动捕获异常 ，error filter处理

		/**
		 * try {
			doSomething();
		}
		catch (Exception ex) {	
			/***
			 * 这里如果发生异常 不去设置错误码 
			 * SendErrorFilter不会执行，页面不会显示任何东西
			 * 	@Override
				public boolean shouldFilter() {
					RequestContext ctx = RequestContext.getCurrentContext();
					// only forward to errorPath if it hasn't been forwarded to already
					return ctx.containsKey("error.status_code")
							&& !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
				}	context.set("error.status_code",
					HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			context.set("error.exception", ex);
		}
			 */
		
	
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	private void doSomething() {
		throw new RuntimeException("filter dosomething error...");
	}

}
