package org.my.spring.filter.error;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**

 * @author ShuaishuaiXiao
 */
@Component
public class ErrorFilter extends ZuulFilter {
	private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		logger.info("error filter run()");
		
		RequestContext context = RequestContext.getCurrentContext();
		Throwable throwable = context.getThrowable();
		context.set("error.status_code",
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		context.set("error.exception", throwable.getCause());
		logger.info("error filter run() {}", throwable.getCause());
		return null;
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 999;
	}

}
