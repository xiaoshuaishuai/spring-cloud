package org.my.spring

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import javax.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PreFilter extends ZuulFilter{

	public static final Logger logger = LoggerFactory.getLogger(PreFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		logger.info(" access pre filter !");
		logger.info("request {} , url : {}",request.getMethod(),request.getRequestURL());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 100;
	}

}
