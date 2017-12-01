package org.my.spring

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import javax.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PostFilter extends ZuulFilter{

	public static final Logger logger = LoggerFactory.getLogger(PostFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		logger.info(" access post filter !");
		
		logger.info("request {} , url : {}",request.getMethod(),request.getRequestURL());
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 100;
	}

}
